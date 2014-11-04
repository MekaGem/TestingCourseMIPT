package com.github.mekagem.testing.tests.wikipedia;

import au.com.bytecode.opencsv.CSVReader;
import com.github.mekagem.testing.data.wikipedia.RegistrationTestData;
import com.github.mekagem.testing.data.wikipedia.RegistrationTestDataBuilder;
import com.github.mekagem.testing.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class WikipediaRegistrationTest extends BaseTest {
    private static final String TESTS_FILENAME = "registration_tests.csv";

    @DataProvider(name = "registration_tests")
    public Iterator<Object[]> registrationData() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(TESTS_FILENAME));
        List<Object[]> registrationDataList = new ArrayList<Object[]>();

        // Reading header
        String[] header = csvReader.readNext();
        System.err.println(header);
        // Just skip it

        RegistrationTestDataBuilder builder = new RegistrationTestDataBuilder();

        // Reading tests
        String[] testData;
        while ((testData = csvReader.readNext()) != null) {
            System.err.println(testData);
            registrationDataList.add(new Object[]{builder.build(testData)});
        }
        return registrationDataList.iterator();
    }

    @Test(dataProvider = "registration_tests")
    public void testRegistration(RegistrationTestData registrationData) {
        String reply = tryRegister(registrationData);
        String prefix = reply.equals("UNKNOWN ERROR") ? "" : "Ошибка создания учётной записи\n";
        assertEquals(tryRegister(registrationData), prefix + registrationData.getExpectedResult(), "test register failed: ");
    }

    private String tryRegister(RegistrationTestData registrationData) {
        driver.get("http://ru.wikipedia.org/wiki/Заглавная_страница");
        driver.findElement(By.linkText("Создать учётную запись")).click();
        driver.findElement(By.id("wpName2")).sendKeys(registrationData.getLogin());
        driver.findElement(By.id("wpPassword2")).sendKeys(registrationData.getPassword());
        driver.findElement(By.id("wpRetype")).sendKeys(registrationData.getPasswordConfirmation());
        driver.findElement(By.id("wpEmail")).sendKeys(registrationData.getEmail());
        driver.findElement(By.id("wpCreateaccount")).click();

        try {
            return driver.findElement(By.className("errorbox")).getText();
        } catch (Exception e) {
            WebElement element = driver.findElement(By.id("wpCreateaccount"));
            if (element == null) {
                return "SUCCESS";
            } else {
                return "UNKNOWN ERROR";
            }
        }
    }
}
