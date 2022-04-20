package com.obilet.pages;

import com.obilet.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    Methods methods;
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.methods = new Methods();
        this.driver = driver;
    }

    public void home() {

        methods.click(By.cssSelector(".container>.logo"));
        methods.waitBySeconds(3);

    }

    public void lightingTextClose(){

        methods.click(By.cssSelector(".close>.icon"));
        methods.waitBySeconds(2);

    }

    public void pageScroll(){
        methods.scrollWithAction(By.cssSelector("#bus-partner-slider-container"));
        methods.waitBySeconds(2);
        methods.scrollWithAction(By.cssSelector(".container>.logo"));
        methods.waitBySeconds(2);
    }

}
