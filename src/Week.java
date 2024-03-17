import java.time.LocalDate;
import java.util.ArrayList;

public class Week {
    private LocalDate monday ;
    private ArrayList<Transaction> items = new ArrayList<Transaction>() ;
    public Week(LocalDate monday){
        this.monday = monday;
    }
    public LocalDate getMonday(){
        return monday ;
    }
    public void addTransaction(Transaction item){
        items.add(item) ;
    }

}
