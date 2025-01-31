import java.time.LocalDate ;

public class Transaction {

    char frequency ; // r = regular / i = irregular
    char incomeOrExpenditure ; // i = income / e = expenditure
    LocalDate date ;
    private int ID ; 

    public Transaction(int ID) {

       this.ID = ID ;
    }
    
    public class Details {

        String title ;
        String description ;
        float amount ;
    }
}
