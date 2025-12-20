package tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final boolean useTopButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentPeriod;
    private final boolean black;
    private final boolean grey;

    public OrderTest(boolean useTopButton, String name, String surname, String address,String metro, String phone, String date, String rentPeriod, boolean black, boolean grey) {
        this.useTopButton = useTopButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentPeriod = rentPeriod;
        this.black = black;
        this.grey = grey;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {true, "Иван", "Иванов", "Московская, 12", "Черкизовская", "89999999999", "22", "двое суток", true, false},
                {false, "Анна", "Петрова", "Какаято, 8А", "Сокольники", "88888888888", "23", "трое суток", false, true}
        });
    }

    @Test
    public void orderTestChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        if (useTopButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, rentPeriod, black, grey);
    }

    @Test
    public void orderTestFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        if (useTopButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, rentPeriod, black, grey);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}