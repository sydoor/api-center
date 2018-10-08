package com.lizikj.api.controller.user.merchant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.utils.AppVersionUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.user.LoginSuccessVO;
import com.lizikj.api.vo.user.MobileCodeTokenVO;
import com.lizikj.api.vo.user.param.LoginParam;
import com.lizikj.common.constants.MobileCodeConstants;
import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.common.enums.UserLoginSourceEnum;
import com.lizikj.common.enums.UserStatusEnum;
import com.lizikj.common.enums.UserTypeEnum;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.SignUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.controller.BaseUserController;
import com.lizikj.login.dto.LoginUserInfoDTO;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.login.util.LoginInfoUtils;
import com.lizikj.merchant.dto.AgentDTO;
import com.lizikj.merchant.dto.AuthShopStaffDTO;
import com.lizikj.merchant.dto.CashierHandoverRecordDTO;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.dto.ShopMerchantStatusDTO;
import com.lizikj.merchant.dto.UserLocalAuthDTO;
import com.lizikj.merchant.dto.UserProfileDTO;
import com.lizikj.merchant.enums.MerchantStaffTypeEnum;
import com.lizikj.merchant.enums.MerchantTypeEnum;
import com.lizikj.merchant.enums.UserErrorEnum;
import com.lizikj.merchant.exception.UserException;
import com.lizikj.merchant.facade.IAgentReadFacade;
import com.lizikj.merchant.facade.IAuthShopAuthWriteFacade;
import com.lizikj.merchant.facade.ICashierHandoverRecordReadFacade;
import com.lizikj.merchant.facade.IMerchantAuthApiReadFacade;
import com.lizikj.merchant.facade.IShopEquipmentReadFacade;
import com.lizikj.merchant.facade.IShopEquipmentWriteFacade;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.merchant.facade.IUserReadFacade;
import com.lizikj.merchant.facade.IUserWriteFacade;
import com.lizikj.message.api.dto.PhoneMsgDTO;
import com.lizikj.message.api.enums.MsgMqTopic;
import com.lizikj.message.api.exception.MessageException;
import com.lizikj.message.api.facade.IPhoneMsgFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 商户用户登录/注销接口
 * @author lijundong
 * @date 2017年7月12日 下午4:47:51
 */
@Controller
@RequestMapping("/user")
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Api(value = "店铺用户相关接口", tags = "店铺用户相关接口")
public class MerchantUserController extends BaseUserController{

	private static final Logger logger = LoggerFactory.getLogger(MerchantUserController.class);

	/**存放手机验证通过后一次性token的缓存key*/
	public static final String MOBILE_CODE_VERIFY_CACHE_KEY = "MOBILE_CODE_VERIFY_CACHE_KEY:";

	@Autowired
	private IUserReadFacade userReadFacade;
	
	@Autowired
	private IUserWriteFacade userWriteFacade;
	
	@Autowired
	private IMerchantAuthApiReadFacade merchantAuthApiReadFacade;

	@Autowired
	private IShopReadFacade shopReadFacade;
	
	@Autowired
	private IPhoneMsgFacade phoneMsgFacade;
	
	@Autowired
	private IShopMerchantReadFacade shopMerchantReadFacade;
	
	@Autowired
	private IShopEquipmentReadFacade shopEquipmentReadFacade;
	
	@Autowired
	private IShopEquipmentWriteFacade shopEquipmentWriteFacade;
	
	@Autowired
	private IAuthShopAuthWriteFacade authShopAuthWriteFacade;
	
	@Autowired
	private IAgentReadFacade agentReadFacade;
	
	@Autowired
	private ICashierHandoverRecordReadFacade cashierHandoverRecordReadFacade;
	
	private AuthShopStaffDTO loginStaff;
	
