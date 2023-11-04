package br.iesb.csvtoolkit.window;

import br.iesb.csvtoolkit.file.*;
import br.iesb.csvtoolkit.util.*;
import br.iesb.csvtoolkit.database.*;

/* Classes Importadas */
//<editor-fold defaultstate="collapsed" desc="(collapsed) General Imported Classes">
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//</editor-fold>

public class Home extends javax.swing.JFrame {
    
    /* Instâncias */
    public ConnectionManager connectionManager;
    public DatabaseUploader databaseUploader;
    public DatabaseConnector databaseConfig;
    public UserSettings userSettings;
    public FileInfo infoCSV;
    public static Home instance;
    public DefaultTableModel tableModel;
    
    /* Atributos - Gerenciamento do Arquivo */    
    String nomeArquivoOriginal = null;
    String nomeArquivo = "csvImportado.csv";
    String diretorio = System.getProperty("user.dir");
    String caminhoArquivo = diretorio + File.separator + nomeArquivo;
    
    public String tamanhoCSV;
    public File arquivo = new File(caminhoArquivo);
    public File arquivoCSV = new File("csvImportado.csv");
    boolean arquivoEstaAberto = false;
    
    /* Contadores do Tempo de Processamento do Arquivo */
    public long startTime = 0;
    public long endTime = 0;
    
    /* Construtor Principal da Classe */
    public Home() {
	this.tamanhoCSV = FileUtil.getFileSize(arquivoCSV);
	instance = this;
	initComponents();
	
	/* Criação de Instâncias de Classes Auxiliares */
	connectionManager = new ConnectionManager();
	userSettings = new UserSettings();
	
	/* Criação do Modelo da Tabela */
	tableModel = new DefaultTableModel();
	tableFileContent.setModel(tableModel);
	
	
	/* Visibilidade de itens ao abrir a janela */
	buttonForceCloseConnection.setVisible(false);
	labelConnectionStatus.setText("Banco desconectado");
	
	/* Métodos executados ao abrir a janela */
	checkFileExistence();

    }

