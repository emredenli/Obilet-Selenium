package com.obilet.pages;

import com.obilet.methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BusPage {

    Methods methods;
    WebDriver driver;

    public BusPage(WebDriver driver) {
        this.methods = new Methods();
        this.driver = driver;
    }

    public void findBusTickets() {

        //Departure-City
        methods.click(By.cssSelector("#origin-input"));
        methods.sendKeys(By.cssSelector("#origin-input"), "İstanbul Avrupa");
        methods.waitBySeconds(1);
        List<WebElement> dep = driver.findElements(By.cssSelector("#origin>.results>ul>li"));
        dep.get(0).click();
        methods.waitBySeconds(1);

        //Destination-City
        methods.click(By.cssSelector("#destination-input"));
        methods.sendKeys(By.cssSelector("#destination-input"), "Manisa");
        methods.waitBySeconds(1);
        List<WebElement> des = driver.findElements(By.cssSelector("#destination>.results>ul>li"));
        des.get(0).click();
        methods.waitBySeconds(1);

        //Change-Button
        methods.click(By.cssSelector(".origin.location.group>#swap"));
        methods.waitBySeconds(1);

        //Select-Tomorrow
        methods.click(By.cssSelector("#tomorrow"));
        methods.waitBySeconds(1);

        //Select-Today
        methods.click(By.cssSelector("#today"));
        methods.waitBySeconds(1);

        //Find-Bus-Tickets-Button
        methods.click(By.cssSelector("#search-button>.circle"));

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);

    }

    public void chooseDate() {

        methods.click(By.cssSelector("#departure-input"));
        methods.waitBySeconds(1);

        //Random-Next
        Random randomNext = new Random();
        int rndNext = randomNext.nextInt(10);
        System.out.println("Random-Next : " + rndNext);

        for (int num = 0; num < rndNext; num++) {
            methods.click(By.cssSelector("th.next"));
            methods.waitBySeconds(1);
        }

        //Random-Choose-Date
        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector(".month>tbody>tr>td"));
        long itemsCount = items.stream().count();
        int rndItem = (int) itemsCount;

        items.get(random.nextInt(rndItem)).click();

        methods.waitBySeconds(2);
    }

    public void scroll() {

        methods.scrollWithAction(By.cssSelector("#faq-title"));
        methods.waitBySeconds(2);

        methods.scrollWithAction(By.cssSelector(".container>.logo"));
        methods.waitBySeconds(2);

    }

    public void chooseBus() {

        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector("#main>ul#list>.item.journey"));
        long itemsCount = items.stream().count();
        int rndItem = (int) itemsCount;
        rndItem = rndItem - 1;
        items.get(random.nextInt(rndItem)).click();
        methods.waitBySeconds(3);

    }

    public void chooseSeat() {


        int value1 = 0, value2 = 0;

        Random rnd = new Random();
        int num = rnd.nextInt(4);
        int rndItem = 0, rItem = 0;

        while (num == 0) {
            num = rnd.nextInt(4);
        }

        for (int i = 0; i < num; i++) {
            Random random = new Random();
            List<WebElement> items = driver.findElements(By.cssSelector(".bus-layout>.available.active.not-single-seat"));
            List<WebElement> itm = driver.findElements(By.cssSelector(".available.active.single-seat.ins-init-condition-tracking"));
            long itemsCount = items.stream().count();
            rndItem = (int) itemsCount;

            long itmCount = itm.stream().count();
            rItem = (int) itmCount;

            if (i % 2 == 0 && rndItem != 0) {
                value1 = random.nextInt(rndItem);
                items.get(value1).click();
                methods.waitBySeconds(2);
                methods.click(By.cssSelector("button.male"));
                methods.waitBySeconds(1);

                /*//ikili-koltuklarda-yanlis-cinsiyet-secimi(failChoose)
                boolean failChoose = methods.isElementVisible(By.cssSelector(".ob-modal.error.pop.open"));
                if (failChoose == true) {
                    methods.click(By.cssSelector("button.red"));
                    methods.waitBySeconds(2);
                    items.get(value1).click();
                    methods.waitBySeconds(2);
                    methods.click(By.cssSelector("button.female "));
                    methods.waitBySeconds(1);
                }*/

            } else if (rItem != 0) {
                value2 = random.nextInt(rItem);
                itm.get(value2).click();
                methods.waitBySeconds(2);
                methods.click(By.cssSelector("button.male"));
                methods.waitBySeconds(1);
            } else {
                System.out.println("Otobüs dolu.");
            }

        }
        methods.click(By.cssSelector("span.ready"));
        methods.waitBySeconds(1);


    }

    public void failBuy() {

        //contact-information
        methods.sendKeys(By.id("email"), "abc@gmail.com");
        methods.sendKeys(By.id("phone"), "5555555555");

        List<WebElement> items = driver.findElements(By.cssSelector(".name.group>.input-group>input"));
        List<WebElement> itm = driver.findElements(By.cssSelector(".gov-id.group>.input-group>input"));
        long itemsCount = items.stream().count();
        int nameSurname = (int) itemsCount;

        String nSurname[] = {"Hasan Denli", "Emre Denli", "Eray Denli", "Halil Denli"};
        String tcNumber[] = {"25252525254", "25252525254", "25252525254", "25252525254"};

        for (int i = 0; i < nameSurname; i++) {
            //passenger-information
            items.get(i).sendKeys(nSurname[i]);
            methods.waitBySeconds(1);
            itm.get(i).sendKeys(tcNumber[i]);
            methods.waitBySeconds(1);
        }

        //payment-information
        methods.sendKeys(By.id("card-number"), "4543");
        methods.sendKeys(By.id("card-number"), "0123");
        methods.sendKeys(By.id("card-number"), "4567");
        methods.sendKeys(By.id("card-number"), "8900");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "01");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "2023");
        methods.sendKeys(By.cssSelector(".input-group>#card-csc"), "365");
        methods.waitBySeconds(2);

        //pay-button
        methods.click(By.cssSelector(".center>#amount"));
        methods.waitBySeconds(2);

    }

    public void passBuy() {

        //contact-information
        methods.sendKeys(By.id("email"), "abc@gmail.com");
        methods.sendKeys(By.id("phone"), "5555555555");

        List<WebElement> items = driver.findElements(By.cssSelector(".name.group>.input-group>input"));
        List<WebElement> itm = driver.findElements(By.cssSelector(".gov-id.group>.input-group>input"));
        long itemsCount = items.stream().count();
        int nameSurname = (int) itemsCount;

        String nSurname[] = {"Hasan Denli", "Emre Denli", "Eray Denli", "Halil Denli"};
        String tcNumber[] = {"25252525254", "25252525254", "25252525254", "25252525254"};

        for (int i = 0; i < nameSurname; i++) {
            //passenger-information
            items.get(i).sendKeys(nSurname[i]);
            methods.waitBySeconds(1);
            itm.get(i).sendKeys(tcNumber[i]);
            methods.waitBySeconds(1);
        }

        //payment-information
        methods.sendKeys(By.id("card-number"), "4543");
        methods.sendKeys(By.id("card-number"), "0123");
        methods.sendKeys(By.id("card-number"), "4567");
        methods.sendKeys(By.id("card-number"), "8900");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "01");
        methods.sendKeys(By.cssSelector(".input-group>#card-expiration"), "2023");
        methods.sendKeys(By.cssSelector(".input-group>#card-csc"), "365");
        methods.waitBySeconds(2);

        //pay-button
        methods.click(By.cssSelector(".center>#amount"));
        methods.waitBySeconds(2);

    }

}
