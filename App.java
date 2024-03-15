import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App extends JFrame {
    //Declaring a JTextArea
    private JTextArea textArea;


//App Class Constructor.
    public App() {
        // JFrame settings
        setTitle("Your Name's CSV Loader Application");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1-Create a new button named loadButton
        JButton loadButton = new JButton("LoadCSV");
        // 2-Initialize a new TextArea
        textArea = new JTextArea();
        // 3-Set the new textArea to be uneditable
        textArea.setEditable(false);
        // 4-Create a JScrollPane within the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        // 5-Add an action listener to the button
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call loadCsv function when the button is clicked
                loadCsv("path_to_your_CSV_file.csv");
            }
        });

        // Add components to the JFrame
        add(loadButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    private void loadCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            textArea.setText(content.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load the CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
            }
        });
    }
}
