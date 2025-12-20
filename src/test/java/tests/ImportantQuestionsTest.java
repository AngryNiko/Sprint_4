package tests;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;

import static org.junit.Assert.assertFalse;

public class ImportantQuestionsTest {

    private WebDriver driver;

    @Test
    public void checkQuestionsAndAnswersChrome() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);

        for (int i = 0; i < 8; i++) {
            mainPage.openQuestion(i);
            Thread.sleep(2000);
            String answerText = mainPage.getAnswerText(i);
            assertFalse(answerText.isEmpty());
        }
    }

    @Test
    public void checkQuestionsAndAnswersFirefox() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);

        for (int i = 0; i < 8; i++) {
            mainPage.openQuestion(i);
            Thread.sleep(2000);
            String answerText = mainPage.getAnswerText(i);
            assertFalse(answerText.isEmpty());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}