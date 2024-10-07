package com.example.bico.appraiser.util;

public class BicoUtilities {

    public static String idGenerator(String firstName , String lastName) {
        final StringBuilder builder = new StringBuilder();
        builder.append(firstName.charAt(0)).append(firstName.charAt(1));
        builder.append('-');
        builder.append(lastName.charAt(0)).append(lastName.charAt(1));
        return builder.toString().toUpperCase();
    }
}
