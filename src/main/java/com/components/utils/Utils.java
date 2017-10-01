package com.components.utils;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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

//       java 7
//    public static Timestamp getCurrentTimestampAsUTC() {
//        Timestamp timestamp = null;
//        String DATE_FORMAT = "yyyy.MM.dd.HH.mm.ss";
//        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(DATE_FORMAT);
//        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
//        SimpleDateFormat dateFormatLocal = new SimpleDateFormat(DATE_FORMAT);
//        try {
//            timestamp = new Timestamp(dateFormatLocal.parse(dateFormatGmt.format(new Date())).getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return timestamp;
//    }


    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public static Date getPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }


    public static boolean userIsLogged() {
        Collection<GrantedAuthority> authorities = Utils.getUserRoles();
        for (GrantedAuthority authority : authorities) {
            String authAsString = authority.getAuthority();
            if (authAsString.equals(ANONYMOUS_USER)) {
                return false;
            }
        }
        return true;
    }

}
