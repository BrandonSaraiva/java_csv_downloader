package com.javaswing;

import com.formdev.flatlaf.FlatDarkLaf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Janela extends javax.swing.JFrame {

    public Janela() {
        initComponents();
    }
    
    public void updateTextArea(String csvContent) {
        textArea.setText(csvContent);
    }
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jButtonApagarConsole = new javax.swing.JButton();
        jButtonVisualizarCSV = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jSubMenuAbrirLocal = new javax.swing.JMenu();
        jSubMenuAbrirWeb = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jSubMenuEnviarBD = new javax.swing.JMenu();
        jMenuFerramentas = new javax.swing.JMenu();
        jSubMenuExibirInfoCSV = new javax.swing.JMenu();
        jSubMenuInfoProcessamento = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuSobre = new javax.swing.JMenu();

        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setDialogTitle("Escolha um arquivo CSV");
        fileChooser.setFileFilter(new MyCustomFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Análise de CSV");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 750, 500));
        setMaximumSize(new java.awt.Dimension(750, 500));
        setPreferredSize(new java.awt.Dimension(650, 400));
        setResizable(false);
        setSize(new java.awt.Dimension(650, 400));

        jPanel1.setAutoscrolls(true);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButtonApagarConsole.setText("Limpar console");
        jButtonApagarConsole.setToolTipText("");
        jButtonApagarConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonApagarConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagarConsoleActionPerformed(evt);
            }
        });

        jButtonVisualizarCSV.setText("Visualizar CSV");
        jButtonVisualizarCSV.setToolTipText("");
        jButtonVisualizarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVisualizarCSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVisualizarCSVMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVisualizarCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonApagarConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonApagarConsole)
                    .addComponent(jButtonVisualizarCSV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        textArea.setBackground(new java.awt.Color(51, 51, 51));
        textArea.setBorder(null);
        textArea.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        textArea.setForeground(new java.awt.Color(0, 204, 51));
        textArea.setText(" Aguardando ação do usuário...");
        textArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textArea.setFocusable(false);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuArquivo.setText("Arquivo");
        jMenuArquivo.setToolTipText("");

        jSubMenuAbrirLocal.setText("Abrir arquivo local...");
        jSubMenuAbrirLocal.setToolTipText("Abre um arquivo a partir do seu computador.");
        jSubMenuAbrirLocal.setRolloverEnabled(false);
        jSubMenuAbrirLocal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSubMenuAbrirLocalMouseClicked(evt);
            }
        });
        jMenuArquivo.add(jSubMenuAbrirLocal);

        jSubMenuAbrirWeb.setText("Fazer download de arquivo da Web...");
        jSubMenuAbrirWeb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuAbrirWeb(evt);
            }
        });
        jMenuArquivo.add(jSubMenuAbrirWeb);
        jMenuArquivo.add(jSeparator1);

        jSubMenuEnviarBD.setText("Enviar arquivo para Banco de Dados...");
        jMenuArquivo.add(jSubMenuEnviarBD);

        jMenuBar1.add(jMenuArquivo);

        jMenuFerramentas.setText("Ferramentas");

        jSubMenuExibirInfoCSV.setText("Exibir informações do CSV");
        jSubMenuExibirInfoCSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSubMenuExibirInfoCSVMouseClicked(evt);
            }
        });
        jMenuFerramentas.add(jSubMenuExibirInfoCSV);

        jSubMenuInfoProcessamento.setText("Exibir informações de Processamento");
        jMenuFerramentas.add(jSubMenuInfoProcessamento);

        jMenu1.setText("jMenu1");
        jMenuFerramentas.add(jMenu1);

        jMenuBar1.add(jMenuFerramentas);

        jMenuSobre.setText("Sobre");
        jMenuSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSobreMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    
    
    private void jSubMenuAbrirLocalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSubMenuAbrirLocalMouseClicked
        System.out.println("Item de menu clicado!");
        JFileChooser fileChooser = new JFileChooser();

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                FileReader fileReader = new FileReader(selectedFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                StringBuilder content = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                // Exibe o conteúdo do arquivo na textArea
                textArea.setText(content.toString());

                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
                // Lidar com exceções, se houverem.
            }
        }
    }//GEN-LAST:event_jSubMenuAbrirLocalMouseClicked

    
    
    
    private void jMenuSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSobreMouseClicked
        System.out.println("Item de menu clicado!");
        JDialogSobre dialog = new JDialogSobre(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuSobreMouseClicked

    
    
    
    private void MenuAbrirWeb(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuAbrirWeb
        System.out.println("Item de menu clicado!");
        JDialogFromWeb dialog = new JDialogFromWeb(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_MenuAbrirWeb

    
    
    
    private void jButtonApagarConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagarConsoleActionPerformed
        textArea.setText("Aguardando ação do usuário...");
    }//GEN-LAST:event_jButtonApagarConsoleActionPerformed

    
    
    
    private void jButtonVisualizarCSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVisualizarCSVMouseClicked
        try {
            // Ler o conteúdo do arquivo CSV e exibi-lo na textArea
            BufferedReader reader = new BufferedReader(new FileReader("csvBaixado.csv"));
            String linha;
            StringBuilder conteudo = new StringBuilder();

            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }

            textArea.setText(conteudo.toString());

            reader.close();
            
        } catch (FileNotFoundException e) {
            // Arquivo CSV não encontrado, exibe uma mensagem de erro
            textArea.setText("Erro: Arquivo não encontrado. \nAbra um arquivo CSV do seu computador ou faça o download usando o menu acima.");
        } catch (IOException e) {
            // Lida com outras exceções de E/S, se necessário
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonVisualizarCSVMouseClicked

    private void jSubMenuExibirInfoCSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSubMenuExibirInfoCSVMouseClicked
        System.out.println("Item de menu clicado!");
        JDialogInfoCSV dialog = new JDialogInfoCSV(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jSubMenuExibirInfoCSVMouseClicked

    
    
    

    
    
    
    
    public static void main(String args[]) {
        /* Set the look and feel */
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Janela().setVisible(true);
        });
    }

    
    class DownloadCSV {
        public static void downloadCSV(String url) throws IOException {
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();

            // Cria um arquivo para salvar o CSV
            File file = new File("csvBaixado.csv");
            FileOutputStream outputStream = new FileOutputStream(file);

            // Copia o conteúdo do arquivo para o arquivo local
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Fecha os streams
            inputStream.close();
            outputStream.close();
        }
}

    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButtonApagarConsole;
    private javax.swing.JButton jButtonVisualizarCSV;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFerramentas;
    private javax.swing.JMenu jMenuSobre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu jSubMenuAbrirLocal;
    private javax.swing.JMenu jSubMenuAbrirWeb;
    private javax.swing.JMenu jSubMenuEnviarBD;
    private javax.swing.JMenu jSubMenuExibirInfoCSV;
    private javax.swing.JMenu jSubMenuInfoProcessamento;
    private javax.swing.JTextPane textArea;
    // End of variables declaration//GEN-END:variables
}
