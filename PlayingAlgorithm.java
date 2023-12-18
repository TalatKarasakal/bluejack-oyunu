package bluejack;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

import bluejack.ShufflingCards.Card;

public class PlayingAlgorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    private ShufflingCards shufflingCards;
    //private ShufflingCards.Card drawnCard = shufflingCards.new Card("", "");
    private ShufflingCards.Card drawnCard;

    private int userTableIndex = 0;
    private int computerTableIndex = 0;

    private ShufflingCards.Card[] userTable;
    private ShufflingCards.Card[] computerTable;
    
    public PlayingAlgorithm(ShufflingCards shufflingCards, ShufflingCards.Card[] userTable, ShufflingCards.Card[] computerTable) {
        
        this.shufflingCards = shufflingCards;
        this.drawnCard = shufflingCards.new Card("", "");
    }
    
    int userSum=0;
    int computerSum=0;
        
    int userScore=0;
    int computerScore=0;
    
    ShufflingCards.Card[] userHand = shufflingCards.getUserHand();
    ShufflingCards.Card[] computerHand = shufflingCards.getComputerHand();
    
    int deckIndex = 0;
    boolean gameover=false;
    boolean tourover=false;

    public void playGame() {

        int userHandIndex=0;
        int computerHandIndex=0;

    while (gameover == false) {

        if(userScore==3||computerScore==3){
            System.out.println("The game has finished.");
            }

        if(userScore == 3){
                System.out.println("User won the game.");
                System.out.println("Congratulations !!!");
                gameover = true;
            }

        if(computerScore == 3){
                System.out.println("Computer won the game.");
                System.out.println("Nice try ");
                System.out.println("we wait again");
                gameover = true;
            }
        
        while(tourover == false) {

            drawnCard = shufflingCards.drawUnusedCard(); // draw an unused card
            System.out.println("Unused card drawn: " + drawnCard);

            userTable[userTableIndex++] = drawnCard;

            //drawnCard = shufflingCards.drawCard(); // draw a card for user
            System.out.println("User drawn card: " + drawnCard); // show the drawn card to user
            //shufflingCards.userHand[userHandIndex++] = new Card(drawnCard.getValue(), drawnCard.getColor()); // Add the drawn card to user's table

            calculateUserTableSum(userTable); // Calculate user table sum
            System.out.println("User table sum: " + userTable + "Computer table sum: " + computerTable); // Show player's sum
            
            System.out.println("Enter 'P' to play a card, or 'C' to continue:"); // give a choise to user using a card from his/her hand or wait for the other tour
            String userChoice = scanner.nextLine();

            if("P".equals(userChoice)){ // using a card
                System.out.println("Enter the number of the card you want to play (1-2-3-4):");
                int cardNumber = scanner.nextInt();
                scanner.nextLine();
                
                System.out.println(Arrays.toString(userHand)); // show his/her hand deck
            
                userSum = calculateUserTableSum(userTable);
                computerSum = calculateComputerTableSum(computerTable);

                if (cardNumber >= 1 && cardNumber <= 4) { // control for a selectable selection
                    
                    int selectedCardIndex = cardNumber - 1;

                    Card selectedCard = userHand[selectedCardIndex];

                    if (selectedCard != null) { // control for a selectable selection
                        
                        userSum += Integer.parseInt(drawnCard.getValue());

                        System.out.println("User played the card: " + selectedCard); // show the card that has been used

                        userHand[selectedCardIndex] = null; // remove the card has been used

                    } else {
                        System.out.println("Invalid card number. Please choose a card that is not null."); // if user do not select a selectable card the system warns
                    }
                } else {
                    System.out.println("Invalid card number. Please enter a number between 1 and 4."); // if user do not select a selectable card the system warns
                }
            }else if("C".equals(userChoice)){ // if user want to wait for next tour, It's time for the computer

            //drawnCard = shufflingCards.drawCard();// draw a card for computer
            //shufflingCards.computerHand[computerHandIndex++] = new Card(drawnCard.getValue(), drawnCard.getColor());// add the drawn card to computer hand
            System.out.println("Computer drawm card: " + drawnCard); // Print drawn card by computer
            int drawnCardValue = getValue(drawnCard);
            
            calculateComputerTableSum(computerTable); //Calculate computer table sum

            for(int i = 0 ; i < computerHand.length ; i++){
                ShufflingCards.Card currentCard = computerHand[i];
                int cardValue = getValue(currentCard);
                if(20 - cardValue == computerSum){
                    computerTableIndex++;
                    computerHand[i] = computerTable[computerTable.length - 1];
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + computerTable);
                    calculateComputerTableSum(computerTable);
                    System.out.println("Computer table sum: " + computerSum);
                    computerHand[i] = null;
                }
                if(20 + cardValue == computerSum){
                    computerTableIndex++;
                    computerHand[i] = computerTable[computerTable.length - 1];
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + computerTable);
                    calculateComputerTableSum(computerTable);
                    System.out.println("Computer table sum: " + computerSum);
                    computerHand[i] = null;
                }
                if (cardValue == 0) {
                    if(drawnCardValue*2 + computerSum == 20){
                        computerTableIndex++;
                        //computerTable[computerTable.length - 1] =  "2x";
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + computerTable);
                        calculateComputerTableSum(computerTable);
                        computerSum += drawnCardValue*2;
                        System.out.println("Computer table sum: " + computerSum);
                        computerHand[i] = null;
                    }
                    if(-1*drawnCardValue + (computerSum - drawnCardValue) == 20){
                        computerTableIndex++;
                        //computerTable[computerTable.length - 1] = "+/-";
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + computerTable);
                        computerSum -= 2*(-1*drawnCardValue);
                        System.out.println("Computer table sum: " + computerSum);
                        computerHand[i] = null;
                    }
                } 
                else{
                        System.out.println("Computer did not play a card.");
                    }
            }
            
        if(userSum==20){ //Checking if the user won or not
            userScore++;
            System.out.println("User won the tour !!!");
        }
        if(computerSum==20){ //Checking if the computer won or not
            computerScore++;
            System.out.println("Computer won the tour :(");
        }

        System.out.println("User: " + userScore + ", Computer: " + computerScore); // show the score table
        System.out.println(Arrays.toString(userHand)); // show user hand 

            }else{
                System.out.println("Invalid choice. Please enter 'P' to play a card, or 'C' to continue.");
            }
        }        
    }
}    
    public int calculateUserTableSum(ShufflingCards.Card[] userTable) {
        
        for (ShufflingCards.Card card : userTable) {
            if (card.value.equals("1")) {
                userSum += 1;
            } else if (card.value.equals("2")) {
                userSum += 2;
            } else if (card.value.equals("3")) {
                userSum += 3;
            } else if (card.value.equals("4")) {
                userSum += 4;
            } else if (card.value.equals("5")) {
                userSum += 5;
            } else if (card.value.equals("6")) {
                userSum += 6;
            } else if (card.value.equals("7")) {
                userSum += 7;
            } else if (card.value.equals("8")) {
                userSum += 8;
            } else if (card.value.equals("9")) {
                userSum += 9;
            } else if (card.value.equals("10")) {
                userSum += 10;
            } else if (card.value.equals("-1")) {
                userSum += -1;
            } else if (card.value.equals("-2")) {
                userSum += -2;
            } else if (card.value.equals("-3")) {
                userSum += -3;
            } else if (card.value.equals("-4")) {
                userSum += -4;
            } else if (card.value.equals("-5")) {
                userSum += -5;
            } else if (card.value.equals("-6")) {
                userSum += -6;
            }
        }
        return userSum;
    }

    public int calculateComputerTableSum(ShufflingCards.Card[] computerTable) {
        
        for (ShufflingCards.Card card : computerTable) {
            if (card.value.equals("1")) {
                computerSum += 1;
            } else if (card.value.equals("2")) {
                computerSum += 2;
            } else if (card.value.equals("3")) {
                computerSum += 3;
            } else if (card.value.equals("4")) {
                computerSum += 4;
            } else if (card.value.equals("5")) {
                computerSum += 5;
            } else if (card.value.equals("6")) {
                computerSum += 6;
            } else if (card.value.equals("7")) {
                computerSum += 7;
            } else if (card.value.equals("8")) {
                computerSum += 8;
            } else if (card.value.equals("9")) {
                computerSum += 9;
            } else if (card.value.equals("10")) {
                computerSum += 10;
            } else if (card.value.equals("-1")) {
                computerSum += -1;
            } else if (card.value.equals("-2")) {
                computerSum += -2;
            } else if (card.value.equals("-3")) {
                computerSum += -3;
            } else if (card.value.equals("-4")) {
                computerSum += -4;
            } else if (card.value.equals("-5")) {
                computerSum += -5;
            } else if (card.value.equals("-6")) {
                computerSum += -6;
            }
        }return computerSum;

    }
    public int getValue(ShufflingCards.Card card) { 
        String value = card.getValue();

        switch (value) {
            case "-1":
                return -1;
            case "-2":
                return -2;
            case "-3":
                return -3;
            case "-4":
                return -4;
            case "-5":
                return -5;
            case "-6":
                return -6;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "+/-":
            case "2x":
                return 0;
            default:
                return Integer.parseInt(value);
        }
        }
}
