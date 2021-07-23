package com.stackroute.userservice.authentication;

import com.stackroute.userservice.users.Users;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class GoogleServiceImpl implements IGoogleService  {
    @Value("${spring.social.google.app-id}")
    private String googleId;

    @Value("${spring.social.google.app-secret}")
    private String googleSecret;

    @Value("${redirect_url}")
    private String redirectUrl;

    private static final String SystemData = "SYSTEM";
    // Creates Google OAuth Connection
    private GoogleConnectionFactory createGoogleConnection() {
        return new GoogleConnectionFactory(googleId, googleSecret);
    }

    // Opens the Google Consent Form
    @Override
    public String googlelogin() {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(redirectUrl);
        parameters.setScope("profile email");
        return createGoogleConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
    }

    // For getting Google oauth access token
    @Override
    public String getGoogleAccessToken(String code) {
        return createGoogleConnection().getOAuthOperations().exchangeForAccess(code, redirectUrl, null)
                .getAccessToken();
    }

    // For getting google profile of particular user
    @Override
    public Users getGoogleUserProfile(String accessToken) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String profileData = restTemplate.getForObject("https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="+accessToken, String.class);
//        Google google = new GoogleTemplate(accessToken);
        System.out.println("profileData" + profileData.getClass());
        System.out.println("profileData" + profileData);
//        Person person = google.plusOperations().getGoogleProfile();
        JSONParser parser = new JSONParser();
        JSONObject profileObj = (JSONObject) parser.parse(profileData);
        Users user = new Users();
        System.out.println("profileobj" + profileObj.getClass());
        System.out.println("name" + profileObj.get("name"));

        user.setUserName(profileObj.get("email").toString());
        user.setFirstName(profileObj.get("name").toString());
        user.setName(profileObj.get("name").toString());
        user.setEmail(profileObj.get("email").toString());
        user.setAvatarURL(profileObj.get("picture").toString());
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(SystemData);
        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy(SystemData);
        return user;
    }

}
