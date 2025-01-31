package com.userInterface ;

import java.util.Scanner ;

//interfaces
import com.userInterface.AddOrEditTracactionDialog ;

/*
 * This is the entry point for the program and serves as the core of the interface
 */
public class MyBudget {

    //Constants
    final private static String RESET = "\u001B[0m" ;
    final private static String RED = "\u001B[31m" ;
    final private static String horizontalRule = "\n------------------------------------------------\n\n" ;

    //Global
    private static Scanner scanner;
    //final private static BufferedWriter write = new BufferedWriter(new FileWriter(new File("resources/transactions.csv"))) ;

    public static void main(String args[]) {



        boolean run = true ;
        while (run) {

            System.out.print(horizontalRule) ;

            byte response = mainMenu() ;
            switch (response) {

                case -1:
                    break ;
                case 0 :
                    run = false ;
                    break ;
                case 1 :
                    AddOrEditTracactionDialog.mainMenu() ;
                    break ;
            }
        }
    }

    private static byte mainMenu() {

        scanner = new Scanner(System.in) ;

        System.out.println("        Main Menu") ;
        System.out.println("select one of the following options by entering its position in the list\n") ;
        System.out.println("0. quit") ;
        System.out.println("1. add/edit transactions") ;

        String rawResponse = scanner.nextLine() ; 
        byte response ;
        try {
            response = Byte.parseByte(rawResponse) ;
            if (response < -1 || response > 1)
                throw new NumberFormatException() ;
        } catch (NumberFormatException exception) {

            System.out.println(exception.getMessage()) ;
            System.out.printf("\n%sERROR: %s The input \"%s\" is invalud please enter a number to indicate the option you desire\n\n",RED, RESET, rawResponse) ;
            return -1 ;
        }

        return response;
    }
}
