package project.backend.mvc;

import mybank.dao.interfaces.LoansInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @GetMapping("/calculate")
    public String calculate(){
        return "calculate";
    }

    @GetMapping("viewAll")
    public String viewAll(){
        return "viewAll";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
