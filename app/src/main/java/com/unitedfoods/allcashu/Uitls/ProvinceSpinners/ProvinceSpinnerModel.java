package com.unitedfoods.allcashu.Uitls.ProvinceSpinners;

public class ProvinceSpinnerModel {
    public String ProvinceID;
    public String ProvinceName;

    public ProvinceSpinnerModel(String ProvinceID, String ProvinceName){

        this.ProvinceID = ProvinceID;
        this.ProvinceName = ProvinceName;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String provinceID) {
        ProvinceID = provinceID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }
}
