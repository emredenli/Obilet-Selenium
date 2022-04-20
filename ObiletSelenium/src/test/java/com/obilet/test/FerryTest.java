package com.obilet.test;

import com.obilet.driver.BaseTest;
import com.obilet.pages.FerryPage;
import com.obilet.pages.HomePage;
import org.junit.Test;

public class FerryTest extends BaseTest {

    @Test
    public void ferryTest(){
        HomePage homePage = new HomePage(driver);
        FerryPage ferryPage = new FerryPage(driver);

        homePage.lightingTextClose();
        ferryPage.findFerryTickets();
        ferryPage.departureDate();
        //ferryPage.addPerson();
        ferryPage.findFlightTicketsButton();
        ferryPage.failBuy();
        //ferryPage.passBuy();

    }
}
