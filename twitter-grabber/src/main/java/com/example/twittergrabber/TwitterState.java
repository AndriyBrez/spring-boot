package com.example.twittergrabber;

import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

/**
 * @author Andriy Brezetskyy
 */
@Component
public class TwitterState {

    private Stream twitterStream;

    public Stream getTwitterStream() {
        return twitterStream;
    }

    public void setTwitterStream(Stream twitterStream) {
        this.twitterStream = twitterStream;
    }
}
