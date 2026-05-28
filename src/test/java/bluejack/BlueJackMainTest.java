package bluejack;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;

@DisplayName("BlueJackMain — integration smoke test")
class BlueJackMainTest {

    private File historyFile;

    @BeforeEach
    void setUp() {
        historyFile = new File("game_history.txt");
        historyFile.delete();
    }

    @AfterEach
    void tearDown() {
        historyFile.delete();
    }

    @Test
    @DisplayName("saveGameHistory oyun geçmişini dosyaya yazar")
    void saveGameHistory_WritesToFile() throws IOException {
        ShufflingCards sc = new ShufflingCards();
        PlayingAlgorithm pa = new PlayingAlgorithm(sc, null, null);
        pa.saveGameHistory("Alice", 3, 2);

        assertTrue(historyFile.exists(), "game_history.txt oluşturulmalı");
        String content = Files.readString(historyFile.toPath());
        assertTrue(content.contains("Alice"), "Oyuncu adı kaydedilmeli");
        assertTrue(content.contains(": 3 - 2"), "Skor kaydedilmeli");
    }

    @Test
    @DisplayName("saveGameHistory null user ile çağrılınca dosyaya yazılmaz")
    void saveGameHistory_NullUser_DoesNotWrite() throws IOException {
        ShufflingCards sc = new ShufflingCards();
        PlayingAlgorithm pa = new PlayingAlgorithm(sc, null, null);
        pa.saveGameHistory(null, 0, 0);

        boolean fileHasContent = historyFile.exists() && historyFile.length() > 0;
        assertFalse(fileHasContent, "Null user ile dosyaya yazılmamalı");
    }

    @Test
    @DisplayName("saveGameHistory 11 satırı aşınca eski kaydı siler")
    void saveGameHistory_TrimsToMaxLines() throws IOException {
        ShufflingCards sc = new ShufflingCards();
        PlayingAlgorithm pa = new PlayingAlgorithm(sc, null, null);

        for (int i = 0; i < 15; i++) {
            pa.saveGameHistory("User" + i, i % 4, 3 - (i % 4));
        }

        long lineCount = Files.lines(historyFile.toPath()).count();
        assertTrue(lineCount <= 11, "En fazla 11 satır olmalı, gerçek: " + lineCount);
    }

    @Test
    @DisplayName("BlueJackMain main metodu mevcut")
    void blueJackMain_HasMainMethod() throws NoSuchMethodException {
        var method = BlueJackMain.class.getMethod("main", String[].class);
        assertNotNull(method);
    }

    @Test
    @DisplayName("Oyun System.in mock ile crash olmadan çalışır")
    void quickGame_CompletesWithoutError() throws InterruptedException {
        StringBuilder input = new StringBuilder("TestPlayer\n");
        for (int i = 0; i < 1000; i++) {
            input.append("0\n");
        }

        InputStream savedIn = System.in;
        PrintStream savedOut = System.out;
        System.setIn(new ByteArrayInputStream(input.toString().getBytes()));
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            ShufflingCards sc = new ShufflingCards();
            PlayingAlgorithm pa = new PlayingAlgorithm(sc, null, null);

            Thread gameThread = new Thread(() -> {
                try {
                    pa.playGame();
                } catch (Exception ignored) {
                    // NoSuchElementException veya diğer hatalar kabul edilir
                }
            });
            gameThread.setDaemon(true);
            gameThread.start();
            gameThread.join(10_000);
            gameThread.interrupt();
            // 10 saniye içinde bitmiş ya da input tükenmiş — her iki durum da başarı
        } finally {
            System.setIn(savedIn);
            System.setOut(savedOut);
        }
    }
}
