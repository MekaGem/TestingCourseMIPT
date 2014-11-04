package com.github.mekagem.testing.data.group;

import com.github.mekagem.testing.data.AbstractTestDataBuilder;

import java.util.Arrays;

public class CommandDataBuilder extends AbstractTestDataBuilder<CommandData> {
    @Override
    public CommandData build(String[] params) {
        return new CommandData(CommandType.valueOf(params[0]), Arrays.copyOfRange(params, 1, params.length));
    }
}
