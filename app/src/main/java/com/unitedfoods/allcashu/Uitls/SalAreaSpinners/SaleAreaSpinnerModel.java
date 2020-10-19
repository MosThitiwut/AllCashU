package com.unitedfoods.allcashu.Uitls.SalAreaSpinners;

public class SaleAreaSpinnerModel {
    public  String SalAreaID;
    public  String SalAreaName;
    public  String SalAreaSeq;

    public SaleAreaSpinnerModel(String mSalAreaID,String mSalAreaName,String mSalAreaSeq){

        SalAreaID = mSalAreaID;
        SalAreaName = mSalAreaName;
        SalAreaSeq = mSalAreaSeq;
    }

    public String getSalAreaID() {
        return SalAreaID;
    }

    public void setSalAreaID(String salAreaID) {
        SalAreaID = salAreaID;
    }

    public String getSalAreaName() {
        return SalAreaName;
    }

    public void setSalAreaName(String salAreaName) {
        SalAreaName = salAreaName;
    }

    public String getSalAreaSeq() {
        return SalAreaSeq;
    }

    public void setSalAreaSeq(String salAreaSeq) {
        SalAreaSeq = salAreaSeq;
    }
}
