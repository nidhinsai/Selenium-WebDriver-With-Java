package org.automation.enums;

public enum DropdownOptions {
    OPTION_1("Option 1"),
    OPTION_2("Option 2");

    private final String option;

    DropdownOptions(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}

