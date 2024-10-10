package org.alwyn.minispring.beans.factory;

public class TestBean {
    String name;
    public TestBean(){}
    public TestBean(String name){
        this.name = name;
    }
    public void service(){
        System.out.println("Test bean service.");
    }
}
