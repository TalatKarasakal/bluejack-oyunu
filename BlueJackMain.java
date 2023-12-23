package bluejack;

public class BlueJackMain {
    public static void main(String [] args)throws Exception{
        ShufflingCards shufflingCards = new ShufflingCards();
        System.out.println(shufflingCards);
        PlayingAlgorithm playingAlgorithm = new PlayingAlgorithm(shufflingCards, null, null);
        System.out.println(playingAlgorithm);
        playingAlgorithm.playGame();
        playingAlgorithm.saveGameHistory(playingAlgorithm.getuser(), playingAlgorithm.getuserScore(), playingAlgorithm.getcomputerScore());
    }
}
