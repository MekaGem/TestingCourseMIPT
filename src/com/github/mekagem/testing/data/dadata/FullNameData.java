package com.github.mekagem.testing.data.dadata;

import com.github.mekagem.testing.data.AbstractTestData;

public class FullNameData extends AbstractTestData {
    private String input;
    private String expectedFirstName;
    private String expectedSecondName;
    private String expectedPatronymic;
    private String expectedGender;
    private String comment;

    public FullNameData() {
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedFirstName() {
        return expectedFirstName;
    }

    public void setExpectedFirstName(String expectedFirstName) {
        this.expectedFirstName = expectedFirstName;
    }

    public String getExpectedSecondName() {
        return expectedSecondName;
    }

    public void setExpectedSecondName(String expectedSecondName) {
        this.expectedSecondName = expectedSecondName;
    }

    public String getExpectedPatronymic() {
        return expectedPatronymic;
    }

    public void setExpectedPatronymic(String expectedPatronymic) {
        this.expectedPatronymic = expectedPatronymic;
    }

    public String getExpectedGender() {
        return expectedGender;
    }

    public void setExpectedGender(String expectedGender) {
        this.expectedGender = expectedGender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "FullNameData: {" + "input: " + input +
                ", expectedFirstName: " + expectedFirstName +
                ", expectedSecondName: " + expectedSecondName +
                ", expectedPatronymic: " + expectedPatronymic +
                ", expectedGender: " + expectedGender +
                ", comment: " + comment + "}";
    }
}
