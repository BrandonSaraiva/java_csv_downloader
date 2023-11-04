package br.iesb.csvtoolkit.window;

import br.iesb.csvtoolkit.util.FileUtil;
import java.io.File;
import java.text.DecimalFormat;

public class FileInfo extends javax.swing.JDialog {
    
    private final Home home;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    public File arquivoCSV = new File("csvImportado.csv");
    
    
    public FileInfo(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	
	home = (Home) parent;
	
	
	/* Atualiza nome_arquivo */
	String nomeArquivo = home.getNomeArquivo();
	if (nomeArquivo != null) {
	    nome_arquivo.setText(nomeArquivo);
	} else {
	    nome_arquivo.setText("[Originado da Web]");
	}
	
	/* Atualiza tamanho_arquivo */
	String tamanhoArquivo = FileUtil.getFileSize(arquivoCSV);
        tamanho_arquivo.setText(tamanhoArquivo);

	/* Atualiza local_arquivo */
	String localArquivo = home.getLocalArquivo();
        local_arquivo.setText(localArquivo);
	
	/* Atualiza linhas_arquivo */
        int quantLinhas = FileUtil.getFileRowCount(arquivoCSV);
        linhas_arquivo.setText(decimalFormat.format(quantLinhas));
	
	/* Atualiza colunas_arquivo */
	int quantColunas = FileUtil.getFileColumnCount(arquivoCSV);
	colunas_arquivo.setText(decimalFormat.format(quantColunas));
	
	/* Atualiza registros_arquivo */ 
	int quantRegistros = (FileUtil.getFileRowCount(arquivoCSV) - 1);
	registros_arquivo.setText(decimalFormat.format(quantRegistros));
	
	/* Atualiza separador_arquivo */
	String separadorArquivo = (FileUtil.determineFileSeparator(arquivoCSV));
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
	    FileInfo dialog = new FileInfo(new javax.swing.JFrame(), true);
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

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nome_arquivo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tamanho_arquivo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        local_arquivo = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        linhas_arquivo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        colunas_arquivo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        registros_arquivo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        separador_arquivo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ok_botao = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informações do CSV");
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivo"));

        jLabel1.setText("Nome do Arquivo:");

        nome_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nome_arquivo.setText("NaN");

        jLabel3.setText("Tamanho:");

        tamanho_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tamanho_arquivo.setText("NaN");

        jLabel7.setText("Localização:");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nome_arquivo)
                    .addComponent(tamanho_arquivo)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nome_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tamanho_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Conteúdo"));

        jLabel4.setText("Linhas:");

        linhas_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        linhas_arquivo.setText("NaN");

        jLabel5.setText("Colunas:");

        colunas_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        colunas_arquivo.setText("NaN");

        jLabel6.setText("Registros:");

        registros_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        registros_arquivo.setText("NaN");

        jLabel8.setText("Separador:");

        separador_arquivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        separador_arquivo.setText("NaN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separador_arquivo)
                    .addComponent(registros_arquivo)
                    .addComponent(colunas_arquivo)
                    .addComponent(linhas_arquivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(linhas_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(colunas_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(registros_arquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(separador_arquivo))
                .addContainerGap(15, Short.MAX_VALUE))
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ok_botao)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel linhas_arquivo;
    private javax.swing.JTextArea local_arquivo;
    private javax.swing.JLabel nome_arquivo;
    private javax.swing.JToggleButton ok_botao;
    private javax.swing.JLabel registros_arquivo;
    private javax.swing.JLabel separador_arquivo;
    private javax.swing.JLabel tamanho_arquivo;
    // End of variables declaration//GEN-END:variables
}
