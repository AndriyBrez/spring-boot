package com.example.facebookgrabber;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagePostData;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookTests {
    @Value("${spring.social.facebook.appId}")
    private String appId;
    @Value("${spring.social.facebook.appSecret}")
    private String appSecret;

    @Test
    public void shouldFatchFacebookuserNameById() {
        OAuth2Operations oauth = new FacebookConnectionFactory(appId, appSecret).getOAuthOperations();
        String appToken = oauth.authenticateClient().getAccessToken();
        Facebook facebook = new FacebookTemplate(appToken);
        String markZuckerbergFacebookId = "4";
        User user = facebook.fetchObject(markZuckerbergFacebookId, User.class);
        assertEquals("Mark Zuckerberg", user.getName());
    }

    @Test
    public void shouldReturnEmptyUserName() {
        UserProfileBuilder userProfileBuilder = new UserProfileBuilder().setName("");
        UserProfile user = userProfileBuilder.build();
        assertEquals("", user.getFirstName());
        assertNull(user.getLastName());
    }
}
