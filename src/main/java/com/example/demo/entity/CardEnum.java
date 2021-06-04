package com.example.demo.entity;

import java.util.Objects;

public enum CardEnum {
    NAME("name","姓名"),
    NATIONAL("national","民族"),
    ADDRESS("address","住址"),
    ID("id","公民身份号码"),
    BIRTHDAY("birthday","出生"),
    SEX("sex","性别");

    private String name;

    private String cname;

     CardEnum(String name,String cname){
        this.cname = cname;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public static String getNameByCname(String cname){
         for (CardEnum card :CardEnum.values()){
             if(Objects.equals(cname,card.getCname())){
                 return card.getName();
             }
         }
         return null;
    }
}
