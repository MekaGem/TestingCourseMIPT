package com.github.mekagem.testing.data.dadata;

import com.github.mekagem.testing.data.AbstractTestDataBuilder;

public class FullNameDataBuilder extends AbstractTestDataBuilder<FullNameData> {
    @Override
    public FullNameData build(String[] params) {
        if (params.length != 6) {
            throw new IllegalArgumentException("You should pass exactly 6 parameters");
        }
        FullNameData data = new FullNameData();
        data.setInput(params[0]);
        data.setExpectedFirstName(params[1].equals("-") ? null : params[1]);
        data.setExpectedSecondName(params[2].equals("-") ? null : params[2]);
        data.setExpectedPatronymic(params[3].equals("-") ? null : params[3]);
        data.setExpectedGender(params[4].equals("-") ? null : params[4]);
        data.setComment(params[5]);
        return data;
    }
}
