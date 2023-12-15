package bluejack;

public class BlueJackMain {
    public static void main(String [] args)throws Exception{
        ShufflingCards shufflingCards = new ShufflingCards();
        System.out.println(shufflingCards);
        PlayingAlgorithm PlayingAlgorithm = new PlayingAlgorithm();
        System.out.println(PlayingAlgorithm);
    }
}