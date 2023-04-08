package com.cy.pojo;

public enum DeviceType {
    temprature("温度传感器"),
    humidity("湿度传感器"),
    smoke("烟雾传感器");
    private String chineseName;

    private DeviceType(String cn){
        this.chineseName=cn;
    }
    public String getChineseName(){
        return this.chineseName;
    }
}
