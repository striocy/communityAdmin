package com.cy.pojo;

public enum SafetyGrade {
    safe("安全"),
    caution("谨慎"),
    danger("危险");
    private String chineseName;
    private SafetyGrade(String cname){
        this.chineseName=cname;
    }
    public String getChineseName() {
        return chineseName;
    }
}
