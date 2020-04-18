package net.zonia3000.demo;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    // Possible CSRF detected if this flag is false
    private boolean working = false;

    @GetMapping(value = "/authorize")
    public void authorize(@RequestParam("redirect_uri") String redirect, @RequestParam("state") String state,
            HttpServletResponse response) throws IOException {
        response.sendRedirect(redirect + "?state=" + state + "&code=fake");
    }

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String tokenFromCode() {
        return "{\n"
                + "       \"access_token\":\"2YotnFZFEjr1zCsicMWpAA\",\n"
                + "       \"token_type\":\"example\",\n"
                + "       \"expires_in\":" + (working ? 3600 : -3600) + ",\n"
                + "       \"refresh_token\":\"tGzv3JOkF0XG5Qx2TlKWIA\"\n"
                + "     }";
    }

    @PostMapping(value = "/check_token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkToken() {
        return "{\n"
                + "       \"access_token\":\"2YotnFZFEjr1zCsicMWpAA\",\n"
                + "       \"user_name\":\"foo\",\n"
                + "       \"exp\":3600,\n"
                + "       \"refresh_token\":\"tGzv3JOkF0XG5Qx2TlKWIA\",\n"
                + "       \"client_id\":\"clientid\"\n"
                + "     }";
    }
}
