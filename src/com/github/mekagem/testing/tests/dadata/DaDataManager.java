package com.github.mekagem.testing.tests.dadata;

import com.github.mekagem.testing.tests.BaseManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class DaDataManager extends BaseManager {
    public DaDataManager(WebDriver driver) {
        super(driver);
    }

    public Map<String, String> getReply(String baseURL, String input) {
        driver.get(baseURL);
        type(By.id("id_name"), input);
        click(By.xpath("//*[@id=\"process-person-form\"]/table/tbody/tr[4]/td/button"));

        WebElement table = find(By.id("processing_result_name"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        rows = rows.subList(1, rows.size());

        Map<String, String> reply = new HashMap<String, String>();
        for (WebElement row : rows) {
            if (row.findElement(By.tagName("th")).getText().equals("&nbsp;")) {
                continue;
            }
            final String key = row.findElement(By.tagName("th")).getText();
            final String value = row.findElement(By.tagName("td")).getText();
            reply.put(key, value);
        }
        return reply;
    }
}
