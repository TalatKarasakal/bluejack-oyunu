package bluejack;
import java.util.Arrays;
import java.util.Random;
public class ShufflingCards {
    private class Card {
        String value;
        String color;

        Card(String value, String color) {
            this.value = value;
            this.color = color;
        }
        public String toString() {
            return value + (color.isEmpty() ? "" : "-" + color);
        }

        public int getValue(){
            return Integer.parseInt(value);
        }

        public boolean isB(){
            return value.equals("B");
        } 
    }

    Card[] deck = new Card[40];
    Card[] additionalDeck = new Card[48];
    String[] signedDeck = {"+/-", "+/-", "x2", "x2", "0"};
    Random random = new Random(); // shuffle the deck.
    
    //computer
    Card hiddenCard;
    Card[] computerDeck = new Card[10];
    String[] computerHand = new String[4];
    int computerSum;
    int computerBlue;
    int computerIndex = 0;

    //user
    Card[] userDeck = new Card[10];
    String[] userHand = new String[4];
    int userSum;
    int userBlue;
    int userIndex = 0;
    
    ShufflingCards(){
        startgame();
    }

    public void startgame() {
        //deck
        buildDeck();
        shuffleDeck();

        //computer
        computerDeck = new Card[10];
        computerHand = new String[4];
        computerSum = 0;
        computerBlue = 0;

        hiddenCard = deck[deck.length-1];
        deck = Arrays.copyOf(deck, deck.length - 1);
        computerBlue += hiddenCard.isB() ? 1 : 0; //if computer has a blue card "computerBlue" increases.

        for(int f=0;f<5;f++){
            Card card = deck[deck.length-1];
            deck = Arrays.copyOf(deck, deck.length - 1);
            computerBlue += card.isB() ? 1 : 0;
            computerDeck[computerIndex++] = card;
            }
        for(int i = 0 ; i < 3 ; i++) {
                Card card = additionalDeck[additionalDeck.length - 1];
                additionalDeck = Arrays.copyOf(additionalDeck, additionalDeck.length - 1);
                computerDeck[computerIndex++] = card;
            }
        for (int i = 0; i < 2; i++) {
            int randomIndex = random.nextInt(signedDeck.length);
            String selectedValue = signedDeck[randomIndex];
            computerDeck[computerIndex++] = new Card(selectedValue, "");
            }

        //user
        userDeck = new Card[10];
        userHand = new String[4];
        userSum = 0;
        userBlue = 0;

        for(int i=0;i<5;i++){
            Card card = deck[deck.length - 1];
            deck = Arrays.copyOf(deck, deck.length - 1);
            userBlue += card.isB() ? 1 : 0;
            userDeck[userIndex++] = card;
            }
            for(int i = 0 ; i < 3 ; i++){
                Card card = additionalDeck[additionalDeck.length - 1];
                additionalDeck = Arrays.copyOf(additionalDeck, additionalDeck.length - 1);
                userDeck[userIndex++] = card;
            }
            
            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(signedDeck.length);
                String selectedValue = signedDeck[randomIndex];
                userDeck[userIndex++] = new Card(selectedValue, "");
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
        System.out.println("Computer's Deck: " + Arrays.toString(computerDeck));
        System.out.println("Computer's Hand: " + Arrays.toString(Arrays.copyOf(computerDeck, computerIndex)));
        System.out.println("User's Deck: " + Arrays.toString(userDeck));
        System.out.println("User's Hand: " + Arrays.toString(Arrays.copyOf(userDeck, userIndex)));
    }
}