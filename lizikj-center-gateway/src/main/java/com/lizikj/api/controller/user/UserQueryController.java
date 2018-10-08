package com.lizikj.api.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.user.AgentUserVO;
import com.lizikj.api.vo.user.MerchantUserVO;
import com.lizikj.api.vo.user.param.UserQueryVO;
import com.lizikj.common.enums.UserTypeEnum;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchant.dto.UserProfileDTO;
import com.lizikj.merchant.facade.IUserReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户查询接口
 * @author lijundong
 * @date 2017年7月12日 下午4:47:51
 */
@Controller
@RequestMapping("/user")
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Api(value = "用户查询接口", tags = "用户查询接口")
public class UserQueryController{

	private static final Logger logger = LoggerFactory.getLogger(UserQueryController.class);

	@Autowired
	private IUserReadFacade merchantUserReadFacade;
	
	@RequestMapping(value = "/agent/query", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "代理商用户查询", notes = "代理商用户查询", httpMethod = "POST")
	public Result<PageVO<AgentUserVO>> agentQuery(int pageNo, int pageSize, @RequestBody(required = true) UserQueryVO userQueryVO){
		if(null == ThreadLocalContext.getUserId()){
			return Result.FAILURE("请登录后再操作");
		}
		if(null == userQueryVO){
			return Result.FAILURE("查询信息为空");
		}
		
		PageInfo<UserProfileDTO> pageInfo = null;
		List<AgentUserVO> list = null;
		try {
			UserProfileDTO userProfileDTO = ObjectConvertUtil.map(userQueryVO, UserProfileDTO.class);
			userProfileDTO.setUserType(UserTypeEnum.AGENT_USER.getType());
			pageInfo = merchantUserReadFacade.listUserByReport(pageNo, pageSize, userProfileDTO);
			if(null != pageInfo.getList()){
				list = ObjectConvertUtil.mapList(pageInfo.getList(), UserProfileDTO.class, AgentUserVO.class);
			}
		} catch (BaseException e) {
			return Result.FAILURE();
		}
		return Result.SUCESS(new PageVO(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
	
	@RequestMapping(value = "/merchant/query", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "商户用户查询", notes = "商户用户查询", httpMethod = "POST")
	public Result<PageVO<MerchantUserVO>> merchantQuery(int pageNo, int pageSize, @RequestBody(required = true) UserQueryVO userQueryVO){
		if(null == ThreadLocalContext.getUserId()){
			return Result.FAILURE("请登录后再操作");
		}
		if(null == userQueryVO){
			return Result.FAILURE("查询信息为空");
		}
		
		PageInfo<UserProfileDTO> pageInfo = null;
		List<MerchantUserVO> list = null;
		try {
			UserProfileDTO userProfileDTO = ObjectConvertUtil.map(userQueryVO, UserProfileDTO.class);
			userProfileDTO.setUserType(UserTypeEnum.MERCHANT_USER.getType());
			pageInfo = merchantUserReadFacade.listUserByReport(pageNo, pageSize, userProfileDTO);
			if(null != pageInfo.getList()){
				list = ObjectConvertUtil.mapList(pageInfo.getList(), UserProfileDTO.class, MerchantUserVO.class);
			}
		} catch (BaseException e) {
			return Result.FAILURE();
		}
		return Result.SUCESS(new PageVO(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
}
