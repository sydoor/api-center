package com.lizikj.api.controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.enums.MemberRoleEnum;
import com.lizikj.api.utils.MerchantImportUtils;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.CurrentMemberStatisticsVO;
import com.lizikj.api.vo.member.MemberBaseVO;
import com.lizikj.api.vo.member.MemberH5StatisticsVO;
import com.lizikj.api.vo.member.MemberInfoVO;
import com.lizikj.api.vo.member.MemberLevelInfoVO;
import com.lizikj.api.vo.member.MemberRoleVO;
import com.lizikj.api.vo.member.MerchantMemberAccountVO;
import com.lizikj.api.vo.member.MerchantMemberImportVO;
import com.lizikj.api.vo.member.MerchantMemberLevelConditionVO;
import com.lizikj.api.vo.member.MerchantMemberLevelRelVO;
import com.lizikj.api.vo.member.MerchantMemberLevelVO;
import com.lizikj.api.vo.member.MerchantMemberQueryVO;
import com.lizikj.api.vo.member.MerchantMemberStatisticsVO;
import com.lizikj.api.vo.member.MerchantMemberVO;
import com.lizikj.api.vo.member.param.MemberLevelParam;
import com.lizikj.api.vo.member.param.MemberLevelUpdateParam;
import com.lizikj.common.enums.ArgumentCheckErrorEnum;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.UserCouponQueryDTO;
import com.lizikj.marketing.api.enums.MerchantCouponTypeEnum;
import com.lizikj.marketing.api.enums.MerchantCouponUseStatusEnum;
import com.lizikj.marketing.api.facade.IMerchantCouponReadFacade;
import com.lizikj.member.dto.MemberAccountDTO;
import com.lizikj.member.dto.MemberBaseDTO;
import com.lizikj.member.dto.MemberInfoDTO;
import com.lizikj.member.dto.MemberLevelAndConditionDTO;
import com.lizikj.member.dto.MemberLevelInfoDTO;
import com.lizikj.member.dto.MerchantMemberAccountDTO;
import com.lizikj.member.dto.MerchantMemberDTO;
import com.lizikj.member.dto.MerchantMemberImportDTO;
import com.lizikj.member.dto.MerchantMemberLevelConditionDTO;
import com.lizikj.member.dto.MerchantMemberLevelDTO;
import com.lizikj.member.dto.MerchantMemberLevelRelDTO;
import com.lizikj.member.dto.MerchantMemberQueryDTO;
import com.lizikj.member.dto.MerchantMemberStatisticsDTO;
import com.lizikj.member.enums.MemberDiscountTypeEnum;
import com.lizikj.member.enums.MemberErrorEnum;
import com.lizikj.member.enums.MemberSourceTypeEnum;
import com.lizikj.member.exception.MemberException;
import com.lizikj.member.facade.IMemberFacade;
import com.lizikj.member.facade.IMemberSettingFacade;
import com.lizikj.member.facade.IMerchantMemberAccountFacade;
import com.lizikj.member.facade.IMerchantMemberFacade;
import com.lizikj.member.facade.IMerchantMemberLevelFacade;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.dto.UserProfileDTO;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.merchant.facade.IUserReadFacade;
import com.lizikj.tender.dto.response.TenderMemberDTO;
import com.lizikj.tender.facade.ITenderMemberQueryFacade;
import com.lizikj.user.dto.MerchantUserInfoDTO;
import com.lizikj.user.dto.ThirdUserInfoDTO;
import com.lizikj.user.dto.WechatMerchantUserRelDTO;
import com.lizikj.user.enums.SubscribeStatusEnum;
import com.lizikj.user.facade.IMerchantUserInfoFacade;
import com.lizikj.user.facade.IThirdUserInfoUserFacade;
import com.lizikj.user.facade.IWechatMerchantUserRelReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Healthy
 * @description
 * @date 2017/7/10
 */

