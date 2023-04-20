package com.cy.myException;

public class ValueInvalidException extends Exception{
    public String valueName;
    public String msg;
    public ValueInvalidException(){
        super();
    }
    public ValueInvalidException(String valueName){
        this.valueName=valueName;
    }
    public ValueInvalidException(String valueName,String errorMsg){
        this.valueName=valueName;
        this.msg=errorMsg;
    }
}
