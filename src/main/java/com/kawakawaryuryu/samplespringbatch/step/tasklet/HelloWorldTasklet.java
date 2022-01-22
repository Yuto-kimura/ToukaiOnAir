package com.kawakawaryuryu.samplespringbatch.step.tasklet;

import com.kawakawaryuryu.samplespringbatch.entity.ToukaiEntity;
import com.kawakawaryuryu.samplespringbatch.service.ParamConverter;
import com.kawakawaryuryu.samplespringbatch.service.ParamConverterImpl;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class HelloWorldTasklet implements Tasklet {
    Logger logger = Logger.getLogger("ツイートログ");

    @Autowired
    ToukaiEntity toukaiEntity = new ToukaiEntity();
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        TwitterClient twitterClient = new TwitterClient(TwitterClient.OBJECT_MAPPER
//                .readValue(new File("/Users/coron/東海オンエア/sample-spring-batch/key.json"), TwitterCredentials.class));

        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken(toukaiEntity.getAccessToken())
                .accessTokenSecret(toukaiEntity.getAccessTokenSecret())
                .apiKey(toukaiEntity.getApiKey())
                .apiSecretKey(toukaiEntity.getApiSecretKey())
                .build());

        String randomParams = new ParamConverterImpl().convretParam();

        //ツイート内容のログを落とす
        logger.log(Level.INFO,randomParams);
        Tweet  resultPost = twitterClient.postTweet(randomParams);
        return RepeatStatus.FINISHED;
    }
}
