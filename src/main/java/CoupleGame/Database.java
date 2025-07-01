import java.io.*;
import java.util.*;

public class Database {
    private static List<Word> words = new ArrayList<>();

    // Загрузка слов (пример с жестко заданным списком)
    public static List<Word> loadWords() throws IOException {
        if (words.isEmpty()) {
            words.add(new Word("Apple", "Яблоко"));
            words.add(new Word("Dog", "Собака"));
            words.add(new Word("Cat", "Кошка"));
            words.add(new Word("Book", "Книга"));
            words.add(new Word("Table", "Стол"));
        }
        return words;
    }

    // Сохранить слово
    public static void saveWord(Word word) {
        words.add(word);
        System.out.println("Слово сохранено: " + word.getEnglishWord());
    }

    // Удалить слово по английскому переводу
    public static void deleteWord(String englishWord) {
        for (Iterator<Word> iterator = words.iterator(); iterator.hasNext();) {
            Word word = iterator.next();
            if (word.getEnglishWord().equals(englishWord)) {
                iterator.remove();
                System.out.println("Слово удалено: " + englishWord);
                return;
            }
        }
        System.out.println("Слово не найдено: " + englishWord);
    }
}
