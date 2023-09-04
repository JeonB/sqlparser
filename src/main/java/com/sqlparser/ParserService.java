package com.sqlparser;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParserService {

    Parser parser = new Parser();

    /**
     * @param lines
     * @return
     */
    ArrayList<String> parsingQuery(String[] lines) {

        ArrayList<String> parsedSQLQuery = new ArrayList<>();

        for (String line : lines) {

            if (line.contains("--")) {
                parsedSQLQuery.add(line + " -> 주석");
                continue;
            }
            String[] tokens = line.split("\\s");
            for (String token : tokens) {
                parsedSQLQuery.add(parsingToken(token));
            }
        }

        return parsedSQLQuery;
    }

    /**
     * @param token 쿼리문 파싱 함수. 주석에 대한 파싱은 따로 처리
     * @return
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
     * @param token
     * @return
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
}
