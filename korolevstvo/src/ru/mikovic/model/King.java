package ru.mikovic.model;

import java.util.HashSet;
import java.util.Set;

public class King extends Entity{
    private Set<UnluckyVassal> set = new HashSet<>();
    public King(String name, String title) {
        super(name, title);
    }

    public Set<UnluckyVassal> getReport() {
        return set;
    }

}
