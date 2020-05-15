package com.todouno.kardex.core.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Util {

public @NotNull
    static String getDateActual() {

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String date = sdf.format(now);
        return date;

    }

    public static String getCode() {
        int number = (int) (Math.random() * 10000000     + 1);
        return String.valueOf(number);
    }
}
