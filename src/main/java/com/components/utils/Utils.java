package com.components.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;

public class Utils {

    public static Collection<GrantedAuthority> getUserRoles() {
        return (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }


    public static Timestamp getCurrentTimestampAsUTC() {
        return Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    }


}
