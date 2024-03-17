import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddTransaction {


    @ParameterizedTest
    @CsvSource({

            "true,Wage,Meadow Court,400,19-03-2024"
    })
    public void incomeTransaction(boolean income, String title, String description, double price, String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") ;
        LocalDate date = LocalDate.parse(stringDate, formatter);

        Interface.addTransaction(income,title,description,price,date);
        Interface.display();
    }


}
