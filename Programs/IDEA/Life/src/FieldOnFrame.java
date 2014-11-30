import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dan on 11/17/14.
 */
public class FieldOnFrame extends JPanel
                          implements ActionListener {

    private JButton[][] field;
    private int squareSize;
    private int height;
    private int width;

    FieldOnFrame(int height, int width, boolean[][] field, int squareSize) {
        this.field = new JButton[height][width];
        this.squareSize = squareSize;
        this.height = height;
        this.width = width;
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                try {
                    this.field[r][c] = new JButton();
                    this.field[r][c].setActionCommand(String.valueOf(r) + " " + String.valueOf(c));
                    //this.field[r][c].setSize(squareSize, squareSize);
                    this.field[r][c].addActionListener(this);
                    if (field[r][c]) {
                        this.field[r][c].setEnabled(true);
                        this.field[r][c].setBackground(Color.black);
                    }
                    else {
                        this.field[r][c].setEnabled(false);
                        this.field[r][c].setBackground(Color.white);
                    }
                    add(this.field[r][c], BorderLayout.AFTER_LAST_LINE);
                    System.err.println(this.field[r][c].getBounds());
                }
                catch (NullPointerException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] info = e.getActionCommand().split(" ");
        int r = Integer.parseInt(info[0]);
        int c = Integer.parseInt(info[1]);
        field[r][c].setBackground(field[r][c].getBackground() == Color.white ? Color.black : Color.white);
        field[r][c].setEnabled(field[r][c].getBackground() == Color.white ? false : true);
        Controller.changeSquare(r, c);
    }

    public void update(boolean[][] field) {
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                if (field[r][c]) {
                    this.field[r][c].setEnabled(true);
                    this.field[r][c].setBackground(Color.black);
                }
                else {
                    this.field[r][c].setEnabled(false);
                    this.field[r][c].setBackground(Color.white);
                }
            }
        }
    }
}
