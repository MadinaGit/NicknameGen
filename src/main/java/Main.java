import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger three = new AtomicInteger();
    static AtomicInteger four = new AtomicInteger();
    static AtomicInteger five = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {

            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread wordPalindrome = new Thread(() -> {

            for (String text : texts) {

                if (isPalindrome(text)) {
                    switch (text.length()) {
                        case 3:
                            three.addAndGet(1);
                            break;
                        case 4:
                            four.addAndGet(1);
                            break;
                        case 5:
                            five.addAndGet(1);
                            break;
                    }
                }
            }

        });

        Thread wordSameLetters = new Thread(() -> {

            for (String text : texts) {

                if (isSameLetters(text)) {
                    switch (text.length()) {
                        case 3:
                            three.addAndGet(1);
                            break;
                        case 4:
                            four.addAndGet(1);
                            break;
                        case 5:
                            five.addAndGet(1);
                            break;
                    }
                }
            }

        });

        Thread wordSearch3 = new Thread(() -> {

            for (String text : texts) {

                if (inAscendingOrder(text)) {
                    switch (text.length()) {
                        case 3:
                            three.addAndGet(1);
                            break;
                        case 4:
                            four.addAndGet(1);
                            break;
                        case 5:
                            five.addAndGet(1);
                            break;
                    }
                }
            }
        });

        wordPalindrome.start();
        wordSameLetters.start();
        wordSearch3.start();

        wordPalindrome.join();
        wordSameLetters.join();
        wordSearch3.join();

        System.out.println("Красивых слов с длиной 3: " + three.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + four.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + five.get() + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String word) {
        String reversed = new StringBuilder(word).reverse().toString();
        return word.equals(reversed);
    }

    public static boolean isSameLetters(String word) {
        char firstChar = word.charAt(0);
        for (char a : word.toCharArray()) {
            if (a != firstChar) {
                return false;
            }
        }
        return true;
    }

    public static boolean inAscendingOrder(String word) {

        for (int u = 1; u < word.length(); u++) {
            if (word.charAt(u) < word.charAt(u - 1)) {
                return false;
            }
        }
        return true;
    }
}
