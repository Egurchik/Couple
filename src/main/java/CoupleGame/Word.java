import java.util.Objects;

public class Word {
    private String englishWord;
    private String translation;

    public Word(String englishWord, String translation) {
        this.englishWord = englishWord;
        this.translation = translation;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(englishWord, word.englishWord) && 
               Objects.equals(translation, word.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishWord, translation);
    }
}
