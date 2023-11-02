
package br.iesb.csvtoolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.IOException;
import javax.swing.SwingWorker;

public class AbrirArquivoWeb extends javax.swing.JDialog {
    
    /* Atributos */
    private String filePath;
    public Main main;

    public AbrirArquivoWeb(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	
	main = (Main) parent;
    }
    
    public static void main(String args[]) {
	/* Definição do tema da UI */
	try {
	    UIManager.setLookAndFeel( new FlatMacDarkLaf() );
	} catch( UnsupportedLookAndFeelException ex ) {
	    System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	}

	/* Create and display the dialog */
	java.awt.EventQueue.invokeLater(() -> {
	    AbrirArquivoWeb dialog = new AbrirArquivoWeb(new javax.swing.JFrame(), true);
	    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
		@Override
		public void windowClosing(java.awt.event.WindowEvent e) {
		    System.exit(0);
		}
	    });
	    dialog.setVisible(true);
	});
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        URLWebLink = new javax.swing.JTextField();
        DownloadArquivoWeb = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ConsoleWeb = new javax.swing.JTextArea();
        FecharDialogoWeb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abrir Arquivo da Web");
        setResizable(false);

        jLabel1.setText("URL do arquivo:");

        DownloadArquivoWeb.setText("Download");
        DownloadArquivoWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownloadArquivoWeb(evt);
            }
        });

        ConsoleWeb.setColumns(20);
        ConsoleWeb.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        ConsoleWeb.setForeground(new java.awt.Color(0, 204, 102));
        ConsoleWeb.setLineWrap(true);
        ConsoleWeb.setRows(3);
        ConsoleWeb.setWrapStyleWord(true);
        jScrollPane1.setViewportView(ConsoleWeb);

        FecharDialogoWeb.setText("Fechar");
        FecharDialogoWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FecharDialogoWeb(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(URLWebLink, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DownloadArquivoWeb))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(FecharDialogoWeb))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(URLWebLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DownloadArquivoWeb))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FecharDialogoWeb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
                             
    
    
    private void DownloadArquivoWeb(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownloadArquivoWeb
	String urlArquivo = URLWebLink.getText();
	ConsoleWeb.setText("Preparando download...");
	SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
        @Override
        protected Void doInBackground() throws Exception {
            try {
                Main.downloadArquivo(urlArquivo, ConsoleWeb);
            } catch (IOException e1) {
                ConsoleWeb.setText("Ocorreu um erro ao fazer o download do arquivo. \n" + e1.getMessage());
            }
            return null;
        }
    };

    worker.execute();

    }//GEN-LAST:event_DownloadArquivoWeb

    
    private void FecharDialogoWeb(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FecharDialogoWeb
        main.verificaExistenciaArquivo();
	dispose();
    }//GEN-LAST:event_FecharDialogoWeb


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ConsoleWeb;
    private javax.swing.JButton DownloadArquivoWeb;
    private javax.swing.JButton FecharDialogoWeb;
    private javax.swing.JTextField URLWebLink;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
