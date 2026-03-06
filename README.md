# bluejack-oyunu 🃏

## 🇹🇷 Türkçe Bölüm

### 📝 Proje Hakkında
**bluejack-oyunu**, popüler Blackjack (21) oyununa dayanan ancak kendine has dinamikleri bulunan, Java tabanlı stratejik bir kart oyunudur. Proje, hem kullanıcıya hem de bilgisayara (AI) karşı rekabetçi bir oyun deneyimi sunmak amacıyla geliştirilmiştir. Masaüstü Java ortamında çalışan bu uygulama, oyun mantığı, nesne yönelimli programlama (OOP) prensipleri ve verimlilik odaklı algoritmaların bir sentezidir.

### ✨ Öne Çıkan Özellikler
- **Dinamik Kart Mekaniği:** Standart desteye ek olarak özel "asıl kartlar" ve "+/-" veya "2x" gibi stratejik çarpan kartları mevcuttur.
- **Akıllı Bilgisayar Rakibi:** Bilgisayar, elindeki kartları kazanma ihtimaline göre optimize eden bir karar verme algoritması kullanır.
- **Kalıcı Oyun Geçmişi:** `game_history.txt` dosyası üzerinden son oynanan oyunların skorlarını ve tarihlerini saklar.
- **BlueJack Kuralı:** 20 puana ulaşıldığında tetiklenen özel kazanma senaryosu ile oyun derinliği artırılmıştır.
- **Hata Yönetimi:** Kullanıcı girişleri ve dosya işlemleri için kapsamlı exception handling mekanizmaları içerir.

### 🛠 Kullanılan Teknolojiler ve Kütüphaneler
- **Dil:** Java (SE)
- **Modüler Yapı:** Java Packages (`bluejack`)
- **Dosya İşlemleri:** `java.io` (BufferedReader, FileWriter, PrintWriter)
- **Veri Yapıları:** `java.util.Random`, `java.util.Arrays`, `java.util.Scanner`
- **Zaman Yönetimi:** `java.text.SimpleDateFormat`

### 🤖 Geliştirme Süreci
Bu projenin mimarisi ve geliştirme aşamaları, bir **"Yapay Zeka Ajanı" (AI Agent)** ile insan-makine iş birliği prensipleri çerçevesinde yürütülmüştür. Mimari kararlar, kod optimizasyonları ve algoritmik yapılandırmalar (özellikle `PlayingAlgorithm.java` içindeki karar ağaçları), modern yazılım mühendisliği pratikleri ve AI desteğinin birleşimiyle en üst seviyeye getirilmiştir. Bu yaklaşım, projenin hem teknik kalitesini hem de sürdürülebilirliğini artırmıştır.

### 🚀 Kurulum ve Çalıştırma Talimatları
Uygulamayı yerel makinenizde çalıştırmak için aşağıdaki adımları takip edebilirsiniz:

1. **Gereksinimler:** Sisteminizde JDK (Java Development Kit) 8 veya üzeri kurulu olmalıdır.
2. **Derleme:** Terminal üzerinden proje ana dizinine gidin ve aşağıdaki komutu çalıştırın:
   ```bash
   javac -d . *.java
   ```
3. **Çalıştırma:** Derleme sonrası uygulamayı şu komutla başlatın:
   ```bash
   java bluejack.BlueJackMain
   ```

---

## 🇺🇸 English Section

### 📝 About the Project
**bluejack-oyunu** is a Java-based strategic card game inspired by the mechanics of Blackjack (21) but featuring unique rule variations. Developed to provide a competitive experience between a human player and an AI opponent, this application demonstrates sound object-oriented programming (OOP) principles and algorithmic logic within a desktop environment.

### ✨ Key Features
- **Dynamic Card Mechanics:** Includes standard decks along with special cards like "+/-" and "2x" multipliers for strategic depth.
- **Smart AI Opponent:** Features a decision-making algorithm that optimizes the computer's moves based on the current table sum and hand value.
- **Persistent Game History:** Saves scores and timestamps of recent matches in a `game_history.txt` file.
- **BlueJack Rule:** A specialized victory scenario triggered upon reaching 20 points, enhancing the game's competitive edge.
- **Robust Error Handling:** Implements comprehensive exception handling for user inputs and file I/O operations.

### 🛠 Technologies and Libraries Used
- **Language:** Java (SE)
- **Modular Structure:** Java Packages (`bluejack`)
- **File I/O:** `java.io` (BufferedReader, FileWriter, PrintWriter)
- **Data Structures:** `java.util.Random`, `java.util.Arrays`, `java.util.Scanner`
- **Time Management:** `java.text.SimpleDateFormat`

### 🤖 Development Process
The architecture and development of this project were executed through **Human-AI Collaboration**, utilizing a sophisticated **AI Agent**. Architectural decisions, code optimizations, and algorithmic structuring (specifically the logic within `PlayingAlgorithm.java`) were refined through this iterative partnership. This approach showcases the integration of modern AI-assisted engineering to achieve high-quality, efficient, and robust code.

### 🚀 Installation and Execution Instructions
Follow these steps to run the application locally:

1. **Prerequisites:** Ensure you have JDK (Java Development Kit) 8 or later installed.
2. **Compilation:** Navigate to the project directory and run the following command:
   ```bash
   javac -d . *.java
   ```
3. **Execution:** After compilation, launch the application using:
   ```bash
   java bluejack.BlueJackMain
   ```
