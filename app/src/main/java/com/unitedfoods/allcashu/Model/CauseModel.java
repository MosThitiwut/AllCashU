package com.unitedfoods.allcashu.Model;

public class CauseModel {

    public  String CauseID;
    public  String CauseCode;
    public  String CauseName;
    public  String CauseGroup;

    public CauseModel(String CauseID,String CauseCode,String CauseName,String CauseGroup)
    {
        this.CauseID = CauseID;
        this.CauseCode = CauseCode;
        this.CauseName = CauseName;
        this.CauseGroup = CauseGroup;
    }

    public String getCauseID() {
        return CauseID;
    }

    public void setCauseID(String causeID) {
        CauseID = causeID;
    }

    public String getCauseCode() {
        return CauseCode;
    }

    public void setCauseCode(String causeCode) {
        CauseCode = causeCode;
    }

    public String getCauseName() {
        return CauseName;
    }

    public void setCauseName(String causeName) {
        CauseName = causeName;
    }

    public String getCauseGroup() {
        return CauseGroup;
    }

    public void setCauseGroup(String causeGroup) {
        CauseGroup = causeGroup;
    }
}
