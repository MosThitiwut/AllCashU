package com.unitedfoods.allcashu;

public class VisitContract {

    public static final String TABLE_NAME = "Visit";

    public static class Columns{

        public static final String TASK_BRANCHID = "BranchID";
        public static final String TASK_WHID = "WHID";
        public static final String TASK_CUSTOMERID = "CustomerID";
        public static final String TASK_VISITID = "VisitID";
        public static final String TASK_VISTITYPE = "VisitType";
        public static final String TASK_VISITDATE =  "VisitDate";
        public static final String TASK_VISITSTATUS = "VisitStatus";
        public static final String TASK_CAUSEID =  "CauseID";
        public static final String TASK_CAUSEREMARK = "CauseRemark";
        public static final String TASK_LATITUDE = "Latitude";
        public static final String TASK_LONGITUDE = "Longitude";
        public static final String TASK_CRDATE = "CrDate";
        public static final String TASK_EDDATE = "EdDate";

        private Columns(){

        }
    }
}
