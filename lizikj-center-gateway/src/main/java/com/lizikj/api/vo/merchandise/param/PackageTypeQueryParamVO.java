package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.PackageTypeEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;

/**
 * @author dyh
 * @created at 2018 01 15 11:18
 */
public class PackageTypeQueryParamVO {

    private PackageTypeEnum packageType;

    private YesOrNoEnum needComputeSpecialDiscount;

    public PackageTypeEnum getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageTypeEnum packageType) {
        this.packageType = packageType;
    }

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }

}
