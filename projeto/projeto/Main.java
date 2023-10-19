package projeto;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CSV Downloader");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new FlowLayout());

            JLabel urlLabel = new JLabel("URL do CSV:");
            JTextField urlField = new JTextField(30);
            JLabel nameLabel = new JLabel("Nome do arquivo CSV:");
            JTextField nameField = new JTextField(20);
            JButton downloadButton = new JButton("Baixar CSV");

            frame.add(urlLabel);
            frame.add(urlField);
            frame.add(nameLabel);
            frame.add(nameField);
            frame.add(downloadButton);

            downloadButton.addActionListener(new Interface(frame, urlField, nameField));

            frame.setVisible(true);
        });
    }
}
