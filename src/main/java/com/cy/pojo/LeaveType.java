package com.cy.pojo;

public enum LeaveType { oneday("oneDay"),threedays("threeDays"),leave("leave"),enter("enter"),visit("visit");
    private String dataBaseName;
    private LeaveType(String dataBaseName){
        this.dataBaseName=dataBaseName;
    }
    public String getDataBaseName() {
        return dataBaseName;
    }
}
