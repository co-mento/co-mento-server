package com.example.comento.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil {
    public static String changeTimeAgo(LocalDateTime createdAt) {

        Period period = Period.between(createdAt.toLocalDate(), LocalDate.now());

        long years = period.getYears();
        long months = period.getMonths();
        long days = period.getDays();

        if (years > 0) return years + "년 전";
        if (months > 0) return months + "달 전";
        if (days > 0) return days + "일 전";

        Duration ago = Duration.between(createdAt, LocalDateTime.now());

        long second = ago.getSeconds();
        long minutes = second / 60;
        long hours = minutes / 60;

        if (hours > 0) return hours + "시간 전";
        if (minutes > 0) return minutes + "분 전";
        if (second > 0) return second + "초 전";

        return "방금 전";
    }
}
