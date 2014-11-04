package com.github.mekagem.testing.data.group;

import com.github.mekagem.testing.data.AbstractTestData;
import com.github.mekagem.testing.tests.group.GroupManager;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class CommandData extends AbstractTestData {
    public final CommandType type;
    public final String[] params;

    public CommandData(CommandType type, String[] params) {
        this.type = type;
        this.params = params;
    }

    public void perform(GroupManager manager) {
        if (type == CommandType.ADD) {
            final String name = params[0];
            final String description = params[1];
            final boolean expectedResult = Boolean.valueOf(params[2]);
            assertEquals(manager.add(name, description), expectedResult, "ADD error");
        } else if (type == CommandType.REMOVE) {
            final String name = params[0];
            final boolean expectedResult = Boolean.valueOf(params[1]);
            assertEquals(manager.remove(name), expectedResult, "REMOVE error");
        } else if (type == CommandType.CHECK) {
            assertEquals(params.length % 2, 0, "There should be even params in CHECK command");
            List<DictionaryInfo> info = manager.listInfo();
            int length = params.length / 2;
            for (int index = 0; index < length; ++index) {
                final String name = params[index * 2];
                final String description = params[index * 2 + 1];
                assertEquals(info.get(index).toString(), new DictionaryInfo(name, description).toString(), "Check failed");
            }
        }
    }

    @Override
    public String toString() {
        return "CommandData: {" + "type: " + type + ", params: " + Arrays.toString(params) + "}";
    }
}
