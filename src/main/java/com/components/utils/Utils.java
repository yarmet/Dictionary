package com.components.utils;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;



public class Utils {

    private final static String ANONYMOUS_USER = "ROLE_ANONYMOUS";

    public static Collection<? extends GrantedAuthority> getUserRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }


    public static Timestamp getCurrentTimestampAsUTC() {
        return Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    }


    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public static boolean userIsLogged() {
        return getUserRoles().stream().noneMatch(e -> e.getAuthority().equals(ANONYMOUS_USER));
    }


}
