package com.obilet.test;

import com.obilet.driver.BaseTest;
import com.obilet.pages.HomePage;
import com.obilet.pages.LoginPage;
import org.junit.Test;

public class ObiletTest extends BaseTest {

    @Test
    public void obiletTest(){

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage();

        homePage.home();
        homePage.lightingTextClose();
        //homePage.pageScroll();
        //loginPage.failLogin();
        loginPage.passLogin();
        //homePage.busJs();

    }

}
