import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddWordWindow extends JFrame {
    private JTextField englishField;
    private JTextField translationField;
    private JButton saveButton;

    // Пустой конструктор для добавления нового слова
    public AddWordWindow() {
        setTitle("Добавить пару слов");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel englishLabel = new JLabel("Английское слово:");
        englishField = new JTextField();

        JLabel translationLabel = new JLabel("Перевод:");
        translationField = new JTextField();

        saveButton = new JButton("Сохранить");

        add(englishLabel);
        add(englishField);
        add(translationLabel);
        add(translationField);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String english = englishField.getText();
                String translation = translationField.getText();
                if (!english.isEmpty() && !translation.isEmpty()) {
                    Word word = new Word(english, translation);
                    Database.saveWord(word);
                    JOptionPane.showMessageDialog(null, "Слово сохранено!");
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
                }
            }
        });
    }

    // Конструктор с двумя параметрами для редактирования существующей пары слов
    public AddWordWindow(String englishWord, String translation) {
        setTitle("Редактировать пару слов");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel englishLabel = new JLabel("Английское слово:");
        englishField = new JTextField(englishWord);

        JLabel translationLabel = new JLabel("Перевод:");
        translationField = new JTextField(translation);

        saveButton = new JButton("Сохранить");

        add(englishLabel);
        add(englishField);
        add(translationLabel);
        add(translationField);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String english = englishField.getText();
                String translation = translationField.getText();
                if (!english.isEmpty() && !translation.isEmpty()) {
                    Word word = new Word(english, translation);
                    Database.saveWord(word); // Сохранение обновленного слова
                    JOptionPane.showMessageDialog(null, "Слово обновлено!");
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
                }
            }
        });
    }
}
