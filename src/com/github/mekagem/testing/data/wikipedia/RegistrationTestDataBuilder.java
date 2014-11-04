package com.github.mekagem.testing.data.wikipedia;

import com.github.mekagem.testing.data.AbstractTestDataBuilder;

public class RegistrationTestDataBuilder extends AbstractTestDataBuilder<RegistrationTestData> {
    @Override
    public RegistrationTestData build(String[] params) {
        if (params.length != 5) {
            throw new IllegalArgumentException("RegistrationData has exactly 5 string fields");
        }
        return new RegistrationTestData(params[0], params[1], params[2], params[3], params[4]);
    }
}
