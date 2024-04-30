package project.backend.mvc;

import mybank.dao.interfaces.LoansInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weblogin")
public class LoansUiController {
    @Autowired
    LoansInterface loansInterface;

    @GetMapping("/")
    public String landing(){

        return "index";
    }
    @GetMapping("/dashboard")
    public String dashboard(){

        return "dashboard";
    }
}