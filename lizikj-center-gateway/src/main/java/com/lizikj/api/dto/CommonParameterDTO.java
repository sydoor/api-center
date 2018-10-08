package com.lizikj.api.dto;

import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.version.dto.BaseDTO;

/**
 * 公共参数传输层DTO
 *
 * @author Michael.Huang
 * @date 2017/8/18 11:08
 */
public class CommonParameterDTO extends BaseDTO {
    /**
     * 用户ID，根据用户类型区分
     */
    private Long currentUserId;
    /**
     * 代理商ID
     */
    private Long currentAgentId;
    /**
     * 商户ID
     */
    private Long currentMerchantId;
    /**
     * 店铺ID
     */
    private Long currentShopId;
    /**
     * 员工ID
     */
    private Long currentStaffId;
    /**
     * 设备号(POS,APP各自获取)
     */
    private String currentSnNum;
    /**
     * 用户类型
     */
    private Byte currentUserType;
    /**
     * 李子会员ID
     */
    private Long currentMemberId;
    /**
     * 商户会员ID
     */
    private Long currentMerchantMemberId;


    /**
     * 填充公共参数，userId,shopId等
     * 此方法会调用ThreadLocalContext#remove()
     */
    public static CommonParameterDTO buildParameters() {
        return new CommonParameterDTO().build();
    }

    /**
     * 填充公共参数，userId,shopId等
     * 此方法会调用ThreadLocalContext#remove()
     */
    public CommonParameterDTO build() {

        this.setCurrentAgentId(ThreadLocalContext.getAgentId());
        this.setCurrentShopId(ThreadLocalContext.getShopId());
        this.setCurrentUserId(ThreadLocalContext.getUserId());
        this.setCurrentUserType(ThreadLocalContext.getUserType());
        this.setCurrentMerchantId(ThreadLocalContext.getMerchantId());
        this.setCurrentSnNum(ThreadLocalContext.getDid());
        this.setCurrentStaffId(ThreadLocalContext.getStaffId());
        this.setVersion(ThreadLocalContext.getLZVersion());
        return this;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Long getCurrentAgentId() {
        return currentAgentId;
    }

    public void setCurrentAgentId(Long currentAgentId) {
        this.currentAgentId = currentAgentId;
    }

    public Long getCurrentMerchantId() {
        return currentMerchantId;
    }

    public void setCurrentMerchantId(Long currentMerchantId) {
        this.currentMerchantId = currentMerchantId;
    }

    public Long getCurrentShopId() {
        return currentShopId;
    }

    public void setCurrentShopId(Long currentShopId) {
        this.currentShopId = currentShopId;
    }

    public Long getCurrentStaffId() {
        return currentStaffId;
    }

    public void setCurrentStaffId(Long currentStaffId) {
        this.currentStaffId = currentStaffId;
    }

    public String getCurrentSnNum() {
        return currentSnNum;
    }

    public void setCurrentSnNum(String currentSnNum) {
        this.currentSnNum = currentSnNum;
    }

    public Byte getCurrentUserType() {
        return currentUserType;
    }

    public void setCurrentUserType(Byte currentUserType) {
        this.currentUserType = currentUserType;
    }

    public Long getCurrentMemberId() {
        return currentMemberId;
    }

    public void setCurrentMemberId(Long currentMemberId) {
        this.currentMemberId = currentMemberId;
    }

    public Long getCurrentMerchantMemberId() {
        return currentMerchantMemberId;
    }

    public void setCurrentMerchantMemberId(Long currentMerchantMemberId) {
        this.currentMerchantMemberId = currentMerchantMemberId;
    }
}
