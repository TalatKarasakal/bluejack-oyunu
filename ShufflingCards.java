package BlueJack;
import java.util.Arrays;
import java.util.Random;
public class ShufflingCards {
    private class Card{
        String value;
        String type;

        Card(String value, String type){
            this.value = value;
            this.type = type;
        }
        public String toString() {
            return value + "-" + type;
        }
    }

    Card[] deck = new Card[40];
    Random random = new Random(); //shuffle the deck.
    
    
    
    
    ShufflingCards(){
        startgame();
    }

    public void startgame(){

        buildDeck();
        shuffleDeck();
    }
    public void buildDeck(){
        //Card deck = new Card[40];
        String[] values = {"1","2","3","4","5","6","7","8","9","10"};
        String[] types = {"B","Y","G","R"};
        
        int index = 0;
        for(int i = 0;i<types.length;i++){
            for(int j = 0;j < values.length;j++){
                Card card = new Card(values[j], types[i]);
                deck[index++] = card;
            }
        }

        System.out.println("Build Deck: " + Arrays.toString(deck));
    }
    public void shuffleDeck(){
        for(int i = 0;i < deck.length;i++){
            int j = random.nextInt(deck.length);
            Card currCard = deck[i];
            Card randomCard = deck[j];
            deck[i] = randomCard;
            deck[i] = randomCard;
        }
        System.out.println("After shuffle: "+Arrays.toString(deck));
    }
}