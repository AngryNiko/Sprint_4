package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

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

    public OrderTest(boolean useTopButton, String name, String surname, String address, String metro, String phone, String date, String rentPeriod, boolean black, boolean grey) {
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

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void orderTest() {

        if (useTopButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, rentPeriod, black, grey);
        assertTrue("Окно успешного заказа не отображается", orderPage.isOrderSuccessDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}