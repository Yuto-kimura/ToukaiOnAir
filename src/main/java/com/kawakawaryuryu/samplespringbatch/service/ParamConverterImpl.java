package com.kawakawaryuryu.samplespringbatch.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ParamConverterImpl implements ParamConverter {
    @Override
    public String convretParam(){
        String invalidParams = "東海オンエア";

            invalidParams = toRandomParam(invalidParams);

            // 0.5%の確率で名前を表示させる
            String Name =  decideNameByPercentage(invalidParams);

        return Name;
    }

    private String toRandomParam(String invalidParams){
        // 初期文字列を配列化
        final List<String> params = new ArrayList<String>(Arrays.asList(invalidParams.split("")));
        // ランダム文字列格納用の配列を宣言
        final List<String> randomParams = new ArrayList<String>();
        final Random random = new Random();

        while (params.size() > 0){
            int randomNum = random.nextInt(params.size());
            randomParams.add(params.get(randomNum));
            params.remove(randomNum);
        }
        return randomParams.stream().collect(Collectors.joining());
    }

    private String decideNameByPercentage(String invalidParam){
        int randomPercentage = new Random().nextInt(1001);
        // 5%以下なら「東海オンエア」と格納
        if(randomPercentage<6){
            return "東海オンエア";
        }
        return invalidParam;
    }

    public String selectImageFile(){
        //
        int randomNumberToFindImage = new Random().nextInt(7);

        switch (randomNumberToFindImage){
            case 1:
                return "tetuya.jpeg";
            case 2:
                return "ryou.jpeg";
            case 3:
                return "yumemaru.png";
            case 4:
                return "sibayu.png";
            case 5:
                return "toshimitu.png";
            case 6:
                return "musimegane.png";
        }
        return "tesuya.jpeg";
    }
}
