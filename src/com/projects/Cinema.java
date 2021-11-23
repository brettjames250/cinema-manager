package com.projects;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get rows and seats from user
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        // create new cinema, fill and print
        CinemaGrid newCinema = new CinemaGrid(numRows, numSeats);
        newCinema.fillCinema();

        // get menu option from user
        int chosenOption;
        boolean ticketOfficeOpen = true;

        while (ticketOfficeOpen) {

            chosenOption = newCinema.getMenuOption();

            switch (chosenOption) {
                case 1:
                    newCinema.printCinema();
                    break;
                case 2:
                    newCinema.bookSeat();
                    break;
                case 3:
                    newCinema.showStatistics();
                    break;
                case 0:
                    ticketOfficeOpen = false;
            }
        }
    }
}
