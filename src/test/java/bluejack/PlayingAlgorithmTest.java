package bluejack;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlayingAlgorithm — saf fonksiyonlar")
class PlayingAlgorithmTest {

    private ShufflingCards sc;
    private PlayingAlgorithm pa;

    @BeforeEach
    void setUp() {
        sc = new ShufflingCards();
        pa = new PlayingAlgorithm(sc, null, null);
    }

    private ShufflingCards.Card card(String value, String color) {
        return sc.new Card(value, color);
    }

    // ——— getValue ———

    @ParameterizedTest(name = "getValue(\"{0}\") == {1}")
    @DisplayName("getValue pozitif değerler 1-10")
    @CsvSource({"1,1", "2,2", "3,3", "4,4", "5,5", "6,6", "7,7", "8,8", "9,9", "10,10"})
    void getValue_PositiveValues(String value, int expected) {
        assertEquals(expected, pa.getValue(card(value, "B")));
    }

    @ParameterizedTest(name = "getValue(\"{0}\") == {1}")
    @DisplayName("getValue negatif değerler -1 ile -6")
    @CsvSource({"-1,-1", "-2,-2", "-3,-3", "-4,-4", "-5,-5", "-6,-6"})
    void getValue_NegativeValues(String value, int expected) {
        assertEquals(expected, pa.getValue(card(value, "Y")));
    }

    @ParameterizedTest
    @DisplayName("getValue özel kart '2x' ve '+/-' için sıfır döndürür")
    @ValueSource(strings = {"2x", "+/-"})
    void getValue_SpecialCards_ReturnsZero(String value) {
        assertEquals(0, pa.getValue(card(value, "")));
    }

    // ——— calculateUserTableSum ———

    @Test
    @DisplayName("calculateUserTableSum boş tablo → 0")
    void calculateUserTableSum_EmptyTable_ReturnsZero() {
        assertEquals(0, pa.calculateUserTableSum(new ShufflingCards.Card[10]));
    }

