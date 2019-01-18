package com.jockay.apmt.controller.util;


public class MyUtil {

    public static int getIndexOfElement(String[] items, String element) {
        for(int i = 0; i < items.length; i++) {
            if(items[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
