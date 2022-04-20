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

public class FlightPage {
    Methods methods;
    WebDriver driver;

    public FlightPage(WebDriver driver) {
        this.methods = new Methods();
        this.driver = driver;
    }

    public void findFlightTickets() {

        methods.click(By.xpath("//*[@id=\"header\"]/div/ul[1]/li[2]"));
        methods.waitBySeconds(1);

        //Departure-City
        methods.click(By.cssSelector("#origin"));
        methods.sendKeys(By.cssSelector("#origin-input"), "Ankara");
        methods.waitBySeconds(1);
        List<WebElement> dep = driver.findElements(By.cssSelector("#origin>.results>ul>li"));
        dep.get(0).click();
        methods.waitBySeconds(1);

        //Destination-City
        methods.click(By.cssSelector("#destination"));
        methods.sendKeys(By.cssSelector("#destination-input"), "Antalya");
        methods.waitBySeconds(1);
        List<WebElement> des = driver.findElements(By.cssSelector("#destination>.results>ul>li"));
        des.get(0).click();
        methods.waitBySeconds(1);

        //Change-Button
        methods.click(By.cssSelector("button#swap"));
        methods.waitBySeconds(1);

    }

    public void departureDate() {

        //Random-Next
        Random randomNext = new Random();
        int rndNext = randomNext.nextInt(10);
        System.out.println("Random-Next : " + rndNext);

        methods.click(By.id("departure-input"));
        methods.waitBySeconds(1);

        for (int num = 0; num < rndNext; num++) {
            methods.click(By.cssSelector(".next>svg"));
            methods.waitBySeconds(1);
        }

        //Random-Choose-Date
        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector(".table-condensed>tbody>tr>td>button"));
        long itemsCount = items.stream().count();
        int rndItem = (int) itemsCount;
        int value = random.nextInt(rndItem);
        items.get(value).click();

