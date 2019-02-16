package com.nilay;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConferenceScheduler {

    public static void main (String[] args){
        Map<String,String> session = new HashMap<String,String>();
        session = setSessionAndTimeDuration();
        scheduleConferenceMeetings(session);
    }

    private static void scheduleConferenceMeetings(Map<String, String> session) {
        ArrayList<String> meetingWithTime = new ArrayList<String>();
        String meetingStartTime = "9:00";

        for (Map.Entry<String, String> entry : session.entrySet())
        {
            String time = calculateMeetingTime(entry.getValue().toString(),meetingStartTime);
            if(time.equals("12:00")){

            }
            meetingWithTime.add(meetingStartTime + "|"+ entry.getKey() + "|" + entry.getValue() + "mins");
            meetingStartTime = time;
        }
        System.out.println(meetingWithTime);
    }

    private static String calculateMeetingTime(String currentMeetingTimeInMins, String meetingStartTime) {

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = null;
        if(meetingStartTime.equalsIgnoreCase("lightning")){
            meetingStartTime = "5";
        }
        try {
            d = df.parse(meetingStartTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, Integer.parseInt(currentMeetingTimeInMins));

        return df.format(cal.getTime());
    }

    private static Map<String,String> setSessionAndTimeDuration() {
        Map<String,String> tempSession = new HashMap<String,String>();
        tempSession.put("Writing Fast Tests Against Enterprise Rails","60");
        tempSession.put("Overdoing it in Python","45");
        tempSession.put("Lua for the Masses","30");
        tempSession.put("Ruby Errors from Mismatched Gem Versions","45");
        //tempSession.put("Lunch","60");
        tempSession.put("Ruby on Rails: Why We Should Move On","60");
        tempSession.put("Common Ruby Errors","45");
        tempSession.put("Pair Programming vs Noise","45");
        tempSession.put("Programming in the Boondocks of Seattle","30");
        tempSession.put("Ruby vs. Clojure for Back-End Development","30");
        tempSession.put("User Interface CSS in Rails Apps","30");
        tempSession.put("Networking Event ","30");
        return tempSession;
    }
}
