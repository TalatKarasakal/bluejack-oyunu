package bluejack;
import java.util.Arrays;
import java.util.Random;

public class ShufflingCards{

    public class Card {
        String value;
        String color;
        
        Card(String value, String color) {
            this.value = value;
            this.color = color;
        }
        public String getValue() {
            return value;
        }
        public String getColor() {
            return color;
        }
        public String toString() {
            if (color == null || color.length() == 0) {
                return value;
            } else {
                return value + "-" + color;
            }
        }
    }
    Random random = new Random();

    Card[] deck = new Card[40];
    Card[] additionalDeck = new Card[48];
    int[] signedDeckChooser = {1, 1, 1, 1, 0};
    int[] signedDeckChooser2 = {1, 0};
    
    //computer
    Card hiddenCard;
    Card[] computerDeck = new Card[10];
    Card[] computerHand = new Card[4];
    int computerSum;
    int computerBlue;
    int computerIndex = 0;
    int computerHandIndex = 0;

    //user
    Card[] userDeck = new Card[10];
    Card[] userHand = new Card[4];
    int userSum;
    int userBlue;
    int userIndex = 0;
    int userHandIndex = 0;

    public ShufflingCards() {
        startgame();
    }

    public void startgame() {
      
        //deck
        buildDeck();
        buildAdditionalSignDeck();
        shuffleDeck();
        shuffleAdditionalDeck();

        //computer deck
        computerDeck = new Card[10];
        computerHand = new Card[4];
        computerSum = 0;
        computerBlue = 0;

        hiddenCard = deck[deck.length-1];
        
        for (int i = 0; i < 5; i++) {
            computerDeck[computerIndex++] = deck[i];
            deck[i]=null;
        }
        for(int i = 0 ; i < 3 ; i++) {
            int randomIndex = random.nextInt(additionalDeck.length);
            if(additionalDeck[randomIndex] != null){
            Card card = additionalDeck[randomIndex];
            computerDeck[computerIndex++] = card;
            additionalDeck[randomIndex]=null;
            }else{
                i--;
            }
        }
         
        int selectedElement1;
        int selectedElement1Sum = 0;
        int randomIndex11 = random.nextInt(signedDeckChooser2.length);
        int selectedElement11 = signedDeckChooser2[randomIndex11];

        for(int i = 0; i <2 ; i++){
            int randomIndex1 = random.nextInt(signedDeckChooser.length);
            selectedElement1 = signedDeckChooser[randomIndex1];
            if(selectedElement1 == 0){
                selectedElement1Sum++;
            }
        }

        if (selectedElement1Sum == 2) {
            
            computerDeck[8] = new Card("2x", "");
            computerDeck[9] = new Card("+/-", "");
        } 
        else if(selectedElement1Sum == 1) {
            if(selectedElement11 == 1) {
               computerDeck[8] = new Card("2x", "");
               for(int i=0;i<1;i++) {
                   int randomIndex = random.nextInt(additionalDeck.length);
                   if(additionalDeck[randomIndex] != null){
                       Card card = additionalDeck[randomIndex];
                       computerDeck[9] = card;
                       additionalDeck[randomIndex] = null;
                }else{
                    i--;
                }
                }   
            }else{
                computerDeck[8] = new Card("+/-", "");
                for(int i=0 ; i<1 ; i++){
                int randomIndex = random.nextInt(additionalDeck.length);
                    if(additionalDeck[randomIndex] != null){
                        Card card = additionalDeck[randomIndex];
                        computerDeck[9] = card;
                        additionalDeck[randomIndex] = null;
                    }else{
                        i--;
                    }
               }
            }
        
        }else{
            for (int i = 0 ; i < 2 ; i++) {
                int randomIndex = random.nextInt(additionalDeck.length);
                if(additionalDeck[randomIndex] != null){
                Card card = additionalDeck[randomIndex];
                computerDeck[computerIndex++] = card;
                additionalDeck[randomIndex]=null;
                }else{
                    i--;
                }
            }
        }

        //computer hand
        for (int i = 0 ; i < 4 ; i++) {
            int randomIndex = random.nextInt(computerDeck.length);
            if(computerDeck[randomIndex] != null){
                computerHand[i] = computerDeck[randomIndex];
                computerDeck[randomIndex] = null;
            }else{
               i--;
            }
        }
        
        //user deck
        userDeck = new Card[10];
        userHand = new Card[4];
        userSum = 0;
        userBlue = 0;

        for (int i = deck.length - 5; i < deck.length; i++) {
            userDeck[userIndex++] = deck[i];
        }
        for(int i = 0 ; i < 3 ; i++){
            int randomIndex = random.nextInt(additionalDeck.length);
            if(additionalDeck[randomIndex] != null){
                Card card = additionalDeck[randomIndex];
                userDeck[userIndex++] = card;
                additionalDeck[randomIndex] = null;
            }else{
                i--;
            }
        }
        
        int signedDeckChooser2Index = random.nextInt(signedDeckChooser2.length);
        int selectedElement2;
        int selectedElement2Sum = 0;
        int selectedElement22 = signedDeckChooser2[signedDeckChooser2Index];

        for(int i = 0; i <2 ; i++){
            int randomIndex2 = random.nextInt(signedDeckChooser.length);
            selectedElement2=signedDeckChooser[randomIndex2];
            if(selectedElement2 == 0){
                selectedElement1Sum++;
            }
        }

        if (selectedElement2Sum == 2) {
            
            userDeck[8] = new Card("2x", "");
            userDeck[9] = new Card("+/-", "");
        }
        else if(selectedElement2Sum == 1){
            if(selectedElement22 == 1) {
                userDeck[8] = new Card("2x", "");
                for(int i = 0; i < 1 ; i++){
                    int randomIndex = random.nextInt(additionalDeck.length);
                    if(additionalDeck[randomIndex] != null) {
                        Card card = additionalDeck[randomIndex];
                        userDeck[9] = card;
                        additionalDeck[randomIndex] = null;
                    }else{
                        i--;
                    }
                }
            }
            else {
                computerDeck[8] = new Card("+/-", "");
                for(int i = 0 ; i < 1 ; i++) {
                    int randomIndex = random.nextInt(additionalDeck.length);
                        if(additionalDeck[randomIndex] != null) {
                            Card card = additionalDeck[randomIndex];
                            userDeck[9] = card;
                            additionalDeck[randomIndex] = null;
                        }else{
                            i--;
                        }
                }   
            }
        }else {
            
            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(additionalDeck.length);
                if(additionalDeck[randomIndex] != null){
                    Card card = additionalDeck[additionalDeck.length - 1];
                    userDeck[userIndex++] = card;
                    additionalDeck[randomIndex]=null;
                }else{
                    i--;
                }
            }
        }
        
