package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(URL);
    }

    // Верхняя кнопка «Заказать»
    private final By orderButtonTop =
            By.className("Button_Button__ra12g");

    // Нижняя кнопка «Заказать»
    private final By orderButtonBottom =
            By.xpath(".//button[contains(@class,'Button_Middle__1CSJM')]");

    // Вопрос о важном
    public By questionHeading(int index) {
        return By.id("accordion__heading-" + index);
    }
    // Ответ о важном
    public By answerPanel(int index) {
        return By.id("accordion__panel-" + index);
    }
    // Верхняя кнопка заказа
    public void clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }
    // Нижняя кнопка заказа
    public void clickBottomOrderButton() {
        WebElement bottomButton = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", bottomButton);
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
    }
    // Открыть вопросы о важном
    public void openQuestion(int index) {
        WebElement question = wait.until(ExpectedConditions.visibilityOfElementLocated(questionHeading(index)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", question);
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
    }
    // Получить ответы о важном
    public String getAnswerText(int index) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answerPanel(index))).getText();
    }
}