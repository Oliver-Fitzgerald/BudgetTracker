package com.CLIInterface ;

import java.io.File ;
import java.io.FileWriter ;
import java.io.FileReader ;
import java.io.BufferedWriter ;
import java.io.BufferedReader ;
import java.io.IOException ;
import java.util.Scanner ;
import java.time.LocalDate ;
import java.time.format.DateTimeFormatter;
import java.util.regex.* ;

/*
 * This class contains the interface to allow the user to add, edit or remove tranactions
 */
public class AddOrEditTracactionDialog {

    //Constants
    final private static String RESET = "\u001B[0m" ;
    final private static String RED = "\u001B[31m" ;
    final private static String horizontalRule = "\n------------------------------------------------\n\n" ;
    final private static String transactionsFilePath = "src/main/resources/transactions.csv" ;
    //Global
    private static Scanner scanner;
    private static BufferedWriter writer ; 
    private static BufferedReader readGlobal ; 
    private static BufferedWriter writeGlobal; 
    private static int ID = -1;

    /**
     * mainMenu
     * Prompts the user with a selection of operations that they can perform in relation to adding, removing or editing a transaction
     */
    public static void mainMenu() {

        //get global id
        try {
            readGlobal = new BufferedReader(new FileReader("src/main/resources/global.csv")) ;
            ID = Integer.parseInt(readGlobal.readLine()) ;

            readGlobal.close() ;

        } catch (IOException | NumberFormatException exception) {

            System.out.println(exception.getMessage()) ;
        } 

        
        boolean run = true ;
        while (run) {

            scanner = new Scanner(System.in) ;
            System.out.print(horizontalRule) ;

            //menu
            System.out.println("select one of the following options by entering its position in the list\n") ;
            System.out.println("0. return") ;
            System.out.println("1. add a new transaction") ;
            System.out.println("2. edit an existing transaction") ;
            System.out.println("3. remove a transaction") ;


            //get response 
            String rawResponse = scanner.nextLine() ; 
            byte response ;
            try {
                response = Byte.parseByte(rawResponse) ;
                if (response < 0 || response > 3)
                    throw new NumberFormatException() ;

            } catch (NumberFormatException exception) {

                System.out.printf("%sERROR: %s The input \"%s\" is invalud please enter a number to indicate the option you desire\n",RED, RESET, rawResponse) ;
                response = -1 ;
            }

            //handle response 
            switch (response) {

                case 0 :
                    run = false ;
                    break ;
                case 1 :
                    add() ;
                    break ;
                case 2 :
                    remove() ;
                    break ;
                case 3 :
                    edit() ;
                    break ;
            }
        }


        //set global id
        try {
            writeGlobal = new BufferedWriter(new FileWriter("src/main/resources/global.csv")) ;
            writeGlobal.write(String.valueOf(ID)) ;
            writeGlobal.flush() ;
            writeGlobal.close() ;

        } catch (IOException | NumberFormatException exception) {

            System.out.println(exception.getMessage()) ;
        } 
    }

    /**
     * add
     * Adds a transaction to the users history
     */
    private static void add() {

        //transaction paramaters
        char frequency = 'N' ; 
        char incomeOrExpenditure = 'N' ; 
        String title = "not-identified" ;
        String description = "not-identified" ;
        float amount = 0 ;
        LocalDate date = LocalDate.now() ; 


        //other
        scanner = new Scanner(System.in) ;
        boolean invalidInput = true ;

        try { 
            writer = new BufferedWriter(new FileWriter(transactionsFilePath, true)) ;
            String input = "null" ;

            //title
            System.out.print("Title: ") ;
            title = scanner.nextLine() ;
            if (title.equals(""))
                title = "not-identified" ;
            System.out.println() ;

            
            //description
            System.out.print("Description: ") ;
            description = scanner.nextLine() ;
            if (description.equals(""))
                description = "not-identified" ;
            System.out.println() ;


            //amount
            boolean validInput = false ;
            while (!validInput) {

                System.out.print("Amount: ") ;
                input = scanner.nextLine() ;
                try {
                    amount = Float.parseFloat(input) ;
                    validInput = true ;

                } catch (NumberFormatException exception) {

                    System.out.printf("%sERROR: %s The input \"%s\" is invalud please enter a number to indicate the option you desire\n",RED, RESET, input) ;
                }
            }
            System.out.println() ;


            //date
            System.out.println("Please enter the date of the transaction in the format \"dd/mm/yyyy\"") ;
            Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$");
            Matcher matcher = pattern.matcher(input);
            while ( !matcher.matches() ) {

                System.out.print("Date: ") ;
                input = scanner.nextLine() ;
                matcher = pattern.matcher(input);

                if ( matcher.matches() ) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    date = LocalDate.parse(input, formatter);
                } else
                    System.out.printf("%sERROR: %s The input \"%s\" is invalid please enter the date of the transaction in the format dd/mm/yyyy\n",RED, RESET, input) ;
                
            }
            System.out.println() ;


            //income or expenditure
            System.out.println("Please enter the  \"i\" for income or \"e\" for expenditure to accuratly indicate the nature of this transaction") ;
            while (incomeOrExpenditure == 'N') {

                System.out.print("Income or Expenditure: ") ;
                input = scanner.nextLine() ;

                if ( input.equals("i") || input.equals("e") )
                    incomeOrExpenditure = input.charAt(0) ;
                else
                    System.out.printf("%sERROR: %s The input \"%s\" is invalid please enter \"i\" for income or \"e\" for expenditure to indicate the option you desire\n",RED, RESET, input) ;
                
            }
            System.out.println() ;


            //frequency
            System.out.println("Please enter the frequency of this transaction as \"i\" for irregular or \"r\" for regular or \"n\" for not identified") ;
            while (frequency == 'N') {

                System.out.print("Frequency: ") ;
                input = scanner.nextLine() ;

                if ( input.equals("i") || input.equals("r") || input.equals("n") )
                    frequency = input.charAt(0) ;
                else
                    System.out.printf("%sERROR: %s The input \"%s\" is invalid please enter \"i\" for irregular or \"r\" for regular or \"n\" for not identified to indicate the option you desire\n",RED, RESET, input) ;
                
            }
            System.out.println() ;


            //create transaction
            ID++ ;
            writer.newLine() ;
            writer.write(ID + "," + title + "," + description + "," + amount + "," + date + "," + incomeOrExpenditure + "," + frequency) ;
            writer.flush() ; 
            writer.close() ; 


        } catch ( IOException exception) {
            System.out.printf("%sError: %s%s\n", RED, RESET, exception.getMessage()) ;

        }
    }

    /**
     * remove
     * Removes a transaction from the users histiory
     * note: a transaction cannot be permenantly removed only marked as removed
     */
    private static void remove() {

        try { 
            writer = new BufferedWriter(new FileWriter(new File("resources/transactions.csv"))) ;

        } catch ( IOException exception) {
            // !!! To Do: handle 
        }
    }

    /**
     * edit
     * Allows the user to edit the details of the transactions they have created
     * note: id cannot be altered
     */
    private static void edit() {

        try { 
            writer = new BufferedWriter(new FileWriter(new File("resources/transactions.csv"))) ;

        } catch ( IOException exception) {
            // !!! To Do: handle 
        }
    }
}
