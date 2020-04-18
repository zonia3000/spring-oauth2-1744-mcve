package net.zonia3000.demo;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "/")
    public String home(Principal principal) {
        return "User=" + principal.getName();
    }
}
