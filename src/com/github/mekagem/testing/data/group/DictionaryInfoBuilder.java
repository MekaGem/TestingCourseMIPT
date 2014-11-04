package com.github.mekagem.testing.data.group;

import com.github.mekagem.testing.data.AbstractTestDataBuilder;

public class DictionaryInfoBuilder extends AbstractTestDataBuilder<DictionaryInfo> {
    @Override
    public DictionaryInfo build(String[] params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("DictionaryInfo has exactly 2 string fields");
        }
        return new DictionaryInfo(params[0], params[1]);
    }
}