    /* Main - Definição do tema da UI */
    public static void main(String args[]) {
	    try {
		UIManager.setLookAndFeel( new FlatMacLightLaf() );
	    } catch( UnsupportedLookAndFeelException ex ) {
		System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	    }

	    java.awt.EventQueue.invokeLater(() -> {
		new Home().setVisible(true);
	    });
	}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        superiorPanel = new javax.swing.JPanel();
        buttonViewFileContent = new javax.swing.JButton();
        newConsole = new javax.swing.JLabel();
        inferiorPanel = new javax.swing.JToolBar();
        labelConnectionStatus = new javax.swing.JLabel();
        buttonForceCloseConnection = new javax.swing.JButton();
        toolbarFiller = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        labelProcessingTime = new javax.swing.JLabel();
        middlePanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        tableFileContent = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        mainMenuFile = new javax.swing.JMenu();
        menuOpenLocalFile = new javax.swing.JMenuItem();
        menuOpenFileFromWeb = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuCloseFile = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        mainMenuTools = new javax.swing.JMenu();
        menuFileInfo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuConnectToDB = new javax.swing.JMenuItem();
        menuUploadToDB = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuNewQuery = new javax.swing.JMenuItem();
        mainMenuHelp = new javax.swing.JMenu();
        menuKeyboardShortcuts = new javax.swing.JMenuItem();
        menuAbout = new javax.swing.JMenuItem();

        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setDialogTitle("Abrir arquivo local");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos CSV", "csv"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DataBridge Pro");
        setMinimumSize(new java.awt.Dimension(600, 600));

        buttonViewFileContent.setText("Visualizar CSV");
        buttonViewFileContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewFileContent(evt);
            }
        });

        newConsole.setText("Bem-Vindo!");

        javax.swing.GroupLayout superiorPanelLayout = new javax.swing.GroupLayout(superiorPanel);
        superiorPanel.setLayout(superiorPanelLayout);
        superiorPanelLayout.setHorizontalGroup(
            superiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, superiorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newConsole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonViewFileContent, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        superiorPanelLayout.setVerticalGroup(
            superiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(superiorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(superiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonViewFileContent)
                    .addComponent(newConsole))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        inferiorPanel.setRollover(true);

        labelConnectionStatus.setForeground(new java.awt.Color(102, 102, 102));
        labelConnectionStatus.setLabelFor(labelConnectionStatus);
        labelConnectionStatus.setToolTipText("");
        inferiorPanel.add(labelConnectionStatus);

        buttonForceCloseConnection.setText("Encerrar conexão");
        buttonForceCloseConnection.setFocusable(false);
        buttonForceCloseConnection.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonForceCloseConnection.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonForceCloseConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonForceCloseConnectionActionPerformed(evt);
            }
        });
        inferiorPanel.add(buttonForceCloseConnection);
        inferiorPanel.add(toolbarFiller);
        inferiorPanel.add(labelProcessingTime);

        middlePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tableFileContent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableFileContent.setAlignmentX(0.0F);
        tableFileContent.setAlignmentY(0.0F);
        tableFileContent.setCellSelectionEnabled(true);
        tableFileContent.setFocusable(false);
        tableFileContent.setShowGrid(true);
        tableScrollPane.setViewportView(tableFileContent);

        javax.swing.GroupLayout middlePanelLayout = new javax.swing.GroupLayout(middlePanel);
        middlePanel.setLayout(middlePanelLayout);
        middlePanelLayout.setHorizontalGroup(
            middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        middlePanelLayout.setVerticalGroup(
            middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        mainMenuFile.setText("Arquivo");

        menuOpenLocalFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpenLocalFile.setText("Abrir arquivo local...");
        menuOpenLocalFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenLocalFile(evt);
            }
        });
        mainMenuFile.add(menuOpenLocalFile);

        menuOpenFileFromWeb.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpenFileFromWeb.setText("Abrir arquivo da Web...");
        menuOpenFileFromWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenFileFromWeb(evt);
            }
        });
        mainMenuFile.add(menuOpenFileFromWeb);
        mainMenuFile.add(jSeparator1);

        menuCloseFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuCloseFile.setText("Fechar arquivo");
        menuCloseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCloseFileActionPerformed(evt);
            }
        });
        mainMenuFile.add(menuCloseFile);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuExit.setText("Sair");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        mainMenuFile.add(menuExit);

        menuBar.add(mainMenuFile);

        mainMenuTools.setText("Comandos");

        menuFileInfo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuFileInfo.setText("Informações do CSV");
        menuFileInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileInfoActionPerformed(evt);
            }
        });
        mainMenuTools.add(menuFileInfo);
        mainMenuTools.add(jSeparator2);

        menuConnectToDB.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuConnectToDB.setText("Conectar Banco de Dados...");
        menuConnectToDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConnectToDB(evt);
            }
        });
        mainMenuTools.add(menuConnectToDB);

        menuUploadToDB.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuUploadToDB.setText("Enviar arquivo para Banco de Dados");
        menuUploadToDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUploadToDBActionPerformed(evt);
            }
        });
        mainMenuTools.add(menuUploadToDB);
        mainMenuTools.add(jSeparator3);

        menuNewQuery.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuNewQuery.setText("Filtrar dados");
        menuNewQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewQueryActionPerformed(evt);
            }
        });
        mainMenuTools.add(menuNewQuery);

        menuBar.add(mainMenuTools);

        mainMenuHelp.setText("Ajuda");

        menuKeyboardShortcuts.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuKeyboardShortcuts.setText("Atalhos de Teclado");
        menuKeyboardShortcuts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKeyboardShortcutsActionPerformed(evt);
            }
        });
        mainMenuHelp.add(menuKeyboardShortcuts);

        menuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuAbout.setText("Sobre");
        menuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAboutActionPerformed(evt);
            }
        });
        mainMenuHelp.add(menuAbout);

        menuBar.add(mainMenuHelp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(middlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inferiorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(superiorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(superiorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(middlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inferiorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


/* MÉTODOS DE EXIBIÇÃO */
    
    /* Método para exibir o CSV local na Tabela */
    private void showFileContentInTable(String fileName) {
	String detectedSeparator = FileUtil.determineFileSeparator(arquivoCSV);
	
	try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            startTime = System.currentTimeMillis();
	    String row;
	    boolean firstRow = true;
	    
	    while ((row = reader.readLine()) != null) {
		if (firstRow) {
		    row = row.toLowerCase();
		    firstRow = false;
		    String[] columns = row.split(Pattern.quote(detectedSeparator));
		    tableModel.setColumnIdentifiers(columns);
		    
		} else {
		    String[] fileData = row.split(Pattern.quote(detectedSeparator));
		    tableModel.addRow(fileData);
		    
		}
	    }
	} catch (IOException e) {
	    System.out.println("ERRO: Falha ao preencher a tabela com CSV. " + e.getMessage());
	} 
	
        endTime = System.currentTimeMillis();
        
        updateProcessingTimeLabel();
    }
    

    /* Método para verificar a existência do arquivo na pasta e ativar/desativar menus da interface */
    public final Boolean checkFileExistence() {
	File csvFile = new File("csvImportado.csv");
	if (csvFile.exists()) {
	    menuCloseFile.setEnabled(true);
	    menuFileInfo.setEnabled(true);
	    menuFileInfo.setEnabled(true);
	    
	    if (connectionManager.conexaoAtiva == true) {
		menuUploadToDB.setEnabled(true); 
	    }
	    
	    showFileContentInTable(nomeArquivo);
	    return true;
	} else {
	    menuCloseFile.setEnabled(false);
	    menuFileInfo.setEnabled(false); 
	    menuUploadToDB.setEnabled(false); 
	    menuFileInfo.setEnabled(false);
	    return false;
	}
    }
    

    
/* MÉTODOS COMPARTILHÁVEIS */

    /* Método compartilhável para permitir alteração da mensagem no Console */
    public void updateConsoleMessage(String mensagem) {
	newConsole.setText(mensagem);
    }
    
    /* Método para atualizar a caixa de status do Banco */
    public void updateDatabaseConnectionStatus(boolean status, String host) {
	labelConnectionStatus.setText("Conectado a: " + host);
	buttonForceCloseConnection.setVisible(true);
	System.out.println("conexaoAtiva: " + connectionManager.isConexaoAtiva());
    }
    
    /* Método para exibir o status de conexão com o Banco */
    private void forceCloseConnection() {
	if (connectionManager.isConexaoAtiva()) {
	    connectionManager.fecharConexao();
	    labelConnectionStatus.setText("Banco desconectado");
	    buttonForceCloseConnection.setVisible(false);
	} else {
	    labelConnectionStatus.setText("Não foi possível encerrar a conexão");
	    System.out.println("ERRO: não foi possível encerrar a conexão com o banco de dados.");
	}
    }
    
    /* Método compartilhável para obter o separador do arquivo em formato String 
    public String getSeparadorArquivo() {
	return setSeparadorArquivo();
    }*/
    
    /* Método para obter o nome do arquivo */
    public String getNomeArquivo() {
	if (arquivoEstaAberto) {
	    return nomeArquivo;
	} else {
	    return nomeArquivoOriginal;
	}
    }
    
    
    /* Método compartilhável para retornar o local do arquivo */
    public String getLocalArquivo() {
	return caminhoArquivo;
    }
   
    
    /* Método para atualizar o label com o tempo de processamento do arquivo */
    private void updateProcessingTimeLabel() {
	if (startTime > 0 && endTime > 0) {
	    long processingTime = endTime - startTime;
	    // Formate o tempo para exibição, por exemplo, em segundos
	    double processingTimeInSeconds = processingTime / 1000.0;
	    labelProcessingTime.setText("Tempo de processamento: " + processingTimeInSeconds + " segundos");
	} else {
	    labelProcessingTime.setText("Tempo de processamento: N/A");
	}
    }

    
/* AÇÕES GERAIS DA INTERFACE */

    /* Abrir arquivo Local - Menu */
    private void menuOpenLocalFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenLocalFile
	File selectedFile = FileUtil.openLocalFile(fileChooser);
	if (selectedFile != null) {
	    nomeArquivoOriginal = selectedFile.getName();
	    File copyFile = new File("csvImportado.csv");

	    try {
		FileUtil.copyFile(selectedFile, copyFile);
		showFileContentInTable(selectedFile.getAbsolutePath());
		checkFileExistence();
	    } catch (IOException e) {
		System.out.println("ERRO: Impossível abrir arquivo local. " + e);
	    }
	}
    }//GEN-LAST:event_menuOpenLocalFile

    
    /* Visualizar Arquivo no Console - Botão */
    private void buttonViewFileContent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewFileContent
        String allowedFileName = "csvImportado.csv";
	String selectedFileName = null;
	
	File file = new File(allowedFileName);
	if (file.exists()) {
	    selectedFileName = allowedFileName;
	}
	
	if (selectedFileName != null) {
	    showFileContentInTable(selectedFileName);
	    
	} else {
	    newConsole.setText("Não há nenhum arquivo aberto");
	}
    }//GEN-LAST:event_buttonViewFileContent
  
    
    /* Abrir arquivo da Web - Menu */
    private void menuOpenFileFromWeb(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenFileFromWeb
        FileDownloader dialogoAbrirWeb = new FileDownloader(this, true);
	dialogoAbrirWeb.setVisible(true);
    }//GEN-LAST:event_menuOpenFileFromWeb

    
    /* Sair do Aplicativo - Menu */
    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        dispose();
    }//GEN-LAST:event_menuExitActionPerformed

    
    /* Fechar Arquivo Aberto no momento - Menu */
    private void menuCloseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCloseFileActionPerformed
	FileClose dialogFecharArquivo = new FileClose(this, true);
	dialogFecharArquivo.setVisible(true);
        labelProcessingTime.setText("Tempo de processamento: N/A");
    }//GEN-LAST:event_menuCloseFileActionPerformed

    
    /* Conectar ao Banco de Dados - Menu */
    private void menuConnectToDB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConnectToDB
        databaseConfig = new DatabaseConnector(this, true);
	databaseConfig.setVisible(true);
    }//GEN-LAST:event_menuConnectToDB

    
    /* Encerrar Conexão com o Banco - Botão Especial */
    private void buttonForceCloseConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonForceCloseConnectionActionPerformed
	forceCloseConnection();
    }//GEN-LAST:event_buttonForceCloseConnectionActionPerformed

    
    /* Enviar Arquivo para o Banco de Dados - Menu */
    private void menuUploadToDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUploadToDBActionPerformed
        DatabaseUploader dialogoUpload = new DatabaseUploader(this, true);
	dialogoUpload.setVisible(true);
    }//GEN-LAST:event_menuUploadToDBActionPerformed

    
    /* Informações do CSV - Menu */
    private void menuFileInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileInfoActionPerformed
        FileInfo dialogoInfoCSV = new FileInfo(this, true);
	dialogoInfoCSV.setVisible(true);
    }//GEN-LAST:event_menuFileInfoActionPerformed

    
    /* Filtrar fileData do Arquivo - Menu */
    private void menuNewQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewQueryActionPerformed
	if (connectionManager.conexaoAtiva == true) {
	QueryManager dialogFiltragemDados = new QueryManager(this, true);
	dialogFiltragemDados.setVisible(true);
	} else {
	    JOptionPane.showMessageDialog(this, "A filtragem de dados requer conexão com um banco de dados e não há uma conexão no momento. Conecte-se a um banco de dados e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
	}
    }//GEN-LAST:event_menuNewQueryActionPerformed

    
    /* About - Menu */
    private void menuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAboutActionPerformed
        About dialogSobre = new About(this, true);
	dialogSobre.setVisible(true);
    }//GEN-LAST:event_menuAboutActionPerformed

    private void menuKeyboardShortcutsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKeyboardShortcutsActionPerformed
        KeyboardShortcuts dialogAtalhos = new KeyboardShortcuts(this, true);
	dialogAtalhos.setVisible(true);
    }//GEN-LAST:event_menuKeyboardShortcutsActionPerformed

    
   
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonForceCloseConnection;
    private javax.swing.JButton buttonViewFileContent;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JToolBar inferiorPanel;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    public javax.swing.JLabel labelConnectionStatus;
    public javax.swing.JLabel labelProcessingTime;
    private javax.swing.JMenu mainMenuFile;
    private javax.swing.JMenu mainMenuHelp;
    private javax.swing.JMenu mainMenuTools;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuCloseFile;
    private javax.swing.JMenuItem menuConnectToDB;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuFileInfo;
    private javax.swing.JMenuItem menuKeyboardShortcuts;
    private javax.swing.JMenuItem menuNewQuery;
    private javax.swing.JMenuItem menuOpenFileFromWeb;
    private javax.swing.JMenuItem menuOpenLocalFile;
    private javax.swing.JMenuItem menuUploadToDB;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JLabel newConsole;
    private javax.swing.JPanel superiorPanel;
    private javax.swing.JTable tableFileContent;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.Box.Filler toolbarFiller;
    // End of variables declaration//GEN-END:variables
}
