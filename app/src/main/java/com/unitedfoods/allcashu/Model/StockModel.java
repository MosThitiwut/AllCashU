package com.unitedfoods.allcashu.Model;

public class StockModel {

    public String BranchID;
    public String WHID;
    public String ProductID;
    public String StockQty;
    public String StockDate;

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public String getWHID() {
        return WHID;
    }

    public void setWHID(String WHID) {
        this.WHID = WHID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getStockQty() {
        return StockQty;
    }

    public void setStockQty(String stockQty) {
        StockQty = stockQty;
    }

    public String getStockDate() {
        return StockDate;
    }

    public void setStockDate(String stockDate) {
        StockDate = stockDate;
    }
}
