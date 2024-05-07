package com.hotelreservationapp.models.Settings;

import  com.google.gson.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class ApiSettingsReader {
    public ApiSettingsReader() {

    }

    public static String readInputStreamAsString(InputStream in) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            byte b = (byte)result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
    }

    public ApiSettingsModel readSettings() {
        ApiSettingsModel toReturn = new ApiSettingsModel();
        ClassLoader classLoader = SettingsReader.class.getClassLoader();
        try(InputStream inputStream = classLoader.getResourceAsStream("/apisettings.json")){
            String str = readInputStreamAsString(inputStream);
            //read input stream to String
            inputStream.close();
            Gson gson = new Gson();
            toReturn = gson.fromJson(str, ApiSettingsModel.class);
            //System.out.println(str);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            
        }
        return toReturn;

    }
}
