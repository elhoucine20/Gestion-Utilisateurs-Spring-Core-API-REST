package com.userApi;

import com.userApi.model.Produit;
import com.userApi.model.User;
import config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("hello world!");

        // method de config par XML
           ApplicationContext context = new  ClassPathXmlApplicationContext("spring.xml");  // create container
           //Produit produit = (Produit) context.getBean("produit"); // spring va cree produit automatique
           //  produit.info();
           //produit.setAge(12);

        // method de config par Java
        //ApplicationContext contex = new  AnnotationConfigApplicationContext(SpringConfig.class);

        Produit produit = context.getBean("produit", Produit.class);
        System.out.println(produit.getAge());

    }
}