package com.github.mekagem.testing.tests.group;

import au.com.bytecode.opencsv.CSVReader;
import com.github.mekagem.testing.data.group.CommandData;
import com.github.mekagem.testing.data.group.CommandDataBuilder;
import com.github.mekagem.testing.tests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.testng.Assert.assertEquals;

public class GroupTest extends BaseTest {
    private static final String TESTS_FILENAME = "resources/group_tests.csv";
    protected final Properties properties = new Properties();

    @BeforeMethod
    public void setUp() throws Exception {
        String filePath = "resources/group.properties";
        InputStream is = new FileInputStream(filePath);
        properties.load(is);
    }

    @DataProvider(name = "group_tests")
    public Iterator<Object[]> groupCommandsData() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(TESTS_FILENAME));
        List<Object[]> commandBlocks = new ArrayList<Object[]>();

        CommandDataBuilder builder = new CommandDataBuilder();

        // Reading tests
        List<String[]> data = csvReader.readAll();
        List<CommandData> command = new ArrayList<CommandData>();
        for (String[] testData : data) {
            if (testData.length <= 1) {
                if (command.size() > 0) {
                    commandBlocks.add(new Object[]{command});
                    command = new ArrayList<CommandData>();
                }
            } else {
                CommandData commandData = builder.build(testData);
                command.add(commandData);
            }
        }
        if (command.size() > 0) {
            commandBlocks.add(new Object[]{command});
        }
        return commandBlocks.iterator();
    }

    @Test(dataProvider = "group_tests")
    public void test(List<CommandData> commands) {
        final String baseURL = properties.getProperty("baseURL");
        final String login = properties.getProperty("login");
        final String pass = properties.getProperty("pass");
        final String workingGroup = properties.getProperty("group");

        GroupManager manager = new GroupManager(driver);
        manager.login(baseURL, login, pass);
        manager.enterGroup(workingGroup);
        manager.removeAll();

        for (CommandData command : commands) {
            System.err.println(command);
            command.perform(manager);
        }
    }
}
