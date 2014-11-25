import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

/**
 * Created by dan on 11/17/14.
 */
public class Controller {


    private static Model model;
    private static JFrame frame;
    private static FieldOnFrame fieldOnFrame;

    public static void main(String[] args) {
        frame = new JFrame("The game of life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InputPanel inputPanel;
        inputPanel = new InputPanel();
        frame.add(inputPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void changeSquare(int r, int c) {
        model.changeSquare(r, c);
    }

    public static void makeField(int height, int width) {
        model = new Model(height, width);
        boolean [][] blank = new boolean[height][width];
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int squareSize = (int) Math.min(sSize.getHeight() / 1.5 / height, sSize.getWidth() / 1.5 / width);
        fieldOnFrame = new FieldOnFrame(height, width, blank, squareSize);
        frame = new JFrame("Make the initial configuration");
        frame.getContentPane().add(fieldOnFrame);
        JButton commitButton = new JButton();
        commitButton.setText("Commit");
        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    private static void startGame() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.makeIteration();
                fieldOnFrame.update(model.getField());
                frame.repaint();
            }
        });
    }
}
