package com.github.javacommons.environment;

import java.io.IOException;

public class TestMain {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Integer i = 1234;
        Settings.saveObject(i);
        Integer j = Settings.loadObject(Integer.class);
        System.out.println(j);
    }
}
