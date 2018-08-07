import javax.swing.*;
import java.awt.event.ActionListener;

public class TickTackToe extends JFrame {

    private int size;

    public TickTackToe(int size){
        this.size = size;
        setTitle("Tick Tack Toe");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
