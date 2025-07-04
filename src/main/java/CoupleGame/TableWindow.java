import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TableWindow extends JFrame {
    private JTable wordTable;
    private JButton deleteButton;
    private JButton editButton;

    public TableWindow() {
        setTitle("Редактировать пары слов");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        wordTable = new JTable();  // Тебе нужно заполнить таблицу данными из базы
        JScrollPane scrollPane = new JScrollPane(wordTable);

        deleteButton = new JButton("Удалить");
        editButton = new JButton("Редактировать");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(deleteButton);
        panel.add(editButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = wordTable.getSelectedRow();
                if (selectedRow != -1) {
                    String englishWord = wordTable.getValueAt(selectedRow, 0).toString();
                    Database.deleteWord(englishWord);
                    ((DefaultTableModel) wordTable.getModel()).removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Слово удалено!");
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите слово для удаления!");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = wordTable.getSelectedRow();
                if (selectedRow != -1) {
                    String englishWord = wordTable.getValueAt(selectedRow, 0).toString();
                    String translation = wordTable.getValueAt(selectedRow, 1).toString();
                    // Открываем окно для редактирования пары слов
                    new AddWordWindow(englishWord, translation).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите слово для редактирования!");
                }
            }
        });
    }
}
