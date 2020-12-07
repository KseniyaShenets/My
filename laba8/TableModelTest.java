package laba8;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;

public class TableModelTest extends JFrame
{
    // Модель данных таблицы
    private DefaultTableModel tableModel;
    private JTable table1;
    // Данные для таблиц
    private Object[][] array = new String[][] {{ "A21" , "Балетки", "Cиний" , "47", "Кожа", "ЛУЧ"},
            { "Б34"  , "Ботфорты", "Черный", "43", "Замша", "Марко" },
            { "В72", "Кеды" , "Белый", "34", "Кожа", "Белвест" }};
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"Наименование", "Вид обуви", "Цвет", "Размер", "Материал", "Производитель"};

    public TableModelTest()
    {
        super("Каталог обуви");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание стандартной модели
        tableModel = new DefaultTableModel();
        // Определение столбцов
        tableModel.setColumnIdentifiers(columnsHeader);
        // Наполнение модели данными
        for (int i = 0; i < array.length; i++)
            tableModel.addRow(array[i]);

        // Создание таблицы на основании модели данных
        table1 = new JTable(tableModel);
        final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
        table1.setRowSorter((RowSorter<? extends TableModel>) sorter);

        table1.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table1);
        add(pane, BorderLayout.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Фильтр");
        panel.add(label, BorderLayout.WEST);
        final JTextField filterText = new JTextField(" ");
        panel.add(filterText, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        JButton button1 = new JButton("Фильтр");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if (text.length() == 0) ((TableRowSorter<TableModel>) sorter).setRowFilter(null);
                else {
                    try {
                        ((TableRowSorter<TableModel>) sorter).setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        System.err.println("Bad regex pattern");
                    }
                }
            }
        });
        add(button1, BorderLayout.SOUTH);


        // Создание кнопки добавления строки таблицы
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Вставка новой строки после выделенной
                tableModel.insertRow(idx +1, new String[] {
                        "" , "", "", "", "", ""});
            }
        });
        // Создание кнопки удаления строки таблицы
        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Удаление выделенной строки
                tableModel.removeRow(idx);
            }
        });

        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);
        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);
        buttons.add(button1);
        getContentPane().add(buttons, "South");
        // Вывод окна на экран
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TableModelTest();

    }
}