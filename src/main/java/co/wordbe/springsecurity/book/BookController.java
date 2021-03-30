package co.wordbe.springsecurity.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("loginPage")
    public String loginPage() {
        return "loginPage";
    }
}
