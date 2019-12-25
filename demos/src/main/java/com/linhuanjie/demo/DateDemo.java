package com.linhuanjie.demo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @Author : linhuanjie
 * @Description :
 *                              原文： https://juejin.im/post/5d7787625188252388753eae
 *                              localdatatime是没有时区概念的 。如果服务器是utc时间 返回做本地化的时候需要注意一下 。
 * @CreateTime : 2019-12-24 18:17
 **/
public class DateDemo {

    public static void main(String[] args) {
//        LocalDateTime time = new LocalDateTime();

        // --------------- LocalDate 只会获取年月日 start --------------- //
        System.out.println(" --------------- LocalDate 只会获取年月日 start ---------------");
        // 获取当前年月日
        LocalDate localDate = LocalDate.now();
        System.out.println("获取当前年月日:localDate = " + localDate);

        // 构造指定的年月日
        LocalDate localDate1 = LocalDate.of(2019, 9, 10);
        System.out.println("生成指定年月日：localDate1 = " + localDate1);

        // 获取年、月、日、星期几
        int year = localDate.getYear();
        System.out.println("year = " + year);
        int year1 = localDate.get(ChronoField.YEAR);
        System.out.println("year1 = " + year1);
        Month month = localDate.getMonth();
        int month1 = month.getValue();
        System.out.println("month1 = " + month1);
        int month2 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("month2 = " + month2);
        int day = localDate.getDayOfMonth();
        System.out.println("day = " + day);
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("day1 = " + day1);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = dayOfWeek.getValue();
        System.out.println("dayOfWeek1 = " + dayOfWeek1);
        int dayOfWeek2 = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println("dayOfWeek2 = " + dayOfWeek2);
        System.out.println(" --------------- LocalDate 只会获取年月日 end ---------------");
        // --------------- LocalDate 只会获取年月日 end --------------- //

        // --------------- LocalTime 只会获取时分秒 start --------------- //
        System.out.println(" --------------- LocalTime 只会获取时分秒 start ---------------");

        // 获取当前时分秒
        LocalTime currentTime = LocalTime.now();
        System.out.println("currentTime = " + currentTime);

        // 构造指定的时分秒
        LocalTime genTime = LocalTime.of(13, 51, 10);
        System.out.println("genTime = " + genTime);

        //获取小时
        int hour = currentTime.getHour();
        System.out.println("hour = " + hour);
        int hour1 = currentTime.get(ChronoField.HOUR_OF_DAY);
        System.out.println("hour1 = " + hour1);
        //获取分
        int minute = currentTime.getMinute();
        System.out.println("minute = " + minute);
        int minute1 = currentTime.get(ChronoField.MINUTE_OF_HOUR);
        System.out.println("minute1 = " + minute1);
        //获取秒
        int second = currentTime.getSecond();
        System.out.println("second = " + second);
        int second1 = currentTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("second1 = " + second1);

        System.out.println(" --------------- LocalTime 只会获取时分秒 end ---------------");
        // --------------- LocalTime 只会获取时分秒 end --------------- //

        // --------------- LocalDateTime 获取年月日时分秒，等于LocalDate+LocalTime  start --------------- //
        System.out.println(" --------------- LocalDateTime 获取年月日时分秒，等于LocalDate+LocalTime  start ---------------");

        // 创建LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        System.out.println("localDateTime1 = " + localDateTime1);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, currentTime);
        System.out.println("localDateTime2 = " + localDateTime2);
        LocalDateTime localDateTime3 = localDate.atTime(currentTime);
        System.out.println("localDateTime3 = " + localDateTime3);
        LocalDateTime localDateTime4 = currentTime.atDate(localDate);
        System.out.println("localDateTime4 = " + localDateTime4);

        // 获取LocalDate
        LocalDate localDate2 = localDateTime.toLocalDate();
        System.out.println("localDate2 = " + localDate2);
        // 获取LocalTime
        LocalTime localTime2 = localDateTime.toLocalTime();
        System.out.println("localTime2 = " + localTime2);

        System.out.println(" --------------- LocalDateTime 获取年月日时分秒，等于LocalDate+LocalTime  end ---------------");
        // --------------- LocalDateTime 获取年月日时分秒，等于LocalDate+LocalTime  end --------------- //

        // --------------- Instant 时间戳  start --------------- //
        System.out.println(" --------------- Instant 时间戳  start ---------------");
        Instant instant = Instant.now();
        // 获取秒数
        long currentSecond = instant.getEpochSecond();
        System.out.println("currentSecond = " + currentSecond);
        // 获取毫秒数
        long currentMilli = instant.toEpochMilli();
        System.out.println("currentMilli = " + currentMilli);
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis = " + currentTimeMillis);
        System.out.println(" --------------- Instant 时间戳  end ---------------");
        // --------------- Instant 时间戳  end --------------- //

        // --------------- 修改LocalDate、LocalTime、LocalDateTime、Instant  start --------------- //
        System.out.println(" --------------- 修改LocalDate、LocalTime、LocalDateTime、Instant  start ---------------");
        // LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本
        System.out.println(" --------------- LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本---------------");

        // 增加、减少年数、月数、天数等 以LocalDateTime为例
        System.out.println("增加、减少年数、月数、天数等 以LocalDateTime为例");
        LocalDateTime creatDateTime = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        System.out.println("creatDateTime = " + creatDateTime);

        //增加一年
        creatDateTime = creatDateTime.plusYears(1);
        System.out.println("creatDateTime = " + creatDateTime);
        creatDateTime = creatDateTime.plus(1, ChronoUnit.YEARS);
        System.out.println("creatDateTime = " + creatDateTime);

        //减少一个月
        creatDateTime = creatDateTime.minusMonths(1);
        System.out.println("creatDateTime = " + creatDateTime);
        creatDateTime = creatDateTime.minus(1, ChronoUnit.MONTHS);
        System.out.println("creatDateTime = " + creatDateTime);

        // 通过with修改某些值
        //修改年为2019
        creatDateTime = creatDateTime.withYear(2020);
        System.out.println("creatDateTime = " + creatDateTime);
        //修改为2022
        creatDateTime = creatDateTime.with(ChronoField.YEAR, 2022);
        System.out.println("creatDateTime = " + creatDateTime);



        System.out.println(" --------------- 修改LocalDate、LocalTime、LocalDateTime、Instant  end ---------------");
        // --------------- 修改LocalDate、LocalTime、LocalDateTime、Instant  end --------------- //

        // --------------- 时间计算  start --------------- //
        System.out.println(" --------------- 时间计算  start --------------- ");
        System.out.println(" 比如有些时候想知道这个月的最后一天是几号、下个周末是几号，通过提供的时间和日期API可以很快得到答案 ");

        LocalDate firstDayOfYearDate = localDate.with(firstDayOfYear());
        System.out.println("firstDayOfYearDate = " + firstDayOfYearDate);

        System.out.println(" --------------- 时间计算  end --------------- ");
        // --------------- 时间计算  end --------------- //

        // --------------- 格式化时间  start --------------- //
        System.out.println(" --------------- 格式化时间  start --------------- ");
        LocalDate createDate = LocalDate.of(2019, 9, 10);
        String s1 = createDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("s1 = " + s1);
        String s2 = createDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("s2 = " + s2);
        //自定义格式化
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = createDate.format(dateTimeFormatter);
        System.out.println("s3 = " + s3);

        System.out.println(" --------------- 格式化时间  end --------------- ");
        // --------------- 格式化时间  end --------------- //

        // --------------- 解析时间  start --------------- //
        System.out.println(" --------------- 解析时间  start --------------- ");
        LocalDate parseDate1 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("parseDate1 = " + parseDate1);
        LocalDate parseDate2 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("parseDate2 = " + parseDate2);

        System.out.println(" --------------- 解析时间  end --------------- ");
        // --------------- 解析时间  end --------------- //

    }
}
