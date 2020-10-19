package com.unitedfoods.allcashu.Model;

public class PDAModel {

    public  String DistrictID;
    public  String DistrictName;
    public  String DistrictCode;
    public  String AreaID;
    public  String AreaName;
    public  String ProvinceID;
    public  String ProvinceName;
    public  String WHID;

    public PDAModel(String DistrictID,String DistrictName,String AreaID,String AreaName,String ProvinceID,String ProvinceName,String WHID,String DistrictCode){
        this.DistrictID = DistrictID;
        this.DistrictName = DistrictName;
        this.AreaID = AreaID;
        this.AreaName = AreaName;
        this.ProvinceID = ProvinceID;
        this.ProvinceName = ProvinceName;
        this.WHID = WHID;
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

    public String getWHID() {
        return WHID;
    }

    public void setWHID(String WHID) {
        this.WHID = WHID;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }
}