@RestController
@RequestMapping("/member")
@Api(value = "member", tags = "会员API接口")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private IMerchantMemberFacade merchantMemberFacade;
    @Autowired
	private IMemberFacade memberFacade;
    
    @Autowired
    private IMerchantMemberAccountFacade merchantMemberAccountFacade;
    
    @Autowired
    private IMerchantMemberLevelFacade merchantMemberLevelFacade;
    @Autowired
	private IMemberSettingFacade memberSettingFacade;
    @Autowired
	private IShopReadFacade shopReadFacade;
    @Autowired
	private IUserReadFacade userReadFacade;
    @Autowired
	private IMerchantUserInfoFacade merchantUserInfoFacade;
    @Autowired
	private IMerchantCouponReadFacade merchantCouponReadFacade;
    @Autowired
	private IShopMerchantReadFacade shopMerchantReadFacade;
    @Autowired
	private ITenderMemberQueryFacade tenderMemberQueryFacade;
    @Autowired
	private IWechatMerchantUserRelReadFacade wechatMerchantUserRelReadFacade;
	@Autowired
	private IThirdUserInfoUserFacade thirdUserInfoUserFacade;

	@RequestMapping(value = "/batchAdd",method = RequestMethod.POST)
	@ApiOperation(value = "批量添加商户会员", notes = "批量添加商户会员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<MerchantMemberImportVO>> batchAdd(@RequestParam("file")MultipartFile file){
		List<MerchantMemberImportDTO> dtos = null;
		try {
			dtos = MerchantImportUtils.readFromExcel(file.getInputStream(),file.getOriginalFilename());
		} catch (IOException e) {
			logger.info("批量导入会员，读取文件失败 {}",file.getOriginalFilename());
			return Result.FAILURE("读取文件失败");
		}

		if (dtos != null && !dtos.isEmpty()) {
			for(MerchantMemberImportDTO dto : dtos){
				dto.setShopId(ThreadLocalContext.getShopId());
				dto.setMerchantId(ThreadLocalContext.getMerchantId());
			}

			try {
				List<MerchantMemberImportDTO> dtos1 = merchantMemberFacade.importMerchantMember(dtos);
				return Result.SUCESS(ObjectConvertUtil.mapList(dtos1,MerchantMemberImportDTO.class,MerchantMemberImportVO.class));
			} catch (Exception e) {
				logger.info("批量导入会员失败 {}",e);
			}
		}

		return Result.FAILURE("读取文件内容为空");
	}

	@RequestMapping(value = "/listMemberRoles/{mobile}",method = RequestMethod.GET)
	@ApiOperation(value = "获取手机的会员角色列表", notes = "获取手机的会员角色列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<MemberRoleVO>>  listMemberRoles(@ApiParam(name = "mobile",value = "手机号")@PathVariable(value = "mobile") String mobile){
		Long merchantId = ThreadLocalContext.getMerchantId();
		Long shopId = ThreadLocalContext.getShopId();
		if(StringUtils.isBlank(mobile)){
			return Result.FAILURE("手机号不能为空");
		}
		List<MemberRoleVO> memberRoleVOS = new ArrayList<>();
		MerchantMemberDTO merchantMember = this.merchantMemberFacade.getMerchantMemberByMobile(merchantId, mobile);
		if(merchantMember != null){
			MemberRoleVO memberRoleVO = new MemberRoleVO();
			memberRoleVO.setMemberRoleEnum(MemberRoleEnum.MERCHANT_MEMBER);
			memberRoleVO.setShopId(merchantMember.getSourceShopId());
			memberRoleVO.setMobile(merchantMember.getMobile());
			memberRoleVO.setName(merchantMember.getRealName());
			memberRoleVOS.add(memberRoleVO);
		}

		TenderMemberDTO tenderMemberDTO = this.tenderMemberQueryFacade.getByMobile(shopId, mobile);
		if(tenderMemberDTO != null){
			MemberRoleVO memberRoleVO = new MemberRoleVO();
			memberRoleVO.setMemberRoleEnum(MemberRoleEnum.TENDER_MEMBER);
			memberRoleVO.setShopId(tenderMemberDTO.getShopId());
			memberRoleVO.setMobile(tenderMemberDTO.getMobile());
			memberRoleVO.setName(tenderMemberDTO.getRealName());
			memberRoleVOS.add(memberRoleVO);
		}

		return Result.SUCESS(memberRoleVOS);
	}

	@RequestMapping(value = "/getAccountStatistics",method = RequestMethod.POST)
	@ApiOperation(value = "H5获取会员详细信息", notes = "获取会员详细信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MemberH5StatisticsVO> queryAccountStatistics(){
		Long userId = ThreadLocalContext.getUserId();
		MerchantUserInfoDTO dto = this.merchantUserInfoFacade.getById(userId);
		if(null == dto){
			logger.info("会员不存在 {}",userId);
			return Result.FAILURE("会员不存在");
		}
		Long merchantId = ThreadLocalContext.getMerchantId();
		MemberH5StatisticsVO statisticsVO = new MemberH5StatisticsVO();

		WechatMerchantUserRelDTO wechatMerchantUserRelDTO = wechatMerchantUserRelReadFacade.getByUserIdMerchantId(userId, merchantId);
		MerchantMemberAccountDTO merchantMemberAccount = null;
		if (wechatMerchantUserRelDTO != null) {
			merchantMemberAccount = this.merchantMemberAccountFacade.getAccountByMemberId(wechatMerchantUserRelDTO.getMerchantMemberId());
			if (wechatMerchantUserRelDTO.getMemberId() != null && wechatMerchantUserRelDTO.getMemberId() > 0) {
				statisticsVO.setMemberId(wechatMerchantUserRelDTO.getMemberId());
				MemberInfoDTO memberInfoDTO = memberFacade.getMemberBase(wechatMerchantUserRelDTO.getMemberId());
				if (memberInfoDTO != null) {
					statisticsVO.setMembershipExpiredTime(memberInfoDTO.getMembershipExpiredTime());
					statisticsVO.setMembershipValidStatus(memberInfoDTO.getMembershipValidStatus());
					statisticsVO.setMembershipDaysRemaining(memberInfoDTO.getMembershipDaysRemaining());
				}
			}
		}

		if (merchantMemberAccount != null) {
			statisticsVO.setCurrentAmount(Math.max(0L,merchantMemberAccount.getTotalRechargeAmount() - merchantMemberAccount.getFrozenAmount() - merchantMemberAccount.getTotalCostRechargeAmount()));
			statisticsVO.setCurrentCredit(Math.max(0L,merchantMemberAccount.getTotalCredit() - merchantMemberAccount.getFrozenCredit() - merchantMemberAccount.getTotalUseCredit()));
			MerchantMemberDTO merchantMemberDTO = this.merchantMemberFacade.getById(wechatMerchantUserRelDTO.getMerchantMemberId());
			if (merchantMemberDTO != null) {
				statisticsVO.setLevelCode(merchantMemberDTO.getLevelCode());
				statisticsVO.setLevelName(merchantMemberDTO.getLevelName());
				statisticsVO.setDiscount(merchantMemberDTO.getDiscount());
				statisticsVO.setMerchantName(merchantMemberDTO.getMemberNum());
				statisticsVO.setMobile(merchantMemberDTO.getMobile());
				MemberAccountDTO account = this.memberFacade.getAccountByBaseId(merchantMemberDTO.getMemberId());
				if (account != null) {
					statisticsVO.setCurrentPlumCoin(account.getTotalPlumCoin() - account.getFrozenPlumCoin() - account.getUsedPlumCoin());
				}
			}
			statisticsVO.setMerchantMemberId(wechatMerchantUserRelDTO.getMerchantMemberId());
		}
		statisticsVO.setCouponNum(getCouponNum(merchantId, userId));

		statisticsVO.setMerchantId(merchantId);
		statisticsVO.setUserId(userId);

		ShopMerchantDTO shopMerchant = this.shopMerchantReadFacade.findById(merchantId);
		if(shopMerchant != null){
			statisticsVO.setMerchantLogoPicId(shopMerchant.getLogoPicId());
			statisticsVO.setMerchantName(shopMerchant.getMerchantName());
		}
		
		// 判断新老用户
		statisticsVO.setNewUser(false);
		ThirdUserInfoDTO example = new ThirdUserInfoDTO();
		example.setUserId(userId);
		ThirdUserInfoDTO thirdUserInfo = thirdUserInfoUserFacade.selectUserByExample(example);
		if (thirdUserInfo != null) {
			if ((thirdUserInfo.getSubcribeStatus() == null || thirdUserInfo.getSubcribeStatus().byteValue() == SubscribeStatusEnum.UNSUBSCRIBE.getValue()) 
					&& thirdUserInfo.getSubcribeTime() == null) {// 从没关注过
				statisticsVO.setNewUser(true);
			}
		}
		
		return Result.SUCESS(statisticsVO);
	}

    @RequestMapping(value = "/queryMerchantMemberInfo",method = RequestMethod.POST)
	@ApiOperation(value = "获取会员详细信息）", notes = "获取会员详细信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<MerchantMemberStatisticsVO>> queryMerchantMemberInfo(@RequestBody MerchantMemberQueryVO merchantMemberQueryVO,
																			  @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
																			  @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
    	MerchantMemberQueryDTO queryDTO = null;
    	if(merchantMemberQueryVO != null){
    		queryDTO = ObjectConvertUtil.map(merchantMemberQueryVO,MerchantMemberQueryDTO.class);
		}
		PageParameter pageParameter = new PageParameter(pageNum,pageSize);
		PageInfo<MerchantMemberStatisticsDTO> pageInfo = this.merchantMemberFacade.queryMemberStatistics(queryDTO, pageParameter);
		PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
		List<MerchantMemberStatisticsVO> vos = new ArrayList<>();
		if(pageInfo.getList() != null){
			pageInfo.getList().forEach(dto -> {
				MerchantMemberStatisticsVO vo = ObjectConvertUtil.map(dto,MerchantMemberStatisticsVO.class);
				vo.setCurrentCredit(Math.max(0L,dto.getTotalCredit() - dto.getFrozenCredit() - dto.getTotalUseCredit()));
				vo.setCurrentAmount(Math.max(0L,dto.getTotalRechargeAmount() - dto.getFrozenAmount() - dto.getTotalCostRechargeAmount()));
				vos.add(vo);
			});
		}
		pageVO.setList(vos);
		return Result.SUCESS(pageVO);
	}

	/**
	 * 获取会员（POS端会员登录使用）
	 * @param mobile
	 * @return
	 */
    @RequestMapping(value = "/findMerchantMember/{mobile}",method = RequestMethod.GET)
	@ApiOperation(value = "获取会员（POS端会员登录使用）", notes = "POS端会员登录使用", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MerchantMemberVO> findMerchantMemberByMobile(
			@ApiParam(name = "mobile",value = "手机号")@PathVariable(value = "mobile") String mobile){
    	Long merchantId = ThreadLocalContext.getMerchantId();
    	Long shopId = ThreadLocalContext.getShopId();
    	//先查询股东会员，如果有，立刻返回
		if (shopId != null) {
			TenderMemberDTO tenderMemberDTO = this.tenderMemberQueryFacade.getByMobile(shopId, mobile);
			if(tenderMemberDTO != null){
				MerchantMemberVO merchantMemberVO = ObjectConvertUtil.copyProperties(MerchantMemberVO.class,tenderMemberDTO);
				merchantMemberVO.setMemberRoleEnum(MemberRoleEnum.TENDER_MEMBER);
				merchantMemberVO.setTenderMemberId(tenderMemberDTO.getTenderId());
				return Result.SUCESS(merchantMemberVO);
			}
		}

		MerchantMemberDTO merchantMemberDTO = this.merchantMemberFacade.getMerchantMemberByMobile(merchantId,mobile);
    	if(merchantMemberDTO == null){
    		return Result.FAILURE(MemberErrorEnum.MEMBER_NOT_EXIST_ERROR.getCode(),"会员不存在");
		}
		MemberDiscountTypeEnum discountTypeEnum = this.memberSettingFacade.findDiscountTypeByMerchant(merchantId);
		MerchantMemberVO merchantMemberVO = ObjectConvertUtil.copyProperties(MerchantMemberVO.class,merchantMemberDTO);
		if(merchantMemberVO != null){
			merchantMemberVO.setMemberDiscountTypeEnum(discountTypeEnum);
		}

		if (merchantMemberDTO != null) {
			MerchantMemberAccountDTO account = this.merchantMemberAccountFacade.getAccountByMemberId(merchantMemberDTO.getMerchantMemberId());
			if(account != null){
                merchantMemberVO.setCurrentAmount(Math.max(0L,account.getTotalRechargeAmount() - account.getFrozenAmount() - account.getTotalCostRechargeAmount()));
            }
			merchantMemberVO.setMemberRoleEnum(MemberRoleEnum.MERCHANT_MEMBER);
		}

		return Result.SUCESS(merchantMemberVO);
	}

	/**
	 * 获取商户七天新增会员
	 * @return
	 */
	@RequestMapping(value = "/queryMemberSevenDayStatistics",method = RequestMethod.GET)
	@ApiOperation(value = "获取商户七天新增会员", notes = "获取七天新增会员", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<MerchantMemberStatisticsVO>> queryMemberSevenDayStatistics(){
		Long merchantId = ThreadLocalContext.getMerchantId();
		logger.info(String.format("/member/queryMemberSevenDayStatistics/%s ",merchantId));
		if(merchantId == null){
			logger.error("/member/queryMemberSevenDayStatistics/ 参数为NULL.");
			return Result.FAILURE(MemberErrorEnum.MEMBER_NULL_PARAM_ERROR.getCode(),MemberErrorEnum.MEMBER_NULL_PARAM_ERROR.getMsg());
		}

		List<MerchantMemberStatisticsDTO> memberStatisticsDTOS = this.merchantMemberFacade.getSevenDaysMemberStatisticsList(merchantId,null);
		List<MerchantMemberStatisticsVO> merchantMemberStatisticsVOS = new ArrayList<>(memberStatisticsDTOS.size());
		memberStatisticsDTOS.forEach(memberBaseDTO -> {
			MerchantMemberStatisticsVO merchantMemberStatisticsVO = ObjectConvertUtil.copyProperties(MerchantMemberStatisticsVO.class,memberBaseDTO);
			merchantMemberStatisticsVO.setRegisterDate(memberBaseDTO.getRegisterDate());
			merchantMemberStatisticsVO.setCurrentCredit(
					Math.max(0L,merchantMemberStatisticsVO.getTotalCredit() - merchantMemberStatisticsVO.getFrozenCredit() - merchantMemberStatisticsVO.getTotalUseCredit()));
			merchantMemberStatisticsVO.setCurrentAmount(
					Math.max(0L,merchantMemberStatisticsVO.getTotalRechargeAmount() - merchantMemberStatisticsVO.getFrozenAmount() - merchantMemberStatisticsVO.getTotalCostRechargeAmount()));
			merchantMemberStatisticsVOS.add(merchantMemberStatisticsVO);
		});

		return Result.SUCESS(ObjectConvertUtil.copyListProperties(merchantMemberStatisticsVOS,MerchantMemberStatisticsVO.class));
	}


	@RequestMapping(value = "/queryMemberStatisticsPage",method = RequestMethod.POST)
	@ApiOperation(value = "获取商户会员账号详情分页", notes = "获取商户会员账号详情分页", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<MerchantMemberStatisticsVO>> queryMemberStatisticsPage(@RequestBody MerchantMemberQueryVO merchantMemberQueryVO,
																				@ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
																				@ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
																				@ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
		MerchantMemberQueryDTO merchantMemberQueryDTO = ObjectConvertUtil.map(merchantMemberQueryVO,MerchantMemberQueryDTO.class);
		merchantMemberQueryDTO.setMerchantId(ThreadLocalContext.getMerchantId());
		PageParameter pageParameter = new PageParameter(pageNum,pageSize);
		pageParameter.setOrderBy(orderBy);
		PageInfo<MerchantMemberStatisticsDTO> pageInfo = this.merchantMemberFacade.queryMemberPageInfo(merchantMemberQueryDTO,pageParameter);

		List<MerchantMemberStatisticsVO> merchantMemberStatisticsVOS = new ArrayList<>();
		if (pageInfo.getList() != null) {
			pageInfo.getList().forEach(dto -> {
                merchantMemberStatisticsVOS.add(MerchantMemberStatisticsVO.from(dto));
            });
		}

		return Result.SUCESS(ObjectConvertUtil.copyListProperties(merchantMemberStatisticsVOS,MerchantMemberStatisticsVO.class));
	}

	/**
	 * 获取会员统计列表
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
	@RequestMapping(value = "/queryMemberStatistics",method = RequestMethod.GET)
	@ApiOperation(value = "获取会员统计列表", notes = "获取会员统计列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<MerchantMemberStatisticsVO>> queryMemberStatistics(
			@ApiParam(name = "mobile",value = "手机号") @RequestParam(value = "mobile") String mobile,
			@ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
			@ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
			@ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
		Long merchantId = ThreadLocalContext.getMerchantId();
		PageParameter pageParameter = new PageParameter(pageNum,pageSize);
		pageParameter.setOrderBy(orderBy);
		PageInfo<MerchantMemberStatisticsDTO> pageInfo = this.merchantMemberFacade.getMemberStatisticsList(merchantId,mobile,pageParameter);
		PageVO<MerchantMemberStatisticsVO> pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
		List<MerchantMemberStatisticsVO> result = new ArrayList<>();
		if(pageInfo.getList() != null){
			pageInfo.getList().forEach(t -> {
				if (t != null) {
					MerchantMemberStatisticsVO vo = ObjectConvertUtil.map(t,MerchantMemberStatisticsVO.class);
					vo.setCurrentAmount(Math.max(0L,t.getTotalRechargeAmount() - t.getTotalCostRechargeAmount() - t.getFrozenAmount()));
					vo.setCurrentCredit(Math.max(0L,t.getTotalCredit() - t.getTotalUseCredit() - t.getFrozenCredit()));
					result.add(vo);
				}
			});
		}

		pageVO.setList(result);

		return Result.SUCESS(pageVO);
	}
	/**
	 * 搜索会员列表分页
	 * @param mobile
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
    @RequestMapping(value = "/queryMerchantMembers",method = RequestMethod.GET)
	@ApiOperation(value = "搜索会员列表分页(暂不使用)", notes = "搜索会员列表分页",hidden = true, httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<MemberBaseVO>> queryMembers(
			@ApiParam(name = "mobile",value = "电话号码") @RequestParam(value = "mobile") String mobile,
			@ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
			@ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
			@ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
		PageParameter pageParameter = new PageParameter(pageNum,pageSize);
		pageParameter.setOrderBy(orderBy);
		Long merchantId = ThreadLocalContext.getMerchantId();

//		PageInfo<MemberBaseDTO> memberBaseDTOS = this.merchantMemberFacade.getMemberPageByMobile(pageParameter,merchantId,mobile);
//		PageVO<MemberBaseVO> pageVO = new PageVO(memberBaseDTOS.getPageNum(),memberBaseDTOS.getPageSize(),memberBaseDTOS.getPages(),memberBaseDTOS.getTotal(), null);
//		pageVO.setList(ObjectConvertUtil.copyListProperties(memberBaseDTOS.getList(),MemberBaseVO.class));

		return Result.SUCESS();
	}

    /**
     * 商户会员实时信息  -- 会员列表
     * @return
     */
    @RequestMapping(value="/currentMemberStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "商户会员实时信息-- 会员列表", notes = "商户会员实时信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<CurrentMemberStatisticsVO> currentMemberStatistics() {
    	Long merchantId = ThreadLocalContext.getMerchantId();

		int memberCount = merchantMemberFacade.getMemberCount(merchantId);
		int todayIncrease = merchantMemberFacade.getTodayMemberCount(merchantId);

		CurrentMemberStatisticsVO currentMemberVO = new CurrentMemberStatisticsVO();
		currentMemberVO.setMemberCount(memberCount);
		currentMemberVO.setTodayIncrease(todayIncrease);

		return Result.SUCESS(currentMemberVO);
	}

    /**
     * 商户会员账户信息
     * @param merchantMemberId
     * @return
     */
    @RequestMapping(value="/selectMemberAccount/{merchantMemberId}",method = RequestMethod.GET)
    @ApiOperation(value = "商户会员账户信息", notes = "商户会员账户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MerchantMemberAccountVO> selectMemberAccount(@ApiParam(name = "merchantMemberId",value = "会员ID",required = true) @PathVariable Long merchantMemberId){
		MerchantMemberAccountDTO account = merchantMemberAccountFacade.getAccountByMemberId(merchantMemberId);
    	
        MerchantMemberAccountVO merchantMemberAccountVO = new MerchantMemberAccountVO();
		if (account != null) {
			merchantMemberAccountVO.setAccountId(account.getAccountId());
			merchantMemberAccountVO.setMerchantId(account.getMerchantId());
			merchantMemberAccountVO.setMerchantMemberId(account.getMerchantMemberId());
			merchantMemberAccountVO.setSurplusAmount(account.getTotalRechargeAmount() - account.getTotalCostRechargeAmount());
			merchantMemberAccountVO.setTotalCostAmount(account.getTotalCostAmount());
			merchantMemberAccountVO.setTotalCredit(account.getTotalCredit());
			merchantMemberAccountVO.setRechargeTimes(account.getRechargeTimes());
			merchantMemberAccountVO.setCostTimes(account.getCostTimes());
			merchantMemberAccountVO.setSurplusCredit(account.getTotalCredit() - account.getTotalUseCredit() - account.getFrozenCredit());
			merchantMemberAccountVO.setTotalUseCredit(account.getTotalUseCredit());
			merchantMemberAccountVO.setCurrentCredit(Math.max(0L,account.getTotalCredit() - account.getTotalUseCredit() - account.getFrozenCredit()));
			long frozen = account.getFrozenAmount() == null ? 0L:account.getFrozenAmount();
			merchantMemberAccountVO.setCurrentAmount(Math.max(0L,account.getTotalRechargeAmount() - account.getTotalCostRechargeAmount() - frozen));
		}

		return Result.SUCESS(merchantMemberAccountVO);
    }

    /**
     * 商户会员信息
     * @param merchantMemberId
     * @return
     */
    @RequestMapping(value="/merchantMember/{merchantMemberId}")
    @ApiOperation(value = "商户会员信息", notes = "商户会员信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MerchantMemberVO> selectMerchantMember(
			@ApiParam(name = "merchantMemberId", value = "商户会员ID", required = true, type = "Long") @PathVariable Long merchantMemberId) {
		logger.info(String.format("merchantMember %s",merchantMemberId));
		MerchantMemberDTO merchantMemberDTO = this.merchantMemberFacade.getById(merchantMemberId);
		if(merchantMemberDTO == null){
			logger.info("会员不存在 {}",merchantMemberId);
			return Result.FAILURE(MemberErrorEnum.MEMBER_NOT_EXIST_ERROR.getCode(),MemberErrorEnum.MEMBER_NOT_EXIST_ERROR.getMsg());
		}
		MerchantMemberVO merchantMemberVO = ObjectConvertUtil.copyProperties(MerchantMemberVO.class,merchantMemberDTO);
		MerchantMemberLevelDTO memberLevelDTO = this.merchantMemberLevelFacade.getMemberLevelRelByMemberId(merchantMemberId);
		if(memberLevelDTO != null){
			merchantMemberVO.setLevelName(memberLevelDTO.getLevelName());
			merchantMemberVO.setDiscount(Double.valueOf(memberLevelDTO.getDiscount()));
			merchantMemberVO.setLevelName(memberLevelDTO.getLevelName());
			merchantMemberVO.setLevelCode(memberLevelDTO.getLevelCode());
		}
		merchantMemberVO.setRegisterDate(merchantMemberDTO.getCreateTime());


		if (merchantMemberDTO.getWaiterUserId() != null && merchantMemberDTO.getWaiterUserId() != 0) {
			UserProfileDTO userProfileDTO = this.userReadFacade.getUserProfileById(merchantMemberDTO.getWaiterUserId());
			if(userProfileDTO != null){
				merchantMemberVO.setWaiterName(userProfileDTO.getFullName()); //推荐服务员姓名
			}
		}


		if (merchantMemberDTO.getSourceShopId() != null && merchantMemberDTO.getSourceShopId() != 0) {
			ShopDTO shopDTO = this.shopReadFacade.findById(merchantMemberDTO.getSourceShopId());
			if(shopDTO != null){
				merchantMemberVO.setSourceShopName(shopDTO.getShopName()); //注册店铺
			}
		}

		merchantMemberVO.setCouponNum(0);
		MerchantUserInfoDTO userInfoDTO = merchantUserInfoFacade.getByMerchantMember(merchantMemberId);
		if (userInfoDTO == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("{} 没找到对应的用户", merchantMemberId);
			}
		} else {
			long merchantId = ThreadLocalContext.getMerchantId();
			// 设置优惠券总数量
			Date now = new Date();
			UserCouponQueryDTO queryDTO = new UserCouponQueryDTO();
			queryDTO.setMerchantId(merchantId);
			queryDTO.setCouponType(MerchantCouponTypeEnum.CASH_COUPON.getType());
			queryDTO.setStartTime(now);
			queryDTO.setEndTime(now);
			queryDTO.setUseStatus(MerchantCouponUseStatusEnum.UNUSED.getStatus());
			queryDTO.setUserId(userInfoDTO.getUserId());
			queryDTO.setCouponName(null);
			merchantMemberVO.setCouponNum(merchantCouponReadFacade.getCouponNum(queryDTO));
		}

		return Result.SUCESS(merchantMemberVO);
    }

	/**
	 * 添加商户会员
	 */
	@RequestMapping(value = "/addMerchantMember",method = RequestMethod.POST)
	@ApiOperation(value = "添加商户会员", notes = "添加商户会员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> addMerchantMember(@RequestBody MerchantMemberVO merchantMemberVO){
		logger.info(String.format("/member/addMerchantMember %s",merchantMemberVO));
		if(merchantMemberVO == null){
			logger.error("/member/addMerchantMember 参数为null.");
			return Result.FAILURE(MemberErrorEnum.MEMBER_NULL_PARAM_ERROR.getCode(),MemberErrorEnum.MEMBER_NULL_PARAM_ERROR.getMsg());
		}

		Long merchantId = ThreadLocalContext.getMerchantId();
		Long shopId = ThreadLocalContext.getShopId();

		MerchantMemberDTO merchantMemberDTO = ObjectConvertUtil.copyProperties(MerchantMemberDTO.class,merchantMemberVO);
		if (shopId >= 0) {
			merchantMemberDTO.setSourceShopId(shopId);
		}
		if (merchantId >= 0) {
			merchantMemberDTO.setMerchantId(merchantId);
		}
		merchantMemberDTO.setLoginAccount(merchantMemberDTO.getMobile()); // 手机号作为登录号
		merchantMemberDTO.setWaiterUserId(ThreadLocalContext.getUserId());
		merchantMemberDTO.setSourceType(MemberSourceTypeEnum.WAITER_RECOMMEND.getSourceType()); // 商户注册

		try {
			this.merchantMemberFacade.addMerchantMember(merchantMemberDTO);
		} catch (MemberException e) {
			logger.error("/member/addMerchantMember Exception {}",e);
			String error = e.getMessage();
			if(e.getCode() == MemberErrorEnum.MOBILE_HAS_REGISTER_ERROR.getCode()){
				error = "手机号已存在，请重新输入";
			}
			return Result.FAILURE(e.getCode(),error);
		}

		return Result.SUCESS();
	}


	/**
	 * 添加基础会员
	 * @return
	 */
	@RequestMapping(value="/getMemberFans",method = RequestMethod.GET)
	@ApiOperation(value = "获取撩美味会员", notes = "获取撩美味会员", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MemberBaseVO> getMemberFans() {
		Long userId = ThreadLocalContext.getUserId();
		MemberBaseDTO memberBaseByUser = memberFacade.getMemberBaseByUser(userId);
		if (null == memberBaseByUser){
			MemberException exception = new MemberException(MemberErrorEnum.MEMBER_NOT_EXIST_ERROR, "根据userId(" + userId + ")查询不到平台会员信息！");
			if (logger.isErrorEnabled()) {
				logger.error("获取撩美味会员错误：", exception);
			}
			return Result.SUCESS(null);
		}
		MemberBaseVO baseVO = ObjectConvertUtil.map(memberBaseByUser, MemberBaseVO.class);
		baseVO.setUserId(userId);
		
		// 判断新老用户
		baseVO.setNewUser(false);
		ThirdUserInfoDTO example = new ThirdUserInfoDTO();
		example.setUserId(userId);
		ThirdUserInfoDTO thirdUserInfo = thirdUserInfoUserFacade.selectUserByExample(example);
		if (thirdUserInfo != null) {
			if ((thirdUserInfo.getSubcribeStatus() == null || thirdUserInfo.getSubcribeStatus().byteValue() == SubscribeStatusEnum.UNSUBSCRIBE.getValue()) && thirdUserInfo.getSubcribeTime() == null) {// 从没关注过
				baseVO.setNewUser(true);
			}
		}
		
		return Result.SUCESS(baseVO);
	}

	/**
     * 添加基础会员
     * @return
     */
    @RequestMapping(value="/addBaseMember",method = RequestMethod.POST)
    @ApiOperation(value = "添加基础会员", notes = "添加基础会员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result addMember(@RequestBody MemberBaseVO memberBaseVO) {
    	Long merchantId = ThreadLocalContext.getMerchantId();
    	MemberBaseDTO memberBaseDTO = ObjectConvertUtil.copyProperties(MemberBaseDTO.class,memberBaseVO);
    	memberBaseDTO.setMerchantId(merchantId);

    	return Result.SUCESS();
	}

    /**
     * 商户会员等级列表
     * @return
     */
    @RequestMapping(value="/selectMemberLevels/{levelType}",method = RequestMethod.GET)
    @ApiOperation(value = "商户会员等级列表", notes = "商户会员等级列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<MerchantMemberLevelVO>> selectMemberLevels(
			@ApiParam(value = "levelType",name = "会员类型 5100 普通会员 5200 特殊会员",required = true)@PathVariable Integer levelType) {
    	Long merchantId = ThreadLocalContext.getMerchantId();
		List<MemberLevelAndConditionDTO> levelDTOList = this.merchantMemberLevelFacade.getMemberLevelListByMerchantId(Long.valueOf(merchantId), levelType);

		final List<MerchantMemberLevelVO> merchantMemberLevelVOList = new ArrayList<>();
		if (levelDTOList != null && levelDTOList.size() > 0) {
			levelDTOList.forEach(level -> {
				MerchantMemberLevelVO merchantMemberLevelVO = ObjectConvertUtil.map(level,MerchantMemberLevelVO.class);
				merchantMemberLevelVO.setConditionList(ObjectConvertUtil.copyListProperties(level.getMerchantMemberLevelConditionDTOS(),MerchantMemberLevelConditionVO.class));
				merchantMemberLevelVOList.add(merchantMemberLevelVO);
			});
		}

        return Result.SUCESS(merchantMemberLevelVOList);
    }

    /**
     * 查询等级详情
     * @param levelId
     * @return
     */
    @RequestMapping(value="/levelDetail/{levelId}",method = RequestMethod.GET)
    @ApiOperation(value = "查询等级详情", notes = "查询等级详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MerchantMemberLevelVO> levelDetail(
			@ApiParam(value = "levelId",name = "会员等级ID",required = true) @PathVariable Long levelId) {
		MemberLevelAndConditionDTO memberLevelAndConditionDTO = this.merchantMemberLevelFacade.getMemberLevelInfoById(levelId);
        
		MerchantMemberLevelVO levelVO = ObjectConvertUtil.map(memberLevelAndConditionDTO,MerchantMemberLevelVO.class);
		if(memberLevelAndConditionDTO.getMerchantMemberLevelConditionDTOS() != null){
			levelVO.setConditionList(ObjectConvertUtil.copyListProperties(memberLevelAndConditionDTO.getMerchantMemberLevelConditionDTOS(),MerchantMemberLevelConditionVO.class));
		}

		Long merchantId = memberLevelAndConditionDTO.getMerchantId();
		if(merchantId == null){
			merchantId = ThreadLocalContext.getMerchantId();
		}

		//查询其他等级
		List<MemberLevelAndConditionDTO> memberLevelAndConditionDTOS = this.merchantMemberLevelFacade.getMemberLevelListByMerchantId(merchantId,memberLevelAndConditionDTO.getLevelType());
		if (memberLevelAndConditionDTOS != null && memberLevelAndConditionDTOS.size() > 0) {
			List<MerchantMemberLevelVO> otherLevelVOList = new ArrayList<>();
			for (MemberLevelAndConditionDTO otherLevelDTO : memberLevelAndConditionDTOS) {
				if(otherLevelDTO.getLevelId() == levelId){
					continue;
				}
				MerchantMemberLevelVO otherLevelVO = ObjectConvertUtil.copyProperties(MerchantMemberLevelVO.class,otherLevelDTO);
				if(otherLevelDTO.getMerchantMemberLevelConditionDTOS() != null){
					otherLevelVO.setConditionList(ObjectConvertUtil.copyListProperties(otherLevelDTO.getMerchantMemberLevelConditionDTOS(),MerchantMemberLevelConditionVO.class));
				}

				otherLevelVOList.add(otherLevelVO);
        	}

			levelVO.setOtherLevelInfoList(otherLevelVOList);
		}
        
        return Result.SUCESS(levelVO);
    }

    /**
     * 添加等级
     * @param memberLevelParam
     * @return
     */
    @RequestMapping(value="/addLevelAndCondition")
    @ApiOperation(value = "添加等级（含等级升级条件）", notes = "添加等级", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MemberLevelParam> addLevelAndCondition(@RequestBody MemberLevelParam memberLevelParam) {
    	Long merchantId = ThreadLocalContext.getMerchantId();
    	if(memberLevelParam == null || merchantId == null|| StringUtils.isBlank(memberLevelParam.getLevelName())){
    		logger.info(String.format("添加等级参数为NULL."));
    		return Result.FAILURE(MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getCode(),MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getMsg());
		}
    	MerchantMemberLevelDTO memberLevelDTO = ObjectConvertUtil.copyProperties(MerchantMemberLevelDTO.class,memberLevelParam);
    	memberLevelDTO.setMerchantId(merchantId);
    	List<MerchantMemberLevelConditionDTO> memberLevelConditionDTOS = new ArrayList<>();
    	memberLevelParam.getConditionList().parallelStream().filter(t->t != null).forEach(
				merchantMemberLevelConditionVO ->{
					memberLevelConditionDTOS.add(ObjectConvertUtil.copyProperties(MerchantMemberLevelConditionDTO.class, merchantMemberLevelConditionVO));
				}
		);

		MemberLevelAndConditionDTO memberLevelAndConditionDTO = this.merchantMemberLevelFacade.addMemberLevel(memberLevelDTO,memberLevelConditionDTOS);
		MemberLevelParam levelParam = ObjectConvertUtil.map(memberLevelAndConditionDTO,MemberLevelParam.class);
		levelParam.setConditionList(ObjectConvertUtil.mapList(memberLevelAndConditionDTO.getMerchantMemberLevelConditionDTOS(),MerchantMemberLevelConditionDTO.class,MerchantMemberLevelConditionVO.class));
    	return Result.SUCESS(levelParam);
	}

    /**
     * 删除等级
     * @param levelId
     * @return
     */
    @RequestMapping(value="/delLevel/{levelId}")
    @ApiOperation(value = "删除等级", notes = "删除等级", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> delLevel(
			@ApiParam(value = "levelId",name = "会员等级ID",required = true) @PathVariable Long levelId) {
		if (this.merchantMemberLevelFacade.delMemberLevel(levelId) > 0) {
    		return Result.SUCESS(true);
		} else {
			return Result.FAILURE();
		}
    }

	@RequestMapping(value="/modifyLevel",method = RequestMethod.POST)
	@ApiOperation(value = "修改等级", notes = "修改等级（不修改等级条件）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MerchantMemberLevelVO> modifyLevel(@RequestBody MemberLevelUpdateParam memberLevelUpdateParam) {
    	MerchantMemberLevelDTO memberLevelDTO = ObjectConvertUtil.map(memberLevelUpdateParam,MerchantMemberLevelDTO.class);
		MerchantMemberLevelDTO levelDTO = null;
		MerchantMemberLevelVO levelVO = null;
		try {
			levelDTO = this.merchantMemberLevelFacade.updateMemberLevel(memberLevelDTO);
		} catch (MemberException e) {
			return Result.FAILURE(e.getCode(),e.getMessage());
		}
		if(levelDTO != null){
			levelVO = ObjectConvertUtil.map(levelDTO,MerchantMemberLevelVO.class);
			List<MerchantMemberLevelConditionDTO> levelConditionList = this.merchantMemberLevelFacade.getLevelConditionList(levelDTO.getLevelId());
			if(levelConditionList != null){
				List<MerchantMemberLevelConditionVO> merchantMemberLevelConditionVOS = ObjectConvertUtil.mapList(levelConditionList,MerchantMemberLevelConditionDTO.class,MerchantMemberLevelConditionVO.class);
				levelVO.setConditionList(merchantMemberLevelConditionVOS);
			}
		}

		return Result.SUCESS(levelVO);
	}

    /**
     * 添加等级条件
     * @param merchantMemberLevelConditionVO
     * @return
     */
    @RequestMapping(value="/addLevelCondition")
    @ApiOperation(value = "添加等级条件", notes = "添加等级条件", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MerchantMemberLevelConditionVO> addLevelCondition(@RequestBody MerchantMemberLevelConditionVO merchantMemberLevelConditionVO) {
    	if(merchantMemberLevelConditionVO == null || merchantMemberLevelConditionVO.getLevelId() == null){
			logger.info(String.format("addLevelCondition %s 缺少必要参数", merchantMemberLevelConditionVO));
			return Result.FAILURE(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage());
		}
		MerchantMemberLevelConditionDTO levelConditionDTO = ObjectConvertUtil.copyProperties(MerchantMemberLevelConditionDTO.class, merchantMemberLevelConditionVO);
		MerchantMemberLevelConditionDTO memberLevelConditionDTO = null;
		try {
			memberLevelConditionDTO = this.merchantMemberLevelFacade.addLevelCondition(levelConditionDTO);
		} catch (MemberException e) {
			Result.FAILURE(e.getCode(),e.getMessage());
		}

		return Result.SUCESS(ObjectConvertUtil.map(memberLevelConditionDTO,MerchantMemberLevelConditionVO.class));
    }
    
    /**
     * 修改等级条件
     * @param merchantMemberLevelConditionVO
     * @return
     */
    @RequestMapping(value="/modifyLevelCondition")
    @ApiOperation(value = "修改等级条件", notes = "修改等级条件", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MerchantMemberLevelConditionVO> modifyLevelCondition(@RequestBody MerchantMemberLevelConditionVO merchantMemberLevelConditionVO) {
		MerchantMemberLevelConditionDTO levelConditionDTO = null;
		try {
			levelConditionDTO = this.merchantMemberLevelFacade.updateLevelCondition(ObjectConvertUtil.map(merchantMemberLevelConditionVO,MerchantMemberLevelConditionDTO.class));
		} catch (MemberException e) {
			return Result.FAILURE(e.getCode(),e.getMessage());
		}

		return Result.SUCESS(ObjectConvertUtil.map(levelConditionDTO,MerchantMemberLevelConditionVO.class));
	}
    
    /**
     * 删除等级条件
     * @param conditionId
     * @return
     */
    @RequestMapping(value="/delLevelCondition/{conditionId}",method = RequestMethod.POST)
    @ApiOperation(value = "删除等级条件", notes = "删除等级条件", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> delLevelCondition(
    		@ApiParam(value = "conditionId",name = "等级条件ID",required = true)@PathVariable Long conditionId) {
    	if (this.merchantMemberLevelFacade.delLevelCondition(conditionId) > 0) {
    		return Result.SUCESS(true);
    	} else {
    		return Result.FAILURE();
    	}
    }

    /**
     * 添加会员等级关系
     * @param merchantMemberLevelRelVO
     * @return
     */
    @RequestMapping(value="/addMemberLevel",method = RequestMethod.POST)
	@ApiOperation(value = "添加会员等级关系", notes = "添加会员等级关系", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> addMemberLevel(@RequestBody MerchantMemberLevelRelVO merchantMemberLevelRelVO) {
		logger.info("addMemberLevel {}", merchantMemberLevelRelVO);
		if(merchantMemberLevelRelVO == null || merchantMemberLevelRelVO.getLevelId() == null || merchantMemberLevelRelVO.getMerchantMemberId() == null){
			logger.error("addMemberLevel 参数为NULL");
			return Result.FAILURE(MemberErrorEnum.MEMBER_NULL_PARAM_ERROR.getCode(),MemberErrorEnum.MEMBER_NULL_PARAM_ERROR.getMsg());
		}

		MerchantMemberLevelRelDTO memberLevelRelDTO = ObjectConvertUtil.copyProperties(MerchantMemberLevelRelDTO.class, merchantMemberLevelRelVO);
		memberLevelRelDTO.setMerchantId(ThreadLocalContext.getMerchantId());
		memberLevelRelDTO.setShopId(ThreadLocalContext.getShopId());
		if (this.merchantMemberLevelFacade.addMemberLevelRel(memberLevelRelDTO) > 0) {
			return Result.SUCESS(true);
		} else {
			return Result.FAILURE();
		}
	}

	@RequestMapping(value="/updateMerchantMemberRemark/{merchantMemberId}",method = RequestMethod.POST)
	@ApiOperation(value = "更新会员备注", notes = "更新会员备注", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> updateMerchantMemberRemark(@ApiParam(name = "merchantMemberId",value = "会员ID",required = true) @PathVariable(value = "merchantMemberId") Long merchantMemberId,
													 @ApiParam(name = "remark",value = "备注",required = true) @RequestParam(value = "remark") String remark) {

		int result = this.merchantMemberFacade.updateMemberRemark(merchantMemberId,remark);

		if (result > 0) {
			return Result.SUCESS(true);
		} else {
			return Result.FAILURE();
		}
	}


	@RequestMapping(value="/getPlatformMemberLevelInfos/{memberId}",method = RequestMethod.POST)
	@ApiOperation(value = "获取李子会员在商户的等级情况", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MemberInfoVO> getPlatformMemberLevelInfos(@ApiParam(name = "memberId",value = "会员ID",required = true)@PathVariable(value = "memberId")Long memberId) {
		MemberInfoDTO result = this.memberFacade.getMemberAndLevels(memberId);
		if(result == null){
			if(logger.isInfoEnabled())
				logger.info("getPlatformMemberLevelInfos memberId={} 会员不存在",memberId);
			return Result.FAILURE(MemberErrorEnum.MEMBER_NOT_EXIST_ERROR.getCode(),MemberErrorEnum.MEMBER_NOT_EXIST_ERROR.getMsg());
		}

		MemberInfoVO memberInfoVO = ObjectConvertUtil.map(result,MemberInfoVO.class);
		memberInfoVO.setMemberLevelInfoVOS(ObjectConvertUtil.mapList(result.getMemberLevelInfoDTOS(),MemberLevelInfoDTO.class,MemberLevelInfoVO.class));

		return Result.SUCESS(memberInfoVO);
	}


	@RequestMapping(value="/queryMemberConsumeStatistics",method = RequestMethod.POST)
	@ApiOperation(value = "查询会员消费统计", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<MerchantMemberStatisticsVO>> queryMemberConsumeStatistics(@RequestBody MerchantMemberQueryVO merchantMemberQueryVO,
																		   @ApiParam(name = "info",value = "查询信息内容 1带会员优惠方式设置 0不带",defaultValue = "0")@RequestParam(value = "info",defaultValue = "0",required = false) int info,
																		   @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
																		   @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
																		   @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy) {
		merchantMemberQueryVO.setMerchantId(ThreadLocalContext.getMerchantId());
		MerchantMemberQueryDTO merchantMemberQueryDTO = ObjectConvertUtil.map(merchantMemberQueryVO,MerchantMemberQueryDTO.class);
		PageParameter pageParameter = new PageParameter(pageNum,pageSize);
		PageInfo<MerchantMemberStatisticsDTO> statisticsDTOPageInfo = this.merchantMemberFacade.queryMemberStatistics(merchantMemberQueryDTO,pageParameter);
		PageVO pageVO = new PageVO(statisticsDTOPageInfo.getPageNum(),statisticsDTOPageInfo.getPageSize(),statisticsDTOPageInfo.getPages(),statisticsDTOPageInfo.getTotal());

		MemberDiscountTypeEnum discountTypeEnum = MemberDiscountTypeEnum.NONE_DISCOUNT;
		if (info == 1) {
			discountTypeEnum = this.memberSettingFacade.findDiscountTypeByMerchant(ThreadLocalContext.getMerchantId());
		}

		List<MerchantMemberStatisticsVO> merchantMemberStatisticsVOS = new ArrayList<>();
		if (statisticsDTOPageInfo.getList() != null) {
			for(MerchantMemberStatisticsDTO statisticsDTO :statisticsDTOPageInfo.getList()){
				MerchantMemberStatisticsVO memberStatisticsVO = ObjectConvertUtil.map(statisticsDTO,MerchantMemberStatisticsVO.class);
				memberStatisticsVO.setDiscountTypeEnum(discountTypeEnum);
				memberStatisticsVO.setCurrentAmount(Math.max(0L,memberStatisticsVO.getTotalRechargeAmount() - memberStatisticsVO.getTotalCostRechargeAmount() - memberStatisticsVO.getFrozenAmount()));
				memberStatisticsVO.setCurrentCredit(Math.max(0L,memberStatisticsVO.getTotalCredit() - memberStatisticsVO.getTotalUseCredit() - memberStatisticsVO.getFrozenCredit()));
				merchantMemberStatisticsVOS.add(memberStatisticsVO);
			}
		}
		pageVO.setList(merchantMemberStatisticsVOS);

		return Result.SUCESS(pageVO);
	}

	@RequestMapping(value = "/merchantMemberConsumeStatistic/{merchantMemberId}",method = RequestMethod.GET)
	@ApiOperation(value = "获取会员消费情况", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MerchantMemberStatisticsVO> getMemberConsumeStatistic(@ApiParam(name = "merchantMemberId",value = "商户会员ID")@PathVariable(name = "merchantMemberId") Long merchantMemberId){
		MerchantMemberStatisticsDTO memberStatisticsDTO = this.merchantMemberFacade.getStatisticsById(merchantMemberId);
		if (memberStatisticsDTO != null){
			MerchantMemberStatisticsVO merchantMemberStatisticsVO = MerchantMemberStatisticsVO.from(memberStatisticsDTO);
			setShopName(merchantMemberStatisticsVO);
			merchantMemberStatisticsVO.setCouponNum(getCouponNum(ThreadLocalContext.getMerchantId(),ThreadLocalContext.getUserId()));
			return Result.SUCESS(merchantMemberStatisticsVO);
		}

		return Result.SUCESS();
	}

	/**
	 * 回填店铺名称
	 * @param vo
	 */
	private void setShopName(MerchantMemberStatisticsVO vo){
		ShopDTO shopDTO = this.shopReadFacade.findById(vo.getSourceShopId());
		if(shopDTO != null){
			vo.setSourceShopName(shopDTO.getShopName());
		}
	}

	/**
	 * 获取商户会员卡卷数量
	 * @param merchantId
	 * @param userId
	 * @return
	 */
	private int getCouponNum(Long merchantId,Long userId){
		if (merchantId != null && userId != null) {
			UserCouponQueryDTO queryDTO = new UserCouponQueryDTO();
			queryDTO.setMerchantId(merchantId);
			queryDTO.setUserId(userId);
			return merchantCouponReadFacade.getCouponNum(queryDTO);
		}

		return 0;
	}
}
