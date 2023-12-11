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
        public boolean isB(){
            return value.equals("B");
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
    
    ShufflingCards(){
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
        deck = Arrays.copyOf(deck, deck.length - 1);
        computerBlue += hiddenCard.isB() ? 1 : 0; //if computer has a blue card "computerBlue" increases.

        for (int i = 0; i < 5; i++) {
            computerDeck[computerIndex++] = deck[i];
        }
            for(int i = 0 ; i < 3 ; i++) {
                Card card = additionalDeck[additionalDeck.length - 1];
                additionalDeck = Arrays.copyOf(additionalDeck, additionalDeck.length - 1);
                computerDeck[computerIndex++] = card;
            }
            
            int randomIndex1 = random.nextInt(signedDeckChooser.length);
            int selectedElement1 = signedDeckChooser[randomIndex1];
    
            boolean result1 = (selectedElement1 == 1);
            if (result1) {   
                computerDeck[8] = new Card("2x", "");
                computerDeck[9] = new Card("+/-", "");
            } else {   
                for (int i = 0; i < 2; i++) {
                    Card card = additionalDeck[additionalDeck.length - 1];
                    additionalDeck = Arrays.copyOf(additionalDeck, additionalDeck.length - 1);
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
        
            computerDeck = Arrays.copyOfRange(computerDeck, 0, computerDeck.length - 1);
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
            additionalDeck = Arrays.copyOf(additionalDeck, additionalDeck.length - 1);
            userDeck[userIndex++] = card;
        }
        int randomIndex2 = random.nextInt(signedDeckChooser.length);
        int selectedElement2 = signedDeckChooser[randomIndex2];

        boolean result2 = (selectedElement2 == 1);

        if (result2) {
            userDeck[8] = new Card("2x", "");
            userDeck[9] = new Card("+/-", "");
        } else {
            for (int i = 0; i < 2; i++) {
                Card card = additionalDeck[additionalDeck.length - 1];
                additionalDeck = Arrays.copyOf(additionalDeck, additionalDeck.length - 1);
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
        
            userDeck = Arrays.copyOfRange(userDeck, 0, userDeck.length - 1);
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