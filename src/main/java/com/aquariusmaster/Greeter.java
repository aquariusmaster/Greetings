package com.aquariusmaster;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Created by harkonnen on 10.05.16.
 */
public class Greeter {

    final static Logger logger = Logger.getLogger(Greeter.class);


    public static void main(String[] args) {

        if(logger.isDebugEnabled()){
            logger.debug("In Main");
        }
        Locale locale = Locale.getDefault();
        ResourceBundle labels = ResourceBundle.getBundle("messages", locale);

        if(logger.isDebugEnabled()){
            logger.debug("Locale is " + locale.getDisplayName());
        }

        String timeOfDay = getCurrentTimeOfDay();

        String greetingLabel = labels.getString("greeting." + timeOfDay);
        try {
            String greeting = new String(greetingLabel.getBytes("ISO-8859-1"), "UTF-8");
            if(logger.isDebugEnabled()){
                logger.debug("Greeting is: " + greeting);
            }
            System.out.println(greeting);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Can't encode your greetings.");
            logger.error("Can't encode your greetings.", e);
            e.printStackTrace();
        }


    }

    public static String getCurrentTimeOfDay() {

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(logger.isDebugEnabled()){
            logger.debug("Current hour is: " + timeOfDay);
        }

        String timeOfDayStr = "";

        if(timeOfDay >= 6 && timeOfDay < 9){
            timeOfDayStr = "morning";
        }else if(timeOfDay >= 9 && timeOfDay < 19){
            timeOfDayStr = "day";
        }else if(timeOfDay >= 19 && timeOfDay < 23){
            timeOfDayStr = "evening";
        }else if(timeOfDay >= 23 || timeOfDay < 6){
            timeOfDayStr = "night";
        }

        if(logger.isDebugEnabled()){
            logger.debug("Current time of a Day is: " + timeOfDayStr);
        }
        return timeOfDayStr;
    }



}