	@Autowired
	Environment env;

	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @param loginSource
	 * @param response
	 * @return Result<Object>
	 * @author lijundong
	 * @date 2017年7月14日 上午10:05:48
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "商户用户登录", notes = "商户用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@LoginExclude
	public Result<LoginSuccessVO> login(@RequestBody LoginParam loginParam,
			@ApiIgnore @SessionAttribute(name = LoginInfoUtils.IMG_CODE, required = false) String imgCode,
			HttpServletRequest request, HttpServletResponse response){
		logger.info("login | 登录begin | loginName: {},  loginSource: {}, imgCode: {}", loginParam.getLoginName(), loginParam.getLoginSource(), imgCode);
		UserLoginSourceEnum loginSourceEnum = UserLoginSourceEnum.getEnum(loginParam.getLoginSource());
		//初始数据加载
		UserLocalAuthDTO userLocalAuthDTO = null;
		UserProfileDTO userProfile = null;
		List<AuthShopStaffDTO> staffList = new ArrayList<AuthShopStaffDTO>();
		long userId = 0;
		try {
			if (loginParam.getStaffId() != null && loginParam.getStaffId().longValue() != 0) {//指定员工账号登录
				loginStaff = merchantAuthApiReadFacade.findStaffById(loginParam.getStaffId());
				if (loginStaff == null || loginStaff.getRemoveStatus().byteValue() == RemoveStatusEnum.REMOVED.getStatus()) {
					return Result.FAILURE("用户不存在");
				}
				if (loginStaff.getStatus().byteValue() == UserStatusEnum.DISABLE.getStatus()) {
					return Result.FAILURE("用户未激活");
				}
				userId = loginStaff.getUserId();
				userLocalAuthDTO = userReadFacade.getUserLocalAuthByUserId(userId);
				userProfile = userReadFacade.getUserProfileById(userLocalAuthDTO.getUserId());
				if (userLocalAuthDTO == null || userProfile == null) {
					return Result.FAILURE("用户不存在");
				}
				
				if (userProfile.getStatus() == UserStatusEnum.DISABLE.getStatus()) {
					throw new UserException(UserErrorEnum.USER_IS_DISABLE);
				}

				if (userProfile.getRemoveStatus() == RemoveStatusEnum.REMOVED.getStatus())	{
					throw new UserException(UserErrorEnum.USER_IS_REMOVED);
				}
			} else {//兼容旧登录方式
				userLocalAuthDTO = userReadFacade.getUserLocalAuthByLoginName(loginParam.getLoginName(), UserTypeEnum.MERCHANT_USER.getType());
				userProfile = userReadFacade.getUserProfileById(userLocalAuthDTO.getUserId());
				if (userLocalAuthDTO == null || userProfile == null) {
					return Result.FAILURE("用户不存在");
				}
				
				if (userProfile.getStatus() == UserStatusEnum.DISABLE.getStatus()) {
					throw new UserException(UserErrorEnum.USER_IS_DISABLE);
				}

				if (userProfile.getRemoveStatus() == RemoveStatusEnum.REMOVED.getStatus())	{
					throw new UserException(UserErrorEnum.USER_IS_REMOVED);
				}
				userId = userLocalAuthDTO.getUserId();// 用户id
				staffList = merchantAuthApiReadFacade.getStaffListByUserId(userId);
				if (staffList == null || staffList.size() == 0) {
					return Result.FAILURE("无员工信息！请联系管理员。");
				}
				if (staffList.size() == 1) {
					loginStaff = staffList.get(0);
				} else {
					for (AuthShopStaffDTO staff: staffList) {
						if (staff.getType().byteValue() == MerchantStaffTypeEnum.MERCHANT.getCode()) {
							loginStaff = staff;
							break;
						}
					}
					
					if (loginStaff == null) {
						loginStaff = staffList.get(0);
					}
				}
			}
			
		} catch (UserException e) {
			return Result.FAILURE(e.getMessage());
		}
		//登陆验证
		Result loginCheckResult = loginCheck(loginParam, userLocalAuthDTO, loginSourceEnum, imgCode);
		if (loginCheckResult.getCode() == Result.FAILURE().getCode()) {// 失败状态直接返回失败结果
			return loginCheckResult;
		}
		
		//app登陆下，通过指定角色登陆 2017/09/29 liaojw
		Result appResult = this.appLoginHandler(loginParam, loginSourceEnum);
		if (appResult.getCode() == Result.FAILURE().getCode()) {// 失败状态直接返回失败结果
			return appResult;
		}
		//设备登陆处理 2017-09-04 liaojw
		Result posResult = this.posLoginHandler(loginParam.getLoginName(), loginSourceEnum);
		if (posResult.getCode() == Result.FAILURE().getCode()) {// 失败状态直接返回失败结果
			return posResult;
		}
		//pc端登陆处理 2017/11/30 liaojw
		Result pcResult = this.pcLoginHandler(loginParam, loginSourceEnum);
		if (pcResult.getCode() == Result.FAILURE().getCode()) {// 失败状态直接返回失败结果
			return pcResult;
		}
		long staffId = loginStaff.getStaffId();//员工id
		long merchantId = loginStaff.getMerchantId();//商户Id
		long shopId = loginStaff.getShopId();//店铺id
		
		//校验端口的登录权限
		if(!checkLoginSource(userId, staffId, loginSourceEnum.getValue())) {
			return Result.FAILURE("9999", "没有"+ loginSourceEnum.getMessage() +"的登录权限");
		}
		
		//封装登录成功的返回对象
		ShopDTO shop = shopReadFacade.findById(shopId);
		ShopMerchantDTO merchant = shopMerchantReadFacade.findById(merchantId);
		ShopMerchantStatusDTO merchantStatus = shopMerchantReadFacade.findStatusByMerchantId(merchantId);
		String staffRoleCode = merchantAuthApiReadFacade.findRoleCodeByStaffId(staffId);// 当前登陆来源，该员工的角色		
		AgentDTO agent = agentReadFacade.findById(merchant.getAgentId());
		ThreadLocalContext.putThreadValue(ThreadLocalContext.HTTP_REQUEST, request);
		ThreadLocalContext.putThreadValue(ThreadLocalContext.HTTP_RESPONSE, response);
		
		LoginUserInfoDTO loginUserInfo = new LoginUserInfoDTO();
		loginUserInfo.setUserId(userLocalAuthDTO.getUserId());
		loginUserInfo.setStaffId(staffId);
		loginUserInfo.setLoginSource(loginSourceEnum.getValue());
		loginUserInfo.setMerchantId(merchantId);
		loginUserInfo.setShopId(shopId);
		loginUserInfo.setUserType(UserTypeEnum.MERCHANT_USER.getType());
		loginUserInfo.setAgentId(merchant.getAgentId());
		
		toLogin(loginUserInfo);
		
		Long merchantlogoPicId = merchant.getLogoPicId();
		Long shopLogoPicId = 0L;
		Long agentLogoPicId = agent.getCompanyLogo();
		String shopName = "";
		if (loginStaff.getType().byteValue() == MerchantStaffTypeEnum.SHOP.getCode()) {//门店员工类型
			if (shop == null) {
				return Result.FAILURE("9999", "门店不存在，无法登陆");
			}
		}
		//设置登陆结果
		LoginSuccessVO loginResult = new LoginSuccessVO();
		
		if (shop !=null) {
			shopLogoPicId = shop.getLogoPicId();
			shopName = shop.getShopName();
			   //二维码模板
			loginResult.setQrcodeTemplateId(shop.getQrcodeTemplateId());
		}
		
		loginResult.setMerchantId(merchantId);
		loginResult.setShopId(shopId);
		if (shopId == 0 || shop == null) {
			loginResult.setNeedRegisterShop(true);
		}
		loginResult.setShopName(shopName);
		loginResult.setShopLogo(shopLogoPicId);
		loginResult.setMobile(userLocalAuthDTO.getMobile());
		loginResult.setMerchantType(merchant.getMerchantType());
		loginResult.setStaffRoleCode(staffRoleCode);
		loginResult.setUserId(userLocalAuthDTO.getUserId());
		if (StringUtils.isBlank(loginStaff.getFullName())) {
			loginResult.setUserName(userProfile.getFullName());
		} else {
			loginResult.setUserName(loginStaff.getFullName());
		} 
		loginResult.setCreateTime(loginStaff.getCreateTime());
		loginResult.setServicePhone("400-0098-008");//客服电话，写死给客户端
		loginResult.setAgentLogoPicId(agentLogoPicId);
		loginResult.setMerchantLogoPicId(merchantlogoPicId);
		loginResult.setStaffId(loginStaff.getStaffId());
		loginResult.setAgentName(agent.getAgentName());
		loginResult.setMerchantName(merchant.getMerchantName());
		CashierHandoverRecordDTO cashierRecord = cashierHandoverRecordReadFacade.findStaffLastRecord(loginStaff.getStaffId());
		if (cashierRecord != null) {
			loginResult.setCashierStatus(cashierRecord.getCashierStatus());
		}

		//根据版本号选择推送服务
		String aversion = ThreadLocalContext.getAversion();
		loginResult.setThirdPlatformTypeEnum(AppVersionUtil.fromVersion(aversion,env.getProperty("message.provider.version")));
		loginResult.setPaymentConfigStatus(merchantStatus.getPaymentConfigStatus());
		
		//mqttMsg配置
		String mqEnvironment = env.getProperty("spring.profiles.active");
		JSONObject mqttMsgConfig = new JSONObject();
		mqttMsgConfig.put("topic", MsgMqTopic.LIZIKJ_MQTT_MSG + "_" + mqEnvironment.toUpperCase());
		mqttMsgConfig.put("groupId", MsgMqTopic.GID_LIZIKJ_MQTT_MSG_GROUP + "_" + mqEnvironment.toUpperCase());
		mqttMsgConfig.put("brokerUrl", env.getProperty("aliware.mqtt.brokerUrl"));
		loginResult.setMqttMsgConfig(mqttMsgConfig.toJSONString());
		
		loginStaff = null;//回收该成员变量
		return Result.SUCESS(loginResult);
	}
	
