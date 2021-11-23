package com.projects;

import java.util.Arrays;
import java.util.Scanner;

public class CinemaGrid {
    final int numRows;
    final int numSeats;
    final char[][] cinemaMatrix;
    final int totalSeats;
    int ticketsPurchased;
    int currentIncome;
    final Scanner scanner;

    public CinemaGrid(int numRows, int numSeats) {
        this.numRows = numRows;
        this.numSeats = numSeats;
        this.cinemaMatrix = new char[numRows][numSeats];
        this.totalSeats = numRows * numSeats;
        this.scanner = new Scanner(System.in);
        this.ticketsPurchased = 0;
        this.currentIncome = 0;
    }

    int calculateTotalincome() {
        int numFrontRows = cinemaMatrix.length / 2;
        int numBackRows = numRows - numFrontRows;
        System.out.println();
        if (totalSeats < 60) {
            return numRows * numSeats * 10;
        } else {
            return (numFrontRows * numSeats * 10) + (numBackRows * numSeats * 8);
        }
    }

    int getMenuOption() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    int calculateSeatPrice(int rowNumber) {
        int frontRows = cinemaMatrix.length / 2;
        System.out.println();
        if (totalSeats < 60) {
            System.out.println("Ticket price: $10");
            return 10;
        } else if (rowNumber <= frontRows) {
            System.out.println("Ticket price: $10");
            return 10;
        } else {
            System.out.println("Ticket price: $8");
            return 8;
        }
    }

    void fillCinema() {
        for (char[] matrix : cinemaMatrix) {
            Arrays.fill(matrix, 'S');
        }
    }

    void printCinema() {
        System.out.println();
        System.out.println("Cinema:");
        // print seat numbers
        for (int i = 0; i < numSeats + 1; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }

        System.out.println();

        for (int i = 0; i < numRows; i++) {
            // print row number
            System.out.print(i + 1 + " ");
            for (int j = 0; j < numSeats; j++) {
                // print seat
                System.out.print(cinemaMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void bookSeat() {

        boolean seatIsNotAvailable = true;

        while (seatIsNotAvailable) {
            // get seat choice from user
            System.out.println();
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");

            int seatNumber = scanner.nextInt();

            if (rowNumber < 1 || rowNumber > numRows || seatNumber < 1 || seatNumber > numSeats) {
                System.out.println("Wrong input!");
            } else if (cinemaMatrix[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                // calculate seat price
                int ticketprice = calculateSeatPrice(rowNumber);
                // mark seat as booked
                cinemaMatrix[rowNumber - 1][seatNumber - 1] = 'B';
                // increment tickets purchased
                ticketsPurchased++;
                // add ticket price to total income
                currentIncome += ticketprice;
                seatIsNotAvailable = false;
            }
        }
    }

    public void showStatistics() {
        int totalincome = calculateTotalincome();
        double percentageTickets = (Float.intBitsToFloat(ticketsPurchased) / Float.intBitsToFloat(totalSeats)) * 100;
        System.out.printf("Number of purchased tickets: %d", ticketsPurchased);
        System.out.println();
        System.out.printf("Percentage: %.2f%%", percentageTickets);
        System.out.println();
        System.out.printf("Current income: $%d", currentIncome);
        System.out.println();
        System.out.printf("Total income: $%d", totalincome);
        System.out.println();
    }
}
