package com.unitedfoods.allcashu.Model;

public class ArCustomerShelfModel {

    public  String CustomerID;
    public  String ShelfID;
    public  String FlagNew;
    public  String FlagEdit;
    public  String FlagDel;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getShelfID() {
        return ShelfID;
    }

    public void setShelfID(String shelfID) {
        ShelfID = shelfID;
    }

    public String getFlagNew() {
        return FlagNew;
    }

    public void setFlagNew(String flagNew) {
        FlagNew = flagNew;
    }

    public String getFlagEdit() {
        return FlagEdit;
    }

    public void setFlagEdit(String flagEdit) {
        FlagEdit = flagEdit;
    }

    public String getFlagDel() {
        return FlagDel;
    }

    public void setFlagDel(String flagDel) {
        FlagDel = flagDel;
    }
}
