package com.unitedfoods.allcashu.Uitls.CauseSpinners;

public class CauseSpinnerModel {

    public String CauseID;
    public String CauseName;

    public CauseSpinnerModel(String CauseID, String CauseName)
    {
        this.CauseID = CauseID;
        this.CauseName = CauseName;
    }

    public String getCauseID() {
        return CauseID;
    }

    public void setCauseID(String causeID) {
        CauseID = causeID;
    }

    public String getCauseName() {
        return CauseName;
    }

    public void setCauseName(String causeName) {
        CauseName = causeName;
    }
}
