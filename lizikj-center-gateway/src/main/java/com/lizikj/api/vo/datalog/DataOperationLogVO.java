package com.lizikj.api.vo.datalog;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @auth zone
 * @date 2017-09-21
 */
@ApiModel(value = "数据操作记录vo")
public class DataOperationLogVO {
	@ApiModelProperty(required = true, name = "id", value = "游标值", dataType = "Long")
	private long id;
	
	@ApiModelProperty(required = true, name = "dataType", value = "同步消息的大类型1:商品 2:会员 3:订单 4:卡券 5:员工 6:店铺管理 7:营销活动 8:基础数据管理", dataType = "Byte")
	private Byte dataType;
    
    @ApiModelProperty(required = true, name = "subDataType", value = "子数据类型  \n"
    		+ "\tdataType为1时  1：商品信息  2：分类  3：加料  4：美食模板  \n"
    		+ "\tdataType为2时  1：会员功能设置  2：会员列表  3：会员信息  \n"
    		+ "\tdataType为3时  没有子类型  \n"
    		+ "\tdataType为4时  没有子类型  \n"
    		+ "\tdataType为5时  1：员工列表  \n"
    		+ "\tdataType为6时  1：桌台管理  2：公告  3：高级功能  4：营业设置  5：POS设置  6：业务功能开关  7：库存阀值设置   8：厨打设备  9：餐桌信息  \n"
    		+ "\tdataType为7时  1：砍价活动  \n"
    		+ "\tdataType为8时  1：单位管理  2：口味偏好管理  3：标签管理  4：呼叫服务内容管理  5：积分规则设置", dataType = "Byte")
 	private Byte subDataType;

    @ApiModelProperty(required = true, name = "operationType", value = "同步消息的大类型  0:啥也不干   1:增加   2:修改", dataType = "Byte")
 	private Byte operationType;

    @ApiModelProperty(required = true, name = "dataId", value = "数据id", dataType = "String")
    private String dataId;

    @ApiModelProperty(required = true, name = "shopId", value = "店铺id", dataType = "Long")
    private long shopId;

    @ApiModelProperty(required = true, name = "merchantId", value = "商户id", dataType = "Long")
    private long merchantId;

     @ApiModelProperty(required = true, name = "dataUpdateTime", value = "数据的更新时间", dataType = "Date")
    private Date dataUpdateTime; 
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Byte getDataType() {
		return dataType;
	}
	public void setDataType(Byte dataType) {
		this.dataType = dataType;
	}
	public Byte getSubDataType() {
		return subDataType;
	}
	public void setSubDataType(Byte subDataType) {
		this.subDataType = subDataType;
	}
	public Byte getOperationType() {
		return operationType;
	}
	public void setOperationType(Byte operationType) {
		this.operationType = operationType;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public Date getDataUpdateTime() {
		return dataUpdateTime;
	}
	public void setDataUpdateTime(Date dataUpdateTime) {
		this.dataUpdateTime = dataUpdateTime;
	}
}
