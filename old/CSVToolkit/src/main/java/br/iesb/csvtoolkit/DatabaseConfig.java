package br.iesb.csvtoolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DatabaseConfig extends javax.swing.JDialog {
    
    private final Main main;
    private final ConnectionManager databaseManager;
    
    public boolean conexaoAtiva = false;
    private String hostConexaoAtiva;
    public String databaseAtivo;
    
    
    /* Getters para variáveis dessa classe */
    public String getDatabaseAtivo() {
        return databaseAtivo;
    }
    

    public DatabaseConfig(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	
	main = (Main) parent; // Obtém a instância da classe Main passando o parent
        databaseManager = main.connectionManager; // Obtém a instância de ConnectionManager criada na classe Main
    }

    
    public static void main(String args[]) {
	/* Definição do tema da UI */
	try {
	    UIManager.setLookAndFeel( new FlatMacDarkLaf() );
	} catch( UnsupportedLookAndFeelException ex ) {
	    System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	}

	java.awt.EventQueue.invokeLater(() -> {
	    DatabaseConfig dialog = new DatabaseConfig(new javax.swing.JFrame(), true);
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

        jPanel4 = new javax.swing.JPanel();
        jLabelHost = new javax.swing.JLabel();
        Host = new javax.swing.JTextField();
        jLabelPorta = new javax.swing.JLabel();
        Port = new javax.swing.JTextField();
        jLabelBanco = new javax.swing.JLabel();
        Database = new javax.swing.JTextField();
        jLabelUser = new javax.swing.JLabel();
        User = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelPass = new javax.swing.JLabel();
        ConectarAoBD = new javax.swing.JButton();
        CancelarConexaoAoBD = new javax.swing.JButton();
        TestarConexaoAoBD = new javax.swing.JButton();
        Password = new javax.swing.JPasswordField();
        jLabelHost1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conectar ao Banco de Dados");
        setResizable(false);

        jLabelHost.setText("SGBD:");

        Host.setToolTipText("");

        jLabelPorta.setText("Porta:");

        jLabelBanco.setText("Nome do Banco: ");

        jLabelUser.setText("Nome de Usuário: ");

        jLabelPass.setText("Senha:");

        ConectarAoBD.setText("Conectar");
        ConectarAoBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConectarAoBD(evt);
            }
        });

        CancelarConexaoAoBD.setText("Cancelar");
        CancelarConexaoAoBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarConexaoAoBD(evt);
            }
        });

        TestarConexaoAoBD.setText("Testar Conexão");
        TestarConexaoAoBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestarConexaoAoBD(evt);
            }
        });

        jLabelHost1.setText("Host:");

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("PostgreSQL");
        jRadioButton1.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(User))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(CancelarConexaoAoBD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(TestarConexaoAoBD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConectarAoBD))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelHost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelBanco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabelPorta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelHost1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Port, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Database)
                            .addComponent(Host)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHost)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHost1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPorta)
                    .addComponent(Port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBanco)
                    .addComponent(Database, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPass)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConectarAoBD)
                    .addComponent(CancelarConexaoAoBD)
                    .addComponent(TestarConexaoAoBD))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void ConectarAoBD(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConectarAoBD
        String host = Host.getText();
        String port = Port.getText();
        String database = Database.getText();
        String user = User.getText();
        String password = new String(Password.getPassword());
	
	if (host.isEmpty() || port.isEmpty() || database.isEmpty() || user.isEmpty() || password.isEmpty()) {
	    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	} else {
	    // Configuração da conexão. Passe essas informações para a classe ConnectionManager para criar a conexão.
	    databaseManager.estabelecerConexao(host, port, database, user, password);
	    
	    // Verifique se a conexão foi bem-sucedida
	    if (databaseManager.isConexaoAtiva()) {
		conexaoAtiva = true;
		hostConexaoAtiva = host;
		databaseAtivo = database;
		JOptionPane.showMessageDialog(this, "Conexão bem-sucedida!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		
		// Chame o método para atualizar a JLabel na classe Main
		main.atualizarStatusDoBanco(conexaoAtiva, hostConexaoAtiva);
		main.verificaExistenciaArquivo();
		dispose();
		
	    } else {
		// A conexão não foi bem-sucedida, exiba uma mensagem de erro
		JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados. Verifique as credenciais e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}
    }//GEN-LAST:event_ConectarAoBD

    private void CancelarConexaoAoBD(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarConexaoAoBD
        dispose();
    }//GEN-LAST:event_CancelarConexaoAoBD

    private void TestarConexaoAoBD(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestarConexaoAoBD
        String host = Host.getText();
        String port = Port.getText();
        String database = Database.getText();
        String user = User.getText();
        String password = new String(Password.getPassword());
	
	if (host.isEmpty() || port.isEmpty() || database.isEmpty() || user.isEmpty() || password.isEmpty()) {
	    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	} else {
	    // Configuração da conexão. Passe essas informações para a classe ConnectionManager para testar a conexão.
	    databaseManager.testarConexao(host, port, database, user, password);
	    
	    // Verifique se a conexão foi bem-sucedida
	    if (databaseManager.isConexaoBemSucedida()) {
		JOptionPane.showMessageDialog(this, "Teste de conexão bem-sucedido. Agora você pode conectar ao banco de dados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		
	    } else {
		// A conexão não foi bem-sucedida, exiba uma mensagem de erro
		JOptionPane.showMessageDialog(this, "Falha na conexão. Verifique as credenciais e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}
    }//GEN-LAST:event_TestarConexaoAoBD




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarConexaoAoBD;
    private javax.swing.JButton ConectarAoBD;
    private javax.swing.JTextField Database;
    private javax.swing.JTextField Host;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Port;
    private javax.swing.JButton TestarConexaoAoBD;
    private javax.swing.JTextField User;
    private javax.swing.JLabel jLabelBanco;
    private javax.swing.JLabel jLabelHost;
    private javax.swing.JLabel jLabelHost1;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelPorta;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
