package projeto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface implements ActionListener {
    private JFrame frame;
    private JTextField urlField;
    private JTextField nameField;

    public Interface(JFrame frame, JTextField urlField, JTextField nameField) {
        this.frame = frame;
        this.urlField = urlField;
        this.nameField = nameField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = urlField.getText();
        String fileName = nameField.getText();

        if (url.isEmpty() || fileName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira o URL e o nome do arquivo CSV.");
            return;
        }

        try {
            CSVDownloader.downloadAndAnalyzeCSV(frame, url, fileName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao baixar o CSV: " + ex.getMessage());
        }
    }
}