        //user hand
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(userDeck.length);
            if(userDeck[randomIndex] != null){
            userHand[i] = userDeck[randomIndex];
            userDeck[randomIndex] = null;
            }else{
                i--;
            }
        }
        
        displayPlayerHands();
    }

    public void buildDeck() {
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] color = {"B", "Y", "G", "R"};

        int index = 0;

        for (int i = 0 ; i < color.length ; i++) {
            for (int j = 0 ; j < values.length ; j++) {
                Card card = new Card(values[j], color[i]);
                deck[index++] = card;
            }
        }

        //System.out.println("Build Deck: " + Arrays.toString(deck));
    }

    public void buildAdditionalSignDeck() {
        String[] additionalValues = {"-1", "-2", "-3", "-4", "-5", "-6", "1", "2", "3", "4", "5", "6" };
        String[] additionalColor = {"B", "Y", "G", "R"};

        int index = 0;
        
        for (int i=0;i < additionalColor.length;i++) {
            for (int j = 0 ; j < additionalValues.length ; j++){
                Card card = new Card(additionalValues[j],additionalColor[i]);
                additionalDeck[index++] = card;
            }
        }

        //System.out.println("Additional Deck: " + Arrays.toString(additionalDeck));
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.length; i++) {
            int j = random.nextInt(deck.length);
            Card currCard = deck[i];
            Card randomCard = deck[j];
            deck[i] = randomCard;
            deck[j] = currCard;
        }
        //System.out.println("After shuffle: " + Arrays.toString(deck));
    }

    public void shuffleAdditionalDeck() {
        for (int a = 0; a < additionalDeck.length; a++) {
            int b = random.nextInt(additionalDeck.length);
            Card currCard = additionalDeck[a];
            Card randomCard = additionalDeck[b];
            additionalDeck[a] = randomCard;
            additionalDeck[b] = currCard;
        }
        //System.out.println("Additional deck after shuffle: " + Arrays.toString(additionalDeck));
    }
    
    public void displayPlayerHands() {
        //System.out.println("Computer's Deck: " + Arrays.toString(computerDeck));
        //System.out.println("Computer's Hand: " + Arrays.toString(computerHand));
        
        //System.out.println("User's Deck: " + Arrays.toString(userDeck));
        System.out.println("User's Hand: " + Arrays.toString(userHand));    
    }

    public Card[] getdeck(){
        return deck;
    }
    public void setdeck(Card[] deck){
        this.deck = deck;
    }
    public Card[] getUserHand() {
        return userHand;
    }
    public Card[] getComputerHand() {
        return computerHand;
    }

    // Drawing unused cards.
    public Card drawUnusedCard() {
        Card drawnCard = null;
        int i = 0;
    
        while (i < 1) {
            int randomIndex = random.nextInt(deck.length);
            if (deck[randomIndex] != null) {
                drawnCard = deck[randomIndex];
                deck[randomIndex] = null;
                i++;
            }
        }
        return drawnCard;
    }
}
