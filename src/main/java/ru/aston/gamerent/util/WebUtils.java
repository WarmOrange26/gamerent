package ru.aston.gamerent.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import static java.lang.Boolean.TRUE;

@UtilityClass
public class WebUtils {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ROOT_PATH = "/";

    public static void addCookieWithTokenToResponse(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(ACCESS_TOKEN, token);
        cookie.setHttpOnly(TRUE);
        cookie.setPath(ROOT_PATH);
        response.addCookie(cookie);
    }
}