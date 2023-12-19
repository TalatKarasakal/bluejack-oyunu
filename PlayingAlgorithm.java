package bluejack;
//import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import bluejack.ShufflingCards.Card;

public class PlayingAlgorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    ShufflingCards shufflingCards = new ShufflingCards();
    
    private ShufflingCards.Card drawnCard;

    int userTableIndex = 0;
    int computerTableIndex = 0;

    private ShufflingCards.Card[] userTable = new Card[4];
    private ShufflingCards.Card[] computerTable = new Card[4];
    
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
    
    public PlayingAlgorithm(){
        playGame();
    }

    public void playGame() {
        
        int userHandIndex=0;
        int computerHandIndex=0;

    while (gameover == false) {

        
        while(tourover == false) {

            if(userSum==20){ //Checking if the user won or not
            userScore++;
            System.out.println("User won the tour !!!");
        }
        if(computerSum==20){ //Checking if the computer won or not
            computerScore++;
            System.out.println("Computer won the tour :(");
        }

        System.out.println("User: " + userScore + ", Computer: " + computerScore); // show the score table

            drawnCard = shufflingCards.drawUnusedCard(); // draw an unused card
            
            userTable[userTableIndex] = drawnCard; // add the drawn card to the user table

            System.out.println("User drawn card: " + drawnCard); // show the drawn card to user
            
            userSum = calculateUserTableSum(userTable); // Calculate user table sum
            System.out.println("User table sum: " + userSum); // Show player's sum
            userTableIndex++;
            System.out.println("Enter 'P' to play a card, or 'C' to continue:"); // give a choise to user using a card from his/her hand or wait for the other tour
            String userChoice = scanner.nextLine();

            if("P".equals(userChoice)){ // using a card
                System.out.println("Enter the number of the card you want to play (1-2-3-4):");
                int cardNumber = scanner.nextInt(); // selecting the card the user wants to play
                scanner.nextLine();
                
                System.out.println(userHand); // show his/her hand deck
            
                userSum = calculateUserTableSum(userTable);

                if (cardNumber >= 1 && cardNumber <= 4) { // control for a selectable selection
                    
                    int selectedCardIndex = cardNumber - 1; // -1 because of arrays' index start from 0

                    Card selectedCard = userHand[selectedCardIndex];

                    if (selectedCard != null) { // control for a selectable selection
                        
                        System.out.println("User played the card: " + selectedCard); // show the card that has been used

                        userTable[userTableIndex] = userHand[selectedCardIndex]; // add the card to the user table

                        userHand[selectedCardIndex] = null; // remove the card has been used

                        userTableIndex++;

                    } else {
                        System.out.println("Invalid card number. Please choose a card that is not null."); // if user do not select a selectable card the system warns
                    }
                } else {
                    System.out.println("Invalid card number. Please enter a number between 1 and 4."); // if user do not select a selectable card the system warns
                }
            }else if("C".equals(userChoice)){ // if user want to wait for next tour, It's time for the computer

            drawnCard = shufflingCards.drawUnusedCard(); // draw a card for computer
            computerTable[computerTableIndex] = drawnCard; // add the card to the computer table
            System.out.println("Computer drawn card: " + drawnCard); // show the card by computer to user
            
            computerSum = calculateComputerTableSum(computerTable); //Calculate computer table sum
            System.out.println("Computer sum: " + computerSum);
            computerTableIndex++;
            for(int i = 0 ; i < computerHand.length ; i++){
                ShufflingCards.Card currentCard = computerHand[i];
                int cardValue = getValue(currentCard);
                if(20 - cardValue == computerSum){
                    
                    computerTable[computerTableIndex] = computerHand[i]; 
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + computerTable);
                    computerSum = calculateComputerTableSum(computerTable);
                    System.out.println("Computer table sum: " + computerSum);
                    computerHand[i] = null;
                    computerTableIndex++;
                }
                if(20 + cardValue == computerSum){
                    
                    computerTable[computerTableIndex] = computerHand[i];
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + computerTable);
                    computerSum = calculateComputerTableSum(computerTable);
                    System.out.println("Computer table sum: " + computerSum);
                    computerHand[i] = null;
                    computerTableIndex++;
                }
                if (cardValue == 0) {
                    int drawnCardValue = getValue(drawnCard);
                    if(drawnCardValue*2 + computerSum == 20){
                        
                        computerHand[i] = computerTable[computerTableIndex];
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + computerTable);
                        computerSum += drawnCardValue*2;
                        System.out.println("Computer table sum: " + computerSum);
                        computerHand[i] = null;
                        computerTableIndex++;
                    }
                    if(-1*drawnCardValue + (computerSum - drawnCardValue) == 20){
                        
                        computerHand[i] = computerTable[computerTableIndex];
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + computerTable);
                        computerSum -= 2*(-1*drawnCardValue);
                        System.out.println("Computer table sum: " + computerSum);
                        computerHand[i] = null;
                        computerTableIndex++;
                    }
                } 
                else{
                        System.out.println("Computer did not play a card.");
                    }
            }
            
        /*if(userSum==20){ //Checking if the user won or not
            userScore++;
            System.out.println("User won the tour !!!");
        }
        if(computerSum==20){ //Checking if the computer won or not
            computerScore++;
            System.out.println("Computer won the tour :(");
        }*/

        System.out.println("User: " + userScore + ", Computer: " + computerScore); // show the score table 

            }else{
                System.out.println("Invalid choice. Please enter 'P' to play a card, or 'C' to continue.");
            }
        }
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
    }
}    
    public int calculateUserTableSum(ShufflingCards.Card[] userTable) {
        int userSum=0;
        for (ShufflingCards.Card card : userTable) {
            if(card != null){
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
            }}
        }
        return userSum;
        }

    public int calculateComputerTableSum(ShufflingCards.Card[] computerTable) {
        int computerSum=0;
        for (ShufflingCards.Card card : computerTable) {
            if(card != null){
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
            }}
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
