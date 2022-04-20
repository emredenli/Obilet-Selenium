package com.obilet.pages;

import com.obilet.methods.Methods;
import org.openqa.selenium.By;

public class LoginPage {

    Methods methods;

    public LoginPage() {
        methods = new Methods();
    }

    //email : abc@gmail.com
    //password : abc

    public void failLogin() {

        methods.click(By.cssSelector(".menu.right>.login"));
        methods.waitBySeconds(2);
        methods.sendKeys(By.name("username"), "abc@gmail.com");
        methods.sendKeys(By.name("password"), "abc");
        methods.waitBySeconds(2);
        methods.click(By.cssSelector(".remember-group>.remember"));
        methods.waitBySeconds(2);
        methods.click(By.cssSelector(".login.login-button"));
        methods.waitBySeconds(3);

        /*methods.click(By.cssSelector(".header.with-icon>.close"));
        methods.waitBySeconds(3);*/

    }

    public void passLogin() {

        methods.click(By.cssSelector(".menu.right>.login"));
        methods.waitBySeconds(2);
        methods.sendKeys(By.name("username"), "abc@gmail.com");
        methods.sendKeys(By.name("password"), "abc");
        methods.waitBySeconds(2);
        methods.click(By.cssSelector(".remember-group>.remember"));
        methods.waitBySeconds(2);
        methods.click(By.cssSelector(".login.login-button"));
        methods.waitBySeconds(3);

    }

}
