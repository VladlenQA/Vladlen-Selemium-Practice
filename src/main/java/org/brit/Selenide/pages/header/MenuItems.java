package org.brit.Selenide.pages.header;

public enum MenuItems {
    All_Items("inventory"),
    ABOUT("about"),
    LOGOUT("logout"),
    RESET("reset");

    private String value;

    MenuItems(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
