package spring.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.jpa.model.Account;
import spring.jpa.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountApi {
    @Autowired
    AccountService accountService;
    @PostMapping(value = "/")
    public Account apiSave(@RequestBody Account account){
        return accountService.callSave(account);
    }

    @PutMapping(value="/update",consumes = "application/json")
    public Account apiUpdate(@RequestBody Account account){
        return accountService.callSave(account);
    }

    @GetMapping(value = "/find")
    public List<Account> apiCallAll(){
        return accountService.callAll();
    }
}
