package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    class Greeting {
        public String getGreeting() {
            return "Greetings from Spring Boot!";
        }
    }

    @RequestMapping("/")
    public Greeting index() {
        return new Greeting();
    }

}