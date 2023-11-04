package br.iesb.csvtoolkit;

import java.text.DecimalFormat;

public class InformacoesCSV extends javax.swing.JDialog {
    
    private final Main main;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    
    public InformacoesCSV(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	
	main = (Main) parent;
	
	
	/* Atualiza nome_arquivo */
	String nomeArquivo = main.getNomeArquivo();
	if (nomeArquivo != null) {
	    nome_arquivo.setText(nomeArquivo);
	} else {
	    nome_arquivo.setText("[Originado da Web]");
	}
	
	/* Atualiza tamanho_arquivo */
	String tamanhoArquivo = main.getTamanhoArquivoMB();
        tamanho_arquivo.setText(tamanhoArquivo);

	/* Atualiza local_arquivo */
	String localArquivo = main.getLocalArquivo();
        local_arquivo.setText(localArquivo);
	
	/* Atualiza linhas_arquivo */
        int quantLinhas = main.getQuantLinhasCSV();
        linhas_arquivo.setText(decimalFormat.format(quantLinhas));
	
	/* Atualiza colunas_arquivo */
	int quantColunas = main.getQuantColunasCSV();
	colunas_arquivo.setText(decimalFormat.format(quantColunas));
	
	/* Atualiza registros_arquivo */ 
	int quantRegistros = (main.getQuantLinhasCSV() - 1);
	registros_arquivo.setText(decimalFormat.format(quantRegistros));
	
	/* Atualiza separador_arquivo */
	String separadorArquivo = (main.getSeparadorArquivo());
	if (";".equals(separadorArquivo)) {
	    separador_arquivo.setText("Ponto-e-Vírgula (;)"); 
	} if (",".equals(separadorArquivo)) {
	    separador_arquivo.setText("Vírgula (,)");
	} if ("\t".equals(separadorArquivo)) {
	    separador_arquivo.setText("Tabulação (Tab)");
	}
	
    }
    
    public static void main(String args[]) {
	java.awt.EventQueue.invokeLater(() -> {
	    InformacoesCSV dialog = new InformacoesCSV(new javax.swing.JFrame(), true);
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
        nome_arquivo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tamanho_arquivo = new javax.swing.JLabel();
        linhas_arquivo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        colunas_arquivo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        registros_arquivo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        separador_arquivo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        local_arquivo = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        ok_botao = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informações do CSV");
        setResizable(false);

        jLabel1.setText("Nome do Arquivo:");

        nome_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nome_arquivo.setText("NaN");

        jLabel3.setText("Tamanho:");

        tamanho_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tamanho_arquivo.setText("NaN");

        linhas_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        linhas_arquivo.setText("NaN");

        jLabel4.setText("Linhas:");

        colunas_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        colunas_arquivo.setText("NaN");

        jLabel5.setText("Colunas:");

        jLabel6.setText("Registros:");

        registros_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        registros_arquivo.setText("NaN");

        jLabel7.setText("Localização:");

        jLabel8.setText("Separador:");

        separador_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        separador_arquivo.setText("NaN");

        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFocusable(false);

        local_arquivo.setEditable(false);
        local_arquivo.setColumns(20);
        local_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        local_arquivo.setLineWrap(true);
        local_arquivo.setRows(5);
        local_arquivo.setBorder(null);
        local_arquivo.setFocusable(false);
        jScrollPane1.setViewportView(local_arquivo);

        ok_botao.setText("OK");
        ok_botao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ok_botaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ok_botao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ok_botao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nome_arquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(tamanho_arquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(linhas_arquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(colunas_arquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registros_arquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(separador_arquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nome_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tamanho_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(linhas_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(colunas_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(registros_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(separador_arquivo))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void ok_botaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ok_botaoActionPerformed
        dispose();
    }//GEN-LAST:event_ok_botaoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel colunas_arquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel linhas_arquivo;
    private javax.swing.JTextArea local_arquivo;
    private javax.swing.JLabel nome_arquivo;
    private javax.swing.JToggleButton ok_botao;
    private javax.swing.JLabel registros_arquivo;
    private javax.swing.JLabel separador_arquivo;
    private javax.swing.JLabel tamanho_arquivo;
    // End of variables declaration//GEN-END:variables
}
