/*
 *  Name: Nirmal Patel
 *  COIS 2240 - Lab 3
 *  Winter 2024
 *  
 *  Bonus: A button that closes user opened file. A NEVER CRASHING PROGRAM ;) 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App extends JFrame {
    //Declaring a JTextArea
    private JTextArea textArea;

//App Class Constructor.
    public App() {
        // JFrame settings
        //Setting Title
        setTitle("Nirmal's CSV Loader Application");
        //Setting Dimensions of Window
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();

        // Initializing buttons with text
        JButton loadButton = new JButton("Load CSV");
        JButton closeFileButton = new JButton("Close File");

        // This program can only read
        textArea.setEditable(false);
        // When there
        closeFileButton.setEnabled(false);

        // Wrapping lines to readablity
        textArea.setLineWrap(true);

        // scrollpane inside textArea for scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // action listner for loadButton; initializes to Browse to Choose a File for user convinience 
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // intializing new JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                // setting default directory to locate a file to user.home
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(App.this);

                // if user chooses a file, and action is successfull
                if(result == JFileChooser.APPROVE_OPTION){
                    // passing path to loadCSV function
                    File selectedFile = fileChooser.getSelectedFile();
                    loadCsv(selectedFile.getAbsolutePath());

                    // setting styling for better readablity
                    textArea.setBackground(Color.yellow);
                    closeFileButton.setEnabled(true);
                    loadButton.setEnabled(false);
                }
            }
        });

        // action listner for closeFileButton; purpose to close opened file, and reset CSV Loader App
        closeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                textArea.setText("");
                textArea.setBackground(Color.gray);
                closeFileButton.setEnabled(false);
                loadButton.setEnabled(true);
            }
        });

        // Default styling for textArea (Background & Font)
        textArea.setBackground(Color.gray);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 

        /*
         * 1-Create a a new button named loadButton. The text in the Button should say LoadCSV
         * 2-After that initialize a new TextArea (we already declared a textArea on Line 11), this is the area where we will display our CSV.
         * 3-Set the new textAtrea to be uneditable (e.g., .setEditable(false))
         * 4-Create a JScrollPane within the text area, so that we can scroll up and down.
         * 5-Add an action listener to the button that calls the load CSV function (below)
         * 6-Play around with coloring and styling to make your application look more professional
         */
        

        add(loadButton, BorderLayout.NORTH);
        add(closeFileButton, BorderLayout.SOUTH);
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
