import java.time.LocalDate;

public class Transaction {
    private String title ;
    private String descrition ;
    private double price ;
    private LocalDate date ;

    public Transaction(String title, String descrition, double price, LocalDate date){
        this.title = title;
        this.descrition = descrition;
        this.price = price;
        this.date = date;
    }
    public String getTitle(){return title ;}
    public String getDescription(){return descrition ;}
    public double getPrice(){return price ;}
}

