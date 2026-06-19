package bluejack;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import bluejack.ShufflingCards.Card;

public class PlayingAlgorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    ShufflingCards shufflingCards = new ShufflingCards();
    
    private ShufflingCards.Card drawnCard;

    private String user;

    int userTableIndex = 0;
    int computerTableIndex = 0;

    ShufflingCards.Card[] userTable = new Card[10];
    ShufflingCards.Card[] computerTable = new Card[10];
    
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

        System.out.println("Please enter your nickname !");
        String user = scanner.nextLine();

    while (gameover == false) {

        while(tourover == false) {

            drawnCard = shufflingCards.drawUnusedCard(); // Drawing an unused card.
            
            userTable[userTableIndex] = drawnCard; // Adding the drawn card to the user's table.

            //System.out.println("User drawn card: " + drawnCard); // Showing the card drawn for the user.
            
            userBlue = calculateUserTableBlue(userTable); // Calculating the sum of the values of the blue cards on the user's table.
            userSum = calculateUserTableSum(userTable); // Calculating the sum of the values of the cards on the user's table.

            System.out.println(user + " table: " + Arrays.toString(userTable)); // Showing the total of cards on the user's table.
            System.out.println(user + " table sum: " + userSum + " - " + userBlue + " :" + user + " blue sum"); // 
            System.out.println(user + " hand: " + Arrays.toString(userHand)); // Showing her/his hand.

            userTableIndex++; // To avoid problems when adding cards.

            System.out.println("Enter '1' to play a card, or '0' to continue:"); // Giving a choise to user using a card from his/her hand or wait for the other tour.
            int userChoice = scanner.nextInt();
            
            while(userChoice != 0 && userChoice != 1){
                System.out.println("Invalid choice. Please enter '1' to play a card, or '0' to continue.");
                userChoice = scanner.nextInt();
            }
            if(1==userChoice){ // Using a card.
                System.out.println(Arrays.toString(userHand)); // Showing his/her hand deck.
                System.out.println("Enter the number of the card you want to play (1-2-3-4)");

                int cardNumber = scanner.nextInt(); // Selecting the card the user wants to play.
                scanner.nextLine();

                while(cardNumber < 1 && cardNumber > 4){
                    System.out.println("Invalid card number. Please enter a number between 1 and 4.");
                    cardNumber = scanner.nextInt();
                }
                if (cardNumber >= 1 && cardNumber <= 4) { // Control for a selectable selection.
                    
                    int selectedCardIndex = cardNumber - 1; // -1 because of arrays' index start from 0.

                    Card selectedCard = userHand[selectedCardIndex]; // Keeping the selected card.
                    
                    if (selectedCard == null) {
                        while(selectedCard == null){
                            System.out.println("Invalid card number. Please choose a card that is not null.");
                            cardNumber = scanner.nextInt();
                            selectedCardIndex = cardNumber - 1;
                            selectedCard = userHand[selectedCardIndex];
                        }   
                    }
                    if (selectedCard != null) { // Controling for a selectable selection.

                        System.out.println(userHand); // Showing his/her hand deck.
                        System.out.println(user + " played the card: " + selectedCard); // Showing the card that has been used.
                        System.out.println(user + " table: " + Arrays.toString(userTable)); // Showing her/his cards on the table.

                        userTable[userTableIndex] = userHand[selectedCardIndex]; // Adding the card to the user's table.
                        userSum = calculateUserTableSum(userTable); // Calculating the sum of the values of the blue cards on the user's table.
                        userHand[selectedCardIndex] = null; // Removing the card has been used.

                        userTableIndex++; // To avoid problems when adding cards.

                        drawnCard = shufflingCards.drawUnusedCard(); // Drawing a card to the computer.
                        computerTable[computerTableIndex] = drawnCard; // Adding the card to the computer's table.
                        //System.out.println("Computer drawn card: " + drawnCard); // Showing the card by computer to user.
            
                        computerBlue = calculateComputerTableBlue(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
                        computerSum = calculateComputerTableSum(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
                        System.out.println("Computer table: " + Arrays.toString(computerTable)); // Showing the total of cards on the computer's table.
                        //System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                        computerTableIndex++; // To avoid problems when adding cards.
                    }
                }
            }else if(0==userChoice){ // If user want to wait for next tour, It's time for the computer.

            drawnCard = shufflingCards.drawUnusedCard(); // Drawing a card to the computer.
            computerTable[computerTableIndex] = drawnCard; // Adding the card to the computer's table.
            //System.out.println("Computer drawn card: " + drawnCard); // Showing the card by computer to user.
            
            computerBlue = calculateComputerTableBlue(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
            computerSum = calculateComputerTableSum(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
            System.out.println("Computer table: " + Arrays.toString(computerTable)); // Showing the total of cards on the computer's table.
            //System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
            
            computerTableIndex++; // To avoid problems when adding cards.

                executeComputerTurn(drawnCard);
            }

            userSum = calculateUserTableSum(userTable); // Calculating the sum of the values of the blue cards on the user's table.
            userBlue = calculateUserTableBlue(userTable); // Calculating the sum of the values of the blue cards on the user's table.
            computerSum = calculateComputerTableSum(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
            computerBlue = calculateComputerTableBlue(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.

            if(userBlue == 20){
                userScore = 3;
                System.out.println("BlueJack !!!");
                System.out.println(user + " won the game !!!");
                System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // Showing the score table.
                gameover = true;
            }
            if(computerBlue == 20){
                computerScore = 3;
                System.out.println("BlueJack !!!");
                System.out.println("Computer won the game !!!");
                System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // Showing the score table.
                gameover = true;
            } 
            if(userSum == 20){ //Checking if the user won or not the tour.
                userScore++;
                System.out.println(user + " won the tour !!!");
                System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // Showing the score table.
                
                if(userScore != 3){
                    System.out.println("Next tour !!!");
                }
                // Resetting values.
                userSum = 0;
                userBlue = 0;
                computerSum = 0;
                computerBlue = 0;
                userTableIndex = 0;
                computerTableIndex = 0;
                // Filling the deck.
                for(int j = 0; j < userTable.length; j++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(userTable[j]!=null){
                        Card currentCard = userTable[j];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);
                    }*/
                    

                    if(shufflingCards.deck[randomIndex] == null /*&& integerValueOfCurrentCard > 0*/){
                        if(userTable[j] != null){
                            shufflingCards.deck[randomIndex] = userTable[j];
                        }else{
                            continue;
                        }        
                    }else{
                        j--;
                    }        
                }
                for(int k = 0; k < computerTable.length; k++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(computerTable[k]!=null){
                        Card currentCard = computerTable[k];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);
                    }*/
                    
                    if(shufflingCards.deck[randomIndex] == null /*&& integerValueOfCurrentCard > 0*/) {
                        if(computerTable[k] != null){
                            shufflingCards.deck[randomIndex] = computerTable[k];
                        }else{
                            continue;
                        }
                    }else{
                        k--;
                    }
                }
                for(int i = 0; i < userTable.length; i++){
                    computerTable[i]=null;
                    userTable[i]=null;
                }
                tourover = true;
            }
            if(computerSum == 20){ // Checking if the computer won or not the tour.
                computerScore++;
                System.out.println("Computer won the tour :(");
                System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // Showing the score table.
                if(computerScore != 3){
                    System.out.println("Next tour !!!");
                }
                // Resetting values.
                userSum = 0;
                userBlue = 0;
                computerSum = 0;
                computerBlue = 0;
                userTableIndex = 0;
                computerTableIndex = 0;
                // Filling the deck.
                for(int j = 0; j < userTable.length; j++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                        if(userTable[j]!=null){
                        Card currentCard = userTable[j];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);
                    }*/
                    

                    if(shufflingCards.deck[randomIndex] == null/*  && integerValueOfCurrentCard > 0*/){
                        if(userTable[j] != null){
                            shufflingCards.deck[randomIndex] = userTable[j];
                        }else{
                            continue;
                        }        
                    }else{
                        j--;
                    }        
                }
                for(int k = 0; k < computerTable.length; k++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(computerTable[k]!=null){
                        Card currentCard = computerTable[k];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);
                    }*/
                    
                    if(shufflingCards.deck[randomIndex] == null/*  && integerValueOfCurrentCard>0*/) {
                        if(computerTable[k] != null){
                            shufflingCards.deck[randomIndex] = computerTable[k];
                        }else{
                            continue;
                        }
                    }else{
                        k--;
                    }
                }
                for(int i = 0; i < computerTable.length;i++){
                    computerTable[i]=null;
                    userTable[i]=null;
                }
                tourover = true;
            }
            if(userSum > 20 && computerSum > 20) { // Checking who won the tour.

                if((userSum-20) > (computerSum-20)) { // Checking who won the tour.
                    computerScore++;
                    System.out.println("Computer won the tour :(");
                    System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // Showing the score table.
                    if(computerScore != 3){
                        System.out.println("Next tour !!!");
                    }
                    // Resetting values.
                    userSum = 0;
                    userBlue = 0;
                    computerSum = 0;
                    computerBlue = 0;
                    userTableIndex = 0;
                    computerTableIndex = 0;
                    // Filling the deck.
                for(int j = 0; j < userTable.length; j++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    int integerValueOfCurrentCard =1;
                    if(userTable[j]!=null){
                        Card currentCard = userTable[j];
                    String valueOfCurrentCard = currentCard.getValue();
                    integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);
                    }
                    
                    if(shufflingCards.deck[randomIndex] == null&&integerValueOfCurrentCard>0){
                        if(userTable[j] != null){
                            shufflingCards.deck[randomIndex] = userTable[j];
                        }else{
                            continue;
                        }        
                    }else{
                        j--;
                    }        
                }
                for(int k = 0; k < computerTable.length; k++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(computerTable[k]!=null){
                        Card currentCard = computerTable[k];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);
                    }*/
                    
                    if(shufflingCards.deck[randomIndex] == null /*&&integerValueOfCurrentCard>0*/) {
                        if(computerTable[k] != null){
                            shufflingCards.deck[randomIndex] = computerTable[k];
                        }else{
                            continue;
                        }
                    }else{
                        k--;
                    }
                }
                    for(int i = 0; i < computerTable.length;i++){
                        computerTable[i]=null;
                        userTable[i]=null;
                    }
                tourover = true;  
                }
                else if((userSum-20) < (computerSum-20)) { // Checking who won the tour.
                    userScore++;
                    System.out.println(user + " won the tour !!!");
                    System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // Showing the score table.
                    if(userScore != 3){
                        System.out.println("Next tour !!!");
                    }
                    // Resetting values.
                    userSum = 0;
                    userBlue = 0;
                    computerSum = 0;
                    computerBlue = 0;
                    userTableIndex = 0;
                    computerTableIndex = 0;
                    // Filling the deck.
                for(int j = 0; j < userTable.length; j++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(userTable[j]!=null){
                        Card currentCard = userTable[j];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);
                    }*/
                    
                    if(shufflingCards.deck[randomIndex] == null/*&&integerValueOfCurrentCard>0*/){
                        if(userTable[j] != null){
                            shufflingCards.deck[randomIndex] = userTable[j];
                        }else{
                            continue;
                        }        
                    }else{
                        j--;
                    }        
                }
                for(int k = 0; k < computerTable.length; k++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(computerTable[k]!=null){
                        Card currentCard = computerTable[k];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);  
                    }*/
                    
                    if(shufflingCards.deck[randomIndex] == null/*&&integerValueOfCurrentCard>0*/) {
                        if(computerTable[k] != null){
                            shufflingCards.deck[randomIndex] = computerTable[k];
                        }else{
                            continue;
                        }
                    }else{
                        k--;
                    }
                }
                    for(int i = 0; i < computerTable.length;i++){
                        computerTable[i]=null;
                        userTable[i]=null;
                    }
                tourover = true;
                }else{
                System.out.println("Tie !");
                System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // Showing the score table.
                System.out.println("Next tour !!!");
                // Resetting values.
                userSum = 0;
                userBlue = 0;
                computerSum = 0;
                computerBlue = 0;
                userTableIndex = 0;
                computerTableIndex = 0;
                // Filling the deck.
                for(int j = 0; j < userTable.length; j++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(userTable[j]!=null){
                       Card currentCard = userTable[j];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard); 
                    }*/
                    
                    if(shufflingCards.deck[randomIndex] == null/*&&integerValueOfCurrentCard>0*/){
                        if(userTable[j] != null){
                            shufflingCards.deck[randomIndex] = userTable[j];
                        }else{
                            continue;
                        }        
                    }else{
                        j--;
                    }        
                }
                for(int k = 0; k < computerTable.length; k++){
                    int randomIndex = random.nextInt(shufflingCards.deck.length);
                    /*int integerValueOfCurrentCard =1;
                    if(computerTable[k]!=null){
                        Card currentCard = computerTable[k];
                        String valueOfCurrentCard = currentCard.getValue();
                        integerValueOfCurrentCard = Integer.parseInt(valueOfCurrentCard);  
                    }*/
                    
                    if(shufflingCards.deck[randomIndex] == null/*&&integerValueOfCurrentCard>0*/) {
                        if(computerTable[k] != null){
                            shufflingCards.deck[randomIndex] = computerTable[k];
                        }else{
                            continue;
                        }
                    }else{
                        k--;
                    }
                }
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

        if(userScore == 3){ // Checking if the user won the game.
                System.out.println(user + " won the game.");
                System.out.println("Congratulations !!!");
                System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // show the score table
                gameover = true; // Finishing the game.
            }

        if(computerScore == 3){ // Checking if the computer won the game.
                System.out.println("Computer won the game.");
                System.out.println("Nice try ");
                System.out.println("We wait again");
                System.out.println(user + ": " + userScore + ", Computer: " + computerScore); // show the score table
                gameover = true; // Finishing the game.
            }
        if(gameover==true){
            saveGameHistory(user, userScore, computerScore);
        } 
    }
}
    public void saveGameHistory(String user, int userScore, int computerScore) {
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("game_history.txt", true))) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(now);
            if (getLineCount("game_history.txt") >= 11) {
                removeFirstLine("game_history.txt");
            }
            if(user != null){
                writer.printf("[%s] %s: %d - %d :Computer Score%n",formattedDate, user, userScore, computerScore);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving the game history.");
            e.printStackTrace();
        }
    }
    private static int getLineCount(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            return lines;
        }
    }
    private static void removeFirstLine(String fileName) throws IOException {
        File inputFile = new File(fileName);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            reader.readLine(); // Skip first line.

            // Write the remaining lines to the temp file.
            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }
        }

        // Naming temporary file as main file.
        tempFile.renameTo(inputFile);
    }
    // Function that sums the values of the cards on the user's table.
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
    // Function that takes the value of the cards.
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
            if(card != null && "B" == card.getColor()){
                computerBlue += getValue(card);
            }
        }return computerBlue;
    }

    // Function that collects the blue cards on the user's table.
    public int calculateUserTableBlue(ShufflingCards.Card[] userTable){
        int userBlue = 0;

        for (ShufflingCards.Card card : userTable){
            if(card != null && "B" == card.getColor()){
                userBlue += getValue(card);
            }
        }return userBlue;
    }
    public String getuser(){
        return user;
    }
    public int getuserScore(){
        return userScore;
    }
    public int getcomputerScore(){
        return computerScore;
    }

    public void executeComputerTurn(ShufflingCards.Card drawnCard) {
        for(int i = 0 ; i < computerHand.length ; i++){ // Computer checks cards in hand.
            ShufflingCards.Card currentCard = computerHand[i];
            if(currentCard != null){ // Null cards must be audited as their value cannot be retrieved.
                int cardValue = getValue(currentCard); // The value of the currently controlled card is taken.
    
                if(20 - cardValue == computerSum){ // It is checked whether the current card can win or not.
                    computerTable[computerTableIndex] = computerHand[i]; 
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + Arrays.toString(computerTable));
                    computerBlue = calculateComputerTableBlue(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
                    computerSum = calculateComputerTableSum(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
                    //System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                    computerHand[i] = null;// The used card is discarded from the hand.
                    computerTableIndex++; // To avoid problems when adding cards.
                }
                if(20 + cardValue == computerSum){ // It is checked whether the current card can win or not.
                    computerTable[computerTableIndex] = computerHand[i];
                    System.out.println("Computer have played: " + computerHand[i]);
                    System.out.println("Computer table: " + Arrays.toString(computerTable));
                    computerBlue = calculateComputerTableBlue(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
                    computerSum = calculateComputerTableSum(computerTable); // Calculating the sum of the values of the blue cards on the computer's table.
                    //System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                    computerHand[i] = null;// The used card is discarded from the hand.
                    computerTableIndex++; // To avoid problems when adding cards.
                }
                if (cardValue == 0) {
                    int drawnCardValue = getValue(drawnCard);

                    if(drawnCardValue*2 + computerSum == 20){// It is checked whether the current card can win or not.
                        computerHand[i] = computerTable[computerTableIndex]; 
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + Arrays.toString(computerTable));
                        computerSum += drawnCardValue*2;
                        //System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                        computerHand[i] = null;// The used card is discarded from the hand.
                        computerTableIndex++; // To avoid problems when adding cards.
                    }
                    if(-1*drawnCardValue + (computerSum - drawnCardValue) == 20){//It is checked whether the current card can win or not.
                        computerHand[i] = computerTable[computerTableIndex];
                        System.out.println("Computer have played: " + computerHand[i]);
                        System.out.println("Computer table: " + Arrays.toString(computerTable));
                        computerSum -= 2*(-1*drawnCardValue);
                        //System.out.println("Computer table sum: " + computerSum + " - " + computerBlue + " :Computer blue sum");
                        computerHand[i] = null;// The used card is discarded from the hand.
                        computerTableIndex++; // To avoid problems when adding cards.
                    }
                }
            }else{
                continue;
            }     
        }
    }
}
