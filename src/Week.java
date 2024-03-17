import java.time.LocalDate;
import java.util.ArrayList;

public class Week {
    private LocalDate monday ;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>() ;
    public Week(LocalDate monday){
        this.monday = monday;
    }
    public LocalDate getMonday(){
        return monday ;
    }
    public void addTransaction(Transaction item){
        transactions.add(item) ;
    }
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }
}
