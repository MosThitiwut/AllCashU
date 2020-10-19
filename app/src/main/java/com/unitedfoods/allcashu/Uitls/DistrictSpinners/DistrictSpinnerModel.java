package com.unitedfoods.allcashu.Uitls.DistrictSpinners;

public class DistrictSpinnerModel {

    public String DistrictID;
    public String DistrictName;
    public String DistrictCode;

    public DistrictSpinnerModel(String DistrictID,String DistrictName,String DistrictCode)
    {
        this.DistrictID = DistrictID;
        this.DistrictName = DistrictName;
        this.DistrictCode = DistrictCode;
    }

    public String getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(String districtID) {
        DistrictID = districtID;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }
}