        methods.waitBySeconds(2);
    }

    public void addPerson() {

        methods.click(By.cssSelector(".passengers.left>#pax-toggle"));
        List<WebElement> items = driver.findElements(By.cssSelector(".selection>button.plus"));
        long plus = items.stream().count();

        for (int num = 0; num < plus; num++) {
            items.get(num).click();
            methods.waitBySeconds(1);
        }

    }

    public void checkBox() {
        //Aktarmasız
        methods.click(By.cssSelector("#direct>div.box"));
        methods.waitBySeconds(2);
    }

    public void findFlightTicketsButton() {
        methods.click(By.cssSelector("#search>#search-button"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void chooseAirplane() {

        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector("#outbound-journeys>li"));
        long itm = items.stream().count();
        int itmCount = (int) itm;
        int count = random.nextInt(itmCount);
        methods.waitBySeconds(1);
        items.get(count).click();

        boolean airClass = methods.isElementVisible(By.cssSelector(".flight>.flys"));
        if (airClass == true) {
            System.out.println("airClass == true");
            methods.click(By.cssSelector("div.fly-container"));
        } else {
            System.out.println("airClass == false");
        }
        methods.waitBySeconds(2);

    }

    public void failBuy() {

        methods.sendKeys(By.cssSelector(".input-group>#email"), "abc@gmail.com");
        methods.sendKeys(By.cssSelector(".input-group>#phone"), "5555555555");

        List<WebElement> nameList = driver.findElements(By.cssSelector(".first-name.name.group>.input-group>input"));
        List<WebElement> surnameList = driver.findElements(By.cssSelector(".last-name.name.group>.input-group>input"));
        List<WebElement> birthDayList = driver.findElements(By.cssSelector("select.day"));
        List<WebElement> birthMonthList = driver.findElements(By.cssSelector("select.month"));
        List<WebElement> birthYearList = driver.findElements(By.cssSelector("select.year"));
        List<WebElement> tcNumberList = driver.findElements(By.cssSelector(".gov-id.gov-id-group.group>.input-group>input"));
        List<WebElement> list = driver.findElements(By.cssSelector("#passengers-info>.passengers-info-content>.passenger"));

        long lCount = list.stream().count();
        int listCount = (int) lCount;

        String[] name = {"Halil", "Hasan", "Emre", "Eray", "Ahmet", "Mehmet"};
        String[] surname = {"Denli", "Denli", "Denli", "Denli", "Salıker", "Yardımcı"};
        String[] birthDay = {"16", "1", "8", "20", "15", "23"};
        String[] birthMonth = {"1", "1", "11", "3", "5", "8"};
        String[] birthYear = {"1965", "1991", "2021", "2003", "1960", "1980"};
        String[] tcNumber = {"25252525254", "25252525254", "25252525254", "25252525254", "25252525254", "25252525254"};
        String[] gender = {"#male-1", "#male-2", "#male-3", "#male-4", "#male-5", "#male-6"};

        for (int i = 0; i < listCount; i++) {
            nameList.get(i).sendKeys(name[i]);
            surnameList.get(i).sendKeys(surname[i]);
            birthDayList.get(i).sendKeys(birthDay[i]);
            birthMonthList.get(i).sendKeys(birthMonth[i]);
            birthYearList.get(i).sendKeys(birthYear[i]);
            tcNumberList.get(i).sendKeys(tcNumber[i]);
            methods.click(By.cssSelector(gender[i]));
            methods.waitBySeconds(1);
        }

        methods.click(By.cssSelector("#journey-prices>#pay-info-content"));
        methods.waitBySeconds(2);

        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "4543");
        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "0123");
        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "4567");
        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "8900");

        methods.sendKeys(By.cssSelector(".input-group>input#card-expiration"), "01");
        methods.sendKeys(By.cssSelector(".input-group>input#card-expiration"), "2023");
        methods.sendKeys(By.cssSelector(".input-group>#card-csc"), "365");

        methods.waitBySeconds(1);

        methods.click(By.cssSelector("button#pay"));
        System.out.println("failBuy-Basarili");
        methods.waitBySeconds(2);
    }

    public void passBuy() {

        methods.sendKeys(By.cssSelector(".input-group>#email"), "abc@gmail.com");
        methods.sendKeys(By.cssSelector(".input-group>#phone"), "5555555555");

        List<WebElement> nameList = driver.findElements(By.cssSelector(".first-name.name.group>.input-group>input"));
        List<WebElement> surnameList = driver.findElements(By.cssSelector(".last-name.name.group>.input-group>input"));
        List<WebElement> birthDayList = driver.findElements(By.cssSelector("select.day"));
        List<WebElement> birthMonthList = driver.findElements(By.cssSelector("select.month"));
        List<WebElement> birthYearList = driver.findElements(By.cssSelector("select.year"));
        List<WebElement> tcNumberList = driver.findElements(By.cssSelector(".gov-id.gov-id-group.group>.input-group>input"));
        List<WebElement> list = driver.findElements(By.cssSelector("#passengers-info>.passengers-info-content>.passenger"));

        long lCount = list.stream().count();
        int listCount = (int) lCount;

        String[] name = {"Halil", "Hasan", "Emre", "Eray", "Ahmet", "Mehmet"};
        String[] surname = {"Denli", "Denli", "Denli", "Denli", "Salıker", "Yardımcı"};
        String[] birthDay = {"16", "1", "8", "20", "15", "23"};
        String[] birthMonth = {"1", "1", "11", "3", "5", "8"};
        String[] birthYear = {"1965", "1991", "2021", "2003", "1960", "1980"};
        String[] tcNumber = {"25252525254", "25252525254", "25252525254", "25252525254", "25252525254", "25252525254"};
        String[] gender = {"#male-1", "#male-2", "#male-3", "#male-4", "#male-5", "#male-6"};

        for (int i = 0; i < listCount; i++) {
            nameList.get(i).sendKeys(name[i]);
            surnameList.get(i).sendKeys(surname[i]);
            birthDayList.get(i).sendKeys(birthDay[i]);
            birthMonthList.get(i).sendKeys(birthMonth[i]);
            birthYearList.get(i).sendKeys(birthYear[i]);
            tcNumberList.get(i).sendKeys(tcNumber[i]);
            methods.click(By.cssSelector(gender[i]));
            methods.waitBySeconds(1);
        }

        methods.click(By.cssSelector("#journey-prices>#pay-info-content"));
        methods.waitBySeconds(1);

        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "4543");
        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "0123");
        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "4567");
        methods.sendKeys(By.cssSelector(".input-group>input#card-number"), "8900");

        methods.sendKeys(By.cssSelector(".input-group>input#card-expiration"), "01");
        methods.sendKeys(By.cssSelector(".input-group>input#card-expiration"), "2023");
        methods.sendKeys(By.cssSelector(".input-group>#card-csc"), "365");

        methods.waitBySeconds(1);

        methods.click(By.cssSelector("button#pay"));
        System.out.println("passBuy-Basarili");
        methods.waitBySeconds(1);

    }
}
