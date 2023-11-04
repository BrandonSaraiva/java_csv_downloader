package br.iesb.projetocsv;

import com.formdev.flatlaf.FlatDarkLaf;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.iesb.projetocsv.PaginaInicial.DownloadCSV;
import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AbrirArquivoWeb extends javax.swing.JDialog {
    
    private String filePath; // Caminho para o arquivo baixado

    public AbrirArquivoWeb(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	
	jMenuItem_AbrirArquivoBaixado.setEnabled(false); // Desativa o botão no início
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_URL = new javax.swing.JTextField();
        jButton_Download = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ConsoleWeb = new javax.swing.JTextArea();
        jButton_Fechar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_AbrirArquivoBaixado = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abrir Arquivo da Web");

        jLabel1.setText("URL do arquivo:");

        jButton_Download.setText("Download");
        jButton_Download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DownloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_URL, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Download)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_URL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Download))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        ConsoleWeb.setColumns(20);
        ConsoleWeb.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        ConsoleWeb.setForeground(new java.awt.Color(0, 204, 102));
        ConsoleWeb.setLineWrap(true);
        ConsoleWeb.setRows(3);
        ConsoleWeb.setText("Aguardando URL...");
        ConsoleWeb.setWrapStyleWord(true);
        jScrollPane1.setViewportView(ConsoleWeb);

        jButton_Fechar.setText("Fechar");
        jButton_Fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_Fechar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton_Fechar))
        );

        jMenu1.setText("Opções");

        jMenuItem_AbrirArquivoBaixado.setText("Abrir arquivo no Leitor de CSV padrão...");
        jMenuItem_AbrirArquivoBaixado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_AbrirArquivoBaixadoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_AbrirArquivoBaixado);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_DownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DownloadActionPerformed
        String csvURL = jTextField_URL.getText();

    try {
    long startTime = System.currentTimeMillis(); // Marque o tempo de início

    DownloadCSV.downloadCSV(csvURL);

    long endTime = System.currentTimeMillis(); // Marque o tempo de término
    long elapsedTime = (endTime - startTime) / 1000; // Calcule o tempo em segundos

    jMenuItem_AbrirArquivoBaixado.setEnabled(true);
    String message = "Download realizado com sucesso. Local: " + System.getProperty("user.dir") + "\\csvBaixado.csv\n";
    message += "Tempo consumido para o download: " + elapsedTime + " segundos";

    // Conte o número de registros no CSV
    int totalRecords = DownloadCSV.countCSVRecords(new File("csvBaixado.csv"));
    message += "\nQuantidade total de registros no CSV: " + totalRecords;

    ConsoleWeb.setText(message);
    System.out.println(message);
    
    ConsoleWeb.setText("Número de valores '2020' na coluna 'List Year': " + count2020);

    // Atualize a JLabel com o tempo
    jLabelTempo.setText("Tempo consumido para o download: " + elapsedTime + " segundos");
} catch (IOException e1) {
    ConsoleWeb.setText("Ocorreu um erro ao fazer o download do arquivo: \n" + e1.getMessage());
    System.out.println("Erro ao fazer o download do arquivo: " + e1.getMessage());
}

    }//GEN-LAST:event_jButton_DownloadActionPerformed

    private void jButton_FecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FecharActionPerformed
        dispose();
    }//GEN-LAST:event_jButton_FecharActionPerformed

    private void jMenuItem_AbrirArquivoBaixadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_AbrirArquivoBaixadoActionPerformed
        try {
            File file = new File(System.getProperty("user.dir") + "\\csvBaixado.csv");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            ConsoleWeb.setText("Erro ao abrir o arquivo baixado: " + e.getMessage());
            System.out.println("Erro ao abrir o arquivo baixado: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem_AbrirArquivoBaixadoActionPerformed

    

    
    
    
    
    
    
    
   
    

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize theme. Using fallback.");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AbrirArquivoWeb dialog = new AbrirArquivoWeb(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    private javax.swing.JLabel jLabelTempo;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ConsoleWeb;
    private javax.swing.JButton jButton_Download;
    private javax.swing.JButton jButton_Fechar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_AbrirArquivoBaixado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_URL;
    // End of variables declaration//GEN-END:variables
}
