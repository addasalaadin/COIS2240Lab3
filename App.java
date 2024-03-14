import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App extends JFrame {
    // Declaring a JTextArea
    private JTextArea textArea;
    private JButton loadButton;
    private JScrollPane scrollPane;

    // App Class Constructor.
    public App() {
        // JFrame settings
        // Setting Title
        setTitle("Lorena Fernandez CSV Loader Application");
        // Setting Dimensions of Window
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        



        // Creating LoadCSV button
        loadButton = new JButton("LoadCSV");
        loadButton.setBackground(Color.BLUE);
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        //Creating Checkbox
        JLabel name = new JLabel(" Lorena Fernandez");
        JCheckBox checkBox = new JCheckBox("Check Box");
        //Creating Text Field
        JTextField textField = new JTextField();
        textField.setBounds(1,2,10,5);
        Border border = BorderFactory.createLineBorder(Color.PINK);
        textField.setBorder(border);
        
        // Set font and foreground color
        name.setFont(new Font("Arial", Font.BOLD, 15));
        name.setForeground(Color.BLUE);

        // Initializing the textArea
        textArea = new JTextArea();
        textArea.setEditable(true);

        // Creating a JScrollPane for the text area
        scrollPane = new JScrollPane(textArea);


        // Add action listener to the loadButton
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    loadCsv(filePath);
                }
            }
        });

        // Add components to JFrame
        add(loadButton, BorderLayout.NORTH);
        add(checkBox, BorderLayout.WEST);
        add(name , BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
       add(textField , BorderLayout.EAST);
       
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