    @Test
    @DisplayName("calculateUserTableSum null girişleri yoksayar")
    void calculateUserTableSum_IgnoresNullEntries() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("5", "B");
        table[2] = card("3", "R");
        assertEquals(8, pa.calculateUserTableSum(table));
    }

    @Test
    @DisplayName("calculateUserTableSum 20 hedef toplamına ulaşır")
    void calculateUserTableSum_Reaches20() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("10", "B");
        table[1] = card("10", "R");
        assertEquals(20, pa.calculateUserTableSum(table));
    }

    @Test
    @DisplayName("calculateUserTableSum negatif kartları çıkarır")
    void calculateUserTableSum_NegativeCardsSubtract() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("10", "G");
        table[1] = card("-3", "Y");
        assertEquals(7, pa.calculateUserTableSum(table));
    }

    @Test
    @DisplayName("calculateUserTableSum 21 üstü bust durumunu hesaplar")
    void calculateUserTableSum_BustOver21() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("10", "B");
        table[1] = card("10", "G");
        table[2] = card("5", "R");
        assertEquals(25, pa.calculateUserTableSum(table));
    }

    // ——— calculateComputerTableSum ———

    @Test
    @DisplayName("calculateComputerTableSum boş tablo → 0")
    void calculateComputerTableSum_EmptyTable_ReturnsZero() {
        assertEquals(0, pa.calculateComputerTableSum(new ShufflingCards.Card[10]));
    }

    @Test
    @DisplayName("calculateComputerTableSum doğru toplar")
    void calculateComputerTableSum_SumsCorrectly() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("7", "R");
        table[1] = card("8", "G");
        assertEquals(15, pa.calculateComputerTableSum(table));
    }

    @Test
    @DisplayName("calculateComputerTableSum null girişleri yoksayar")
    void calculateComputerTableSum_IgnoresNullEntries() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[3] = card("9", "B");
        assertEquals(9, pa.calculateComputerTableSum(table));
    }

    // ——— calculateUserTableBlue ———

    @Test
    @DisplayName("calculateUserTableBlue sadece mavi (B) kartları sayar")
    void calculateUserTableBlue_OnlyCountsBlueCards() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("5", "B");
        table[1] = card("7", "R");   // mavi değil, atlanır
        table[2] = card("8", "B");
        assertEquals(13, pa.calculateUserTableBlue(table));
    }

    @Test
    @DisplayName("calculateUserTableBlue mavi kart yoksa → 0")
    void calculateUserTableBlue_NoBlueCards_ReturnsZero() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("10", "R");
        table[1] = card("5", "G");
        assertEquals(0, pa.calculateUserTableBlue(table));
    }

    @Test
    @DisplayName("BlueJack: mavi kart toplamı 20 → BlueJack koşulu")
    void calculateUserTableBlue_BlueJackAt20() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("10", "B");
        table[1] = card("10", "B");
        assertEquals(20, pa.calculateUserTableBlue(table));
    }

    // ——— calculateComputerTableBlue ———

    @Test
    @DisplayName("calculateComputerTableBlue sadece mavi kartları sayar")
    void calculateComputerTableBlue_OnlyCountsBlueCards() {
        ShufflingCards.Card[] table = new ShufflingCards.Card[10];
        table[0] = card("6", "B");
        table[1] = card("5", "Y");   // sayılmaz
        table[2] = card("4", "B");
        assertEquals(10, pa.calculateComputerTableBlue(table));
    }

    @Test
    @DisplayName("calculateComputerTableBlue boş tablo → 0")
    void calculateComputerTableBlue_EmptyTable_ReturnsZero() {
        assertEquals(0, pa.calculateComputerTableBlue(new ShufflingCards.Card[10]));
    }

    // ——— getters ———

    @Test
    @DisplayName("getuserScore başlangıçta 0 döndürür")
    void getuserScore_InitiallyZero() {
        assertEquals(0, pa.getuserScore());
    }

    @Test
    @DisplayName("getcomputerScore başlangıçta 0 döndürür")
    void getcomputerScore_InitiallyZero() {
        assertEquals(0, pa.getcomputerScore());
    }

    @Test
    @DisplayName("getuser başlangıçta null döndürür")
    void getuser_InitiallyNull() {
        assertNull(pa.getuser());
    }

    // ——— AI Decision Algorithm (executeComputerTurn) ———

    @Test
    @DisplayName("executeComputerTurn: Bilgisayar elinde kazanmasını sağlayacak normal bir kart varsa onu oynar")
    void executeComputerTurn_PlaysWinningStandardCard() {
        pa.computerHand = new ShufflingCards.Card[] {
            card("6", "Y"), null, null, null
        };
        pa.computerTable = new ShufflingCards.Card[10];
        pa.computerTable[0] = card("10", "R");
        pa.computerTable[1] = card("4", "G");
        pa.computerTableIndex = 2;
        pa.computerSum = 14;

        pa.executeComputerTurn(card("1", "R"));

        assertEquals(20, pa.computerSum);
        assertNull(pa.computerHand[0]);
        assertEquals("6-Y", pa.computerTable[2].toString());
        assertEquals(3, pa.computerTableIndex);
    }

    @Test
    @DisplayName("executeComputerTurn: Bilgisayar elinde 20 + cardValue == computerSum koşulunu sağlayan bir kartı oynar")
    void executeComputerTurn_PlaysBustFixingStandardCard() {
        pa.computerHand = new ShufflingCards.Card[] {
            card("5", "Y"), null, null, null
        };
        pa.computerTable = new ShufflingCards.Card[10];
        pa.computerTable[0] = card("10", "R");
        pa.computerTable[1] = card("10", "G");
        pa.computerTable[2] = card("5", "B");
        pa.computerTableIndex = 3;
        pa.computerSum = 25;

        pa.executeComputerTurn(card("1", "R"));

        assertEquals(30, pa.computerSum);
        assertNull(pa.computerHand[0]);
        assertEquals("5-Y", pa.computerTable[3].toString());
        assertEquals(4, pa.computerTableIndex);
    }

    @Test
    @DisplayName("executeComputerTurn: 2x kartı ile tam 20'ye ulaşıyorsa oynar")
    void executeComputerTurn_Plays2xSpecialCard() {
        pa.computerSum = 12;
        pa.computerTableIndex = 1;
        pa.computerTable = new ShufflingCards.Card[10];
        pa.computerTable[1] = card("4", "G");
        pa.computerHand = new ShufflingCards.Card[] {
            card("2x", ""), null, null, null
        };

        pa.executeComputerTurn(pa.computerTable[1]);

        assertEquals(20, pa.computerSum);
        assertNull(pa.computerHand[0]);
        assertEquals(2, pa.computerTableIndex);
    }

    @Test
    @DisplayName("executeComputerTurn: +/- kartı ile 20'ye ulaşma koşulu sağlanıyorsa oynar")
    void executeComputerTurn_PlaysPlusMinusSpecialCard() {
        pa.computerSum = 28;
        pa.computerTableIndex = 1;
        pa.computerTable = new ShufflingCards.Card[10];
        pa.computerTable[1] = card("4", "G");
        pa.computerHand = new ShufflingCards.Card[] {
            card("+/-", ""), null, null, null
        };

        pa.executeComputerTurn(pa.computerTable[1]);

        assertEquals(36, pa.computerSum);
        assertNull(pa.computerHand[0]);
        assertEquals(2, pa.computerTableIndex);
    }

    @Test
    @DisplayName("executeComputerTurn: Kazanma koşulu yoksa hiçbir kart oynamaz")
    void executeComputerTurn_NoWinningCard_DoesNotPlay() {
        pa.computerSum = 10;
        pa.computerTableIndex = 0;
        pa.computerTable = new ShufflingCards.Card[10];
        pa.computerHand = new ShufflingCards.Card[] {
            card("5", "Y"), card("2", "G"), null, null
        };

        pa.executeComputerTurn(card("1", "R"));

        assertEquals(10, pa.computerSum);
        assertNotNull(pa.computerHand[0]);
        assertNotNull(pa.computerHand[1]);
        assertEquals(0, pa.computerTableIndex);
    }
}
