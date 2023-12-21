package bluejack;
//import java.util.Arrays;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import bluejack.ShufflingCards.Card;

public class PlayingAlgorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    ShufflingCards shufflingCards = new ShufflingCards();
    
    private ShufflingCards.Card drawnCard;

    int userTableIndex = 0;
    int computerTableIndex = 0;

    private ShufflingCards.Card[] userTable = new Card[6];
    private ShufflingCards.Card[] computerTable = new Card[6];
    
    public PlayingAlgorithm(ShufflingCards shufflingCards, ShufflingCards.Card[] userTable, ShufflingCards.Card[] computerTable) {
        
        this.shufflingCards = shufflingCards;
        this.drawnCard = shufflingCards.new Card("", "");
    
    }
    
    int userSum = 0;
    int computerSum = 0;

    int userBlue = 0;
    int computerBlue = 0;

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

    while (gameover == false) {

        while(tourover == false) {

            drawnCard = shufflingCards.drawUnusedCard(); // Drawing an unused card.
            
            userTable[userTableIndex] = drawnCard; // Adding the drawn card to the user's table.

            System.out.println("User drawn card: " + drawnCard); // Showing the card drawn for the user.
            
            userBlue = calculateUserTableBlue(userTable);
            userSum = calculateUserTableSum(userTable); // Calculate user table sum.
            System.out.println("User table: " + Arrays.toString(userTable));// Showing the total of cards on the user's table.
            System.out.println("User table sum: " + userSum + " - " + userBlue + " :User blue sum"); 
            userTableIndex++;
            System.out.println("Enter '1' to play a card, or '0' to continue:"); // Giving a choise to user using a card from his/her hand or wait for the other tour.
            int userChoice = scanner.nextInt();

            if(1==userChoice){ // Using a card.
                System.out.println(userHand); // Showing his/her hand deck.
                System.out.println("Enter the number of the card you want to play (1-2-3-4):");
                int cardNumber = scanner.nextInt(); // Selecting the card the user wants to play.
                scanner.nextLine();
                
                if (cardNumber >= 1 && cardNumber <= 4) { // Control for a selectable selection.
                    
                    int selectedCardIndex = cardNumber - 1; // -1 because of arrays' index start from 0.

                    Card selectedCard = userHand[selectedCardIndex];

                    if (selectedCard != null) { // Controling for a selectable selection.

                        System.out.println(userHand); // Showing his/her hand deck.
                        System.out.println("User played the card: " + selectedCard); // Showing the card that has been used.
                        System.out.println("User table: " + Arrays.toString(userTable));

                        userTable[userTableIndex] = userHand[selectedCardIndex]; // Adding the card to the user's table.
                        userSum = calculateUserTableSum(userTable);
                        userBlue = calculateUserTableBlue(userTable);
                        userHand[selectedCardIndex] = null; // Removing the card has been used.

                        userTableIndex++;

                        drawnCard = shufflingCards.drawUnusedCard(); // Drawing a card to the computer.
                        computerTable[computerTableIndex] = drawnCard; // Adding the card to the computer's table.
                        System.out.println("Computer drawn card: " + drawnCard); // Showing the card by computer to user.
            
                        computerBlue = calculateComputerTableBlue(computerTable);
                        computerSum = calculateComputerTableSum(computerTable); // Calculating computer's table sum.
                        System.out.println("Computer table: " + Arrays.toString(computerTable));
                        System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                        computerTableIndex++;

                    } else {
                        System.out.println("Invalid card number. Please choose a card that is not null."); // If user do not select a selectable card the system warns.
                    }
                } else {
                    System.out.println("Invalid card number. Please enter a number between 1 and 4."); // If user do not select a selectable card the system warns.
                }
            }else if(0==userChoice){ // If user want to wait for next tour, It's time for the computer.

            drawnCard = shufflingCards.drawUnusedCard(); // Drawing a card to the computer.
            computerTable[computerTableIndex] = drawnCard; // Adding the card to the computer's table.
            System.out.println("Computer drawn card: " + drawnCard); // Showing the card by computer to user.
            
            computerBlue = calculateComputerTableBlue(computerTable);
            computerSum = calculateComputerTableSum(computerTable); // Calculating computer's table sum.
            System.out.println("Computer table: " + Arrays.toString(computerTable));
            System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
            
            computerTableIndex++;

            for(int i = 0 ; i < computerHand.length ; i++){
                ShufflingCards.Card currentCard = computerHand[i];
                int cardValue = getValue(currentCard);
                if(20 - cardValue == computerSum){
                    
                    computerTable[computerTableIndex] = computerHand[i]; 
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + Arrays.toString(computerTable));
                    computerBlue = calculateComputerTableBlue(computerTable);
                    computerSum = calculateComputerTableSum(computerTable);
                    System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                    computerHand[i] = null;
                    computerTableIndex++;
                }
                if(20 + cardValue == computerSum){
                    
                    computerTable[computerTableIndex] = computerHand[i];
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + Arrays.toString(computerTable));
                    computerBlue = calculateComputerTableBlue(computerTable);
                    computerSum = calculateComputerTableSum(computerTable);
                    System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                    computerHand[i] = null;
                    computerTableIndex++;
                }
                if (cardValue == 0) {

                    int drawnCardValue = getValue(drawnCard);

                    if(drawnCardValue*2 + computerSum == 20){
                        
                        computerHand[i] = computerTable[computerTableIndex];
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + Arrays.toString(computerTable));
                        computerSum += drawnCardValue*2;
                        System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                        computerHand[i] = null;
                        computerTableIndex++;
                    }
                    if(-1*drawnCardValue + (computerSum - drawnCardValue) == 20){
                        
                        computerHand[i] = computerTable[computerTableIndex];
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + Arrays.toString(computerTable));
                        computerSum -= 2*(-1*drawnCardValue);
                        System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                        computerHand[i] = null;
                        computerTableIndex++;
                    }
                } 
            }
            
            }else{
                System.out.println("Invalid choice. Please enter '1' to play a card, or '0' to continue.");
            }
            userSum = calculateUserTableSum(userTable);
            userBlue = calculateUserTableBlue(userTable);
            if(userSum == 20){ //Checking if the user won or not the tour.
                userScore++;
                System.out.println("User won the tour !!!");
                System.out.println("User: " + userScore + ", Computer: " + computerScore); // Showing the score table.
                System.out.println("Next tour !!!");
                userSum = 0;
                userBlue = 0;
                computerSum = 0;
                computerBlue = 0;
                userTableIndex = 0;
                computerTableIndex = 0;
                for(int i = 0; i < userTable.length;i++){
                    computerTable[i]=null;
                    userTable[i]=null;
                }
                tourover = true;
        }
            if(computerSum == 20){ // Checking if the computer won or not the tour.
                computerScore++;
                System.out.println("Computer won the tour :(");
                System.out.println("User: " + userScore + ", Computer: " + computerScore); // Showing the score table.
                System.out.println("Next tour !!!");  
                userSum = 0;
                userBlue = 0;
                computerSum = 0;
                computerBlue = 0;
                userTableIndex = 0;
                computerTableIndex = 0;
                for(int i = 0; i < computerTable.length;i++){
                    computerTable[i]=null;
                    userTable[i]=null;
                }
                tourover = true;
        }
            if(userSum > 20 && computerSum > 20) {

                if((userSum-20) > (computerSum-20)) {
                    computerScore++;
                    System.out.println("Computer won the tour :(");
                    System.out.println("User: " + userScore + ", Computer: " + computerScore); // Showing the score table.
                    System.out.println("Next tour !!!");  
                    userSum = 0;
                    userBlue = 0;
                    computerSum = 0;
                    computerBlue = 0;
                    userTableIndex = 0;
                    computerTableIndex = 0;
                    for(int i = 0; i < computerTable.length;i++){
                        computerTable[i]=null;
                        userTable[i]=null;
        }
                tourover = true;  
                }
                else if((userSum-20) < (computerSum-20)) {
                    userScore++;
                    System.out.println("User won the tour !!!");
                    System.out.println("User: " + userScore + ", Computer: " + computerScore); // Showing the score table.
                    System.out.println("Next tour !!!");
                    userSum = 0;
                    userBlue = 0;
                    computerSum = 0;
                    computerBlue = 0;
                    userTableIndex = 0;
                    computerTableIndex = 0;
                    for(int i = 0; i < computerTable.length;i++){
                        computerTable[i]=null;
                        userTable[i]=null;
        }
                tourover = true;
                }
                else{
                    System.out.println("Tie !");
                    System.out.println("User: " + userScore + ", Computer: " + computerScore); // Showing the score table.
                    System.out.println("Next tour !!!");
                    userSum = 0;
                    userBlue = 0;
                    computerSum = 0;
                    computerBlue = 0;
                    userTableIndex = 0;
                    computerTableIndex = 0;
                    for(int i = 0; i < computerTable.length;i++){
                        computerTable[i]=null;
                        userTable[i]=null;
        }
                tourover = true;
                }
            }
        }
        tourover = false;
        
        // Checking if the game is over
        if(userScore==3||computerScore==3){
            System.out.println("The game has finished.");
            }

        if(userScore == 3){
                System.out.println("User won the game.");
                System.out.println("Congratulations !!!");
                System.out.println("User: " + userScore + ", Computer: " + computerScore); // show the score table
                gameover = true;
            }

        if(computerScore == 3){
                System.out.println("Computer won the game.");
                System.out.println("Nice try ");
                System.out.println("we wait again");
                System.out.println("User: " + userScore + ", Computer: " + computerScore); // show the score table
                gameover = true;
            }     
    }
}    
    // function that sums the values of the cards on the user's table
    public int calculateUserTableSum(ShufflingCards.Card[] userTable) {
        int userSum=0;

        for (ShufflingCards.Card card : userTable) {
            if(card != null){
                if (card.value=="1") {
                    userSum += 1;
                } else if (card.value=="2") {
                    userSum += 2;
                } else if (card.value=="3") {
                    userSum += 3;
                } else if (card.value=="4") {
                    userSum += 4;
                } else if (card.value=="5") {
                    userSum += 5;
                } else if (card.value=="6") {
                    userSum += 6;
                } else if (card.value=="7") {
                    userSum += 7;
                } else if (card.value=="8") {
                    userSum += 8;
                } else if (card.value=="9") {
                    userSum += 9;
                } else if (card.value=="10") {
                    userSum += 10;
                } else if (card.value=="-1") {
                    userSum += -1;
                } else if (card.value=="-2") {
                    userSum += -2;
                } else if (card.value=="-3") {
                    userSum += -3;
                } else if (card.value=="-4") {
                    userSum += -4;
                } else if (card.value=="-5") {
                    userSum += -5;
                } else if (card.value=="-6") {
                    userSum += -6;
                }
            }
        }return userSum;
    }

    // function that sums the values of the cards on the computer's table
    public int calculateComputerTableSum(ShufflingCards.Card[] computerTable) {
        int computerSum=0;

        for (ShufflingCards.Card card : computerTable) {
            if(card != null){
                if (card.value=="1") {
                    computerSum += 1;
                } else if (card.value=="2") {
                    computerSum += 2;
                } else if (card.value=="3") {
                    computerSum += 3;
                } else if (card.value=="4") {
                    computerSum += 4;
                } else if (card.value=="5") {
                    computerSum += 5;
                } else if (card.value=="6") {
                    computerSum += 6;
                } else if (card.value=="7") {
                    computerSum += 7;
                } else if (card.value=="8") {
                    computerSum += 8;
                } else if (card.value=="9") {
                    computerSum += 9;
                } else if (card.value=="10") {
                    computerSum += 10;
                } else if (card.value=="-1") {
                    computerSum += -1;
                } else if (card.value=="-2") {
                    computerSum += -2;
                } else if (card.value=="-3") {
                    computerSum += -3;
                } else if (card.value=="-4") {
                    computerSum += -4;
                } else if (card.value=="-5") {
                    computerSum += -5;
                } else if (card.value=="-6") {
                    computerSum += -6;
                }
            }
        }return computerSum;
    }
    // function that takes the value of the cards
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

    // Function that collects the blue cards on the computer's table.
    public int calculateComputerTableBlue(ShufflingCards.Card[] computerTable){
        int computerBlue = 0;

        for (ShufflingCards.Card card : computerTable){
            if(card != null){
                if(card.color == "B"){
                    computerBlue++;
                }
            }
        }return computerBlue;
    }

    // Function that collects the blue cards on the user's table.
    public int calculateUserTableBlue(ShufflingCards.Card[] userTable){
        int userBlue = 0;

        for (ShufflingCards.Card card : userTable){
            if(card != null){
                if(card.color == "B"){
                    userBlue++;
                }
            }
        }return userBlue;
    }
}
