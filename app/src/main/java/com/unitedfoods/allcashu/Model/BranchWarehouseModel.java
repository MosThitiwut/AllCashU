package com.unitedfoods.allcashu.Model;

public class BranchWarehouseModel {

    public  String WHID;
    public  String BranchID;
    public  String WHType;
    public  String WHNAME;
    public  String WHSeq;
    public  String EmpID;
    public  String TitleName;
    public  String FirstName;
    public  String LastName;
    public  String Username;
    public  String Password;
    public  String POSNo;
    public  String EmpIDCard;

    public BranchWarehouseModel(String WHID,String BranchID,String WHType,String WHName, String WHSeq,String EmpID,String TitleName,String FirstName,String LastName,String Username,String Password,String POSNo,
            String EmpIDCard){
        this.WHID = WHID;
        this.BranchID = BranchID;
        this.WHType = WHType;
        this.WHNAME = WHName;
        this.WHSeq = WHSeq;
        this.EmpID = EmpID;
        this.TitleName = TitleName;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Username = Username;
        this.Password = Password;
        this.POSNo = POSNo;
        this.EmpIDCard = EmpIDCard;
    }

    public String getWHID() {
        return WHID;
    }

    public void setWHID(String WHID) {
        this.WHID = WHID;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public String getWHType() {
        return WHType;
    }

    public void setWHType(String WHType) {
        this.WHType = WHType;
    }

    public String getWHNAME() {
        return WHNAME;
    }

    public void setWHNAME(String WHNAME) {
        this.WHNAME = WHNAME;
    }

    public String getWHSeq() {
        return WHSeq;
    }

    public void setWHSeq(String WHSeq) {
        this.WHSeq = WHSeq;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPOSNo() {
        return POSNo;
    }

    public void setPOSNo(String POSNo) {
        this.POSNo = POSNo;
    }

    public String getEmpIDCard() {
        return EmpIDCard;
    }

    public void setEmpIDCard(String empIDCard) {
        EmpIDCard = empIDCard;
    }
}
