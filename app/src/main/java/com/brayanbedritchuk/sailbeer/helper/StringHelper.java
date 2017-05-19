package com.brayanbedritchuk.sailbeer.helper;

public class StringHelper {

    public static boolean isEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }

    public static boolean hasContent(String text) {
        return (text != null && !text.trim().isEmpty());
    }

}
