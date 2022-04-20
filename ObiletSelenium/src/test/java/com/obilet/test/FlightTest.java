package com.obilet.test;

import com.obilet.driver.BaseTest;
import com.obilet.pages.FlightPage;
import com.obilet.pages.HomePage;
import org.junit.Test;

public class FlightTest extends BaseTest {

    @Test
    public void flightTest(){
        HomePage homePage = new HomePage(driver);
        FlightPage flightPage = new FlightPage(driver);

        homePage.lightingTextClose();
        flightPage.findFlightTickets();
        flightPage.departureDate();
        flightPage.addPerson();
        flightPage.checkBox();
        flightPage.findFlightTicketsButton();
        flightPage.chooseAirplane();
        flightPage.passBuy();
        //flightPage.failBuy();

    }
}
