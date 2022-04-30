package com.kawakawaryuryu.samplespringbatch.step.tasklet;

import static io.github.redouane59.twitter.dto.tweet.MediaCategory.TWEET_IMAGE;
import com.kawakawaryuryu.samplespringbatch.entity.ToukaiEntity;
import com.kawakawaryuryu.samplespringbatch.service.ParamConverterImpl;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.dto.tweet.TweetParameters;
import io.github.redouane59.twitter.dto.tweet.UploadMediaResponse;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import java.io.File;
import java.util.Collections;
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

        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken(toukaiEntity.getAccessToken())
                .accessTokenSecret(toukaiEntity.getAccessTokenSecret())
                .apiKey(toukaiEntity.getApiKey())
                .apiSecretKey(toukaiEntity.getApiSecretKey())
                .build());

        String randomName = new ParamConverterImpl().convretParam();
        TweetParameters parameters;
        if(randomName.equals("東海オンエア")){
            randomName = "東海オンエア！！！！！！！！！！！！！！";
            // 各メンバーの写真から一枚をランダムに選択する
            String ImageFilePath = new ParamConverterImpl().selectImageFile();
            // 画像ファイル指定
            UploadMediaResponse
                    resultPost2 =twitterClient.uploadMedia(new File("./src/main/resources/"+ImageFilePath), TWEET_IMAGE);

            // Tweet内容と画像指定
            parameters = TweetParameters.builder().text(randomName).media(TweetParameters.Media.builder().mediaIds(
                    Collections.singletonList(resultPost2.getMediaId())).build()).build();
        }
        else{
            // ツイート内容と画像を出力
            parameters = TweetParameters.builder().text(randomName).build();
        }

        //ツイート内容のログを落とす
        logger.log(Level.INFO,parameters.getText());
        Tweet  resultPost = twitterClient.postTweet(parameters);
        return RepeatStatus.FINISHED;
    }
}
