package com.github.mekagem.testing.data.group;

import com.github.mekagem.testing.data.AbstractTestData;

public class DictionaryInfo extends AbstractTestData{
    private final String name;
    private final String description;

    public DictionaryInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryInfo that = (DictionaryInfo) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DictionaryInfo: {" + "name: " + name + ", description: " + description + "}";
    }
}
