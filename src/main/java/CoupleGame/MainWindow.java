import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;  // Исправление: используйте java.util.List
import java.util.ArrayList; // Для использования ArrayList
import java.util.Random;  // Для генерации случайных чисел
import java.util.Collections;

public class MainWindow extends JFrame {
    private JLabel englishWordLabel;
    private JButton option1, option2, option3;
    private List<Word> words;  // Используем java.util.List
    private Word currentWord;
    private JButton addWordButton;  // Кнопка для открытия окна добавления слов

    public MainWindow() {
        setTitle("Главное окно");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        englishWordLabel = new JLabel("", SwingConstants.CENTER);
        option1 = new JButton();
        option2 = new JButton();
        option3 = new JButton();

        // Создаем кнопку для добавления новых слов
        addWordButton = new JButton("Добавить пару слов");
        addWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Открываем окно для добавления пары слов
                new AddWordWindow().setVisible(true);
            }
        });

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 3));
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);

        // Размещение компонентов
        add(englishWordLabel, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(addWordButton, BorderLayout.SOUTH);  // Кнопка будет снизу

        try {
            words = Database.loadWords();  // Загружаем слова из базы данных
            showNextWord();  // Показываем следующее слово
        } catch (IOException e) {
            e.printStackTrace();
        }

        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(option1);
            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(option2);
            }
        });

        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(option3);
            }
        });
    }

    private void showNextWord() {
        Random rand = new Random();
        currentWord = words.get(rand.nextInt(words.size()));
        englishWordLabel.setText(currentWord.getEnglishWord());

        List<String> options = new ArrayList<>();  // Используем ArrayList для хранения опций
        options.add(currentWord.getTranslation());
        while (options.size() < 3) {
            Word randomWord = words.get(rand.nextInt(words.size()));
            if (!options.contains(randomWord.getTranslation())) {
                options.add(randomWord.getTranslation());
            }
        }
        Collections.shuffle(options);
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        
        // Сброс цвета кнопок
        option1.setBackground(null);
        option2.setBackground(null);
        option3.setBackground(null);
    }

    private void checkAnswer(JButton selectedOption) {
        // Проверка правильности выбора
        boolean isCorrect = selectedOption.getText().equals(currentWord.getTranslation());
        
        // Меняем цвет кнопки в зависимости от правильности ответа
        if (isCorrect) {
            selectedOption.setBackground(Color.GREEN);  // Зелёный для правильного ответа
        } else {
            selectedOption.setBackground(Color.RED);    // Красный для неправильного ответа
        }
        
        // Задержка в 1 секунду перед сменой следующего слова
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {  // Используем javax.swing.Timer
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextWord(); // Показываем следующее слово
            }
        });
        timer.setRepeats(false); // Останавливаем таймер после выполнения одного действия
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}
