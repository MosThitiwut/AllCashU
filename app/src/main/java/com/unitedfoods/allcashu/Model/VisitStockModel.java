package com.unitedfoods.allcashu.Model;

public class VisitStockModel {

    public  String BranchID;
    public  String WHID;
    public  String CustomerID;
    public  String DocNo;
    public  String VisitID;
    public  String VisitType;
    public  String VisitDate;
    public  String ProductID;
    public  Integer StockQty;

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

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getDocNo() {
        return DocNo;
    }

    public void setDocNo(String docNo) {
        DocNo = docNo;
    }

    public String getVisitID() {
        return VisitID;
    }

    public void setVisitID(String visitID) {
        VisitID = visitID;
    }

    public String getVisitType() {
        return VisitType;
    }

    public void setVisitType(String visitType) {
        VisitType = visitType;
    }

    public String getVisitDate() {
        return VisitDate;
    }

    public void setVisitDate(String visitDate) {
        VisitDate = visitDate;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public Integer getStockQty() {
        return StockQty;
    }

    public void setStockQty(Integer stockQty) {
        StockQty = stockQty;
    }
}
