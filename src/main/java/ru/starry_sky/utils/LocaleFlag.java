package ru.starry_sky.utils;

import ru.starry_sky.utils.enums.LocaleEnum;


public class LocaleFlag {
    private static String locale = "ru";

    private LocaleFlag(){

    }

    public static String getLocale(){
        return locale;
    }

    public static void setLocale(String l){
        locale = l;
    }

}
