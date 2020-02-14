package com.company;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time() {
        hours=0;
        minutes=0;
        seconds=0;
    }

    public Time(int hours, int minutes, int seconds) throws TimeException {
        setTime(hours,minutes,seconds);
    }

    public void setHours(int hours) throws TimeException {
        if (hours>=0 && hours<=23)
            this.hours = hours;
        else
            throw new TimeException("Часы должны находиться в диапозоне 0..23");
    }

    public void setMinutes(int minutes) throws TimeException {
        if (minutes>=0 && minutes<=59)
            this.minutes = minutes;
        else
            throw new TimeException("Минуты должны находиться в диапозоне 0..59");
    }

    public void setSeconds(int seconds) throws TimeException {
        if (seconds>=0 && seconds<=59)
            this.seconds = seconds;
        else
            throw new TimeException("Секунды должны находиться в диапозоне 0..59");
    }

    public void setTime(int hours,int minutes,int seconds) throws TimeException {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    public int getHours() {
        return hours;
    }
    public int getMinutes() {
        return minutes;
    }
    public int getSeconds() {
       return seconds;
    }

    public String toString() {
        return "Текущее время : " + getHours() + "." + getMinutes() + "." + getSeconds();
    }

    public float toHours() {
        return hours+(float)minutes/60+(float)seconds/3600;
    }

    public float toMinutes() {
        return hours*60+minutes+(float)seconds/60;
    }

    public int toSeconds() {
        return hours*60*60+minutes*60+seconds;
    }

    public void addHours(int hours) throws TimeException {
        if (hours<0) {
            throw new TimeException("Часы должны быть положительными");
        }
        else {
            int time = (int) toHours() + hours;
            while (time > 23) {
                time -= 24;
            }
            this.hours = time;
        }
    }

    public void addMinutes(int minutes) throws TimeException {
        if (minutes<0) {
            throw new TimeException("Минуты должны быть положительными");
        }
        else {
            int time = (int) toMinutes() + minutes;
            while (time > 59) {
                time -= 60;
                addHours(1);
            }
            this.minutes = time;
        }
    }

    public void addSeconds(int seconds) throws TimeException {
        if (seconds<0) {
            throw new TimeException("Секунды должны быть положительными");
        }
        else {
            while(seconds>60) {
                seconds-=60;
                addMinutes(1);
            }
        }
    }

    public Time Sum (Time second) {
        Time result = new Time();
        result.hours=this.hours+second.hours;
        result.minutes=this.minutes+second.minutes;
        result.seconds=this.seconds+second.seconds;
        if (result.seconds>59) {
            result.minutes++;
            result.seconds-=60;
        }
        if (result.minutes>59) {
            result.hours++;
            result.minutes-=60;
        }
        if (result.hours>23) {
            result.hours-=24;
        }
        return result;
    }
    public Time Sub (Time second) {
        Time result = new Time();
        result.hours = this.hours - second.hours;
        result.minutes = this.minutes - second.minutes;
        result.seconds = this.seconds - second.seconds;
        if (result.seconds<0) {
            result.seconds*=(-1);
            result.seconds-=60;
            result.seconds*=(-1);
            result.minutes--;
        }
        if (result.minutes<0) {
            result.minutes*=(-1);
            result.minutes-=60;
            result.minutes*=(-1);
            result.hours--;
        }
        if (result.hours<0) {
            result.hours+=24;
        }
        return result;
    }

}
