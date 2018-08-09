import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    private int size;
    private JButton[][] buttons;
    private boolean firstPlayer;
    private int steps;
    private int toWin;

    public TicTacToe(int size, int toWin) {
        if (size < 3) {
            this.size = 3;
        } else {
            this.size = size;
        }

        steps = 0;
        this.toWin = toWin;
        firstPlayer = true;
        buttons = new JButton[this.size][this.size];
        Color backgroundColor = new Color(80, 80, 80);
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(this.size, this.size));

        generateButtons();
    }

    private void generateButtons() {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Insets m = new Insets(0, 0, 0, 0);
        Color backgroundColor = new Color(80, 80, 80);

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                JButton button = new JButton();
                button.setText("");
                button.setFont(font);
                button.setMargin(m);
                button.setBackground(backgroundColor);
                button.setActionCommand(j + " " + i);
                button.addActionListener(this);
                this.add(button);
                buttons[i][j] = button;
            }
        }
    }

    private boolean checkWinner(int i, int j, String player) {
        int numberOfMarks = 1 +
                countMarks(i, j, player, -1 , 1) +
                countMarks(i, j, player, 1, -1);
        if (numberOfMarks >= toWin) return true;

        numberOfMarks = 1 +
                countMarks(i, j, player, -1 , -1) +
                countMarks(i, j, player, 1, 1);
        if (numberOfMarks >= toWin) return true;

        numberOfMarks = 1 +
                countMarks(i, j, player, 0 , -1) +
                countMarks(i, j, player, 0, 1);
        if (numberOfMarks >= toWin) return true;

        numberOfMarks = 1 +
                countMarks(i, j, player, -1 , 0) +
                countMarks(i, j, player, 1, 0);
        if (numberOfMarks >= toWin) return true;

        return false;
    }

    private void clear() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                buttons[i][j].setText("");
            }
        }
        firstPlayer = true;
        steps = 0;
    }

    private int countMarks(int i, int j, String player, int stepI, int stepJ) {
        int k = i + stepI;
        int l = j + stepJ;
        int counter = 0;

        while (coordsInBounds(k, l) && buttons[k][l].getText().equals(player)) {
            ++counter;
            k += stepI;
            l += stepJ;
        }
        return counter;
    }

    private boolean coordsInBounds(int i, int j) {
        return i >= 0 && i < size && j >= 0 && j < size;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String[] coordinates = e.getActionCommand().split(" ");
        int j = Integer.parseInt(coordinates[0]);
        int i = Integer.parseInt(coordinates[1]);
        Color redColor = new Color (246, 33, 33);
        Color greenColor = new Color (67, 220, 32);


        if (buttons[i][j].getText().equals("")) {
            if (firstPlayer) {
                buttons[i][j].setText("X");
                buttons[i][j].setForeground(redColor);
                firstPlayer = false;
            } else {
                buttons[i][j].setText("O");
                buttons[i][j].setForeground(greenColor);
                firstPlayer = true;
            }
            steps++;
        }
        if (checkWinner(i, j, buttons[i][j].getText())) {
            JOptionPane.showMessageDialog(this, "Game is over! " + buttons[i][j].getText() + " have won!", "Game over!", JOptionPane.INFORMATION_MESSAGE);
            clear();
        } else if (steps == this.size * this.size) {
            JOptionPane.showMessageDialog(this, "Game is over!\nDraw!", "Game over!", JOptionPane.INFORMATION_MESSAGE);
            clear();
        }
    }
}
