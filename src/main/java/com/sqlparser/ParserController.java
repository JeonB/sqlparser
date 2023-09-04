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
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/")
    public String viewMain(Model model,HttpSession session) {

        if (session.getAttribute("parsedQuery") != null) {
            model.addAttribute("parsedQuery",session.getAttribute("parsedQuery"));
        }

        return "index";
    }

    /**
     * @param parser
     * @param session
     * @return
     */
    @PostMapping("/")
    public String postParsingQuery(@ModelAttribute("parser") Parser parser, HttpSession session) {
        String[] lines = parser.inputQuery.split("\n");
        session.setAttribute("parsedQuery",parserService.parsingQuery(lines));
        return "index";
    }
}
