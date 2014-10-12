package mipt.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by alexgechis on 01.10.14.
 */

public class WikipediaLoginTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> loginData() throws IOException {
        return wrapLoginForDataProvider(LoginDataLoader.loadDataFromCsvFile(new File("logintests.csv"))).iterator();
    }

    @Test (dataProvider = "loginData")
    public void testLogin(LoginData loginData) throws Exception {
        assertEquals(doLogin(loginData), loginData.getExpectedResult(), "test login failed: ");
    }

    private String doLogin(LoginData loginData) {
        driver.get("http://ru.wikipedia.org/wiki/Заглавная_страница");
        driver.findElement(By.linkText("Войти")).click();
        driver.findElement(By.id("wpName1")).sendKeys(loginData.getLogin());
        driver.findElement(By.id("wpPassword1")).sendKeys(loginData.getPass());
        driver.findElement(By.id("wpLoginAttempt")).click();

        try {
            return driver.findElement(By.cssSelector("strong")).getText();
        } catch (Exception e) {
            WebElement  element =  driver.findElement(By.id("wpLoginAttempt"));
            if (element == null) {
               return "SUCCESS";
            } else {
               return null;
            }

        }
    }

}
