package com.unitedfoods.allcashu.Model;

public class ProductModel {

    public  String ProductID;
    public  int Sep;
    public  String ProductName;
    public  String ProductShotName;
    public  String Flavour;
    public  String VatType;
    public  int ProductSubGroupID;
    public  String ProductSubGroupName;
    public  int ProductGroupID;
    public  String ProductGroupName;
    public  int UomSetID;
    public  String UomSetName;
    public  int BaseOty;
    public  Double SellPrice;
    public  Double SellPriceVat;
    public  int PriceGroupID;

    public ProductModel(String ProductID,int Sep,String ProductName,String ProductShotName,String Flavour,String VatType,int ProductSubGroupID, String ProductSubGroupName, int ProductGroupID, String ProductGroupName, int UomSetID, String UomSetName, int BaseOty, Double SellPrice, Double SellPriceVat, int PriceGroupID){

    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public int getSep() {
        return Sep;
    }

    public void setSep(int sep) {
        Sep = sep;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductShotName() {
        return ProductShotName;
    }

    public void setProductShotName(String productShotName) {
        ProductShotName = productShotName;
    }

    public String getFlavour() {
        return Flavour;
    }

    public void setFlavour(String flavour) {
        Flavour = flavour;
    }

    public String getVatType() {
        return VatType;
    }

    public void setVatType(String vatType) {
        VatType = vatType;
    }

    public int getProductSubGroupID() {
        return ProductSubGroupID;
    }

    public void setProductSubGroupID(int productSubGroupID) {
        ProductSubGroupID = productSubGroupID;
    }

    public String getProductSubGroupName() {
        return ProductSubGroupName;
    }

    public void setProductSubGroupName(String productSubGroupName) {
        ProductSubGroupName = productSubGroupName;
    }

    public int getProductGroupID() {
        return ProductGroupID;
    }

    public void setProductGroupID(int productGroupID) {
        ProductGroupID = productGroupID;
    }

    public String getProductGroupName() {
        return ProductGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        ProductGroupName = productGroupName;
    }

    public int getUomSetID() {
        return UomSetID;
    }

    public void setUomSetID(int uomSetID) {
        UomSetID = uomSetID;
    }

    public String getUomSetName() {
        return UomSetName;
    }

    public void setUomSetName(String uomSetName) {
        UomSetName = uomSetName;
    }

    public int getBaseOty() {
        return BaseOty;
    }

    public void setBaseOty(int baseOty) {
        BaseOty = baseOty;
    }

    public Double getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        SellPrice = sellPrice;
    }

    public Double getSellPriceVat() {
        return SellPriceVat;
    }

    public void setSellPriceVat(Double sellPriceVat) {
        SellPriceVat = sellPriceVat;
    }

    public int getPriceGroupID() {
        return PriceGroupID;
    }

    public void setPriceGroupID(int priceGroupID) {
        PriceGroupID = priceGroupID;
    }
}
