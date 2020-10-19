package com.unitedfoods.allcashu.Model;

public class SettingModel {
    public String cfgName;
    public String cfgValue;

    public SettingModel(String cfgName,String cfgValue){
        this.cfgName = cfgName;
        this.cfgValue = cfgValue;

    }

    public String getCfgName() {
        return cfgName;
    }

    public void setCfgName(String cfgName) {
        this.cfgName = cfgName;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }
}
