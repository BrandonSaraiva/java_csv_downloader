/*
 * Análise de CSV
 * Projeto Final da Disciplina de Programação II - Java
 * Brandon Cardoso de Araújo Saraiva & Robson Ricardo Leite da Silva
 */

package br.iesb.projetocsv;

import com.formdev.flatlaf.FlatDarkLaf;
import com.opencsv.CSVParserBuilder;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class PaginaInicial extends javax.swing.JFrame {
    
    private boolean arquivoAberto = false;
    private String nomeArquivoAberto = "Nenhum arquivo aberto";
    private Connection conexao;
    private boolean conexaoAtiva = false;
    private String hostConexaoAtiva = "";
    
    /* Atributos exclusivos do arquivo */
    String nomeArquivoBaixado = "csvBaixado.csv"; // Nome do arquivo baixado
    String diretorioProjeto = System.getProperty("user.dir");
    String caminhoAbsoluto = diretorioProjeto + File.separator + nomeArquivoBaixado;
    File arquivo = new File(caminhoAbsoluto);
    private String originalFileName = null;
    
    private SwingWorker<Void, Void> worker;
    
    
    public PaginaInicial() {
	initComponents();
	
	// Verifique a existência do arquivo "csvBaixado.csv"
	File csvFile = new File("csvBaixado.csv");
	if (csvFile.exists()) {
	    jMenuItem_FecharArquivo.setEnabled(true); // Habilita o item de menu
	    exibirConteudoCSV(csvFile); // Exibe o conteúdo do arquivo
	} else {
	    jMenuItem_FecharArquivo.setEnabled(false); // Desabilita o item de menu
	    jMenuItem_EnviarParaBD.setEnabled(false); 
	}

    }
    
    private void exibirConteudoCSV(File csvFile) {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(csvFile));
	    StringBuilder conteudo = new StringBuilder();
	    String linha;
	    while ((linha = reader.readLine()) != null) {
		conteudo.append(linha).append("\n");
	    }
	    Console.setText(conteudo.toString());
	} catch (IOException e) {
	    System.err.println( "Falha ao carregar o arquivo CSV (exibirConteudoCSV())." );
	}
    }

    public static void main(String args[]) {
	/* Definição do tema da UI */
	try {
	    UIManager.setLookAndFeel( new FlatDarkLaf() );
	} catch( UnsupportedLookAndFeelException ex ) {
	    System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	}

	/* Cria e exibe o JFrame principal */
	java.awt.EventQueue.invokeLater(() -> {
	    new PaginaInicial().setVisible(true);
	});
    }
    
    
    private FileReader fileReader = null;
    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jButton_CancelarFechamentoArquivo = new javax.swing.JButton();
        jButton_ConfirmarFechamentoArquivo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jDialog_LoginBD = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabelHost = new javax.swing.JLabel();
        jTextFieldHost = new javax.swing.JTextField();
        jLabelPorta = new javax.swing.JLabel();
        jTextFieldPorta = new javax.swing.JTextField();
        jLabelBanco = new javax.swing.JLabel();
        jTextFieldBanco = new javax.swing.JTextField();
        jLabelUser = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelPass = new javax.swing.JLabel();
        jButtonConectar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonTestarConexao = new javax.swing.JButton();
        jTextFieldPass = new javax.swing.JPasswordField();
        jLabelHost1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jDialog_Sobre = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton_LimparConsole = new javax.swing.JButton();
        jButton_VisualizarCSV = new javax.swing.JButton();
        jTextField_NomeArquivoAberto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Console = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_Arquivo = new javax.swing.JMenu();
        jMenuItem_AbrirLocal = new javax.swing.JMenuItem();
        jMenuItem_AbrirWeb = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_FecharArquivo = new javax.swing.JMenuItem();
        jMenuItem_Sair = new javax.swing.JMenuItem();
        jMenu_Ferramentas = new javax.swing.JMenu();
        jMenuItem_InfoCSV = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_FiltrarDados = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_ConectarBD = new javax.swing.JMenuItem();
        jMenuItem_EnviarParaBD = new javax.swing.JMenuItem();
        jMenu_Sobre = new javax.swing.JMenu();
        jMenuItem_AtalhosTeclado = new javax.swing.JMenuItem();
        jMenuItem_Sobre = new javax.swing.JMenuItem();

        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setDialogTitle("Abrir arquivo local");
        fileChooser.setFileFilter(new br.iesb.projetocsv.FiltragemArquivos());

        jDialog_FecharArquivo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog_FecharArquivo.setLocation(new java.awt.Point(0, 0));
        jDialog_FecharArquivo.setModalExclusionType(null);
        jDialog_FecharArquivo.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog_FecharArquivo.setResizable(false);
        jDialog_FecharArquivo.setSize(new java.awt.Dimension(414, 173));
        jDialog_FecharArquivo.setLocationRelativeTo(null);

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

        jButton_CancelarFechamentoArquivo.setText("Cancelar");
        jButton_CancelarFechamentoArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarFechamentoArquivoActionPerformed(evt);
            }
        });

        jButton_ConfirmarFechamentoArquivo.setText("Fechar arquivo");
        jButton_ConfirmarFechamentoArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConfirmarFechamentoArquivoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe Fluent Icons", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setText("");
        jLabel2.setIconTextGap(0);
        jLabel2.setMaximumSize(new java.awt.Dimension(48, 48));
        jLabel2.setMinimumSize(new java.awt.Dimension(48, 48));
        jLabel2.setPreferredSize(new java.awt.Dimension(48, 48));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton_CancelarFechamentoArquivo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_ConfirmarFechamentoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_ConfirmarFechamentoArquivo)
                    .addComponent(jButton_CancelarFechamentoArquivo))
                .addGap(15, 15, 15))
        );

        jDialog_FecharArquivo.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jDialog_LoginBD.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog_LoginBD.setTitle("Conectar ao Banco de Dados");
        jDialog_LoginBD.setModalExclusionType(null);
        jDialog_LoginBD.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog_LoginBD.setPreferredSize(new java.awt.Dimension(420, 310));
        jDialog_LoginBD.setResizable(false);
        jDialog_LoginBD.setSize(new java.awt.Dimension(420, 310));
        jDialog_LoginBD.setType(java.awt.Window.Type.POPUP);
        jDialog_LoginBD.getContentPane().setLayout(new java.awt.CardLayout());
        jDialog_LoginBD.setLocationRelativeTo(null);

        jLabelHost.setText("SGBD:");

        jTextFieldHost.setToolTipText("XX.XX.XX.XX");

        jLabelPorta.setText("Porta:");

        jLabelBanco.setText("Nome do Banco: ");

        jLabelUser.setText("Nome de Usuário: ");

        jLabelPass.setText("Senha:");

        jButtonConectar.setText("Conectar");
        jButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonTestarConexao.setText("Testar Conexão");
        jButtonTestarConexao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTestarConexaoActionPerformed(evt);
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
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jButtonCancelar)
                            .addGap(110, 110, 110)
                            .addComponent(jButtonTestarConexao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonConectar))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldUser))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelHost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelBanco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabelPorta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelHost1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldPorta, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldBanco)
                            .addComponent(jTextFieldHost)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jLabelHost))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHost1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPorta)
                    .addComponent(jTextFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBanco)
                    .addComponent(jTextFieldBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPass)
                    .addComponent(jTextFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConectar)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonTestarConexao))
                .addGap(19, 19, 19))
        );

        jDialog_LoginBD.getContentPane().add(jPanel4, "card2");

        jDialog_Sobre.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog_Sobre.setTitle("Sobre");
        jDialog_Sobre.setModalExclusionType(null);
        jDialog_Sobre.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog_Sobre.setPreferredSize(new java.awt.Dimension(360, 300));
        jDialog_Sobre.setResizable(false);
        jDialog_Sobre.setSize(new java.awt.Dimension(360, 300));
        jDialog_Sobre.setLocationRelativeTo(null);

        jLabel3.setText("Robson Ricardo Leite da Silva");

        jLabel4.setText("Brandon Cardoso de Araújo Saraiva");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("CSV Toolkit");

        jLabel6.setText("alpha-0.0.5");

        jLabel7.setText("Apagando C:\\WINDOWS\\System32 ...");

        jProgressBar1.setIndeterminate(true);

        jLabel8.setText("build 231023");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(22, 22, 22))
        );

        jDialog_Sobre.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CSV Toolkit");

        jButton_LimparConsole.setText("Limpar console");
        jButton_LimparConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LimparConsoleActionPerformed(evt);
            }
        });

        jButton_VisualizarCSV.setText("Visualizar CSV");
        jButton_VisualizarCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VisualizarCSVActionPerformed(evt);
            }
        });

        jTextField_NomeArquivoAberto.setEditable(false);
        jTextField_NomeArquivoAberto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_NomeArquivoAberto.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_VisualizarCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_NomeArquivoAberto, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_LimparConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_LimparConsole)
                    .addComponent(jButton_VisualizarCSV)
                    .addComponent(jTextField_NomeArquivoAberto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Console.setColumns(20);
        Console.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        Console.setForeground(new java.awt.Color(0, 204, 51));
        Console.setRows(5);
        Console.setText("Aguardando entrada do usuário....");
        Console.setWrapStyleWord(true);
        Console.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 6, 4, 4));
        Console.setFocusable(false);
        jScrollPane1.setViewportView(Console);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );

        jMenu_Arquivo.setText("Arquivo");

        jMenuItem_AbrirLocal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_AbrirLocal.setText("Abrir arquivo local...");
        jMenuItem_AbrirLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_AbrirLocalActionPerformed(evt);
            }
        });
        jMenu_Arquivo.add(jMenuItem_AbrirLocal);

        jMenuItem_AbrirWeb.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_AbrirWeb.setText("Abrir arquivo da Web...");
        jMenuItem_AbrirWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_AbrirWebActionPerformed(evt);
            }
        });
        jMenu_Arquivo.add(jMenuItem_AbrirWeb);
        jMenu_Arquivo.add(jSeparator1);

        jMenuItem_FecharArquivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_FecharArquivo.setText("Fechar arquivo");
        jMenuItem_FecharArquivo.setEnabled(false);
        jMenuItem_FecharArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_FecharArquivoActionPerformed(evt);
            }
        });
        jMenu_Arquivo.add(jMenuItem_FecharArquivo);

        jMenuItem_Sair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem_Sair.setText("Sair");
        jMenuItem_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SairActionPerformed(evt);
            }
        });
        jMenu_Arquivo.add(jMenuItem_Sair);

        jMenuBar1.add(jMenu_Arquivo);

        jMenu_Ferramentas.setText("Comandos");

        jMenuItem_InfoCSV.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_InfoCSV.setText("Informações do CSV");
        jMenu_Ferramentas.add(jMenuItem_InfoCSV);
        jMenu_Ferramentas.add(jSeparator2);

        jMenuItem_FiltrarDados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_FiltrarDados.setText("Filtrar dados");
        jMenu_Ferramentas.add(jMenuItem_FiltrarDados);
        jMenu_Ferramentas.add(jSeparator3);

        jMenuItem_ConectarBD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_ConectarBD.setText("Conectar Banco de Dados...");
        jMenuItem_ConectarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ConectarBDActionPerformed(evt);
            }
        });
        jMenu_Ferramentas.add(jMenuItem_ConectarBD);

        jMenuItem_EnviarParaBD.setText("Enviar arquivo para Banco de Dados");
        jMenuItem_EnviarParaBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_EnviarParaBDActionPerformed(evt);
            }
        });
        jMenu_Ferramentas.add(jMenuItem_EnviarParaBD);

        jMenuBar1.add(jMenu_Ferramentas);

        jMenu_Sobre.setText("Ajuda");

        jMenuItem_AtalhosTeclado.setText("Atalhos de teclado");
        jMenu_Sobre.add(jMenuItem_AtalhosTeclado);

        jMenuItem_Sobre.setText("Sobre");
        jMenuItem_Sobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SobreActionPerformed(evt);
            }
        });
        jMenu_Sobre.add(jMenuItem_Sobre);

        jMenuBar1.add(jMenu_Sobre);

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
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void jMenuItem_AbrirLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_AbrirLocalActionPerformed

	System.out.println("Item de menu clicado: Abrir arquivo local.");

	int returnValue = fileChooser.showOpenDialog(null);

	if (returnValue == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = fileChooser.getSelectedFile();
	    originalFileName = selectedFile.getName(); // Armazene o nome original do arquivo

	    File copyFile = new File("csvImportado.csv"); // Nome do arquivo a ser copiado para o diretório raiz

	    try {
		// Copia o arquivo selecionado para o diretório raiz
		Files.copy(selectedFile.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		FileInputStream fileInputStream = new FileInputStream(copyFile);
		String fileName = copyFile.getName();
		StringBuilder content = new StringBuilder();

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
		    String line;
		    while ((line = bufferedReader.readLine()) != null) {
			content.append(line).append("\n");
		    }
		}

		// Fecha os fluxos de entrada
		fileInputStream.close();

		arquivoAberto = true; // Arquivo foi aberto
		jMenuItem_FecharArquivo.setEnabled(true); // Habilita o item de menu

		// Atualize o nome do arquivo aberto com o nome original
		nomeArquivoAberto = originalFileName;
		jTextField_NomeArquivoAberto.setText(nomeArquivoAberto);

		// Exibe o conteúdo do arquivo copiado na textArea
		Console.setText(content.toString());
	    } catch (IOException e) {
		arquivoAberto = false; // Arquivo não pôde ser aberto
	    }
	}
    }//GEN-LAST:event_jMenuItem_AbrirLocalActionPerformed

    private void jMenuItem_AbrirWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_AbrirWebActionPerformed
        System.out.println("Item de menu clicado: Abrir arquivo da Web.");
        AbrirArquivoWeb dialogAbrirWeb = new AbrirArquivoWeb(this, true);
        dialogAbrirWeb.setVisible(true);
    }//GEN-LAST:event_jMenuItem_AbrirWebActionPerformed

    private void jButton_VisualizarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VisualizarCSVActionPerformed
	System.out.println("Botão clicado: Visualizar CSV.");
	String[] allowedFileNames = {"csvBaixado.csv", "csvImportado.csv"};
	String selectedFileName = null;

	// Verifique se um dos arquivos permitidos está presente
	for (String fileName : allowedFileNames) {
	    File file = new File(fileName);
	    if (file.exists()) {
		selectedFileName = fileName;
		break;
	    }
	}

	if (selectedFileName != null) {
	    try {
		// Ler o conteúdo do arquivo CSV e exibi-lo no Console
		BufferedReader reader = new BufferedReader(new FileReader(selectedFileName));
		String linha;
		StringBuilder conteudo = new StringBuilder();

		while ((linha = reader.readLine()) != null) {
		    conteudo.append(linha).append("\n");
		}

		Console.setText(conteudo.toString());

		reader.close();
		jMenuItem_FecharArquivo.setEnabled(true); // Desabilita o item de menu

		// Atualize o nome do arquivo aberto
		nomeArquivoAberto = originalFileName;
		jTextField_NomeArquivoAberto.setText(nomeArquivoAberto);

		jMenuItem_EnviarParaBD.setEnabled(conexaoAtiva);

	    } catch (FileNotFoundException e) {
		Console.setText("Erro: Arquivo não encontrado. \nAbra um arquivo CSV do seu computador ou faça o download usando o menu acima.");
		nomeArquivoAberto = "Nenhum arquivo aberto";
		jTextField_NomeArquivoAberto.setText(nomeArquivoAberto);
		jMenuItem_EnviarParaBD.setEnabled(false);

	    } catch (IOException e) {
		System.err.println("Falha ao visualizar o CSV (VisualizarCSVActionPerformed()).");
		jMenuItem_EnviarParaBD.setEnabled(false);
	    }
	} else {
	    Console.setText("Nenhum arquivo aberto. Abra um arquivo CSV usando o menu acima.");
	}
    }//GEN-LAST:event_jButton_VisualizarCSVActionPerformed

    private void jButton_LimparConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LimparConsoleActionPerformed
        System.out.println("Botão clicado: Limpar Console.");
	Console.setText("Aguardando entrada do usuário...");
    }//GEN-LAST:event_jButton_LimparConsoleActionPerformed

    private void jMenuItem_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SairActionPerformed
        System.out.println("Item de menu clicado: Sair do aplicativo.");
	dispose();
    }//GEN-LAST:event_jMenuItem_SairActionPerformed

    private void jMenuItem_FecharArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_FecharArquivoActionPerformed
	System.out.println("Item de menu clicado: Fechar arquivo aberto.");
	
	jDialog_FecharArquivo.setVisible(true);
	
    }//GEN-LAST:event_jMenuItem_FecharArquivoActionPerformed

    private void jButton_ConfirmarFechamentoArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConfirmarFechamentoArquivoActionPerformed
	if (jMenuItem_FecharArquivo.isEnabled()) {
	    // Feche o arquivo
	    jMenuItem_FecharArquivo.setEnabled(false); // Desabilita o item de menu

	    // Apague o arquivo "csvBaixado.csv" se existir
	    File csvBaixadoFile = new File("csvBaixado.csv");
	    if (csvBaixadoFile.exists()) {
		System.out.println("Ação automática realizada: Arquivo 'csvBaixado.csv' apagado da raiz do aplicativo.");
		csvBaixadoFile.delete();
	    }

	    // Apague o arquivo "csvImportado.csv" se existir
	    File csvImportadoFile = new File("csvImportado.csv");
	    if (csvImportadoFile.exists()) {
		System.out.println("Ação automática realizada: Arquivo 'csvImportado.csv' apagado da raiz do aplicativo.");
		csvImportadoFile.delete();
	    }

	    jDialog_FecharArquivo.dispose();

	    nomeArquivoAberto = "Nenhum arquivo aberto";
	    jTextField_NomeArquivoAberto.setText(nomeArquivoAberto);
	    jMenuItem_EnviarParaBD.setEnabled(false);

	    Console.setText("Aguardando entrada do usuário..."); // Limpa o Console
	}
    }//GEN-LAST:event_jButton_ConfirmarFechamentoArquivoActionPerformed

    private void jButton_CancelarFechamentoArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarFechamentoArquivoActionPerformed
        System.out.println("Botão clicado: Cancelar fechamento de arquivo.");
	System.out.println("Operação encerrada: Fechamento de arquivo.");
	jDialog_FecharArquivo.dispose();
    }//GEN-LAST:event_jButton_CancelarFechamentoArquivoActionPerformed

    private void jMenuItem_ConectarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ConectarBDActionPerformed
        System.out.println("Item de menu clicado: Conectar Banco de Dados.");
       
	if (conexaoAtiva) {
	    int option = JOptionPane.showConfirmDialog(this, "Há uma conexão ativa com o banco de dados no host: " + hostConexaoAtiva + ". Deseja encerrar a conexão?", "Conexão Ativa", JOptionPane.YES_NO_OPTION);
	    System.out.println("Erro: Há uma conexão com Banco de Dados ativa: " + hostConexaoAtiva);
	    if (option == JOptionPane.YES_OPTION) {
		encerrarConexao(); // Chame o método para encerrar a conexão
		System.out.println("Ação: Conexão com Banco de Dados " + hostConexaoAtiva + " encerrada.");
		jMenuItem_EnviarParaBD.setEnabled(false); // Desabilita o item de menu
		jDialog_LoginBD.setVisible(true);
	    }
	} else {
	    jDialog_LoginBD.setVisible(true);
	    if (conexaoAtiva) {
		jMenuItem_EnviarParaBD.setEnabled(true); // Habilita o item de menu
	    } else {
		jMenuItem_EnviarParaBD.setEnabled(false); // Desabilita o item de menu
	    }
	}
    }//GEN-LAST:event_jMenuItem_ConectarBDActionPerformed

    private void jButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarActionPerformed

        // Obtenha os valores dos campos de texto
        String host = jTextFieldHost.getText();
        String porta = jTextFieldPorta.getText();
        String nomeBanco = jTextFieldBanco.getText();
        String usuario = jTextFieldUser.getText();
        String senha = jTextFieldPass.getText();

	if (host.isEmpty() || porta.isEmpty() || nomeBanco.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
	    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
	} else {
	    // Configuração da conexão
	    String url = "jdbc:postgresql://" + host + ":" + porta + "/" + nomeBanco;
	    String driver = "org.postgresql.Driver";

	    try {
		Class.forName(driver);
		conexao = DriverManager.getConnection(url, usuario, senha);
		JOptionPane.showMessageDialog(this, "Conexão bem-sucedida!");
		System.out.println("Ação: Conexão com Banco de Dados " + hostConexaoAtiva + " bem-sucedida!");
		conexaoAtiva = true; // Marque a conexão como ativa
		hostConexaoAtiva = host; // Armazene o host da conexão ativa
		jDialog_LoginBD.dispose();
	    } catch (ClassNotFoundException | SQLException e) {
		JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.");
		System.out.println("Erro: Falha ao conectar com o banco de dados em " + hostConexaoAtiva);
	    }
	}
    }//GEN-LAST:event_jButtonConectarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jDialog_LoginBD.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonTestarConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTestarConexaoActionPerformed

        // Obtenha os valores dos campos de texto
        String host = jTextFieldHost.getText();
        String porta = jTextFieldPorta.getText();
        String nomeBanco = jTextFieldBanco.getText();
        String usuario = jTextFieldUser.getText();
        String senha = jTextFieldPass.getText();

        // Verifique se todos os campos estão preenchidos
        if (host.isEmpty() || porta.isEmpty() || nomeBanco.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        // Tente estabelecer uma conexão de teste com o banco de dados
        if (testarConexaoBanco(host, porta, nomeBanco, usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Conexão bem-sucedida. Agora você pode conectar ao banco de dados.");
        } else {
            JOptionPane.showMessageDialog(this, "Falha na conexão. Verifique as credenciais e tente novamente.");
        }
    }//GEN-LAST:event_jButtonTestarConexaoActionPerformed

    private void jMenuItem_EnviarParaBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_EnviarParaBDActionPerformed
	enviarArquivoCSVParaBancoDeDados();
    }//GEN-LAST:event_jMenuItem_EnviarParaBDActionPerformed

    private void jMenuItem_SobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SobreActionPerformed
        System.out.println("Item de menu clicado: Sobre.");
	
	jDialog_Sobre.setVisible(true);
    }//GEN-LAST:event_jMenuItem_SobreActionPerformed

    
    
    
    /* MÉTODO PARA BAIXAR O ARQUIVO CSV DA WEB - USADO NO DIALOG AbrirArquivoWeb.java */
    public class DownloadCSV {
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

    static void downloadCSV(String csvURL, ByteArrayOutputStream downloadedContentStream) {
	    throw new UnsupportedOperationException("Not supported yet.");
	}
    }
    
    private boolean testarConexaoBanco(String host, String porta, String nomeBanco, String usuario, String senha) {
	boolean conexaoBemSucedida = false;

	// Tente estabelecer uma conexão de teste com as credenciais fornecidas.
	try {
	    String url = "jdbc:postgresql://" + host + ":" + porta + "/" + nomeBanco;
	    Connection connection = DriverManager.getConnection(url, usuario, senha);
	    // Se a conexão for bem-sucedida, feche-a.
	    connection.close();
	    conexaoBemSucedida = true;
	} catch (SQLException e) {
	    System.err.println("Erro na conexão: " + e.getMessage());
	    conexaoBemSucedida = false;
	}

	return conexaoBemSucedida;
    }

    
    private void encerrarConexao() {
	try {
	    if (conexao != null) {
		conexao.close();
		JOptionPane.showMessageDialog(this, "Conexão com o banco de dados encerrada.", "Conexão Encerrada", JOptionPane.INFORMATION_MESSAGE);
	    }
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(this, "Erro ao encerrar a conexão com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	}
	conexaoAtiva = false; // Marque a conexão como encerrada
	hostConexaoAtiva = ""; // Limpe o host da conexão ativa
}
    
    
    
private void enviarArquivoCSVParaBancoDeDados() {
    if (conexaoAtiva) {
        // Crie um diálogo de progresso com label personalizada
        JDialog progressDialog = new JDialog(this, "Enviando dados para o banco de dados", true);
        progressDialog.setResizable(false); // Impede o redimensionamento do diálogo

        JLabel label = new JLabel("Aguarde. O envio dos dados pode demorar vários minutos. Mantenha o aplicativo aberto.");
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona margens internas à label
        progressDialog.add(label, BorderLayout.NORTH);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Use uma barra de progresso indeterminada
        progressDialog.add(progressBar, BorderLayout.CENTER);

        // Adicione um botão "CANCELAR" com ação para fechar o diálogo
        JButton cancelButton = new JButton("CANCELAR ENVIO");
        cancelButton.addActionListener(e -> {
            if (conexao != null) {
                try {
                    conexao.close(); // Fecha a conexão com o banco de dados
                } catch (SQLException ex) {
                }
            }
            worker.cancel(true); // Cancela a tarefa SwingWorker
            progressDialog.dispose(); // Fecha o diálogo
        });
        progressDialog.add(cancelButton, BorderLayout.SOUTH);

        progressDialog.pack();
        progressDialog.setLocationRelativeTo(this);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    String caminhoAbsoluto = nomeArquivoAberto;
                    if (!new File(nomeArquivoAberto).isAbsolute()) {
                        String diretorioProjeto = System.getProperty("user.dir");
                        caminhoAbsoluto = diretorioProjeto + File.separator + nomeArquivoAberto;
                    }

                    File arquivoCSV = new File(caminhoAbsoluto);
                    String separador = determinarSeparador(arquivoCSV);
                    CSVReader csvReader = new CSVReaderBuilder(new FileReader(arquivoCSV))
                            .withCSVParser(new CSVParserBuilder().withSeparator(separador.charAt(0)).build())
                            .build();
                    String[] colunas = csvReader.readNext();

                    if (colunas != null) {
                        criarTabelaNoBancoDeDados(colunas);

                        // Agora você pode carregar os dados do CSV para a tabela criada
                        carregarDadosDoCSVParaBancoDeDados(colunas, csvReader);

                        // Feche o diálogo de progresso
                        SwingUtilities.invokeLater(progressDialog::dispose);

                        JOptionPane.showMessageDialog(PaginaInicial.this, "Tabela criada e dados do arquivo CSV carregados com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(PaginaInicial.this, "O arquivo CSV não contém colunas.", "Erro", JOptionPane.ERROR_MESSAGE));
                    }
                } catch (IOException | SQLException e) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(PaginaInicial.this, "Erro ao enviar dados para o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE));
                } catch (CsvValidationException ex) {
                    Logger.getLogger(PaginaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }

                return null;
            }
        };
	
        this.worker = worker;
        worker.execute(); // Inicie o SwingWorker para realizar o trabalho em segundo plano
        progressDialog.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Não há uma conexão ativa com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

private void criarTabelaNoBancoDeDados(String[] colunas) throws SQLException {
    // Execute a instrução SQL para criar a tabela
    StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS nome_da_tabela (");

    for (int i = 0; i < colunas.length; i++) {
        String columnName = colunas[i].trim(); // Nome da coluna
        String dataType = "VARCHAR"; // Defina todos os tipos como VARCHAR

        createTableSQL.append("\"").append(columnName).append("\"").append(" ").append(dataType);

        if (i < colunas.length - 1) {
            createTableSQL.append(", ");
        }
    }

    createTableSQL.append(")");

    Statement statement = conexao.createStatement();
    statement.executeUpdate(createTableSQL.toString());

    // Feche a declaração
    statement.close();
}

private void carregarDadosDoCSVParaBancoDeDados(String[] colunas, CSVReader csvReader) throws SQLException, IOException {
    // Prepare a instrução SQL de inserção de dados
    String insertSQL = "INSERT INTO nome_da_tabela (" + String.join(", ", colunas) + ") VALUES ("
            + String.join(", ", Collections.nCopies(colunas.length, "?")) + ")";

    // Crie um objeto PreparedStatement para executar a inserção
    PreparedStatement preparedStatement = conexao.prepareStatement(insertSQL);

    String[] linha;
	try {
	    while ((linha = csvReader.readNext()) != null) {
		for (int i = 0; i < linha.length; i++) {
		    preparedStatement.setString(i + 1, linha[i]);
		}
		preparedStatement.executeUpdate();
	    }	} catch (CsvValidationException ex) {
	    Logger.getLogger(PaginaInicial.class.getName()).log(Level.SEVERE, null, ex);
	}

    // Feche o PreparedStatement
    preparedStatement.close();
}

private String determinarSeparador(File arquivoCSV) throws IOException {
    // Leitura das primeiras linhas para determinar o separador
    try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
        String primeiraLinha = reader.readLine();
        if (primeiraLinha != null) {
            if (primeiraLinha.contains(",")) {
                return ",";
            } else if (primeiraLinha.contains(";")) {
                return ";";
            }
        }
    }
    // Caso não seja possível determinar, use o separador padrão (',')
    return ",";
}
  
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Console;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConectar;
    private javax.swing.JButton jButtonTestarConexao;
    private javax.swing.JButton jButton_CancelarFechamentoArquivo;
    private javax.swing.JButton jButton_ConfirmarFechamentoArquivo;
    private javax.swing.JButton jButton_LimparConsole;
    private javax.swing.JButton jButton_VisualizarCSV;
    private final javax.swing.JDialog jDialog_FecharArquivo = new javax.swing.JDialog();
    private javax.swing.JDialog jDialog_LoginBD;
    private javax.swing.JDialog jDialog_Sobre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBanco;
    private javax.swing.JLabel jLabelHost;
    private javax.swing.JLabel jLabelHost1;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelPorta;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_AbrirLocal;
    private javax.swing.JMenuItem jMenuItem_AbrirWeb;
    private javax.swing.JMenuItem jMenuItem_AtalhosTeclado;
    private javax.swing.JMenuItem jMenuItem_ConectarBD;
    private javax.swing.JMenuItem jMenuItem_EnviarParaBD;
    private javax.swing.JMenuItem jMenuItem_FecharArquivo;
    private javax.swing.JMenuItem jMenuItem_FiltrarDados;
    private javax.swing.JMenuItem jMenuItem_InfoCSV;
    private javax.swing.JMenuItem jMenuItem_Sair;
    private javax.swing.JMenuItem jMenuItem_Sobre;
    private javax.swing.JMenu jMenu_Arquivo;
    private javax.swing.JMenu jMenu_Ferramentas;
    private javax.swing.JMenu jMenu_Sobre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldBanco;
    private javax.swing.JTextField jTextFieldHost;
    private javax.swing.JPasswordField jTextFieldPass;
    private javax.swing.JTextField jTextFieldPorta;
    private javax.swing.JTextField jTextFieldUser;
    private javax.swing.JTextField jTextField_NomeArquivoAberto;
    // End of variables declaration//GEN-END:variables
}
