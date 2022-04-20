package com.obilet.test;

import com.obilet.driver.BaseTest;
import com.obilet.pages.HomePage;
import com.obilet.pages.LoginPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest extends BaseTest {

    @Test
    public void failLoginTest(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage();

        homePage.lightingTextClose();
        loginPage.failLogin();

    }

    @Test
    public void passLoginTest(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage();

        homePage.lightingTextClose();
        loginPage.passLogin();

    }



}
