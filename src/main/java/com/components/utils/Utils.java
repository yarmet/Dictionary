package com.components.utils;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Utils {

    private final static String ANONYMOUS_USER = "ROLE_ANONYMOUS";

    public static Collection<GrantedAuthority> getUserRoles() {
        return (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }


    public static Timestamp getCurrentTimestampAsUTC() {
        return Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    }

    // этот метод действительно нужен?
    public static String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }


    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public static Date getPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }


    public static boolean userIsLogged() {
        return getUserRoles().stream().noneMatch(e -> e.getAuthority().equals(ANONYMOUS_USER));
    }


}
