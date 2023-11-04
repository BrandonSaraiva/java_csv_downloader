package br.iesb.csvtoolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.io.File;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class FechamentoArquivo extends javax.swing.JDialog {
    

    

    public FechamentoArquivo(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
    }
    
    public static void main(String args[]) {
	/* Definição do tema da UI */
	try {
	    UIManager.setLookAndFeel( new FlatMacDarkLaf() );
	} catch( UnsupportedLookAndFeelException ex ) {
	    System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	}

	java.awt.EventQueue.invokeLater(() -> {
	    FechamentoArquivo dialog = new FechamentoArquivo(new javax.swing.JFrame(), true);
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

        jLabel1 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        ConfirmarFecharArquivo = new javax.swing.JButton();
        CancelarFecharArquivo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fechar Arquivo");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Deseja realmente fechar o arquivo aberto?");
        jLabel1.setToolTipText("");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Caso o arquivo aberto seja proveniente de um download, será necessário baixá-lo novamente.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setFocusable(false);
        jTextArea1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTextArea1.setPreferredSize(new java.awt.Dimension(200, 180));
        jTextArea1.setRequestFocusEnabled(false);
        jTextArea1.setVerifyInputWhenFocusTarget(false);

        jLabel2.setFont(new java.awt.Font("Segoe MDL2 Assets", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setText("");
        jLabel2.setIconTextGap(0);
        jLabel2.setMaximumSize(new java.awt.Dimension(48, 48));
        jLabel2.setMinimumSize(new java.awt.Dimension(48, 48));
        jLabel2.setPreferredSize(new java.awt.Dimension(48, 48));

        ConfirmarFecharArquivo.setText("Fechar arquivo");
        ConfirmarFecharArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarFecharArquivo(evt);
            }
        });

        CancelarFecharArquivo.setText("Cancelar");
        CancelarFecharArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarFecharArquivo(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CancelarFecharArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConfirmarFecharArquivo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfirmarFecharArquivo)
                    .addComponent(CancelarFecharArquivo))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /* Confirmar Fechamento de Arquivo - Botão */
    private void ConfirmarFecharArquivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarFecharArquivo
        // Antes de fechar o arquivo, atualize a JTextArea na classe Main
	Main mainInstance = (Main) getParent();
	mainInstance.atualizarMensagemNoConsole("Arquivo fechado com sucesso");
	
	File arquivoAberto = new File("csvImportado.csv");
	if (arquivoAberto.exists()) {
	    arquivoAberto.delete();
	}
	
	mainInstance.tableModel.setRowCount(0);
	mainInstance.tableModel.setColumnCount(0);
	
	mainInstance.verificaExistenciaArquivo();
	
	dispose();
    }//GEN-LAST:event_ConfirmarFecharArquivo
    
    
    /* Cancelar Fechamento de Arquivo - Botão */
    private void CancelarFecharArquivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarFecharArquivo
        dispose();
    }//GEN-LAST:event_CancelarFecharArquivo


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarFecharArquivo;
    private javax.swing.JButton ConfirmarFecharArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
