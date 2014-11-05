package com.github.mekagem.testing.tests.dadata;

import au.com.bytecode.opencsv.CSVReader;
import com.github.mekagem.testing.data.AbstractTestDataBuilder;
import com.github.mekagem.testing.data.dadata.FullNameData;
import com.github.mekagem.testing.data.dadata.FullNameDataBuilder;
import com.github.mekagem.testing.tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.testng.Assert.assertEquals;

public class DaDataTest extends BaseTest {
    private static final String TESTS_FILENAME = "resources/dadata_tests.csv";
    private static final String PROPERTIES_FILE_PATH = "resources/dadata.properties";
    private final Properties properties = new Properties();

    @BeforeMethod
    public void setUp() throws Exception {
        InputStream is = new FileInputStream(PROPERTIES_FILE_PATH);
        properties.load(is);
    }

    @DataProvider(name = "dadata_tests")
    public Iterator<Object[]> fullNameData() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(TESTS_FILENAME));
        List<Object[]> fullNameDataList = new ArrayList<Object[]>();

        // Reading header
        String[] header = csvReader.readNext();
        System.err.println(Arrays.toString(header));
        // Just skip it

        AbstractTestDataBuilder<FullNameData> builder = new FullNameDataBuilder();

        // Reading tests
        String[] testData;
        while ((testData = csvReader.readNext()) != null) {
            fullNameDataList.add(new Object[]{builder.build(testData)});
        }
        return fullNameDataList.iterator();
    }

    @Test(dataProvider = "dadata_tests")
    public void test(FullNameData fullNameData) {
        final String baseURL = properties.getProperty("baseURL");
        DaDataManager manager = new DaDataManager(driver);
        Map<String, String> reply = manager.getReply(baseURL, fullNameData.getInput());

        assertEquals(reply.get("Имя"), fullNameData.getExpectedFirstName());
        assertEquals(reply.get("Фамилия"), fullNameData.getExpectedSecondName());
        assertEquals(reply.get("Отчество"), fullNameData.getExpectedPatronymic());
        assertEquals(reply.get("Пол"), fullNameData.getExpectedGender());
    }
}
