/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.task.fw;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author Dirceu
 * @ajuda Stefano
 */
public class DateTime implements Comparable {

    private long millis = 0;
    //private Locale locale = new Locale("pt", "BR");

    public DateTime(DateTime d) {
        this.millis = d.getMillis();
    }

    private static String getZone() {
        return "America/Sao_Paulo";
    }

    public DateTime(String date, String format) throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone(getZone()));
        DateFormat fmt = new SimpleDateFormat(format);
        this.millis = fmt.parse(date).getTime();
    }

    public DateTime(int... v) throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone(getZone()));
        String date = v[2] + "/" + v[1] + "/" + v[0] + " " + v[3] + ":" + v[4] + ":" + v[5];
        String format = "dd/MM/yyyy HH:mm:ss";
        DateFormat fmt = new SimpleDateFormat(format);
        this.millis = fmt.parse(date).getTime();
    }

    public DateTime(int year, int month, int day) throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone(getZone()));
        String date = day + "/" + month + "/" + year;
        String format = "dd/MM/yyyy";
        DateFormat fmt = new SimpleDateFormat(format);
        this.millis = fmt.parse(date).getTime();
    }

    public DateTime(long millis) {
        TimeZone.setDefault(TimeZone.getTimeZone(getZone()));
        this.millis = millis;
    }

    public DateTime(Timestamp value) {
        TimeZone.setDefault(TimeZone.getTimeZone(getZone()));
        if (value != null) {
            this.millis = value.getTime();
        } else {
            this.millis = 0;
        }
    }

    public DateTime(Date value) {
        TimeZone.setDefault(TimeZone.getTimeZone(getZone()));
        if (value != null) {
            this.millis = value.getTime();
        } else {
            this.millis = 0;
        }
    }

    public static DateTime now() {
        TimeZone.setDefault(TimeZone.getTimeZone(getZone()));
        Calendar c = Calendar.getInstance();
        return new DateTime(c.getTimeInMillis());
    }

    public Date getDate() {
        if (this.millis != 0) {
            return new Date(this.millis);
        } else {
            return null;
        }
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return this.millis;
    }

    public Timestamp getTimestamp() {
        if (this.millis != 0) {
            return new Timestamp(this.millis);
        } else {
            return null;
        }
    }

    public String toString(String format) {
        if (this.millis != 0) {
            //DateFormat fmt = new SimpleDateFormat(format, locale);
            DateFormat fmt = new SimpleDateFormat(format);
            return fmt.format(getTimestamp());
        } else {
            return "";
        }
    }

    public String getWeekDay() {
        return this.toString("EEEE");
    }

    public String getWeekDaySimple() {
        return this.toString("E");
    }

    public int getWeekDayInt() {
        return this.getWeekDaySimple().equals("Dom") || this.getWeekDaySimple().equals("Sun") ? 1
                : this.getWeekDaySimple().equals("Seg") || this.getWeekDaySimple().equals("Mon") ? 2
                : this.getWeekDaySimple().equals("Ter") || this.getWeekDaySimple().equals("Tue") ? 3
                : this.getWeekDaySimple().equals("Qua") || this.getWeekDaySimple().equals("Wed") ? 4
                : this.getWeekDaySimple().equals("Qui") || this.getWeekDaySimple().equals("Thu") ? 5
                : this.getWeekDaySimple().equals("Sex") || this.getWeekDaySimple().equals("Fri") ? 6 : 7;
    }

    public int getDay() {
        return Integer.parseInt(this.toString("dd"));
    }

    public int getMonth() {
        return Integer.parseInt(this.toString("MM"));
    }

    public int getYear() {
        return Integer.parseInt(this.toString("yyyy"));
    }

    public int getHour() {
        return Integer.parseInt(this.toString("HH"));
    }

    public int getMinute() {
        return Integer.parseInt(this.toString("mm"));
    }

    public int getSecond() {
        return Integer.parseInt(this.toString("ss"));
    }

    public void addSecond(long v) {
        this.millis += v * 1000;
    }

    public DateTime addCloneSecond(long v) {
        DateTime c = this.clone();
        c.addSecond(v);
        return c;
    }

    public void addMinute(long v) {
        addSecond(v * 60);
    }

    public DateTime addCloneMinute(long v) {
        DateTime c = this.clone();
        c.addMinute(v);
        return c;
    }

    public void addHour(long v) {
        addMinute(v * 60);
    }

    public DateTime addCloneHour(long v) {
        DateTime c = this.clone();
        c.addHour(v);
        return c;
    }

    public void addDay(long v) {
        addHour(v * 24);
    }

    public DateTime addCloneDay(long v) {
        DateTime c = this.clone();
        c.addDay(v);
        return c;
    }

    public void addMonth(int v) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(this.millis);
        c.add(Calendar.MONTH, v);
        this.millis = c.getTimeInMillis();
    }

    public DateTime addCloneMonth(int v) {
        DateTime c = this.clone();
        c.addMonth(v);
        return c;
    }

    public void addYear(int v) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(this.millis);
        c.add(Calendar.YEAR, v);
        this.millis = c.getTimeInMillis();
    }

    public DateTime addCloneYear(int v) {
        DateTime c = this.clone();
        c.addYear(v);
        return c;
    }

    public void addDateTime(DateTime v) {
        this.millis += v.getMillis();
    }

    public DateTime addCloneDateTime(DateTime v) {
        DateTime c = this.clone();
        c.addDateTime(v);
        return c;
    }

    public int compareTo(Object o) {
        DateTime d = (DateTime) o;
        if (this.millis == d.getMillis()) {
            return 0;
        } else if (this.millis > d.getMillis()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public DateTime clone() {
        return new DateTime(this.getMillis());
    }

//    public void setLocale(Locale locale) {
//        this.locale = locale;
//    }
    public int[] array() {
        int[] v = new int[7];
        v[0] = this.getYear();
        v[1] = this.getMonth();
        v[2] = this.getDay();
        v[3] = this.getHour();
        v[4] = this.getMinute();
        v[5] = this.getSecond();
        v[6] = this.getWeekDaySimple().equals("Dom") || this.getWeekDaySimple().equals("Sun") ? 1
                : this.getWeekDaySimple().equals("Seg") || this.getWeekDaySimple().equals("Mon") ? 2
                : this.getWeekDaySimple().equals("Ter") || this.getWeekDaySimple().equals("Tue") ? 3
                : this.getWeekDaySimple().equals("Qua") || this.getWeekDaySimple().equals("Wed") ? 4
                : this.getWeekDaySimple().equals("Qui") || this.getWeekDaySimple().equals("Thu") ? 5
                : this.getWeekDaySimple().equals("Sex") || this.getWeekDaySimple().equals("Fri") ? 6 : 7;

        return v;
    }

    @Override
    public String toString() {
        return "DateTime{" + toString("dd/MM/yyyy HH:mm:ss EEEE") + '}';
    }

    public static long diffWorkingDayInSeconds(DateTime v1, DateTime v2) throws Exception {

        if (v1.getMillis() <= v2.getMillis()) {

            if (v1.getWeekDayInt() == 1) {
                v1.addDay(1);
                int[] t1 = v1.array();
                t1[3] = 8;
                t1[4] = 0;
                t1[5] = 0;
                v1 = new DateTime(t1);
            }
            if (v2.getWeekDayInt() == 1) {
                v2.addDay(1);
                int[] t2 = v2.array();
                t2[3] = 8;
                t2[4] = 0;
                t2[5] = 0;
                v2 = new DateTime(t2);
            }
            if (v1.getWeekDayInt() == 7) {
                v1.addDay(2);
                int[] t1 = v1.array();
                t1[3] = 8;
                t1[4] = 0;
                t1[5] = 0;
                v1 = new DateTime(t1);
            }
            if (v2.getWeekDayInt() == 7) {
                v2.addDay(2);
                int[] t2 = v2.array();
                t2[3] = 8;
                t2[4] = 0;
                t2[5] = 0;
                v2 = new DateTime(t2);
            }

            if (v1.getDay() == v2.getDay() && v1.getMonth() == v2.getMonth() && v1.getYear() == v2.getYear()) {

                int[] t1 = v1.array();
                int[] t2 = v2.array();

                if (t1[3] > 18) {
                    t1[3] = 18;
                    t1[4] = 0;
                    t1[5] = 0;
                }

                if (t2[3] > 18) {
                    t2[3] = 18;
                    t2[4] = 0;
                    t2[5] = 0;
                }

                if (t1[3] < 8) {
                    t1[3] = 8;
                    t1[4] = 0;
                    t1[5] = 0;
                }

                if (t2[3] < 8) {
                    t2[3] = 8;
                    t2[4] = 0;
                    t2[5] = 0;
                }

                if (t1[3] > 12 && t1[3] < 14) {
                    t1[3] = 14;
                    t1[4] = 0;
                    t1[5] = 0;
                }

                if (t2[3] > 12 && t2[3] < 14) {
                    t2[3] = 14;
                    t2[4] = 0;
                    t2[5] = 0;
                }

                if (t1[3] < 14 && t2[3] >= 14) {
                    t1[3] += 2;
                }
                DateTime d1 = new DateTime(t1);
                DateTime d2 = new DateTime(t2);

                long total = (d2.getMillis() / 1000) - (d1.getMillis() / 1000);

                return total;

            } else {
                DateTime v = v1.clone();
                int[] t = v.array();
                if (t[3] == 8 && t[4] == 0 && t[5] == 0) {
                    v.addDay(1);
                    return diffWorkingDayInSeconds(v, v2) + 28800;
                } else {
                    if (t[3] < 14) {
                        t[3] += 2;
                    }
                    long diff = (((18 - t[3]) * 60) * 60) + (t[4] * 60) + t[5];

                    t[3] = 8;
                    t[4] = 0;
                    t[5] = 0;

                    v = new DateTime(t);
                    v.addDay(1);

                    return diffWorkingDayInSeconds(v, v2) + diff;
                }
            }

        } else {
            return diffWorkingDayInSeconds(v2, v1);
        }

    }

    public boolean equalDay(DateTime d) {
        return this.getDay() == d.getDay() && this.getMonth() == d.getMonth() && this.getYear() == d.getYear();
    }

    public boolean minor(DateTime d) {
        return this.millis < d.getMillis();
    }
}
