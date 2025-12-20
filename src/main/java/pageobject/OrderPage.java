package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Первая форма заказа
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Вторая форма заказа
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentDropdown = By.className("Dropdown-placeholder");
    private final By orderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");
    private final By confirmYes = By.xpath(".//button[text()='Да']");
    // Заполнение первой формы
    public void fillFirstForm(String name, String surname, String addr, String station, String phoneNum) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(name);
        driver.findElement(lastName).sendKeys(surname);
        driver.findElement(address).sendKeys(addr);

        driver.findElement(metro).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//div[text()='" + station + "']"))).click();

        driver.findElement(phone).sendKeys(phoneNum);
        driver.findElement(nextButton).click();
    }
    // Заполнение второй формы
    public void fillSecondForm(
            String date,
            String rentPeriod,
            boolean black,
            boolean grey
    ) {
        wait.until(ExpectedConditions.elementToBeClickable(dateField)).click();
        driver.findElement(By.xpath(".//div[text()='" + date + "']")).click();

        driver.findElement(rentDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//div[text()='" + rentPeriod + "']"))).click();

        if (black) driver.findElement(By.id("black")).click();
        if (grey) driver.findElement(By.id("grey")).click();

        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYes)).click();
    }
}