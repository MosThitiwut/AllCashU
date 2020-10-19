package com.unitedfoods.allcashu.Model;

public class SaleAreaModel {

    public  String BranchID;
    public  String WHID;
    public  String SalAreaID;
    public  String SalAreaSeq;
    public  String SalAreaName;


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

    public String getSalAreaID() {
        return SalAreaID;
    }

    public void setSalAreaID(String salAreaID) {
        SalAreaID = salAreaID;
    }

    public String getSalAreaSeq() {
        return SalAreaSeq;
    }

    public void setSalAreaSeq(String salAreaSeq) {
        SalAreaSeq = salAreaSeq;
    }

    public String getSalAreaName() {
        return SalAreaName;
    }

    public void setSalAreaName(String salAreaName) {
        SalAreaName = salAreaName;
    }
}
