package spring.project.springbootrestcontroller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class Dashboard {
    private List<Loans> loanList = new ArrayList<>();
    public Dashboard() {
        // Adding some sample loans
        loanList.add(new Loans("Annapoorna", 1, 100000));
        loanList.add(new Loans("Akshatha" ,2, 150000));
        loanList.add(new Loans("Raksha", 3, 200000));
    }
    @GetMapping("/{index}")
    public Loans getLoan(@PathVariable int index) {
        if (index >= 0 && index < loanList.size()) {
            return loanList.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }
    @PostMapping("/Addloan")
    public Loans addLoan(@RequestBody Loans loan) {
        loanList.add(loan);
        return loan;
    }
}
