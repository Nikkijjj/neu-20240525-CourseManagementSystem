package com.nikki.shixunpro.entity;

public class SuperManager {
    private String name;
    private String password;

    public SuperManager(){
    }

    public String toString(){
        return "SuperManager Name: "+name+"\nLogin Pwd: "+password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
