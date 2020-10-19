package com.unitedfoods.allcashu.Sale.Sell.SellList;

public class SellModel {

    public String ProductID;
    public String ProductName;
    public String ProductGroupID;
    public Integer StockQty;
    public Integer OrderQty;
    public Integer StockBillOne;
    public Integer POBillOne;
    public Integer StockBillTwo;
    public Integer POBillTwo;

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductGroupID() {
        return ProductGroupID;
    }

    public void setProductGroupID(String productGroupID) {
        ProductGroupID = productGroupID;
    }

    public Integer getStockQty() {
        return StockQty;
    }

    public void setStockQty(Integer stockQty) {
        StockQty = stockQty;
    }

    public Integer getOrderQty() {
        return OrderQty;
    }

    public void setOrderQty(Integer orderQty) {
        OrderQty = orderQty;
    }

    public Integer getStockBillOne() {
        return StockBillOne;
    }

    public void setStockBillOne(Integer stockBillOne) {
        StockBillOne = stockBillOne;
    }

    public Integer getPOBillOne() {
        return POBillOne;
    }

    public void setPOBillOne(Integer POBillOne) {
        this.POBillOne = POBillOne;
    }

    public Integer getStockBillTwo() {
        return StockBillTwo;
    }

    public void setStockBillTwo(Integer stockBillTwo) {
        StockBillTwo = stockBillTwo;
    }

    public Integer getPOBillTwo() {
        return POBillTwo;
    }

    public void setPOBillTwo(Integer POBillTwo) {
        this.POBillTwo = POBillTwo;
    }
}
