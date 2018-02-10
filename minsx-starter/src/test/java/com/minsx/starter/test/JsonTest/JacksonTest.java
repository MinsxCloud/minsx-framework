package com.minsx.starter.test.JsonTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class JacksonTest {

    @Test
    public void javaObjectToJson() throws IOException {

        Student studentA = new Student("小明",13);
        ObjectMapper mapper = new ObjectMapper();

        String studentString = mapper.writeValueAsString(studentA);
        System.out.println(studentString);

        Student studentB = mapper.readValue(studentString, Student.class);
        System.out.println(studentB.getName());
    }


    @Test
    public void nestingJavaObjectToJson() throws IOException {

        Query subQuery = new Query("子条件A",null);
        Query queryA = new Query("主条件A",subQuery);
        ObjectMapper mapper = new ObjectMapper();

        String queryString = mapper.writeValueAsString(queryA);
        System.out.println(queryString);

        Query queryB = mapper.readValue(queryString, Query.class);
        System.out.println(queryB.getCondition());
    }

}
