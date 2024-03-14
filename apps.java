import javax.swing.*;
import java.awt.*;

public class apps {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a JButton
        JButton button = new JButton("Click Me");

        // Set the background color of the button
        button.setBackground(Color.BLUE);

        // Ensure the button fills its content area with the background color
        button.setOpaque(true);
        button.setBorderPainted(false);

        // Add the button to the frame
        frame.add(button);

        // Make the frame visible
        frame.setVisible(true);
    }
}