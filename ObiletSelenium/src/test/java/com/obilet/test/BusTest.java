package com.obilet.test;

import com.obilet.driver.BaseTest;
import com.obilet.pages.BusPage;
import com.obilet.pages.HomePage;
import org.junit.Test;

public class BusTest extends BaseTest {

    @Test
    public void busTest(){
        HomePage homePage = new HomePage(driver);
        BusPage busPage = new BusPage(driver);


        homePage.lightingTextClose();
        busPage.scroll();
        busPage.chooseDate();
        busPage.findBusTickets();
        busPage.chooseBus();
        busPage.chooseSeat();
        busPage.failBuy();
        //busPage.passBuy();
    }
}
