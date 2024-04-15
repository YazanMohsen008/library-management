package com.maids.librarymanagementsystem.log;


import com.maids.librarymanagementsystem.utils.model.ActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    ActionType actionType();
}
