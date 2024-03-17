import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.*;

public class Interface{

    public static ArrayList<Week> lifeTime = new ArrayList<Week>() ;
    public static void main(String[] args){

        Scanner input = new Scanner(System.in) ;
        int choice ;
        while (true) {
            //Main Menu
            choice = 0 ;
            System.out.println("----Main Menu-----");
            System.out.println("1. add income");
            System.out.println("2. Add expenditure");
            System.out.println("3. Display budget");
            System.out.println("4. Quit");
            out.println("------------------" + '\n');

            while (choice == 0) {
                System.out.print("Enter option number: ");
                try{
                try {
                    choice = input.nextInt();
                    if (choice == 0)
                        throw new InvalidInputException("0 is not a valid choice unfortunately better luck next time") ;
                }catch (InputMismatchException e) {
                    throw new InvalidInputException("This is not a valid choice unfortunately better luck next time");
                }
                }catch (InvalidInputException e){
                    out.println(e.getMessage());
                    input.nextLine() ;
                }
            }
            out.println();
                if (choice == 1 || choice == 2) {
                    System.out.print("Enter the transactions title: ");
                    String title = input.next();

                    System.out.print("Enter a transaction description: ");
                    String description = input.next() ;

                    double price = 0;
                    while (price == 0) {
                        try{
                        try {
                            System.out.print("Enter a transaction price: ");
                            input.nextLine();
                            price = input.nextDouble();
                            if (price == 0)
                                throw new InvalidInputException("price cannot be 0");
                            else if (choice == 1 && price < 0)
                                throw new InvalidInputException("Income cannot be negative") ;
                            else if (choice == 2 && price < 0)
                                throw new InvalidInputException("Enter the expenditure as a positive number") ;
                        }catch (InputMismatchException e){
                            throw new InvalidInputException("Not a number") ;
                        }}catch (InvalidInputException e){
                            out.println(e.getMessage());
                            price = 0;
                        }
                    }

                    LocalDate date = null ;
                    while (date == null) {
                            System.out.print("Enter the date of the transaction: "); //
                            String dateString = input.next();
                            out.println();
                            try{
                            try {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                date = LocalDate.parse(dateString, formatter);
                            }catch (DateTimeParseException e){
                                try {
                                    if (e.getMessage().contains("DayOfMonth"))
                                        throw new InvalidInputException("Invalid day value: " + dateString.substring(-1,2)) ;
                                    else if (e.getMessage().contains("MonthOfYear"))
                                        throw new InvalidInputException("Invalid month value: " + dateString.substring(3,5) + " must be from 00 -> 12") ;

                                }catch (IndexOutOfBoundsException exception){
                                    throw new InvalidInputException("Missing information in: " + dateString + '\n' + "Correct format: dd-mm-yyyy") ;
                                }

                                throw new InvalidInputException("Incorrect date format: " + dateString + '\n' + "Correct format: dd-mm-yyyy") ;
                            }}catch (InvalidInputException e){
                                out.println(e.getMessage());
                            }


                    }

                    if (choice == 1)
                        addTransaction(true, title, description, price,date ) ;
                    else if (choice == 2)
                        addTransaction(false, title, description, price, date);
                } else if (choice == 3)
                    display();
                else if (choice == 4) {
                    exit(0) ;
                }


        }
    }
    /**
     *Displays all income and expenditure by week
     **/
    public static void display(){
        System.out.println();
        //Print dates of each week
        for (Week week: lifeTime)
            out.print("          ------  " + week.getMonday() + "  -----");

        System.out.println();
        //Print headings
        System.out.print("          Income");
        int length = 20 ;
        for (int i = 0; i < length; i++)
            out.print(" ");
        System.out.print("Expenditure");

        System.out.println();
        //print items
        double[] totalIncome = new double[lifeTime.size()] ;
        double[] totalExpenditure = new double[lifeTime.size()] ;

        for (int x = 0; x < lifeTime.size();x++){
            Week week = lifeTime.get(x) ;
            for (int number = 0; number < week.getTransactions().size(); number++){
                Transaction transaction = week.getTransactions().get(number) ;
                for (int i = 0; i < 10;i++)
                    out.print(" ");
                out.print(transaction.getTitle() + "  " + transaction.getDescription()  + "  " + transaction.getPrice() + '\n');
                totalIncome[x] += transaction.getPrice() ;
            }

        }

        System.out.println();
        //print totals
        for (int i = 0; i < lifeTime.size();i++) {
            out.print("Sub Total:");
            out.print(totalIncome[i]);
        }
        out.println();

        System.out.print("Total:");

        System.out.println();
        //Display trajectory
        calcTrajectory();

    }
    /**
     * Adds an item to a specified week
     */
    public static void addTransaction(boolean income, String title, String description, double price, LocalDate date){

        //ADD TRANSACTION
        if (income == false)//i.e expenditure true
            price = -price ;

        Transaction transaction = new Transaction(title,description,price,date) ;

        //ADD TO WEEK
        LocalDate monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)) ;
        int weekCreated = 0 ;

        //week exists
        for (Week week: lifeTime){
            if (week.getMonday().equals(monday)) {
                week.addTransaction(transaction);
                lifeTime.add(week) ;
            }
            else weekCreated++ ;
        }
        //week doesn't exist
        if (weekCreated == lifeTime.size()){
            Week week = new Week(monday) ;
            week.addTransaction(transaction) ;
            lifeTime.add(week) ;
        }

    }
    public static void calcTrajectory(){


    }
}
