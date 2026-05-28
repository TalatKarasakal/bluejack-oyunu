package bluejack;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@DisplayName("ShufflingCards — kart yapısı ve dağıtım")
class ShufflingCardsTest {

    private ShufflingCards sc;

    @BeforeEach
    void setUp() {
        sc = new ShufflingCards();
    }

    @Test
    @DisplayName("buildDeck sonrası destede 40 kart var (4 renk × 10 değer)")
    void buildDeck_Creates40Cards() {
        sc.buildDeck();
        long count = Arrays.stream(sc.deck).filter(Objects::nonNull).count();
        assertEquals(40, count);
    }

    @Test
    @DisplayName("buildDeck tüm renk-değer kombinasyonlarını içerir")
    void buildDeck_ContainsAllColorValueCombinations() {
        sc.buildDeck();
        Set<String> expected = new HashSet<>();
        for (String color : new String[]{"B", "Y", "G", "R"})
            for (String value : new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
                expected.add(value + "-" + color);

        Set<String> actual = new HashSet<>();
        for (ShufflingCards.Card card : sc.deck)
            if (card != null) actual.add(card.toString());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("shuffleDeck tüm kartları korur (permütasyon)")
    void shuffleDeck_PreservesAllCards() {
        sc.buildDeck();
        Set<String> before = new HashSet<>();
        for (ShufflingCards.Card c : sc.deck) if (c != null) before.add(c.toString());

        sc.shuffleDeck();

        Set<String> after = new HashSet<>();
        for (ShufflingCards.Card c : sc.deck) if (c != null) after.add(c.toString());

        assertEquals(before, after);
    }

    @Test
    @DisplayName("buildAdditionalSignDeck 48 kart oluşturur (4 renk × 12 değer)")
    void buildAdditionalSignDeck_Creates48Cards() {
        sc.buildAdditionalSignDeck();
        long count = Arrays.stream(sc.additionalDeck).filter(Objects::nonNull).count();
        assertEquals(48, count);
    }

    @Test
    @DisplayName("shuffleAdditionalDeck tüm ek kartları korur")
    void shuffleAdditionalDeck_PreservesAllCards() {
        sc.buildAdditionalSignDeck();
        Set<String> before = new HashSet<>();
        for (ShufflingCards.Card c : sc.additionalDeck) if (c != null) before.add(c.toString());

        sc.shuffleAdditionalDeck();

        Set<String> after = new HashSet<>();
        for (ShufflingCards.Card c : sc.additionalDeck) if (c != null) after.add(c.toString());

        assertEquals(before, after);
    }

    @Test
    @DisplayName("Başlangıçta kullanıcı elinde tam 4 kart var")
    void startgame_UserHandHas4NonNullCards() {
        long count = Arrays.stream(sc.getUserHand()).filter(Objects::nonNull).count();
        assertEquals(4, count);
    }

    @Test
    @DisplayName("Başlangıçta bilgisayar elinde tam 4 kart var")
    void startgame_ComputerHandHas4NonNullCards() {
        long count = Arrays.stream(sc.getComputerHand()).filter(Objects::nonNull).count();
        assertEquals(4, count);
    }

    @Test
    @DisplayName("drawUnusedCard null olmayan kart döndürür")
    void drawUnusedCard_ReturnsNonNull() {
        assertNotNull(sc.drawUnusedCard());
    }

    @Test
    @DisplayName("drawUnusedCard desteden tam bir kart azaltır")
    void drawUnusedCard_DecreasesDeckByOne() {
        long before = Arrays.stream(sc.deck).filter(Objects::nonNull).count();
        sc.drawUnusedCard();
        long after = Arrays.stream(sc.deck).filter(Objects::nonNull).count();
        assertEquals(before - 1, after);
    }

    @Test
    @DisplayName("Card toString renk-değer formatı döndürür")
    void card_ToString_WithColor() {
        ShufflingCards.Card card = sc.new Card("7", "B");
        assertEquals("7-B", card.toString());
    }

    @Test
    @DisplayName("Card toString renksiz sadece değeri döndürür")
    void card_ToString_NoColor() {
        ShufflingCards.Card card = sc.new Card("2x", "");
        assertEquals("2x", card.toString());
    }

    @Test
    @DisplayName("Card getValue ve getColor doğru değer döndürür")
    void card_Getters_ReturnCorrectValues() {
        ShufflingCards.Card card = sc.new Card("5", "G");
        assertEquals("5", card.getValue());
        assertEquals("G", card.getColor());
    }
}
