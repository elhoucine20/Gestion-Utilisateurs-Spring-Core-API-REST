package com.userApi.model;

public class Produit {

    private int age ;

    public void info(){
        System.out.println("product is here !");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("setter called");
        this.age = age;
    }

}
