package com.unitedfoods.allcashu.Uitls.AreaSpinners;

public class AreaSpinnerModel {

    public String AreaID;
    public String AreaName;

    public AreaSpinnerModel(String AreaID, String AreaName){
        this.AreaID = AreaID;
        this.AreaName = AreaName;
    }

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
}

