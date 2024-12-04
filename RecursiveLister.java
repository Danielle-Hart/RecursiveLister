import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecursiveLister extends JFrame {
    private JTextArea textArea;
    private JButton startButton, quitButton;

    public RecursiveLister () {
        setTitle("Recursive Lister");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Recursive Lister", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        startButton = new JButton("Start");
        quitButton = new JButton("Quit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> chooseDirectory());
        quitButton.addActionListener(e -> System.exit(0));
    }
    private void chooseDirectory () {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();
            textArea.setText("");
            listFiles(directory, textArea);
        }
    }
    private void listFiles (File directory, JTextArea textArea) {

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    textArea.append("Directory: " + file.getAbsolutePath() + "\n");
                    listFiles(file, textArea);
                } else {
                    textArea.append("File: " + file.getAbsolutePath() + "\n");
                }
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecursiveLister app = new RecursiveLister();
            app.setVisible(true);
        });
    }
}
