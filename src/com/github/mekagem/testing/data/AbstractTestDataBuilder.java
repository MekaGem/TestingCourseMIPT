package com.github.mekagem.testing.data;

public abstract class AbstractTestDataBuilder<T extends AbstractTestData> {
    public abstract T build(String[] params);
}
