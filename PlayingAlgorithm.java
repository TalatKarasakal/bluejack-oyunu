package bluejack;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

import bluejack.ShufflingCards.Card;

public class PlayingAlgorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    private ShufflingCards shufflingCards;
    private ShufflingCards.Card drawnCard = shufflingCards.new Card("", "");

    public PlayingAlgorithm(ShufflingCards shufflingCards) {
        this.shufflingCards = shufflingCards;
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

    public PlayingAlgorithm() {
        playGame();
    }

    public Card drawRandomCard() {
        int randomIndex = random.nextInt(shufflingCards.deck.length);
        drawnCard = shufflingCards.deck[randomIndex];
        return drawnCard;
    }

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

            drawnCard = drawRandomCard(); // draw a card for user
            System.out.println("User drawn card: " + drawnCard); // show the drawn card to user
            shufflingCards.userHand[userHandIndex++] = new Card(drawnCard.getValue(), drawnCard.getColor()); // Add the drawn card to user's hand

            calculateUserHandSum(userHand); // Calculate user hand sum
            System.out.println("User hand sum: " + userSum); // Show user hand sum
            
            System.out.println("Enter 'P' to play a card, or 'C' to continue:"); // give a choise to user using a card from his/her hand or wait for the other tour
            String userChoice = scanner.nextLine();

            if("P".equals(userChoice)){ // using a card
                System.out.println("Enter the number of the card you want to play (1-2-3-4):");
                int cardNumber = scanner.nextInt();
                scanner.nextLine();
                
                System.out.println(Arrays.toString(userHand)); // show his/her hand deck
            
                userSum = calculateUserHandSum(userHand);
                computerSum = calculateComputerHandSum(computerHand);

                if (cardNumber >= 1 && cardNumber <= 4) { // control for a selectable selection
                    
                    int selectedCardIndex = cardNumber - 1;

                    Card selectedCard = userHand[selectedCardIndex];

                    if (selectedCard != null) { // control for a selectable selection
                        
                        userSum += Integer.parseInt(shufflingCards.card.getValue());

                        System.out.println("User played the card: " + selectedCard); // show the card that has been used

                        userHand[selectedCardIndex] = null; // remove the card has been used

                    } else {
                        System.out.println("Invalid card number. Please choose a card that is not null."); // if user do not select a selectable card the system warns
                    }
                } else {
                    System.out.println("Invalid card number. Please enter a number between 1 and 4."); // if user do not select a selectable card the system warns
                }
            }else if("C".equals(userChoice)){ // if user want to wait for next tour, It's time for the computer

            drawnCard = computerHand.new Card("value", "color"); // draw a card for computer
            System.out.println("Computer drawm card: " + drawnCard); // Print drawn card by computer

            computerHand[computerHandIndex++] = new Card(drawnCard.getValue(), drawnCard.getColor()); // Add the drawn card to computer's hand

            calculateComputerHandSum(computerHand); //Calculate computer hand sum
            drawnCard = shufflingCards.deck[deckIndex++];

            //computer code will be added

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
    public int calculateUserHandSum(ShufflingCards.Card[] userHand) {
        
        for (ShufflingCards.Card card : userHand) {
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
            } else if (card.value.startsWith("-")) {
                userSum += Integer.parseInt(card.getValue());
            }
        }
        return userSum;
    }

    public int calculateComputerHandSum(ShufflingCards.Card[] computerHand) {
        
        for (ShufflingCards.Card card : computerHand) {
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
            } else if (card.value.startsWith("-")) {
                computerSum += Integer.parseInt(card.getValue());
            }
        }return computerSum;

    }
    public void calculateComputerSum(int computerSum) {
            
            for (Card card : computerHand) {
                if (card != null) {
                    computerSum += getValue(card);
                }
            }
        }
    
        public void calculateUserSum(int userSum) {
            
            for (Card card : userHand) {
                    userSum += getValue(card);
                }
            }
}
    