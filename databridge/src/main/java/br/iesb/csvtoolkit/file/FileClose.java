package br.iesb.csvtoolkit.file;

import br.iesb.csvtoolkit.window.Home;
import java.io.File;


public class FileClose extends javax.swing.JDialog {
 
    public FileClose(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
    }
    
    public static void main(String args[]) {

	java.awt.EventQueue.invokeLater(() -> {
	    FileClose dialog = new FileClose(new javax.swing.JFrame(), true);
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

        labelTitle = new javax.swing.JLabel();
        labelText = new javax.swing.JTextArea();
        labelIcon = new javax.swing.JLabel();
        buttonCloseFile = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fechar Arquivo");
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTitle.setText("Deseja realmente fechar o arquivo aberto?");
        labelTitle.setToolTipText("");

        labelText.setEditable(false);
        labelText.setColumns(20);
        labelText.setLineWrap(true);
        labelText.setRows(5);
        labelText.setText("Caso o arquivo aberto seja proveniente de um download, será necessário baixá-lo novamente.");
        labelText.setWrapStyleWord(true);
        labelText.setAutoscrolls(false);
        labelText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        labelText.setFocusable(false);
        labelText.setMargin(new java.awt.Insets(0, 0, 0, 0));
        labelText.setPreferredSize(new java.awt.Dimension(200, 180));
        labelText.setRequestFocusEnabled(false);
        labelText.setVerifyInputWhenFocusTarget(false);

        labelIcon.setFont(new java.awt.Font("Segoe MDL2 Assets", 0, 36)); // NOI18N
        labelIcon.setForeground(new java.awt.Color(255, 204, 0));
        labelIcon.setText("");
        labelIcon.setIconTextGap(0);
        labelIcon.setMaximumSize(new java.awt.Dimension(48, 48));
        labelIcon.setMinimumSize(new java.awt.Dimension(48, 48));
        labelIcon.setPreferredSize(new java.awt.Dimension(48, 48));

        buttonCloseFile.setText("Fechar arquivo");
        buttonCloseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseFile(evt);
            }
        });

        buttonCancel.setText("Cancelar");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancel(evt);
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
                        .addComponent(buttonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCloseFile))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCloseFile)
                    .addComponent(buttonCancel))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /* Confirmar Fechamento de Arquivo - Botão */
    private void buttonCloseFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseFile
        // Antes de fechar o arquivo, atualize a JTextArea na classe Home
	Home mainInstance = (Home) getParent();
	mainInstance.updateConsoleMessage("Arquivo fechado com sucesso");
	
	File arquivoAberto = new File("csvImportado.csv");
	if (arquivoAberto.exists()) {
	    arquivoAberto.delete();
	}
	
	mainInstance.tableModel.setRowCount(0);
	mainInstance.tableModel.setColumnCount(0);
	
	mainInstance.checkFileExistence();
	
	dispose();
    }//GEN-LAST:event_buttonCloseFile
    
    
    /* Cancelar Fechamento de Arquivo - Botão */
    private void buttonCancel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancel
        dispose();
    }//GEN-LAST:event_buttonCancel


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonCloseFile;
    private javax.swing.JLabel labelIcon;
    private javax.swing.JTextArea labelText;
    private javax.swing.JLabel labelTitle;
    // End of variables declaration//GEN-END:variables
}
