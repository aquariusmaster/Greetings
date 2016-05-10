package com.aquariusmaster;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by harkonnen on 10.05.16.
 */
public class GreeterTest {

    @Test
    public void getDayStateTest(){

        String dayState = Greeter.getCurrentTimeOfDay();
        assertNotEquals("", dayState);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 6 && timeOfDay < 9){
            System.out.println("Test: Time of day is morning");
            assertEquals("morning", dayState);
        }else if(timeOfDay >= 9 && timeOfDay < 19){
            System.out.println("Test: Time of day is day");
            assertEquals("day", dayState);
        }else if(timeOfDay >= 19 && timeOfDay < 23){
            System.out.println("Test: Time of day is evening");
            assertEquals("evening", dayState);
        }else if(timeOfDay >= 23 || timeOfDay < 6){
            System.out.println("Test: Time of day is night");
            assertEquals("night", dayState);
        }
    }

    @Test
    public void resourceMessagesTest() throws UnsupportedEncodingException {

        Locale locale = new Locale("en", "US");
        ResourceBundle labels = ResourceBundle.getBundle("messages", locale);

        System.out.println(labels.getString("day"));
        assertEquals("day", labels.getString("day"));
        System.out.println(labels.getString("morning"));
        assertEquals("morning", labels.getString("morning"));

        String l1 = labels.getString("night");
        String encodedNightLabel2 = new String(l1.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(encodedNightLabel2);

        Locale localeRU = new Locale("ru", "RU");
        ResourceBundle labelsRU = ResourceBundle.getBundle("messages", localeRU);

        String dayLabel = labelsRU.getString("evening");
        String encodedEveningLabel = new String(dayLabel.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(encodedEveningLabel);
        assertEquals("вечер", encodedEveningLabel);

        String nightLabel = labelsRU.getString("night");
        String encodedNightLabel = new String(nightLabel.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(encodedNightLabel);
        assertEquals("ночи", encodedNightLabel);

    }

    @Test
    public void greetingsTest() throws UnsupportedEncodingException {

        Locale locale = new Locale("en", "US");
        ResourceBundle labels = ResourceBundle.getBundle("messages", locale);

        String timeOfDay = "day";

        String greetingLabel = labels.getString("greeting." + timeOfDay);
        String greetings = new String(greetingLabel.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(greetings);
        assertEquals("Good day, World!", greetings);

        Locale localeRU = new Locale("ru", "RU");
        ResourceBundle labelsRU = ResourceBundle.getBundle("messages", localeRU);

        String timeOfDay2 = "morning";

        String greetingLabelRU = labelsRU.getString("greeting." + timeOfDay2);
        String greetingsRU = new String(greetingLabelRU.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(greetingsRU);
        assertEquals("Доброе утро, Мир!", greetingsRU);
    }

}