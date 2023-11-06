package br.iesb.csvtoolkit.database;

import br.iesb.csvtoolkit.window.Home;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DatabaseConnector extends javax.swing.JDialog {
    
    private final Home main;
    private final ConnectionManager connectionManager;
    
    public boolean conexaoAtiva = false;
    private String hostConexaoAtiva;
    public String databaseAtivo;
    
    
    /* Getters para variáveis dessa classe */
    public String getDatabaseAtivo() {
        return databaseAtivo;
    }
    

    public DatabaseConnector(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	
	main = (Home) parent; // Obtém a instância da classe Home passando o parent
        connectionManager = main.connectionManager; // Obtém a instância de ConnectionManager criada na classe Home
    }

    
    public static void main(String args[]) {
	/* Definição do tema da UI */
	try {
	    UIManager.setLookAndFeel( new FlatMacDarkLaf() );
	} catch( UnsupportedLookAndFeelException ex ) {
	    System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	}

	java.awt.EventQueue.invokeLater(() -> {
	    DatabaseConnector dialog = new DatabaseConnector(new javax.swing.JFrame(), true);
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
        labelDBType = new javax.swing.JLabel();
        inputHost = new javax.swing.JTextField();
        labelPort = new javax.swing.JLabel();
        inputPort = new javax.swing.JTextField();
        labelDatabase = new javax.swing.JLabel();
        inputDatabase = new javax.swing.JTextField();
        labelUsername = new javax.swing.JLabel();
        inputUser = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        labelPassword = new javax.swing.JLabel();
        buttonConnect = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonTestConnection = new javax.swing.JButton();
        inputPassword = new javax.swing.JPasswordField();
        labelHost = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conectar ao Banco de Dados");
        setResizable(false);

        labelDBType.setText("SGBD:");

        inputHost.setToolTipText("");

        labelPort.setText("Porta:");

        labelDatabase.setText("Nome do Banco: ");

        labelUsername.setText("Nome de Usuário: ");

        labelPassword.setText("Senha:");

        buttonConnect.setText("Conectar");
        buttonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConnect(evt);
            }
        });

        buttonCancel.setText("Cancelar");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancel(evt);
            }
        });

        buttonTestConnection.setText("Testar Conexão");
        buttonTestConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTestConnection(evt);
            }
        });

        labelHost.setText("Host:");

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
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(labelUsername)
                            .addGap(28, 28, 28)
                            .addComponent(inputUser))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(labelPassword)
                            .addGap(92, 92, 92)
                            .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(buttonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(buttonTestConnection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonConnect))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHost)
                            .addComponent(labelDBType)
                            .addComponent(labelPort)
                            .addComponent(labelDatabase))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(inputPort, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputDatabase)
                            .addComponent(inputHost)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDBType)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPort)
                    .addComponent(inputPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDatabase)
                    .addComponent(inputDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsername)
                    .addComponent(inputUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPassword)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConnect)
                    .addComponent(buttonCancel)
                    .addComponent(buttonTestConnection))
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

    
    
    
    private void buttonConnect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConnect
        String host = inputHost.getText();
        String port = inputPort.getText();
        String database = inputDatabase.getText();
        String user = inputUser.getText();
        String password = new String(inputPassword.getPassword());
	
	if (host.isEmpty() || port.isEmpty() || database.isEmpty() || user.isEmpty() || password.isEmpty()) {
	    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	} else {
	    // Configuração da conexão. Passe essas informações para a classe ConnectionManager para criar a conexão.
	    connectionManager.estabelecerConexao(host, port, database, user, password);
	    
	    // Verifique se a conexão foi bem-sucedida
	    if (connectionManager.isConexaoAtiva()) {
		conexaoAtiva = true;
		hostConexaoAtiva = host;
		databaseAtivo = database;
		JOptionPane.showMessageDialog(this, "Conexão bem-sucedida!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		
		// Chame o método para atualizar a JLabel na classe Home
		main.updateDatabaseConnectionStatus(conexaoAtiva, hostConexaoAtiva);
		main.checkFileExistence();
		dispose();
		
	    } else {
		// A conexão não foi bem-sucedida, exiba uma mensagem de erro
		JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados. Verifique as credenciais e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}
    }//GEN-LAST:event_buttonConnect

    private void buttonCancel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancel
        dispose();
    }//GEN-LAST:event_buttonCancel

    private void buttonTestConnection(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTestConnection
        String host = inputHost.getText();
        String port = inputPort.getText();
        String database = inputDatabase.getText();
        String user = inputUser.getText();
        String password = new String(inputPassword.getPassword());
	
	if (host.isEmpty() || port.isEmpty() || database.isEmpty() || user.isEmpty() || password.isEmpty()) {
	    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	} else {
	    // Configuração da conexão. Passe essas informações para a classe ConnectionManager para testar a conexão.
	    connectionManager.testarConexao(host, port, database, user, password);
	    
	    // Verifique se a conexão foi bem-sucedida
	    if (connectionManager.isConexaoBemSucedida()) {
		JOptionPane.showMessageDialog(this, "Teste de conexão bem-sucedido. Agora você pode conectar ao banco de dados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		
	    } else {
		// A conexão não foi bem-sucedida, exiba uma mensagem de erro
		JOptionPane.showMessageDialog(this, "Falha na conexão. Verifique as credenciais e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}
    }//GEN-LAST:event_buttonTestConnection




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConnect;
    private javax.swing.JButton buttonTestConnection;
    private javax.swing.JTextField inputDatabase;
    private javax.swing.JTextField inputHost;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputPort;
    private javax.swing.JTextField inputUser;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelDBType;
    private javax.swing.JLabel labelDatabase;
    private javax.swing.JLabel labelHost;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelPort;
    private javax.swing.JLabel labelUsername;
    // End of variables declaration//GEN-END:variables
}
