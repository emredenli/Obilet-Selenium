package com.obilet.pages;

import com.obilet.methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FerryPage {
    Methods methods;
    WebDriver driver;

    public FerryPage(WebDriver driver) {
        this.methods = new Methods();
        this.driver = driver;
    }

    public void findFerryTickets() {

        methods.click(By.cssSelector(".menu.left>li.sea"));
        methods.waitBySeconds(2);

        //Departure
        methods.click(By.cssSelector("#origin"));
        methods.sendKeys(By.cssSelector("#origin-input"), "Mudanya");
        methods.waitBySeconds(1);
        List<WebElement> dep = driver.findElements(By.cssSelector("#origin>.results>ul>li"));
        dep.get(0).click();
        methods.waitBySeconds(1);

        //Destination
        methods.click(By.cssSelector("#destination"));
        methods.sendKeys(By.cssSelector("#destination-input"), "Eminönü");
        methods.waitBySeconds(1);
        List<WebElement> des = driver.findElements(By.cssSelector("#destination>.results>ul>li"));
        des.get(0).click();
        methods.waitBySeconds(1);

        //Change-Button
        methods.click(By.cssSelector("button#swap"));
        methods.waitBySeconds(1);

    }

    public void departureDate() {

        methods.click(By.id("departure-input"));
        methods.waitBySeconds(1);

        /*//Random-Next
        Random randomNext = new Random();
        int rndNext = randomNext.nextInt(10);
        System.out.println("Random-Next : " + rndNext);

        methods.click(By.cssSelector("#departure-input"));
        methods.waitBySeconds(1);

        for (int num = 0; num < rndNext; num++) {
            methods.click(By.cssSelector(".next>svg"));
            methods.waitBySeconds(1);
        }*/

        //Random-Choose-Date
        Calendar calendar = Calendar.getInstance();
        int cld = calendar.get(Calendar.DATE);

        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector(".table-condensed>tbody>tr>td>button"));
        long itemsCount = items.stream().count();
        int rndItem = (int) itemsCount;
        int value = random.nextInt(rndItem);

        if (cld > value) {
            items.get(cld).click();
        } else if (value > (cld + 10)) {
            items.get(cld + 10).click();
        } else {
            items.get(value).click();
        }

        methods.waitBySeconds(2);
    }

    public void newChooseDate() {
        Calendar calendar = Calendar.getInstance();
        int cld = calendar.get(Calendar.DATE);
        methods.click(By.id("departure-input"));
        methods.waitBySeconds(1);
        methods.click(By.cssSelector("tr>th.prev"));
        methods.waitBySeconds(1);
        List<WebElement> items = driver.findElements(By.cssSelector(".table-condensed>tbody>tr>td>button"));
        items.get(cld).click();
        methods.waitBySeconds(2);
    }

    public void addPerson() {

        methods.click(By.cssSelector(".passengers.left>#pax-toggle"));
        List<WebElement> items = driver.findElements(By.cssSelector(".selection>.plus"));
        long plus = items.stream().count();

        for (int num = 0; num < plus; num++) {
            items.get(num).click();
            methods.waitBySeconds(1);
        }

    }

    public void findFlightTicketsButton() {
        methods.click(By.cssSelector("#search>#search-button>.circle"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);

        /*boolean changeDate = methods.isElementVisible(By.cssSelector(".ob-modal.error.pop.open"));
        if (changeDate == true) {
            System.out.println("changeDate == true");
            methods.click(By.cssSelector("button.default"));
            methods.waitBySeconds(2);
            newChooseDate();
        } else {
            System.out.println("changeDate == false");
        }*/

        List<WebElement> list = driver.findElements(By.cssSelector("#outbound-journeys>li"));
        list.get(1).click();
        methods.waitBySeconds(1);

        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector(".available.active"));
        long itm = items.stream().count();
        int itmCount = (int) itm;
        int value = random.nextInt(itmCount);
        items.get(value).click();
        methods.waitBySeconds(1);

        methods.click(By.cssSelector("span.ready"));
        methods.waitBySeconds(2);

    }

    public void failBuy(){
        //contact-information
        methods.sendKeys(By.id("email"), "abc@gmail.com");
        methods.sendKeys(By.id("phone"), "5555555555");

        methods.sendKeys(By.cssSelector(".name.group>.input-group>input"), "Emre Denli");
        methods.sendKeys(By.cssSelector(".gov-id.group>.input-group>input"),"25252525254");
        methods.sendKeys(By.cssSelector("select.day"),"8");
        methods.sendKeys(By.cssSelector("select.month"),"11");
        methods.sendKeys(By.cssSelector("select.year"),"1990");

        //payment-information
        methods.sendKeys(By.id("card-number"), "4543");
        methods.sendKeys(By.id("card-number"), "0123");
        methods.sendKeys(By.id("card-number"), "4567");
        methods.sendKeys(By.id("card-number"), "8900");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "01");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "2023");
        methods.sendKeys(By.cssSelector(".input-group>#card-csc"), "365");
        methods.waitBySeconds(2);

        methods.click(By.id("pay"));
        System.out.println("failBuy-Basarili");
        methods.waitBySeconds(2);
    }

    public void passBuy(){
        //contact-information
        methods.sendKeys(By.id("email"), "abc@gmail.com");
        methods.sendKeys(By.id("phone"), "5555555555");

        methods.sendKeys(By.cssSelector(".name.group>.input-group>input"), "Emre Denli");
        methods.sendKeys(By.cssSelector(".gov-id.group>.input-group>input"),"25252525254");
        methods.sendKeys(By.cssSelector("select.day"),"8");
        methods.sendKeys(By.cssSelector("select.month"),"11");
        methods.sendKeys(By.cssSelector("select.year"),"1990");

        //payment-information
        methods.sendKeys(By.id("card-number"), "4543");
        methods.sendKeys(By.id("card-number"), "0123");
        methods.sendKeys(By.id("card-number"), "4567");
        methods.sendKeys(By.id("card-number"), "8900");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "01");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "2023");
        methods.sendKeys(By.cssSelector(".input-group>#card-csc"), "365");
        methods.waitBySeconds(2);

        methods.click(By.cssSelector("div#amount"));
        System.out.println("passBuy-Basarili");
        methods.waitBySeconds(2);
    }
}
