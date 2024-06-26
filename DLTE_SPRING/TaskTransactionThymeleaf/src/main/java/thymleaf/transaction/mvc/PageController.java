package thymleaf.transaction.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thymleaf.transaction.Entity.Transactions;
import thymleaf.transaction.Exceptions.TransactionException;
import thymleaf.transaction.Services.TransactionsService;

import java.text.SimpleDateFormat;
import java.util.Date;

    public class PageController {
        @Autowired
        TransactionsService transactionService;
        @RequestMapping(value = "/",method = RequestMethod.GET)
        public String showIndexPage(Model model) {
            model.addAttribute("transaction", new Transactions());
            return "index";
        }
        @RequestMapping(value = "/send",method = RequestMethod.POST)
        public String newTransaction(@ModelAttribute("transaction") Transactions transactionEntity, Model model, RedirectAttributes redirectAttributes) {
            try {
                transactionService.apiInsert(transactionEntity);
                redirectAttributes.addFlashAttribute("message", "Transaction done successfully"+"");
                return "redirect:/web/accept";
            } catch (TransactionException exception) {
                model.addAttribute("error", exception.getMessage());
                return "index";
            }
        }
        @RequestMapping(value = "/accept",method = RequestMethod.GET)
        public String transactionConfirmation(Model model) {
            return "NewTransactions";
        }
        @InitBinder
        public void initBinder(WebDataBinder binder) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        }
    }

