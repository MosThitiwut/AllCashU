package com.unitedfoods.allcashu.Model;

public class ShopTypeModel {

    public String ShopTypeID;
    public String ShopTypeCode;
    public String ShopTypeName;
    public String ShopTypeGroupID;

    public ShopTypeModel(String ShopTypeID,String ShopTypeCode,String ShopTypeName,String ShopTypeGroupID)
    {
        this.ShopTypeID = ShopTypeID;
        this.ShopTypeCode = ShopTypeCode;
        this.ShopTypeGroupID = ShopTypeGroupID;
        this.ShopTypeName = ShopTypeName;
    }

    public String getShopTypeID() {
        return ShopTypeID;
    }

    public void setShopTypeID(String shopTypeID) {
        ShopTypeID = shopTypeID;
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

    public String getShopTypeGroupID() {
        return ShopTypeGroupID;
    }

    public void setShopTypeGroupID(String shopTypeGroupID) {
        ShopTypeGroupID = shopTypeGroupID;
    }
}
