package bluejack;
import java.util.Arrays;
import java.util.Random;
import bluejack.PlayingAlgorithm;

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
            return value + (color.isEmpty() ? "" : "-" + color);
        }
    }
    Random random = new Random(); // shuffle the deck.

    Card[] deck = new Card[40];
    Card[] additionalDeck = new Card[48];
    int[] signedDeckChooser = {1, 1, 1, 1, 0};
    
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
        }
        for(int i = 0 ; i < 3 ; i++) {
            Card card = additionalDeck[additionalDeck.length - 1];
            computerDeck[computerIndex++] = card;
        }
        
        int randomIndex1 = random.nextInt(signedDeckChooser.length);

        int selectedElement1 = signedDeckChooser[randomIndex1];

        if (selectedElement1 == 0) {
            
            computerDeck[8] = new Card("2x", "");
            computerDeck[9] = new Card("+/-", "");
        } else {
            
            for (int i = 0; i < 2; i++) {
                Card card = additionalDeck[additionalDeck.length - 1];
                computerDeck[computerIndex++] = card;
            }
        }

        //computer hand
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(computerDeck.length);
            computerHand[i] = computerDeck[randomIndex];
        
            Card temp = computerDeck[randomIndex];
            computerDeck[randomIndex] = computerDeck[computerDeck.length - 1];
            computerDeck[computerDeck.length - 1] = temp;
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
            Card card = additionalDeck[additionalDeck.length - 1];
            userDeck[userIndex++] = card;
        }
        int randomIndex2 = random.nextInt(signedDeckChooser.length);

        int selectedElement2 = signedDeckChooser[randomIndex2];

        if (selectedElement2 == 0) {
            
            userDeck[8] = new Card("2x", "");
            userDeck[9] = new Card("+/-", "");
        } else {
            
            for (int i = 0; i < 2; i++) {
                Card card = additionalDeck[additionalDeck.length - 1];
                userDeck[userIndex++] = card;
            }
        }

        //user hand
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(userDeck.length);
            userHand[i] = userDeck[randomIndex];
        
            Card temp = userDeck[randomIndex];
            userDeck[randomIndex] = userDeck[userDeck.length - 1];
            userDeck[userDeck.length - 1] = temp;
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
        System.out.println("Computer's Hand: " + Arrays.toString(computerHand));
        
        System.out.println("User's Deck: " + Arrays.toString(userDeck));
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

    // drawing unused cards
    public Card drawUnusedCard() {
        boolean[] usedCards = new boolean[deck.length];

        for (Card card : userDeck) {
            markCardAsUsed(card, usedCards);
        }

        for (Card card : computerDeck) {
            markCardAsUsed(card, usedCards);
        }

        for (Card card : computerHand) {
            markCardAsUsed(card, usedCards);
        }

        for (Card card : userHand) {
            markCardAsUsed(card, usedCards);
        }

        for (int i = 0; i < usedCards.length; i++) {
            if (!usedCards[i]) {
                return deck[i];
            }
        }

        return null; 
    }

    private void markCardAsUsed(Card card, boolean[] usedCards) {
        for (int i = 0; i < deck.length; i++) {
            if (deck[i].equals(card)) {
                usedCards[i] = true;
                break;
            }
        }
    }
}
