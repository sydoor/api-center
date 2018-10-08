package com.lizikj.api.controller.user.client;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.user.ClientBindMobileVO;
import com.lizikj.api.vo.user.ClientMobileCodeVO;
import com.lizikj.api.vo.user.ClientUserInfoVO;
import com.lizikj.common.constants.MobileCodeConstants;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.common.util.ValidatorUtils;
import com.lizikj.login.controller.BaseUserController;
import com.lizikj.login.util.LoginInfoUtils;
import com.lizikj.member.dto.MerchantMemberDTO;
import com.lizikj.member.dto.MerchantMemberInfoDTO;
import com.lizikj.member.enums.MemberSourceTypeEnum;
import com.lizikj.member.facade.IMerchantMemberFacade;
import com.lizikj.merchant.exception.UserException;
import com.lizikj.message.api.dto.PhoneMsgDTO;
import com.lizikj.message.api.exception.MessageException;
import com.lizikj.message.api.facade.IPhoneMsgFacade;
import com.lizikj.user.dto.ThirdUserInfoDTO;
import com.lizikj.user.enums.UserSourceEnum;
import com.lizikj.user.facade.IThirdUserInfoUserFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * C端用户相关接口
 * @author lijundong 
 * @date 2018年1月19日 上午11:07:53
 */
@Controller
@RequestMapping("/user")
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Api(value = "C端用户相关接口", tags = "C端用户相关接口")
public class ClientUserController extends BaseUserController{

	private static final Logger logger = LoggerFactory.getLogger(ClientUserController.class);

	@Autowired
	private IThirdUserInfoUserFacade thirdUserInfoUserFacade;
	
	@Autowired
	private IMerchantMemberFacade merchantMemberFacade;
	
	@Autowired
	private IPhoneMsgFacade phoneMsgFacade;
	
	@RequestMapping(value = "/client/userInfo", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "用户资料", notes = "用户资料", httpMethod = "GET")
	public Result<ClientUserInfoVO> clientUserInfo(){
		Long userId = ThreadLocalContext.getUserId();
		Byte userSource = ThreadLocalContext.getUserSource();
		if(logger.isInfoEnabled()){
			logger.info("获取C端用户资料, userId={}, userSource={}", userId, userSource);
		}
		
		ClientUserInfoVO clientUserInfoVO = null;
		try {
			ThirdUserInfoDTO thirdUserInfoDTO = new ThirdUserInfoDTO();
			thirdUserInfoDTO.setUserId(ThreadLocalContext.getUserId());
			thirdUserInfoDTO.setUserSource(userSource);
			ThirdUserInfoDTO userInfoDTO = thirdUserInfoUserFacade.selectUserByExample(thirdUserInfoDTO);
			if(null != userInfoDTO){
				clientUserInfoVO = new ClientUserInfoVO();
				clientUserInfoVO.setNickName(userInfoDTO.getNickname());
				clientUserInfoVO.setHeadimgurl(userInfoDTO.getHeadimgurl());
				clientUserInfoVO.setSex(userInfoDTO.getSex());
				
				//获取商户会员资料
				MerchantMemberInfoDTO member = merchantMemberFacade.getMerchantMemberInfoById(ThreadLocalContext.getMerchantMemberId());
				if(null != member){
					clientUserInfoVO.setMobile(member.getMobile());
					clientUserInfoVO.setBirthday(member.getBirthYear() + "-" + member.getBirthDate());
				}
			}
		} catch (UserException e) {
			logger.error("UserException error ", e);
			return Result.FAILURE(e.getCode(), "获取用户资料失败");
		} catch (Exception e) {
			logger.error("UserException error2 ", e);
			return Result.FAILURE("获取用户资料失败");
		}
		
		return Result.SUCESS(clientUserInfoVO);
	}
	
	@RequestMapping(value = "/client/mobile/code", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取手机验证码", notes = "获取手机验证码", httpMethod = "POST")
	public Result<Object> mobileCode(@RequestBody ClientMobileCodeVO clientMobileCodeVO, HttpSession session){
		logger.info("获取手机验证码begin, clientMobileCodeVO={}", clientMobileCodeVO);
		if(null == clientMobileCodeVO){
			return Result.FAILURE("获取手机验证码信息不存在");
		}
		
		if(!ValidatorUtils.isMobile(clientMobileCodeVO.getMobile())){
			return Result.FAILURE("手机格式不正确");
		}
		
		Object imgCode = session.getAttribute(LoginInfoUtils.IMG_CODE);
		if(null == imgCode){
			return Result.FAILURE("图片验证码不存在");
		}
		
		if(!imgCode.toString().equals(clientMobileCodeVO.getImgCode())){
			return Result.FAILURE("图片验证码错误");
		}else{
			//校验通过，删除图片验证码
			session.removeAttribute(LoginInfoUtils.IMG_CODE);
		}
		
		String mobile = clientMobileCodeVO.getMobile();
		
		//生成随机验证码
		int code = new Random().nextInt(9000) + 1000;
		
		logger.info("获取手机验证码, code: {}", code);
		
		//放进缓存中，120秒有效期
		cache.set(MobileCodeConstants.getClientMobileCodeCacheKey(mobile), code, 600, TimeUnit.SECONDS);
		
		saveMobileCode(mobile, code);
		
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/client/bind/mobile", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "绑定手机号码", notes = "绑定手机号码", httpMethod = "POST")
	public Result<Object> bindMobile(@RequestBody ClientBindMobileVO clientBindMobileVO){
		Long userId = ThreadLocalContext.getUserId();
		Byte userSource = ThreadLocalContext.getUserSource();
		Long merchantId = ThreadLocalContext.getMerchantId();
		if(logger.isInfoEnabled()){
			logger.info("绑定手机号码, userId={}, userSource={}, clientBindMobileVO={}", userId, UserSourceEnum.getEnum(userSource), clientBindMobileVO);
		}
		
		String mobile = clientBindMobileVO.getMobile();
		Object sourceCode = cache.get(MobileCodeConstants.getClientMobileCodeCacheKey(mobile));
		if(null == sourceCode){
			return Result.FAILURE("验证码已失效，请重新获取");
		}
		
		if(!sourceCode.toString().equals(clientBindMobileVO.getCode())){
			return Result.FAILURE("验证码错误，请重新输入");
		}else{
			//验证通过，删除缓存中验证码
			cache.delete(MobileCodeConstants.getClientMobileCodeCacheKey(mobile));
		}
		
		try {
			//根据手机号查处会员信息
			MerchantMemberDTO merchantMemberDTO = merchantMemberFacade.getMerchantMemberByMobile(merchantId, mobile);
			
			//新增会员
			if(null == merchantMemberDTO){
				merchantMemberDTO = new MerchantMemberDTO();
				if (merchantId >= 0) {
					merchantMemberDTO.setMerchantId(merchantId);
				}
				merchantMemberDTO.setMobile(mobile);
				//手机号作为登录号
				merchantMemberDTO.setLoginAccount(mobile);
				//自行注册
				merchantMemberDTO.setSourceType(MemberSourceTypeEnum.MEMBER_REGISTER.getSourceType());

				this.merchantMemberFacade.addMerchantMember(merchantMemberDTO);
			}
		} catch (UserException e) {
			return Result.FAILURE(e.getCode(), "绑定手机号码失败");
		} catch (Exception e) {
			return Result.FAILURE("绑定手机号码失败");
		}
		
		return Result.SUCESS();
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
