package com.unitedfoods.allcashu.Uitls.ShopTypeSpinners;

public class ShopTypeSpinnerModel {

    public String ShopTypeCode;
    public String ShopTypeName;

    public ShopTypeSpinnerModel(String ShopTypeCode,String ShopTypeName)
    {
        this.ShopTypeCode = ShopTypeCode;
        this.ShopTypeName = ShopTypeName;
    }

    public String getShopTypeCode() {
        return ShopTypeCode;
    }

    public void setShopTypeCode(String shopTypeCode) {
        ShopTypeCode = shopTypeCode;
    }

    public String getShopTypeName() {
        return ShopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        ShopTypeName = shopTypeName;
    }
}
