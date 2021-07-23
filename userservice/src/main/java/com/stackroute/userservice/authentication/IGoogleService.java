package com.stackroute.userservice.authentication;

import com.stackroute.userservice.users.Users;
import org.json.simple.parser.ParseException;

public interface IGoogleService {
    String googlelogin();

    String getGoogleAccessToken(String code);

    Users getGoogleUserProfile(String accessToken) throws ParseException;
}
