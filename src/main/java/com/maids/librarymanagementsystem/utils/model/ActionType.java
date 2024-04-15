package com.maids.librarymanagementsystem.utils.model;

public enum ActionType {

    UPDATE("update"),
    ADD("add"),
    DELETE("delete"),
    BORROW("borrow"),
    RETURN("return"),
    ;

    String name;
    ActionType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
