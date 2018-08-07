import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TickTackToe extends JFrame implements ActionListener{

    private int size;
    private JButton[][] buttons;
    private boolean firstPlayer;
    private int steps;
    private int toWin;

    public TickTackToe(int size, int toWin) {
        if (size < 3){
            this.size = 3;
        } else {
            this.size = size;
        }
        steps = 0;
        this.toWin = toWin;
        firstPlayer = true;
        setTitle("Tick Tack Toe");
        setSize(500, 500);
        setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(this.size, this.size));
        buttons = new JButton[this.size][this.size];
        generateButtons();
    }
    private void generateButtons() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                JButton button = new JButton();
                button.setText("");
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setBackground(Color.white);
                button.setActionCommand(j + " " + i);
                button.addActionListener(this);
                this.add(button);
                buttons[i][j] = button;
            }
        }
    }
    private boolean checkWinner (String player) {
        for (int i = 0; i < this.size; i++) {
            int sameNextToEachOther = 0;
            for (int j = 0; j < this.size; j++) {
                if (buttons[i][j].getText().equals(player)) {
                    sameNextToEachOther++;
                } else {
                    sameNextToEachOther = 0;
                }
                if (sameNextToEachOther >= toWin) {
                    return true;
                }
            }
        }
        for (int i = 0; i < this.size; i++) {
            int sameNextToEachOther = 0;
            for (int j = 0; j < this.size; j++) {
                if (buttons[j][i].getText().equals(player)) {
                    sameNextToEachOther++;
                } else {
                    sameNextToEachOther = 0;
                }
                if (sameNextToEachOther >= toWin) {
                    return true;
                }
            }
        }
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




    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println(e.getActionCommand());
        String[] coordinates = e.getActionCommand().split(" ");
        int j = Integer.parseInt(coordinates[0]);
        int i = Integer.parseInt(coordinates[1]);

        if (buttons[i][j].getText().equals("")) {
            if (firstPlayer) {
                buttons[i][j].setText("X");
                firstPlayer = false;
            } else {
                buttons[i][j].setText("O");
                firstPlayer = true;
            }
            steps++;
        }
        if (checkWinner(buttons[i][j].getText())) {
            JOptionPane.showMessageDialog(this, "Game is over! " + buttons[i][j].getText() + " have won!");
            clear();
        } else if (steps == this.size * this.size) {
            JOptionPane.showMessageDialog(this, "Game is over! Draw!");
            clear();
        }
    }
}
