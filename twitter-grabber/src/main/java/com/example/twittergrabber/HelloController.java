package com.example.twittergrabber;

import com.example.twittergrabber.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Andriy Brezetskyy
 */

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private Twitter twitter;
    @Autowired
    private TwitterState twitterState;

    @Autowired
    private TweetRepository repository;

    @RequestMapping(method= RequestMethod.GET)
    public String helloTwitter(Model model) {

        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        /*model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);

        */


        twitterState.setTwitterStream(twitter.streamingOperations().filter("java", getListeners()));
        return "hello";
    }


    private List<StreamListener> getListeners(){
        StreamListener streamListener = new StreamListener(){
            /**
             * Called when a new Tweet is available on the stream
             *
             * @param tweet a tweet available on the stream
             */
            @Override
            public void onTweet(Tweet tweet) {
                System.out.println("#############################");
                System.out.println(tweet.getFromUser());
                System.out.println(tweet.getText());
                System.out.println("#############################");
                try {
                    Tweet saved = repository.save(tweet);
                    System.out.println(saved);

                }catch (Throwable e){
                    System.err.println("coud not save" + e.getMessage());
                    e.printStackTrace();
                }


                System.out.println("COUNT: "+repository.count());

            }

            /**
             * Called when a delete message is available on the stream
             *
             * @param deleteEvent a delete event
             */
            @Override
            public void onDelete(StreamDeleteEvent deleteEvent) {
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("User:" + deleteEvent.getUserId()+" removed tweet "+deleteEvent.getTweetId());
            }

            /**
             * Called when the stream is being track limited.
             *
             * @param numberOfLimitedTweets the number of tweets being limited on the stream
             */
            @Override
            public void onLimit(int numberOfLimitedTweets) {
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("The stream is being track limite - "+numberOfLimitedTweets);

            }

            /**
             * Called when a client is stalling and the stream is in danger of being disconnected.
             *
             * @param warningEvent a warning event
             */
            @Override
            public void onWarning(StreamWarningEvent warningEvent) {
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("Warning:"+warningEvent.getCode()+" text:"+ warningEvent.getMessage());

            }
        };
        return asList(streamListener);
    }




    @RequestMapping(method= RequestMethod.GET, path = "close")
    public String cloeseStream() {
        twitterState.getTwitterStream().close();
        return "hello";
    }
}
