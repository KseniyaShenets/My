package laba7;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TableToolTipsDemo extends JPanel {

    public TableToolTipsDemo() {
        super(new GridLayout(1,0));

        JTable table = new JTable(new MyTableModel()) {
        };

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
       JScrollPane scrollPane = new JScrollPane(table);
     add(scrollPane);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Название", "Состав", "Масса", "Цена", "Заказать"};
        private Object[][] data = {
                {"Курица с рисом", "Жареная курица, фасоль, морковь, рис",
                        new Integer(220), new Integer(17), new Boolean(false)},
                {"Жареный ягнёнок", "Ягнёнок, перец, салями",
                        new Integer(180), new Integer(15), new Boolean(false)},
                {"Картофель с овощами", "Жареный картофель, огурцы, помидоры, листья салата",
                        new Integer(150), new Integer(12), new Boolean(false)},
                {"Кофе", "Латте",
                        new Integer(40), new Integer(4), new Boolean(false)},
                {"Чай", "Чёрный",
                        new Integer(90), new Integer(3), new Boolean(false)}
        };

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }


        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        public boolean isCellEditable(int row, int col) {
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
            }
                }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Электронное меню");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new TableToolTipsDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}