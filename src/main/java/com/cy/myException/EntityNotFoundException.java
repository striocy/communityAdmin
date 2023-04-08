package com.cy.myException;

public class EntityNotFoundException extends Exception{
    public String msg;
    public int errorCode;
    public EntityNotFoundException(){
        super();
    }
    public EntityNotFoundException(String msg){
        this.msg=msg;
    }
    public EntityNotFoundException(String msg,int errorCode){
        this.msg=msg;
        this.errorCode=errorCode;
    }
}