	@RequestMapping(value = "/staff/login", method = {RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "员工登录", notes = "选择其中一个员工登录", httpMethod = "POST", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Result<Object> stafffLogin(@ApiParam(required = true, name = "staffId", value = "员工Id", type = "Long") @RequestParam(name = "staffId", required = true) long staffId,
			HttpServletRequest request, HttpServletResponse response){
		logger.info("stafffLogin | 员工登录begin | staffId: {}", staffId);
		
		Long sid = ThreadLocalContext.getStaffId();
		
		if(null != sid)
			return Result.FAILURE("不能重复登录");
		
		//获取登录来源
		LoginUserInfoDTO loginUserInfo = new LoginUserInfoDTO();
		loginUserInfo.setUserId(ThreadLocalContext.getUserId());
		loginUserInfo.setStaffId(staffId);
		loginUserInfo.setLoginSource(ThreadLocalContext.getLoginSource());
		loginUserInfo.setMerchantId(ThreadLocalContext.getMerchantId());
		loginUserInfo.setShopId(ThreadLocalContext.getShopId());
		loginUserInfo.setUserType(UserTypeEnum.MERCHANT_USER.getType());
		
		toLogin(loginUserInfo);
		
		return Result.SUCESS();
	}
	
	/**
	 * 注销
	 * @param response
	 * @return Result<Object>
	 * @author lijundong
	 * @date 2017年7月13日 下午8:51:33
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "商户用户注销", notes = "商户用户注销", httpMethod = "GET")
	public Result<Object> logout(HttpServletRequest request, HttpServletResponse response){
		logger.info("login | 注销begin");
		
		Long userId = ThreadLocalContext.getUserId();
		if(null == userId)
			return Result.FAILURE("请登录后再操作");
		
		//清空登录信息
		LoginInfoUtils.clearLoginInfo(LoginInfoUtils.getTokenName(UserTypeEnum.MERCHANT_USER.getCode()));
		//清空app权限
		Byte loginSource = ThreadLocalContext.getLoginSource();
		if (UserLoginSourceEnum.getEnum(loginSource) == UserLoginSourceEnum.APP) {
			Long staffId = ThreadLocalContext.getStaffId();
			if (staffId != null) {
				authShopAuthWriteFacade.clearStaffAuthCache(staffId);
			}
		}
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "刷新登录token", notes = "刷新登录token", httpMethod = "GET")
	public Result<Object> refreshToken(){

		LoginUserInfoDTO loginUserInfo = new LoginUserInfoDTO();
		loginUserInfo.setUserId(ThreadLocalContext.getUserId());
		loginUserInfo.setStaffId(ThreadLocalContext.getStaffId());
		loginUserInfo.setLoginSource(ThreadLocalContext.getLoginSource());
		loginUserInfo.setMerchantId(ThreadLocalContext.getMerchantId());
		loginUserInfo.setShopId(ThreadLocalContext.getShopId());
		loginUserInfo.setUserType(UserTypeEnum.MERCHANT_USER.getType());
		
		toLogin(loginUserInfo);
		
		return Result.SUCESS();
	}
	
	/**
	 * 检验商户员工是否有某个端口的登录权限
	 * @param userId
	 * @param staffId
	 * @param loginSource
	 * @return boolean
	 * @author lijundong
	 * @date 2017年7月28日 下午3:02:39
	 */
	private boolean checkLoginSource(long userId, long staffId, byte loginSource){
		return merchantAuthApiReadFacade.loginCheck(staffId, UserLoginSourceEnum.getEnum(loginSource));
	}
	
	/**
	 * 获取激活帐号的手机验证码，点击获取验证码的时候，校验该帐号是否已激活，如已激活，则告知用户
	 * @param mobile
	 * @return Result<Object>
	 * @author lijundong
	 * @date 2017年8月29日 上午11:23:43
	 */
	@RequestMapping(value = "/activate/mobile/code", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取激活帐号手机验证码", notes = "获取激活帐号手机验证码", httpMethod = "GET")
	@LoginExclude
	public Result<Object> activateMobileCode(@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(name = "mobile", required = true) String mobile){
		logger.info("activateMobileCode | 获取激活帐号手机验证码begin | mobile: {}", mobile);
		
		UserProfileDTO userProfileDTO = null;
			
		userProfileDTO = userReadFacade.getUserProfileByMobile(mobile, UserTypeEnum.MERCHANT_USER.getType());
		if(null == userProfileDTO)
			return Result.FAILURE(UserErrorEnum.USER_PROFILE_NOT_EXIST.getCode(), "该账号不存在，请重新输入");
		
		if(userProfileDTO.getStatus() == UserStatusEnum.ENABLE.getStatus())
			return Result.FAILURE("该账号已激活");
		
		//生成随机验证码
		int code = new Random().nextInt(9000) + 1000;
		
		logger.info("activateMobileCode | 获取激活帐号手机验证码 | code: {}", code);
		
		//放进缓存中，120秒有效期
		cache.set(MobileCodeConstants.getMerchantMobileCodeCacheKey(mobile), code, 600, TimeUnit.SECONDS);
		
		saveMobileCode(mobile, code);
		
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/activate/user", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "激活帐号", notes = "激活帐号", httpMethod = "POST")
	@LoginExclude
	public Result<MobileCodeTokenVO> activateUser(@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(name = "mobile", required = true) String mobile, 
			@ApiParam(name = "code", value = "手机验证码", required = true) @RequestParam(name = "code", required = true) String code){
		logger.info("activateUser | 激活帐号begin | mobile: {}, code: {}", mobile, code);
		
		String token = null;
		Object sourceCode = cache.get(MobileCodeConstants.getMerchantMobileCodeCacheKey(mobile));
		if(null == sourceCode)
			return Result.FAILURE("验证码已失效，请重新获取");
		
		if(!sourceCode.toString().equals(code))
			return Result.FAILURE("验证码错误，请重新输入");
		
		try {
			//激活帐号
			userWriteFacade.activateUser(mobile, UserTypeEnum.MERCHANT_USER.getType());
		} catch (UserException e) {
			//转换一下文案
			return Result.FAILURE(e.getCode(), "该账号不存在，请重新输入");
		}
		
		//一次性token
		token = UUID.randomUUID().toString().replaceAll("-", "");
		//放进缓存中, 暂时设置10分钟有效期吧
		cache.set(getMobileCodeVerifyCacheKey(mobile), token, 600, TimeUnit.SECONDS);
		
		//验证通过，清除缓存
		cache.delete(MobileCodeConstants.getMerchantMobileCodeCacheKey(mobile));
		
		//返回token，给客户端
		return Result.SUCESS(new MobileCodeTokenVO(token));
	}
	
	@RequestMapping(value = "/set/password", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "设置密码", notes = "设置密码", httpMethod = "POST")
	@LoginExclude
	public Result<Object> setPassword(@ApiParam(name = "password", value = "密码", required = true) @RequestParam(name = "password", required = true) String password, 
			@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(name = "mobile", required = true) String mobile,
			@ApiParam(name = "token", value = "授权码", required = true) @RequestParam(name = "token", required = true) String token){
		logger.info("setPassword | 设置密码begin | token: {}", token);
		
		try {
			Object sourceToken = cache.get(getMobileCodeVerifyCacheKey(mobile));
			if(null == sourceToken){
				return Result.FAILURE("授权码已失效，请重新获取");
			}
			
			if(!sourceToken.toString().equals(token)){
				return Result.FAILURE("授权码错误，请重新输入");
			}
			
			if(StringUtils.isBlank(password)){
				return Result.FAILURE("密码不能为空");
			}
			
			//修改密码
			userWriteFacade.updatePassword(mobile, SignUtils.md5Password(password), UserTypeEnum.MERCHANT_USER.getType());
			
			//删除临时授权码
			cache.delete(getMobileCodeVerifyCacheKey(mobile));
		} catch (UserException e) {
			if(e.getCode() == UserErrorEnum.USER_PROFILE_NOT_EXIST.getCode() || e.getCode() == UserErrorEnum.USER_LOCAL_AUTH_NOT_EXIST.getCode() 
					|| e.getCode() == UserErrorEnum.USER_IS_REMOVED.getCode()){
				return Result.FAILURE(e.getCode(), "账号不存在，请重新输入");
			}
			
			if(e.getCode() == UserErrorEnum.USER_IS_DISABLE.getCode()){
				return Result.FAILURE(e.getCode(), "请先激活账号");
			}
				
		}
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/find/mobile/code", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取找回密码手机验证码", notes = "获取找回密码手机验证码", httpMethod = "GET")
	@LoginExclude
	public Result<Object> findMobileCode(@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(name = "mobile", required = true) String mobile){
		logger.info("findMobileCode | 获取找回密码手机验证码begin | mobile: {}", mobile);
		
		UserProfileDTO userProfileDTO = userReadFacade.getUserProfileByMobile(mobile, UserTypeEnum.MERCHANT_USER.getType());
		if(null == userProfileDTO)
			return Result.FAILURE(UserErrorEnum.USER_PROFILE_NOT_EXIST.getCode(), "该账号不存在，请重新输入");
		
		if(userProfileDTO.getStatus() == UserStatusEnum.DISABLE.getStatus())
			return Result.FAILURE("请先激活帐号");
		
		//生成随机验证码
		int code = new Random().nextInt(9000) + 1000;
		
		logger.info("findMobileCode | 获取找回密码手机验证码 code: {}", code);
		
		//放进缓存中，120秒有效期
		cache.set(MobileCodeConstants.getMerchantMobileCodeCacheKey(mobile), code, 600, TimeUnit.SECONDS);
		
		saveMobileCode(mobile, code);
		
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/validate/mobile/code", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "手机验证码校验", notes = "手机验证码校验", httpMethod = "POST")
	@LoginExclude
	public Result<MobileCodeTokenVO> validateMobileCode(@ApiParam(name = "mobile", value = "手机号码", required = true) @RequestParam(name = "mobile", required = true) String mobile, 
			@ApiParam(name = "code", value = "手机验证码", required = true) @RequestParam(name = "code", required = true) String code){
		logger.info("validateMobileCode | 手机验证码校验begin | mobile: {}, code: {}", mobile, code);
		
		String token = null;
		Object sourceCode = cache.get(MobileCodeConstants.getMerchantMobileCodeCacheKey(mobile));
		if(null == sourceCode)
			return Result.FAILURE("验证码已失效，请重新获取");
		
		if(!sourceCode.toString().equals(code))
			return Result.FAILURE("验证码错误，请重新输入");
		
		try {
			//校验手机是否存在
			userReadFacade.getUserProfileByMobile(mobile, UserTypeEnum.MERCHANT_USER.getType());
		} catch (UserException e) {
			return Result.FAILURE(e.getCode(), "该账号不存在，请重新输入");
		}
		
		//一次性token
		token = UUID.randomUUID().toString().replaceAll("-", "");
		//放进缓存中, 暂时设置10分钟有效期吧
		cache.set(getMobileCodeVerifyCacheKey(mobile), token, 600, TimeUnit.SECONDS);
		
		//验证通过，清除缓存
		cache.delete(MobileCodeConstants.getMerchantMobileCodeCacheKey(mobile));
		
		//返回token，给客户端
		return Result.SUCESS(new MobileCodeTokenVO(token));
	}
	
	@RequestMapping(value = "/modify/password", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST")
	public Result<Object> modifyPassword(
			@ApiParam(name = "oPassword", value = "旧密码", required = true) @RequestParam(name = "oPassword", required = true) String oPassword, 
			@ApiParam(name = "password", value = "新密码", required = true) @RequestParam(name = "password", required = true) String password){
		long userId = ThreadLocalContext.getUserId();
		logger.info("modifyPassword | 修改密码begin | userId: {}", userId);
		
		try {
			if(StringUtils.isBlank(oPassword) || StringUtils.isBlank(password))
				return Result.FAILURE("密码不能为空, 请重新输入");
						
			UserLocalAuthDTO userLocalAuthDTO = userReadFacade.getUserLocalAuthByUserId(userId);
			
			if(!userLocalAuthDTO.getPassword().equals(SignUtils.md5Password(oPassword)))
				return Result.FAILURE("旧密码输入错误");
			
			//修改密码
			userWriteFacade.updatePassword(userLocalAuthDTO.getId(), SignUtils.md5Password(password));
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	/**
	 * 处理pos登陆
	 * @param loginName
	 * @param loginSourceEnum
	 * @return Result<Object>
	 * @author ljw
	 * @date 2017年9月5日 下午12:00:27
	 */
	public Result<Object> posLoginHandler(String loginName, UserLoginSourceEnum loginSourceEnum){
		try {
			if (loginSourceEnum.equals(UserLoginSourceEnum.POS)) {
				ShopMerchantDTO merchant = shopMerchantReadFacade.findById(loginStaff.getMerchantId());
				if (loginStaff.getShopId() == 0 && merchant.getMerchantType().equals(MerchantTypeEnum.SINGLE.getCode())) {//单店下，如果商户登陆，设置商户shopId为该门店的ID
					List<ShopDTO> shopList = shopMerchantReadFacade.findShopListByMerchantId(merchant.getMerchantId());
					if (shopList !=null && shopList.size() >0) {
						loginStaff.setShopId(shopList.get(0).getShopId());
					}
				}
			}
		} catch (Exception e) {
			return Result.FAILURE("登陆异常");
		}
		return Result.SUCESS();
	}
	
	/**
	 * 登陆验证
	 * @param loginParam 登陆参数
	 * @param imgCode 验证码
	 * @return Result<Object>
	 * @author liaojw
	 * @date 2017年9月29日 下午12:03:21
	 */
	public Result<Object> loginCheck(LoginParam loginParam, UserLocalAuthDTO userLocalAuthDTO, UserLoginSourceEnum loginSourceEnum, String imgCode){
		if(null == loginSourceEnum){
			return Result.FAILURE("登录端口不存在");
		}
		//PC端校验验证码， 验证码为空/或者验证码错误
		if(loginSourceEnum == UserLoginSourceEnum.PC && (StringUtils.isBlank(loginParam.getCode()) || !loginParam.getCode().equalsIgnoreCase(imgCode))){
			return Result.FAILURE("验证码错误");
		}
		
		String generatePassword = SignUtils.md5Password(loginParam.getPassword());

		//密码不匹配
		if(!userLocalAuthDTO.getPassword().equals(generatePassword))
			return Result.FAILURE("账号或密码错误，请重新输入");
		return Result.SUCESS();
	}
	
	/**
	 * app登陆处理
	 * @param loginName
	 * @param loginSourceEnum
	 * @param authShopStaffDTO
	 * @param allStaffs
	 * @return Result<Object>
	 * @author liaojw
	 * @date 2017年9月29日 下午2:11:33
	 */
	public Result<Object> appLoginHandler(LoginParam loginParam, UserLoginSourceEnum loginSourceEnum){
		try {
			if (loginSourceEnum.equals(UserLoginSourceEnum.APP)) {
				ShopMerchantDTO merchant = shopMerchantReadFacade.findById(loginStaff.getMerchantId());
				if (loginStaff.getShopId() == 0 && merchant.getMerchantType().equals(MerchantTypeEnum.SINGLE.getCode())) {//单店下，如果商户登陆，设置商户shopId为该门店的ID
					List<ShopDTO> shopList = shopMerchantReadFacade.findShopListByMerchantId(merchant.getMerchantId());
					if (shopList !=null && shopList.size() >0) {
						loginStaff.setShopId(shopList.get(0).getShopId());
					}
				}
			}
		} catch (Exception e) {
			return Result.FAILURE("登陆异常");
		}
		return Result.SUCESS();
	}
	
	/**
	 * pc端登陆处理
	 * @param loginName
	 * @param loginSourceEnum
	 * @param authShopStaffDTO
	 * @param allStaffs
	 * @return Result<Object>
	 * @author liaojw
	 * @date 2017年11月30日 上午9:54:55
	 */
	public Result<Object> pcLoginHandler(LoginParam loginParam, UserLoginSourceEnum loginSourceEnum){
		try {
			if (loginSourceEnum.equals(UserLoginSourceEnum.PC)) {
				ShopMerchantDTO merchant = shopMerchantReadFacade.findById(loginStaff.getMerchantId());
				if (loginStaff.getShopId() == 0 && merchant.getMerchantType().equals(MerchantTypeEnum.SINGLE.getCode())) {//单店下，如果商户登陆，设置商户shopId为该门店的ID
					List<ShopDTO> shopList = shopMerchantReadFacade.findShopListByMerchantId(merchant.getMerchantId());
					if (shopList !=null && shopList.size() >0) {
						loginStaff.setShopId(shopList.get(0).getShopId());
					}
				}
			}
		} catch (Exception e) {
			return Result.FAILURE("登陆异常");
		}
		return Result.SUCESS();
	}
	
	private String getMobileCodeVerifyCacheKey(String mobile){
		return MOBILE_CODE_VERIFY_CACHE_KEY + mobile;
	}
	
	/**
	 * 保存验证码到db
	 * @param mobile
	 * @param code void
	 * @author lijundong
	 * @date 2017年11月1日 下午8:34:56
	 */
	private void saveMobileCode(String mobile, int code){
		try {
			//发送验证码服务
			PhoneMsgDTO phoneMsgDTO = new PhoneMsgDTO();
			phoneMsgDTO.setMsgContent("你的验证码是" + code);
			phoneMsgFacade.sendSms(phoneMsgDTO, mobile);
		} catch (MessageException e) {
			e.printStackTrace();
		}
	}
}
