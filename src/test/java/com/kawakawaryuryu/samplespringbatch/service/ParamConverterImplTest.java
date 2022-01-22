package com.kawakawaryuryu.samplespringbatch.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class ParamConverterImplTest {
    @RepeatedTest(5)
    @DisplayName("適切な文字列数が表示されていることの確認")
    public void checkSize(){
        String randomParams = new ParamConverterImpl().convretParam();
        assertEquals(randomParams.length(),6);
    }

    @RepeatedTest(5)
    @DisplayName("重複している文字列がない事の確認")
    public void checkNoDuplicate(){
        String randomParams = new ParamConverterImpl().convretParam();
        String checkParam = Arrays.asList(randomParams.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
        assertEquals(checkParam.length(),6);
    }
}
