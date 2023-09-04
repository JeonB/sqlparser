package com.sqlparser;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParserService {

    Parser parser = new Parser();

    /** 줄 단위 쿼리 처리 메소드
     * @param lines 뷰화면에서 입력된 쿼리한 줄(줄바꿈 문자 기준으로 구분됨)
     * @return 파싱된 쿼리 리턴
     */
    ArrayList<String> parsingQuery(String[] lines) {

        ArrayList<String> parsedSQLQuery = new ArrayList<>();
        boolean isAnnotaion = false;
        StringBuilder anno = new StringBuilder();

        for (String line : lines) {

            line = line.toUpperCase(); //쿼리문은 대문자처리

            // 해당 줄에 "--" 또는 "/*"가 존재하면 주석 처리
            if (line.contains("--")) {
                line = line.replaceAll("\\R","");
                parsedSQLQuery.add(line + "-> 주석");
                continue;
            }
            else if(line.contains("/*")){
                isAnnotaion = true;
                anno.append(line);
                continue;
            }else if(isAnnotaion && !line.contains("*/")){
                anno.append(line);
                continue;
            }
            else if (line.contains("*/")) {
                isAnnotaion = false;
                anno.append(line).append(" -> 주석");
                parsedSQLQuery.add(anno.toString());
                continue;
            }

            // 주석이 없다면 공백문자 기준으로 토큰을 파싱 처리함
            String[] tokens = line.split("\\s");
            for (String token : tokens) {
                parsedSQLQuery.add(parsingToken(token));
            }
        }

        return parsedSQLQuery;

    }

    /** 쿼리문 파싱 함수
     * @param token 파싱할 입력값.
     * @return 입력된 값을 각 조건에 맞게 파싱
     */
    String parsingToken(String token) {

        if (parser.keyWord.contains(token)) {
            return token + " -> Keyword";
        } else if (!isFunction(token).isEmpty()) {
            return isFunction(token);
        } else if (token.contains("'")) {
            return token + " -> 문자열";
        } else if (token.contains(":")) {
            return token + " -> Binding 변수";
        }
        else {
            return token + " -> ETC";
        }

    }

    /**
     * @param token 입력된 값을 함수인지 판별하는 메소드
     * @return 파싱된 값 리턴
     */
    String isFunction(String token) {
        String parsingToken="";
        for (String item : parser.function) {
            if (token.contains(item)) {
                parsingToken = token + " -> Function";
                break; // 일치하는 요소를 찾았으므로 반복문 종료
            }
        }
        return parsingToken;
    }

    /** 입력한 예약어를 추가하는 메소드
     * @param keyword 추가 예약
     */
    void addKeyWord(String keyword) {
        parser.keyWord.add(keyword);
//        System.out.println("키워드추가완료");
    }

    /** 입력한 함수를 추가하는 메소드
     * @param function 추가 함수
     */
    void addFunction(String function) {
        parser.function.add(function);
//        System.out.println("펑션추가완료");
    }
}
