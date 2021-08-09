package com.stackroute.userservice.authentication;


import com.stackroute.userservice.users.UserService;
import com.stackroute.userservice.users.Users;
import com.stackroute.userservice.util.CookieUtil;
import com.stackroute.userservice.util.JwtUtil;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
@RequestMapping("api/v1/authorize")
public class OauthController {

    @Value("${home_page_url}")
    String homepageurl;
    @Autowired
    IGoogleService googleService;

    @Autowired
    UserService userService;
    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Value("${Domain}")
    String domain;

    @GetMapping(value = "/googlelogin")
    public RedirectView googlelogin() {
        RedirectView redirectview = new RedirectView();
        String url = googleService.googlelogin();
        redirectview.setUrl(url);
        return redirectview;
    }

    // Google calls back on user's successful authentication and consent
    @GetMapping(value = "/complete")
    public RedirectView google(@RequestParam("code") String code, HttpServletResponse res) throws ParseException {
        String accessToken = googleService.getGoogleAccessToken(code);

        System.out.println("accessToken: " + accessToken);
        Users user = googleService.getGoogleUserProfile(accessToken);
        String jwtToken = JwtUtil.addToken(res, user);
        CookieUtil.create(res, jwtTokenCookieName, jwtToken, false, -1, domain);
        RedirectView redirectview = new RedirectView();
        try {
            System.out.println("USER:: " + user.toString());
            userService.saveUser(user);
        } catch (Exception exception) {
            System.out.println("In google method " + LocalDateTime.now() + " " + exception.getMessage());
        }
        redirectview.setUrl(homepageurl);
        return redirectview;
    }
}
