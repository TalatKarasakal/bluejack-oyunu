package bluejack;
import java.util.Arrays;
import java.util.Random;
public class ShufflingCards {
    private class Card {
        String value;
        String type;

        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }
        public String toString() {
            return value + "-" + type;
        }

        public int getValue(){
            return Integer.parseInt(value);
        }

        public boolean isB(){
            return value.equals("B");
        } 
    }

    Card[] deck = new Card[40];
    Card[] signedDeck = new Card[4];
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

        hiddenCard = deck[deck.length-1]; //remove card at last index
        deck = Arrays.copyOf(deck, deck.length - 1);
        computerBlue += hiddenCard.isB() ? 1 : 0;

        Card card = deck[deck.length-1];
        deck = Arrays.copyOf(deck, deck.length - 1);
        computerBlue += card.isB() ? 1 : 0;
        computerDeck[computerIndex++] = card;

        //user
        userDeck = new Card[10];
        userHand = new String[4];
        userSum = 0;
        userBlue = 0;

        for(int i=0;i<5;i++){
        card = deck[deck.length - 1];
        deck = Arrays.copyOf(deck, deck.length - 1);
        userBlue += card.isB() ? 1 : 0;
        userDeck[userIndex++] = card;
        }

        //displayPlayerHands();
    }
    public void buildDeck() {
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] types = {"B", "Y", "G", "R"};

        int index = 0;
        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card(values[j], types[i]);
                deck[index++] = card;
            }
        }

        //System.out.println("Build Deck: " + Arrays.toString(deck));
    }

    public void buildSignDeck() {
        String[] signed = {"+/-", "+/-", "x2", "x2"};
        int randomIndex = random.nextInt(signed.length);

        String selectedType = signed[randomIndex];
        //System.out.println("Signed card: " + selectedType);
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

    /*public void displayPlayerHands() {
        //System.out.println("Computer's Deck: " + Arrays.toString(computerDeck));
        //System.out.println("Computer's Hand: " + Arrays.toString(Arrays.copyOf(computerDeck, computerIndex)));
        //System.out.println("User's Deck: " + Arrays.toString(userDeck));
        //System.out.println("User's Hand: " + Arrays.toString(Arrays.copyOf(userDeck, userIndex)));
    }*/
}