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
    String value = null;

    public BusPage(WebDriver driver) {
        this.methods = new Methods();
        this.driver = driver;
    }

    public void findBusTickets() {

        String text = methods.getText(By.cssSelector("#main>h2"));
        System.out.println("Text : " + text);

        //Departure-City
        methods.click(By.cssSelector("#origin-input"));
        methods.sendKeys(By.cssSelector("#origin-input"), "İstanbul Avrupa");
        methods.waitBySeconds(2);
        List<WebElement> dep = driver.findElements(By.cssSelector("#origin>.results>ul>li"));
        dep.get(0).click();
        methods.waitBySeconds(1);

        //Destination-City
        methods.click(By.cssSelector("#destination-input"));
        methods.sendKeys(By.cssSelector("#destination-input"), "Manisa");
        methods.waitBySeconds(2);
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

        methods.waitBySeconds(1);
    }

    public void scroll() {

        methods.scrollWithAction(By.cssSelector("#faq-title"));
        methods.waitBySeconds(1);
        methods.scrollWithAction(By.cssSelector(".container>.logo"));
        methods.waitBySeconds(1);

    }

    public void chooseBus() {

        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector("#main>ul#list>.item.journey"));
        long itemsCount = items.stream().count();
        int rndItem = (int) itemsCount;
        rndItem = rndItem - 1;

        int rnd = random.nextInt(rndItem);
        value = items.get(rnd).getAttribute("id");
        items.get(rnd).click();
        System.out.println("idValue : " + value);
        methods.waitBySeconds(3);

    }

    public void errorControl() {
        boolean error = methods.isElementVisible(By.cssSelector(".error.active"));

        if (error == true) {
            System.out.println("Seçtiğiniz sefer satışa kapatılmış veya iptal edilmiştir.");
            driver.close();
            driver.quit();
        } else {
            System.out.println("errorControl-False");
        }
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

    public void notSingleChooseSeat() {

        Random random = new Random();
        List<WebElement> items = driver.findElements(By.cssSelector(".bus-layout>.available.active.not-single-seat"));
        long itmCount = items.stream().count();
        int itm = (int) itmCount;
        int value = random.nextInt(itm);

        if (itm != 0) {
            items.get(value).click();
            methods.waitBySeconds(1);
            methods.click(By.cssSelector("button.male"));

            boolean maleDsb = driver.getPageSource().contains(" Seçtiğiniz koltuk yalnızca kadın yolculara satılabilir. ");

            if (maleDsb == true) {
                methods.click(By.cssSelector("button.red"));
                items.get(value).click();
                methods.click(By.cssSelector(".drop-content>.female"));
                System.out.println("choose-female");
                methods.waitBySeconds(1);
            } else {
                System.out.println("choose-male");
            }

        } else {
            System.out.println("ikili koltuk bulunamadi.");
        }
    }

    public void singleChooseSeat() {

        String vl = "#main>ul#list>#" + this.value + ">.main.row>.trip.col>.type";
        System.out.println("vl : " + vl);
        System.out.println("#" + this.value + ">.main.row>.trip.col>.type");

        String txtValue = methods.getText(By.cssSelector(vl));

        System.out.println("txtValue : " + txtValue);

        switch (txtValue) {

            case "2+1":

                WebDriverWait wait = new WebDriverWait(driver, 1);
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".available.active.single-seat")));
                methods.waitBySeconds(1);

                if (element.isDisplayed()) {
                    Random random = new Random();
                    List<WebElement> items = driver.findElements(By.cssSelector(".available.active.single-seat"));
                    long itmCount = items.stream().count();
                    int itm = (int) itmCount;
                    int value = random.nextInt(itm);
                    items.get(value).click();
                    methods.waitBySeconds(1);
                    methods.click(By.cssSelector("button.male"));
                    break;
                } else {
                    System.out.println("2+1-Element-Bulunamadi");
                }

            case "2+2":
                System.out.println("tekli koltuk bulunamadi.");
                break;

            default:
                System.out.println("! HATA !");

        }

        //onayla ve devam et buton
        methods.click(By.cssSelector("span.ready"));
        methods.waitBySeconds(1);

        boolean farkliKoltukSec = driver.getPageSource().contains(" Farklı Koltuk Seç ");
        if (farkliKoltukSec == true) {
            methods.click(By.cssSelector(".header.with-icon>button.close"));
            methods.waitBySeconds(1);
            methods.click(By.cssSelector(".available.active.single-seat"));
            methods.waitBySeconds(1);
            methods.click(By.cssSelector("button.male"));
            methods.waitBySeconds(1);
            System.out.println("farkliKoltukSec-Secim");
        } else {
            System.out.println("farkliKoltukSec-SecimOlmadi");
        }

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
