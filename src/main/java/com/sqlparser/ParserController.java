package com.sqlparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ParserController {

    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    /**
     * @param model 파싱처리된 쿼리 데이터를 전달한 모델 객체
     * @param session 세션에 저장된 쿼리 데이터를 호출하기 위함
     * @return 메인화면 리턴
     */
    @GetMapping("/")
    public String viewMain(Model model, HttpSession session) {

        if (session.getAttribute("parsedQuery") != null) {
            model.addAttribute("parsedQuery", session.getAttribute("parsedQuery"));
        }
        return "index";
    }

    /** 입력한 쿼리문 파싱처리 및 세션에 데이터 저장
     * @param parser 입력한 쿼리문
     * @param session 파싱처리된 쿼리문을 담기 위한 세션
     * @return 메인페이지
     */
    @PostMapping("/")
    public String postParsingQuery(@ModelAttribute("parser") Parser parser, HttpSession session) {
        String[] lines = parser.inputQuery.split(System.lineSeparator()); //운영체제에 맞는 개행문자로 라인을 나눔
        session.setAttribute("parsedQuery", parserService.parsingQuery(lines));
        return "index";
    }

    /** 입력한 예약어를 기존 예약어 목록에 더함
     * @param parser 입력한 예약어
     */
    @PostMapping("/addKeyword")
    public String addKeyword(@ModelAttribute("parser") Parser parser) {
        String keyword = parser.inputQuery.toUpperCase();
        parserService.addKeyWord(keyword);
        return "index";
    }

    /** 입력한 함수를 기존 함수 목록에 더함
     * @param parser 입력한 함수
     */
    @PostMapping("/addFunction")
    public String addFunction(@ModelAttribute("parser") Parser parser) {
        String function = parser.inputQuery.toUpperCase();
        parserService.addFunction(function);
        return "index";
    }
}
