import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private static JFrame jFrame;
    private static SudokuPaintPanels sudokuPaintPanels;
    private static JComboBox<String> numberBox;
    private static JComboBox<String> letterBox;
    private static JButton resetCellButton;
    private static JButton resetAllButton;

    private static final int jFrameWidth = 564; // 9*56 + 8*5 + 2*10 = 564, 568 to fix black border
    private static final int jFrameHeight = 650;

    public static void main(String[] args) {
        // contentPane containing all content
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        // objects on pane initialization
        sudokuPaintPanels = new SudokuPaintPanels(10, 5);       // already sets bounds
        numberBox = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"});
        numberBox.setMaximumRowCount(9);
        numberBox.setBounds(10, 580, 40, 25);
        letterBox = new JComboBox<>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I"});
        letterBox.setMaximumRowCount(9);
        letterBox.setBounds(60, 580, 40, 25);
        resetCellButton = new JButton();
        resetCellButton.setText("Reset Cell");
        resetCellButton.setBounds(110, 580, 110, 25);
        resetAllButton = new JButton();
        resetAllButton.setText("Reset All");
        resetAllButton.setBounds(230, 580, 100, 25);

        // add methods to buttons
        resetCellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuPaintPanels.getPanel(letterBox.getSelectedIndex(), numberBox.getSelectedIndex()).reset();
            }
        });
        resetAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 9; x++)
                    for (int y = 0; y < 9; y++)
                        sudokuPaintPanels.getPanel(x, y).reset();
            }
        });

        // add objects to contentPane
        for (PaintPanel[] i : sudokuPaintPanels.getPaintPanels())
            for (PaintPanel j : i)
                contentPane.add(j);
        contentPane.add(numberBox);
        contentPane.add(letterBox);
        contentPane.add(resetCellButton);
        contentPane.add(resetAllButton);

        // jFrame setup
        jFrame = new JFrame();
        jFrame.setTitle("Sudoku!");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(contentPane);
//        jFrame.setSize(jFrameWidth, jFrameHeight);
        jFrame.getContentPane().setPreferredSize(new Dimension(564, 615));
        jFrame.pack();
        jFrame.setLocationByPlatform(true);
        jFrame.setVisible(true);
    }
}
