package com.github.javacommons.environment;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;


public class Settings {
    protected static String directory = null;
    static {
        Settings.directory = System.getProperty("user.home");
    }
    public static void setDirectory(String dir)
    {
        Settings.directory = dir;
    }
    protected static boolean prepareDirectory()
    {
        File dir = new File(Settings.directory);
        return dir.mkdirs();
    }
    protected static File getSavedFile(Class valueType)
    {
        String clazzName = valueType.getName();
        File file = new File(Settings.directory + File.separator + "[" + clazzName + "].json");
        return file;
    }
    public static <T extends Object> T loadObject(Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        File file = getSavedFile(valueType);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, valueType);
    }
    public static void saveObject(Object o) throws IOException
    {
        Settings.prepareDirectory();
        Class valueType = o.getClass();
        File file = getSavedFile(valueType);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(file, o);
    }
}
