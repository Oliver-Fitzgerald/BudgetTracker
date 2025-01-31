package com.userInterface ;

import java.io.File ;
import java.io.FileWriter ;
import java.io.BufferedWriter ;
import java.io.IOException ;
import java.util.Scanner ;


/*
 * This class contains the interface to allow the user to add, edit or remove tranactions
 */
public class AddOrEditTracactionDialog {

    //Constants
    final private static String RESET = "\u001B[0m" ;
    final private static String RED = "\u001B[31m" ;
    final private static String horizontalRule = "\n------------------------------------------------\n\n" ;

    //Global
    private static Scanner scanner;
    private static BufferedWriter write ; 

    /**
     * mainMenu
     * Prompts the user with a selection of operations that they can perform in relation to adding, removing or editing a transaction
     */
    public static void mainMenu() {

        scanner = new Scanner(System.in) ;
        boolean run = true ;

        while (run) {
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

                System.out.printf("\n%sERROR: %s The input \"%s\" is invalud please enter a number to indicate the option you desire\n\n",RED, RESET, rawResponse) ;
                response = -1 ;
            }

            //handle response 
            switch (response) {

                case 0 :
                    return ;
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
    }

    /**
     * add
     * Adds a transaction to the users history
     */
    private static void add() {

        try { 
            write = new BufferedWriter(new FileWriter(new File("resources/transactions.csv"))) ;

        } catch ( IOException exception) {
            // !!! To Do: handle 
        }
    }

    /**
     * remove
     * Removes a transaction from the users histiory
     * note: a transaction cannot be permenantly removed only marked as removed
     */
    private static void remove() {

        try { 
            write = new BufferedWriter(new FileWriter(new File("resources/transactions.csv"))) ;

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
            write = new BufferedWriter(new FileWriter(new File("resources/transactions.csv"))) ;

        } catch ( IOException exception) {
            // !!! To Do: handle 
        }
    }
}
