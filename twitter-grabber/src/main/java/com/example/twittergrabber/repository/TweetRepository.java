package com.example.twittergrabber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.social.twitter.api.Tweet;

/**
 * @author Andriy Brezetskyy
 */
@RepositoryRestResource(collectionResourceRel = "tweet", path = "tweet")
public interface TweetRepository extends CrudRepository<Tweet, Long>
{
}
