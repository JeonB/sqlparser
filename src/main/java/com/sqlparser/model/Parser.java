package com.sqlparser.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;

@Getter
@Setter
public class Parser {

    public String inputQuery;

    // 기본 예약어 및 함수 리스트
    public LinkedList<String> keyWord = new LinkedList<>(Arrays.asList("SELECT","FROM","WHERE","AND","LIKE","JOIN"));
    public LinkedList<String> function = new LinkedList<>(Arrays.asList("MAX", "SUM", "COUNT", "NVL"));

}
