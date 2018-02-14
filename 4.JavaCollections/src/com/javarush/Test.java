package com.javarush;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.NoSuchAlgorithmException;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"some"})
public class Test {
    //текст для хеширования
    private String name;
    private int id;

    public Test() {}

    public Test(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, JsonProcessingException {
        Test test = new Test();

        String result = new ObjectMapper().writeValueAsString(test);

        System.out.println(result);
    }
}