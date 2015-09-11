/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporal;

import java.util.ArrayList;

/**
 *
 * @author drellem
 */
public class Time {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private ArrayList<TimeObserver> observers = new ArrayList<>();
    public String[] month_key;

    public static  Time deltaMinute = new Time(0,0,0,0,1);
    public static  Time deltaHour = new Time(0,0,0,1,0);
    public static  Time deltaDay = new Time(0,0,1,0,0);
    public static  Time deltaMonth = new Time(0,1,0,0,0);
    public static  Time deltaYear = new Time(1,0,0,0,0);
    public static  Time nullTime = new Time(0,0,0,0,0);
    
    public Time(int year, int month, int day, int hour, int minute) {
        this.month_key = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public Time() {
        this(0, 0, 0, 0, 0);
    }

    public void updateObservers(Time delta) {
        for (TimeObserver i : observers) {
            i.observe(this, delta);
        }
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public void incYear() {
        this.year++;
        updateObservers(deltaYear);
    }

    public void incMonth() {
        if (++this.month == 13) {
            incYear();
            this.month = 1;
        }
        updateObservers(deltaMonth);
    }

    public void incDay() {
        if (++this.day == 32) {
            incMonth();
            this.day = 1;
        }
        updateObservers(deltaDay);
    }

    public void incHour() {
        if (++this.hour == 24) {
            incDay();
            this.hour = 0;
        }
        updateObservers(deltaHour);
    }

    public void incMinute() {
        if (++this.minute == 60) {
            incHour();
            this.minute = 0;
        }
        updateObservers(deltaMinute);
    }

    public String militaryTime(int hour, int minute) {
        String s = "";
        if (hour < 10) {
            s += "0";
        }
        s += String.valueOf(hour);
        if (minute < 10) {
            s += "0";
        }
        s += String.valueOf(minute);
        return s;
    }

    public void add(Time t) {
        boolean go = true;
        boolean a;
        boolean b;
        boolean c;
        boolean d;
        for (int i = 0; go; i++) {
            go = i < t.getMinute();
            if (go) {
                incMinute();
            }
            b = i < t.getHour();
            if (b) {
                incHour();
            }
            go = go || b;
            b = i < t.getMonth();
            if (b) {
                incMonth();
            }
            go = go || b;
            b = i < t.getDay();
            if (b) {
                incDay();
            }
            go = go || b;
            b = i < t.getYear();
            if (b) {
                incYear();
            }
            go = go || b;
        }
    }

    @Override
    public String toString() {
        return militaryTime(this.hour, this.minute) + " " + month_key[this.month - 1] + " " + String.valueOf(this.day) + ", " + String.valueOf(this.year);
    }
    
}
