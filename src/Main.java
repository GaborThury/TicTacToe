import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TicTacToe ticTacToe = new TicTacToe(8, 4);
            ticTacToe.setVisible(true);
        });

    }
}
