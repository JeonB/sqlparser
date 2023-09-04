package com.sqlparser;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;

@Getter
@Setter
public class Parser {

    String inputQuery;

    // 모든 예약어를 사전에 설정하기는 어려우므로 추가 가능한 객체 만들기
    LinkedList<String> keyWord = new LinkedList<>(Arrays.asList("SELECT","FROM","WHERE","AND","LIKE","JOIN"));
    LinkedList<String> function = new LinkedList<>(Arrays.asList("MAX", "SUM", "COUNT", "NVL"));



}
