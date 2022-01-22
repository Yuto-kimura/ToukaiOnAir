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
        return invalidParams;
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
}
