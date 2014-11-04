package com.github.mekagem.testing.tests.group;

import com.github.mekagem.testing.data.group.DictionaryInfo;
import com.github.mekagem.testing.tests.BaseManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GroupManager extends BaseManager {
    public GroupManager(WebDriver driver) {
        super(driver);
    }

    public void login(String baseURL, String login, String pass) {
        driver.get(baseURL);
        try {
            waitFor(By.id("j_username"));
            type(By.id("j_username"), login);
            type(By.id("j_password"), pass);
            click(By.className("btn-login"));
        } catch (Exception e) {

        }
    }

    public void enterGroup(String groupName) {
        click(By.linkText(groupName));
    }

    public boolean add(String name, String description) {
        click(By.xpath("//*[@id=\"content-wrapper\"]/div/header/h1/button[2]"));
        type(By.id("dictionaries-add-name"), name);
        type(By.id("dictionaries-add-description"), description);
        click(By.id("dictionaries-add-button-submit"));

        justWait(1000);

        boolean ok = false;//check(By.xpath("//*[@id=\"content-wrapper\"]/div/header/nav/a[2]"));
        if (!check(By.xpath("//*[@id=\"content-wrapper\"]/div/div/div/div[2]/form/div[2]/div/div"))) {
            ok = true;
        } else {
            WebElement message = driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/div/div/div/div[2]/form/div[2]/div/div"));
            if (message.getText().equals("")) {
                ok = true;
            }
        }
        if (ok) {
            click(By.xpath("//*[@id=\"content-wrapper\"]/div/header/nav/a[2]"));
            justWait(1000);
        } else {
            click(By.xpath("//*[@id=\"dictionaries-add-button-cancel\"]"));
            justWait(1000);
        }
        return ok;
    }

    public List<WebElement> list() {
        waitFor(By.xpath("//*[@id=\"content-wrapper\"]/div/section[1]/table"));
        WebElement table = driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/div/section[1]/table"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        rows = rows.subList(1, rows.size());

        if (rows.size() == 1 && rows.get(0).getText().equals("Справочников не найдено")) {
            rows.clear();
        }

        return rows;
    }

    public List<DictionaryInfo> listInfo() {
        List<DictionaryInfo> dictionaryInfo = new ArrayList<DictionaryInfo>();
        for (WebElement element : list()) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            final String name = columns.get(1).findElement(By.tagName("a")).getText();
            final String description = columns.get(2).findElement(By.tagName("span")).getText();
            dictionaryInfo.add(new DictionaryInfo(name, description));
        }
        return dictionaryInfo;
    }

    private void remove(WebElement element) {
        WebElement delete = element.findElements(By.tagName("td")).get(0);
        new Actions(driver).moveToElement(delete).perform();
        justWait(1000);
        delete = delete.findElement(By.tagName("i"));
        delete.click();

        click(By.xpath("//*[@id=\"content-wrapper\"]/div/div/div/div[3]/button[2]"));
        justWait(2000);
    }

    public boolean remove(int index) {
        List<WebElement> rows = list();
        if (index < rows.size()) {
            remove(rows.get(index));
            return true;
        }
        return false;
    }

    public boolean remove(String name) {
        List<WebElement> rows = list();
        for (WebElement element : rows) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            if (columns.get(1).findElement(By.tagName("a")).getText().equals(name)) {
                remove(element);
                return true;
            }
        }
        return false;
    }

    public void removeAll() {
        int groupSize = list().size();
        for (int index = 0; index < groupSize; ++index) {
            remove(0);
        }
    }
}
