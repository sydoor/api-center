package com.lizikj.api.vo.shop;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.shop.api.enums.*;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午10:17:09 
 */
@ApiModel
public class ShopDeskVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	/**
     * 桌子ID
     */
	@ApiModelProperty(value = "桌子ID", required = true)
    private Long areaDeskId;

    /**
     * 店铺ID
     */
	@ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 店铺区域ID
     */
	@ApiModelProperty(value = "店铺区域ID", required = true)
    private Long shopAreaId;

	/**
	 * 区域名称
	 */
	@ApiModelProperty(value = "店铺区域名称", required = true)
	private String areaName;

	/**
	 * 是否属于否默认：DEFAULT.默认区域，CUSTOM.自定义区域
	 */
	@ApiModelProperty(value = "是否默认：见ShopAreaDefaultStatusEnum：DEFAULT.默认区域，CUSTOM.自定义区域。", required = true)
	private ShopAreaDefaultStatusEnum areaDefaultStatus;

    /**
     * 餐桌名称
     */
	@ApiModelProperty(value = "桌台名称", required = true)
    private String deskName;

    /**
     * 座位数
     */
	@ApiModelProperty(value = "座位数", required = true)
    private Integer seatNum;

	/**
	 * 就餐人数
	 */
	@ApiModelProperty(value = "就餐人数", required = true)
	private Integer peoples;

	/**
	 * 接单时间
	 */
	@ApiModelProperty(value = "接单时间：桌台状态不空闲；查看状态该时为为完成的订单接单时间", required = true)
	private Long recTime;

	/**
	 * 订单ID：当有订单出现的桌台，才去查询订单信息
	 */
	@ApiModelProperty(value = "订单ID", required = true)
	private Long orderId;

	/**
	 * 会员身份
	 */
	@ApiModelProperty(value = "会员类型：见：MemberRoleEnum：NONE.非会员,MEMBER.会员,SUPER_MEMBER.特殊会员。", required = true)
	private MemberRoleEnum memberRole;

	/**
	 * 会员等级名称
	 */
	@ApiModelProperty(value = "会员等级名称：桌台状态不空闲；订单状态未完成，且会员登录下单，程序会员系统获取", required = true)
	private String memberLevelName;

	/**
	 * 会员等级名称
	 */
	@ApiModelProperty(value = "会员等级", required = true)
	private Integer memberLevel;

    /**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = true)
    private Integer deskOrder;

    /**
     * 二维码图片地址：放在资源系统中（目前七牛），缩略图用20x20的方式拼装media_id得到的地址获得
     */
	@ApiModelProperty(value = "二维码图片地址：放在资源系统中（目前七牛），缩略图用20x20的方式拼装media_id得到的地址获得", required = true)
    private Long mediaId;

    /**
     * 使用状态：0. 空闲，1. 待接单，2. 待点单，3. 待结账，4. 待叫起，5. 待清台，6. 锁定
     */

	@ApiModelProperty(value = "使用状态：见：DeskUsedStatusEnum：FREE. 空闲，WAIT_REC. 待接单，WAIT_ORDER. 待点单，" +
			"WAIT_PAYMENT. 待结账，WAIT_DESK_CLEAN. 待清台，LOCKED. 锁定。", required = true)
    private DeskUsedStatusEnum usedStatus;

	/**
	 * 桌台状态名称：见：DeskUsedStatusEnum
	 */
	@ApiModelProperty(value = "桌台状态名称：见：DeskUsedStatusEnum", required = true)
	private String usedStatusName;


    /**
     * 呼叫状态：1.有呼叫，0.无呼叫
     */
	@ApiModelProperty(value = "呼叫状态：见CallStatusEnum：HAD_CALL.有呼叫，NOT_CALL.无呼叫", required = true)
    private CallStatusEnum callStatus;

	/**
	 * 呼叫状态名称：1.有呼叫，0.无呼叫
	 */
	@ApiModelProperty(value = "呼叫状态名称：见CallStatusEnum", required = true)
	private String callStatusName;

	/**
	 * 呼叫话术ID
	 */
	@ApiModelProperty(value = "呼叫话术ID", required = true)
	private String callServiceWordsId;

	/**
	 * 呼叫话术
	 */
	@ApiModelProperty(value = "呼叫话术：根据ID查询话术表", required = true)
	private String callServiceWords;

	/**
	 * 是否待叫起
	 */
	@ApiModelProperty(value = "是否待叫起：见DeskUsedSubStatusEnum：WAIT.待叫起，NONE.无", required = true)
	private DeskUsedSubStatusEnum usedSubStatus;

	/**
	 * 是否待叫起
	 */
	@ApiModelProperty(value = "是否待叫起名称：见DeskUsedSubStatusEnum", required = true)
	private String usedSubStatusName;

	/**
	 * 锁定原因
	 */
	@ApiModelProperty(value = "锁定原因", required = true)
	private String lockReason;

	/**
	 * 是否删除:1.删除；0.未删除
	 */
	@ApiModelProperty(value = "是否删除：见：RemoveStatusEnum。", required = true)
	private RemoveStatusEnum removeStatus;

	@ApiModelProperty(value = "父ID：没有默认为0", required = true)
	private Long parentId;

	@ApiModelProperty(value = "是否拼桌", required = true)
	private Boolean mergeFlag;

	@ApiModelProperty(value = "层级:0.父，1.子，2.1的子", required = true)
	private Integer level;

	@ApiModelProperty(value = "绑定的预生成的二维码", required = true)
	private String pregenQrcodeUuid;

	@ApiModelProperty(value = "更新时间", required = true)
	private Date updateTime;

	@ApiModelProperty(value = "公众号二维码url", required = false)
    private String publicQrcodeUrl;

	/**
	 * @return the areaDeskId
	 */
	public Long getAreaDeskId() {
		return areaDeskId;
	}

	/**
	 * @param areaDeskId the areaDeskId to set
	 */
	public void setAreaDeskId(Long areaDeskId) {
		this.areaDeskId = areaDeskId;
	}

	/**
	 * @return the shopId
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return the shopAreaId
	 */
	public Long getShopAreaId() {
		return shopAreaId;
	}

	/**
	 * @param shopAreaId the shopAreaId to set
	 */
	public void setShopAreaId(Long shopAreaId) {
		this.shopAreaId = shopAreaId;
	}

	/**
	 * @return the deskName
	 */
	public String getDeskName() {
		return deskName;
	}

	/**
	 * @param deskName the deskName to set
	 */
	public void setDeskName(String deskName) {
		this.deskName = deskName;
	}

	/**
	 * @return the seatNum
	 */
	public Integer getSeatNum() {
		return seatNum;
	}

	/**
	 * @param seatNum the seatNum to set
	 */
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	/**
	 * @return the deskOrder
	 */
	public Integer getDeskOrder() {
		return deskOrder;
	}

	/**
	 * @param deskOrder the deskOrder to set
	 */
	public void setDeskOrder(Integer deskOrder) {
		this.deskOrder = deskOrder;
	}

	/**
	 * @return the mediaId
	 */
	public Long getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public DeskUsedStatusEnum getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(DeskUsedStatusEnum usedStatus) {
		this.usedStatus = usedStatus;
	}

	public CallStatusEnum getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(CallStatusEnum callStatus) {
		this.callStatus = callStatus;
	}

	public Integer getPeoples() {
		return peoples;
	}

	public void setPeoples(Integer peoples) {
		this.peoples = peoples;
	}

	public Long getRecTime() {
		return recTime;
	}

	public void setRecTime(Long recTime) {
		this.recTime = recTime;
	}

	public String getMemberLevelName() {
		return memberLevelName;
	}

	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getLockReason() {
		return lockReason;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public MemberRoleEnum getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRoleEnum memberRole) {
		this.memberRole = memberRole;
	}

	public String getCallServiceWords() {
		return callServiceWords;
	}

	public void setCallServiceWords(String callServiceWords) {
		this.callServiceWords = callServiceWords;
	}

	public DeskUsedSubStatusEnum getUsedSubStatus() {
		return usedSubStatus;
	}

	public void setUsedSubStatus(DeskUsedSubStatusEnum usedSubStatus) {
		this.usedSubStatus = usedSubStatus;
	}

	public String getUsedStatusName() {
		return usedStatusName;
	}

	public void setUsedStatusName(String usedStatusName) {
		this.usedStatusName = usedStatusName;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCallStatusName() {
		return callStatusName;
	}

	public void setCallStatusName(String callStatusName) {
		this.callStatusName = callStatusName;
	}

	public String getUsedSubStatusName() {
		return usedSubStatusName;
	}

	public void setUsedSubStatusName(String usedSubStatusName) {
		this.usedSubStatusName = usedSubStatusName;
	}

	public String getCallServiceWordsId() {
		return callServiceWordsId;
	}

	public void setCallServiceWordsId(String callServiceWordsId) {
		this.callServiceWordsId = callServiceWordsId;
	}

	public ShopAreaDefaultStatusEnum getAreaDefaultStatus() {
		return areaDefaultStatus;
	}

	public void setAreaDefaultStatus(ShopAreaDefaultStatusEnum areaDefaultStatus) {
		this.areaDefaultStatus = areaDefaultStatus;
	}

	public RemoveStatusEnum getRemoveStatus() {
		return removeStatus;
	}

	public void setRemoveStatus(RemoveStatusEnum removeStatus) {
		this.removeStatus = removeStatus;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getMergeFlag() {
		return mergeFlag;
	}

	public void setMergeFlag(Boolean mergeFlag) {
		this.mergeFlag = mergeFlag;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPregenQrcodeUuid() {
		return pregenQrcodeUuid;
	}

	public void setPregenQrcodeUuid(String pregenQrcodeUuid) {
		this.pregenQrcodeUuid = pregenQrcodeUuid;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPublicQrcodeUrl() {
		return publicQrcodeUrl;
	}

	public void setPublicQrcodeUrl(String publicQrcodeUrl) {
		this.publicQrcodeUrl = publicQrcodeUrl;
	}
}
