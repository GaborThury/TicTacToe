import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TickTackToe tickTackToe = new TickTackToe(8, 4);
            tickTackToe.setVisible(true);
        });

    }
}
