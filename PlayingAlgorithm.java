package bluejack;
import java.util.Arrays;
import java.util.Scanner;

public class PlayingAlgorithm {

    Scanner scanner = new Scanner(System.in);
    
    int userSum=0;
    int computerSum=0;
        
    int UserScore=0;
    int ComputerScore=0;

    ShufflingCards userHand;
    ShufflingCards computerHand;
    
    
    int deckIndex = 0;
    boolean gameover=false;
    boolean tourover=false;

    public PlayingAlgorithm() {
        userHand = new ShufflingCards();
        computerHand = new ShufflingCards();
        playGame();
    }

    public void playGame() {

    while (gameover == false) {

        int userHandIndex=0;
        int computerHandIndex=0;
        
        ShufflingCards shufflingCards = new ShufflingCards();

        ShufflingCards.Card computerDrawnCard = computerHand.new Card("value", "color");
        computerDrawnCard = computerHand.deck[deckIndex++];
        System.out.println("Computer drawn Card: " + computerDrawnCard);
        
        
        userSum = calculateUserHandSum(userHand.userHand);
        computerSum = calculateComputerHandSum(computerHand.computerHand);
        
        while(tourover == false) {
            System.out.println("Enter 'P' to play a card, or 'C' to continue:");
            String userChoice = scanner.nextLine();

            if("P".equals(userChoice)){
                System.out.println("Enter the number of the card you want to play (1-2-3-4):");
                int cardNumber = scanner.nextInt();
                scanner.nextLine();

                ShufflingCards.Card drawnCard = userHand.new Card("value", "color"); // Draw a card for the user
                System.out.println("User drawm card: " + drawnCard); // Print drawn card by the user

                userHand[userHandIndex++] = new Card(drawnCard.getValue(), drawnCard.getColor()); // Add the drawn card to user's hand

                calculateUserHandSum(userHand); // Calculate user hand sum
                
                System.out.println("User: " + userScore + ", Computer: " + computerScore);
            System.out.println(Arrays.toString(userHand));
    
            calculateUserSum();

                if (cardNumber >= 1 && cardNumber <= 4) {
                    
                    int selectedCardIndex = cardNumber - 1;

                    Card selectedCard = userHand[selectedCardIndex];

                    if (selectedCard != null) {
                        
                        System.out.println("User played the card: " + selectedCard);

                        userHand[selectedCardIndex] = null;

                    } else {
                        System.out.println("Invalid card number. Please choose a card that is not null.");
                    }
                } else {
                    System.out.println("Invalid card number. Please enter a number between 1 and 4.");
                }
            }else if("C".equals(userChoice)){
            
            Card drawnCard = deck[deckIndex++]; // Draw a card for the user
            System.out.println("User drawm card: " + drawnCard); // Print drawn card by the user

            userHand[userHandIndex++] = new Card(drawnCard.getValue(), drawnCard.getColor()); // Add the drawn card to user's hand

            calculateUserHandSum(userHand); // Calculate user hand sum
            System.out.println("User hand sum: " + userSum);
        
            drawnCard = deck[deckIndex++];
            System.out.println("Computer drawn Card: " + drawnCard);
            computerHand[computerHandIndex++] = new Card(drawnCard.getValue(), drawnCard.getColor());

        if(userSum==20){
            UserScore++;
        }
        if(computerSum==20){
            ComputerScore++;
        }

        System.out.println("User: " + userScore + ", Computer: " + computerScore);
        System.out.println(Arrays.toString(userHand));
    
        calculateUserSum();
            }else{
                System.out.println("Invalid choice. Please enter 'P' to play a card, or 'C' to continue.");
            }
            }

        if(UserScore==3||ComputerScore==3){
            
            System.out.println("The game has finished");
            
            }
            if(UserScore == 3){
                System.out.println("User won the game");
            }
            if(ComputerScore == 3){
                System.out.println("Computer won the game");
            }
            System.out.println("User: " + UserScore + ", Computer: " + ComputerScore );
            System.out.println(userHand);
                
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
                userSum += Integer.parseInt(card.value);
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
                computerSum += Integer.parseInt(card.value);
            }
        }return computerSum;
        
    }
}
    