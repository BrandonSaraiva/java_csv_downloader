package br.iesb.csvtoolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {
    
    /* Instâncias */
    ConnectionManager connectionManager;
    public DatabaseUploader databaseUploader;
    public DatabaseConfig databaseConfig;
    public UploadUserSettings userSettings;
    public InformacoesCSV infoCSV;
    public static Main instance;
    DefaultTableModel tableModel;
    
    /* Atributos - Gerenciamento do Arquivo */    
    String nomeArquivoOriginal = null;
    String nomeArquivo = "csvImportado.csv";
    String diretorio = System.getProperty("user.dir");
    String caminhoArquivo = diretorio + File.separator + nomeArquivo;
    public String tamanhoCSV = getTamanhoArquivoCSV();
    private int quantColunas;
    public File arquivo = new File(caminhoArquivo);
    public File arquivoCSV = new File("csvImportado.csv");
    boolean arquivoEstaAberto = false;
    
    long startTime;
    long endTime;
    
  
    public Main() {
	instance = this;
	initComponents();
	
	/* Criação de Instâncias de Classes Auxiliares */
	connectionManager = new ConnectionManager();
	userSettings = new UploadUserSettings();
	
	tableModel = new DefaultTableModel();
	jTable1.setModel(tableModel);
	
	startTime = 0;
	endTime = 0;
	
	/* Visibilidade de Itens ao abrir a janela */
	encerrarConexaoBotao.setVisible(false);
	label_BDConnectionStatus.setText("Banco desconectado");
	
	verificaExistenciaArquivo();

    }

    public static void main(String args[]) {
	    /* Definição do tema da UI */
	    try {
		UIManager.setLookAndFeel( new FlatMacDarkLaf() );
	    } catch( UnsupportedLookAndFeelException ex ) {
		System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	    }

	    java.awt.EventQueue.invokeLater(() -> {
		new Main().setVisible(true);
	    });
	}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        VisualizarArquivoConsole = new javax.swing.JButton();
        newConsole = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        label_BDConnectionStatus = new javax.swing.JLabel();
        encerrarConexaoBotao = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        labelProcessingTime = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_Arquivo = new javax.swing.JMenu();
        AbrirArquivoLocal = new javax.swing.JMenuItem();
        AbrirArquivoWeb = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuFecharArquivoAberto = new javax.swing.JMenuItem();
        SairDoAplicativo = new javax.swing.JMenuItem();
        jMenu_Ferramentas = new javax.swing.JMenu();
        InformacoesDoCSV = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        ConectarBD = new javax.swing.JMenuItem();
        EnviarArquivoParaBD = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        FiltrarDados = new javax.swing.JMenuItem();
        jMenu_Sobre = new javax.swing.JMenu();
        AtalhosDeTeclado = new javax.swing.JMenuItem();
        SobreOAplicativo = new javax.swing.JMenuItem();

        FileChooser.setApproveButtonText("Abrir");
        FileChooser.setDialogTitle("Abrir arquivo local");
        FileChooser.setFileFilter(new br.iesb.csvtoolkit.FiltroTipoArquivo());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CSV Toolkit");

        VisualizarArquivoConsole.setText("Visualizar CSV");
        VisualizarArquivoConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizarArquivoConsole(evt);
            }
        });

        newConsole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newConsole.setText("Console");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(newConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(VisualizarArquivoConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VisualizarArquivoConsole)
                    .addComponent(newConsole))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.setRollover(true);

        label_BDConnectionStatus.setForeground(new java.awt.Color(102, 102, 102));
        label_BDConnectionStatus.setLabelFor(label_BDConnectionStatus);
        label_BDConnectionStatus.setToolTipText("");
        jToolBar1.add(label_BDConnectionStatus);

        encerrarConexaoBotao.setText("Encerrar conexão");
        encerrarConexaoBotao.setFocusable(false);
        encerrarConexaoBotao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        encerrarConexaoBotao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        encerrarConexaoBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encerrarConexaoBotaoActionPerformed(evt);
            }
        });
        jToolBar1.add(encerrarConexaoBotao);
        jToolBar1.add(filler1);
        jToolBar1.add(labelProcessingTime);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setBackground(new java.awt.Color(30, 30, 30));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setAlignmentX(0.0F);
        jTable1.setAlignmentY(0.0F);
        jTable1.setEnabled(false);
        jTable1.setFocusable(false);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jMenu_Arquivo.setText("Arquivo");

        AbrirArquivoLocal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        AbrirArquivoLocal.setText("Abrir arquivo local...");
        AbrirArquivoLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirArquivoLocal(evt);
            }
        });
        jMenu_Arquivo.add(AbrirArquivoLocal);

        AbrirArquivoWeb.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        AbrirArquivoWeb.setText("Abrir arquivo da Web...");
        AbrirArquivoWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirArquivoWeb(evt);
            }
        });
        jMenu_Arquivo.add(AbrirArquivoWeb);
        jMenu_Arquivo.add(jSeparator1);

        MenuFecharArquivoAberto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MenuFecharArquivoAberto.setText("Fechar arquivo");
        MenuFecharArquivoAberto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuFecharArquivoAbertoActionPerformed(evt);
            }
        });
        jMenu_Arquivo.add(MenuFecharArquivoAberto);

        SairDoAplicativo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        SairDoAplicativo.setText("Sair");
        SairDoAplicativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairDoAplicativoActionPerformed(evt);
            }
        });
        jMenu_Arquivo.add(SairDoAplicativo);

        jMenuBar1.add(jMenu_Arquivo);

        jMenu_Ferramentas.setText("Comandos");

        InformacoesDoCSV.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        InformacoesDoCSV.setText("Informações do CSV");
        InformacoesDoCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InformacoesDoCSVActionPerformed(evt);
            }
        });
        jMenu_Ferramentas.add(InformacoesDoCSV);
        jMenu_Ferramentas.add(jSeparator2);

        ConectarBD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ConectarBD.setText("Conectar Banco de Dados...");
        ConectarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConectarBD(evt);
            }
        });
        jMenu_Ferramentas.add(ConectarBD);

        EnviarArquivoParaBD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        EnviarArquivoParaBD.setText("Enviar arquivo para Banco de Dados");
        EnviarArquivoParaBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarArquivoParaBDActionPerformed(evt);
            }
        });
        jMenu_Ferramentas.add(EnviarArquivoParaBD);
        jMenu_Ferramentas.add(jSeparator3);

        FiltrarDados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        FiltrarDados.setText("Filtrar dados");
        FiltrarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarDadosActionPerformed(evt);
            }
        });
        jMenu_Ferramentas.add(FiltrarDados);

        jMenuBar1.add(jMenu_Ferramentas);

        jMenu_Sobre.setText("Ajuda");

        AtalhosDeTeclado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        AtalhosDeTeclado.setText("Atalhos de teclado");
        AtalhosDeTeclado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtalhosDeTecladoActionPerformed(evt);
            }
        });
        jMenu_Sobre.add(AtalhosDeTeclado);

        SobreOAplicativo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SobreOAplicativo.setText("Sobre");
        SobreOAplicativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SobreOAplicativoActionPerformed(evt);
            }
        });
        jMenu_Sobre.add(SobreOAplicativo);

        jMenuBar1.add(jMenu_Sobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    
    
/* MÉTODOS DE EXIBIÇÃO */
    
    /* Método para exibir o CSV local na Tabela */
    private void preencherTabelaComCSV(String fileName) {
	tableModel.setRowCount(0);
	String detectedSeparator = detectarSeparadorDados();
	
	try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	    String linha;
	    boolean primeiraLinha = true;
	    
	    while ((linha = reader.readLine()) != null) {
		if (primeiraLinha) {
		    linha = linha.toLowerCase();
		    primeiraLinha = false;
		    String[] colunas = linha.split(Pattern.quote(detectedSeparator));
		    tableModel.setColumnIdentifiers(colunas);
		    
		} else {
		    String[] dados = linha.split(Pattern.quote(detectedSeparator));
		    tableModel.addRow(dados);
		    
		}
	    }
	} catch (IOException e) {
	    System.out.println("ERRO: Falha ao preencher a tabela com CSV. " + e.getMessage());
	}
    }
    
    
    /* Método para exibir o conteúdo do arquivo baixado da Web na Tabela */
    private void exibirConteudoNaTabela(JTable table) {
	DefaultTableModel tableModel = new DefaultTableModel();

	try (BufferedReader reader = new BufferedReader(new FileReader("csvImportado.csv"))) {
	    String linha;
	    boolean primeiraLinha = true;

	    while ((linha = reader.readLine()) != null) {
		if (primeiraLinha) {
		    String[] colunas = linha.toLowerCase().split(","); // Assumindo que o separador é uma vírgula
		    for (String coluna : colunas) {
			tableModel.addColumn(coluna);
		    }
		    primeiraLinha = false;
		} else {
		    String[] dados = linha.split(",");
		    tableModel.addRow(dados);
		}
	    }
	} catch (IOException e) {
	    
	    return;
	}

	table.setModel(tableModel);
	
    }
    
    
    /* Método para verificar a existência do arquivo na pasta e ativar/desativar menus da interface */
    public final Boolean verificaExistenciaArquivo() {
	File csvFile = new File("csvImportado.csv");
	if (csvFile.exists()) {
	    MenuFecharArquivoAberto.setEnabled(true);
	    InformacoesDoCSV.setEnabled(true);
	    InformacoesDoCSV.setEnabled(true);
	    
	    if (connectionManager.conexaoAtiva == true) {
		EnviarArquivoParaBD.setEnabled(true); 
	    }
	    
	    preencherTabelaComCSV(nomeArquivo);
	    return true;
	} else {
	    MenuFecharArquivoAberto.setEnabled(false);
	    InformacoesDoCSV.setEnabled(false); 
	    EnviarArquivoParaBD.setEnabled(false); 
	    InformacoesDoCSV.setEnabled(false);
	    return false;
	}
    }
    

    
/* MÉTODOS COMPARTILHÁVEIS */

    /* Método compartilhável para permitir alteração da mensagem no Console */
    public void atualizarMensagemNoConsole(String mensagem) {
	newConsole.setText(mensagem);
    }
    
    /* Método para atualizar a caixa de status do Banco */
    public void atualizarStatusDoBanco(boolean status, String host) {
	boolean conexaoAtiva = status;
	label_BDConnectionStatus.setText("Conectado a: " + host);
	encerrarConexaoBotao.setVisible(true);
	System.out.println("conexaoAtiva: " + connectionManager.isConexaoAtiva());
    }
    
    /* Método para exibir o status de conexão com o Banco */
    private void encerrarConexao() {
	if (connectionManager.isConexaoAtiva()) {
	    connectionManager.fecharConexao();
	    label_BDConnectionStatus.setText("Banco desconectado");
	    encerrarConexaoBotao.setVisible(false);
	} else {
	    System.out.println("Erro: não foi possível encerrar a conexão com o banco de dados (ConexaoAtiva = false).");
	}
    }
    
    /* Método compartilhável para obter o separador do arquivo em formato String */
    public String getSeparadorArquivo() {
	return detectarSeparadorDados();
    }
    
    /* Método para obter o nome do arquivo */
    public String getNomeArquivo() {
	if (arquivoEstaAberto) {
	    return nomeArquivo;
	} else {
	    return nomeArquivoOriginal;
	}
    }
    
    /* Método compartilhável para retornar o tamanho do arquivo */
    public String getTamanhoArquivoMB() {
	String tamanhoArquivo = getTamanhoArquivoCSV();
	return tamanhoArquivo;
    }
    
    /* Método compartilhável para retornar o local do arquivo */
    public String getLocalArquivo() {
	return caminhoArquivo;
    }
    
    
    
    
/* MÉTODOS DE ARQUIVO */
    
    /* Método para fazer Download do Arquivo da Web */
    public static void downloadArquivo(String url, javax.swing.JTextArea ConsoleWeb) throws IOException {
	
	URLConnection urlconnection = new URL(url).openConnection();
	FileOutputStream outputStream;
	
	try (InputStream inputStream = urlconnection.getInputStream()) {
	    File arquivoExistente = new File("csvImportado.csv");
	    if (arquivoExistente.exists()) {
		arquivoExistente.delete();
	    }   File arquivo = new File("csvImportado.csv");
	    
	    outputStream = new FileOutputStream(arquivo);
	    byte[] buffer = new byte[1024];
	    int bytesRead;
	    long totalBytesRead = 0;
	    long totalFileSize = urlconnection.getContentLengthLong();
	    boolean primeiraLinha = true;
	    
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
		if (primeiraLinha) {
		    String linha = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
		    linha = linha.toLowerCase();
		    buffer = linha.getBytes(StandardCharsets.UTF_8);
		    primeiraLinha = false;
		}
		
		outputStream.write(buffer, 0, bytesRead);
		totalBytesRead += bytesRead;
		
		if (totalFileSize < 0) {
		    double downloadedSizeMB = (double) totalBytesRead / (1024 * 1024); // Tamanho já baixado em MB
		    SwingUtilities.invokeLater(() -> {
			ConsoleWeb.setText("Fazendo download: " + String.format("%.2f", downloadedSizeMB) + " MB");
		    });
		} else {
		    int percent = (int) ((totalBytesRead * 100) / totalFileSize);
		    double totalSizeMB = (double) totalFileSize / (1024 * 1024); // Tamanho total em MB
		    SwingUtilities.invokeLater(() -> {
			ConsoleWeb.setText("Fazendo download: " + percent + "% \nTamanho: " + String.format("%.2f", totalSizeMB) + " MB");
		    });
		}
	    }
	}
	
	outputStream.close();

	SwingUtilities.invokeLater(() -> {
	    ConsoleWeb.setText("Download realizado com sucesso. Local: " + System.getProperty("user.dir") + "/csvImportado.csv");
	});
    }
    
    
    /* Método para determinar o tipo de separador do arquivo CSV */
    public String detectarSeparadorDados() {
	try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
	    String firstLine = reader.readLine();
	    if (firstLine != null) {
		int[] separatorCounts = new int[3];
		separatorCounts[0] = countOccurrences(firstLine, ',');
		separatorCounts[1] = countOccurrences(firstLine, ';');
		separatorCounts[2] = countOccurrences(firstLine, '\t');

		// Encontre o separador com a contagem máxima
		int maxCount = separatorCounts[0];
		char detectedSeparator = ',';
		for (int i = 1; i < separatorCounts.length; i++) {
		    if (separatorCounts[i] > maxCount) {
			maxCount = separatorCounts[i];
			if (i == 1) {
			    detectedSeparator = ';';
			} else if (i == 2) {
			    detectedSeparator = '\t';
			}
		    }
		}
		return String.valueOf(detectedSeparator);
	    }
	} catch (IOException e) {
	    System.out.println("ERRO: Não foi possível determinar o separador do arquivo. " + e);
	}
	return ",";
    }
    private int countOccurrences(String text, char target) {
	int count = 0;
	for (char c : text.toCharArray()) {
	    if (c == target) {
		count++;
	    }
	}
	return count;
    }
    
    
    /* Métodos para obter o nome das colunas do arquivo */
    public List<String> getCSVColumnNames() {
	List<String> columnNames = new ArrayList();
	String detectedSeparator = detectarSeparadorDados();


	try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
	    String headerLine = reader.readLine();
	    if (headerLine != null) {
		String[] columns = headerLine.split(Pattern.quote(detectedSeparator));
		for (String column : columns) {
		    columnNames.add(column);
		}
		quantColunas = columns.length;
	    }
	} catch (IOException e) {
	    System.err.println("ERRO: Não foi possível obter o nome das colunas do arquivo. " + e);
	}
	return columnNames;
    }
    public List<String> getCSVColumnNames(String selectedSeparator) {
	List<String> columnNames = new ArrayList();

	try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
	    String headerLine = reader.readLine();
	    if (headerLine != null) {
		String[] columns = headerLine.split(Pattern.quote(selectedSeparator));
		for (String column : columns) {
		    columnNames.add(column);
		}
	    }
	} catch (IOException e){}
	return columnNames;
    }
    
    
    /* Método para obter a quantidade de linhas do arquivo */
    public int getQuantLinhasCSV() {
	int quantLinhas = 0;

	try {
	    try (BufferedReader reader = new BufferedReader(new FileReader("csvImportado.csv"))) {
		while (reader.readLine() != null) {
		    quantLinhas++;
		}
	    }
	} catch (FileNotFoundException e) {
	     System.err.println("ERRO: Arquivo não encontrado. " + e);
	} catch (IOException e) {
	     System.err.println("ERRO: Erro na leitura do arquivo. " + e);
	}

	return quantLinhas;
    }
    
    
    /* Método para obter a quantidade de colunas do arquivo CSV */
    public int getQuantColunasCSV() {
	int quantidadeColunas = 0;
	String detectedSeparator = detectarSeparadorDados();

	try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
	    String headerLine = reader.readLine();
	    if (headerLine != null) {
		// Divida a linha de cabeçalho usando o separador detectado
		String[] columns = headerLine.split(Pattern.quote(detectedSeparator));
		quantidadeColunas = columns.length;
	    }
	} catch (IOException e) {
	    System.err.println("ERRO: Não foi possível obter a quantidade de colunas do arquivo CSV. " + e);
	}

	return quantidadeColunas;
    }

    
    /* Método para obter o tamanho arquivo CSV */
    public String getTamanhoArquivoCSV() {
        long fileSizeBytes = 0;
	double fileSizeMB = 0;

        try {
            File csvFile = new File("csvImportado.csv");
            if (csvFile.exists()) {
                fileSizeBytes = csvFile.length();
		fileSizeMB = (double) fileSizeBytes / (1024 * 1024);
            }
        } catch (Exception e) {
	    System.err.println("ERRO: Impossível determinar o tamanho do arquivo CSV. " + e);
        }

        return String.format("%.2f MB", fileSizeMB);
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
    private void AbrirArquivoLocal(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirArquivoLocal
 	int returnValue = FileChooser.showOpenDialog(null);
	
	if (returnValue == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = FileChooser.getSelectedFile();
	    nomeArquivoOriginal = selectedFile.getName();
	    
	    File copyFile = new File("csvImportado.csv");
	    
	    try {
		File arquivoExistente = new File("csvImportado.csv");
		if (arquivoExistente.exists()) {
		    arquivoExistente.delete();
		}
		
		// Realiza uma cópia do arquivo local no diretório raiz
		Files.copy(selectedFile.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		FileInputStream fileInputStream = new FileInputStream(copyFile);
		String fileName = copyFile.getName();
		StringBuilder content = new StringBuilder();
		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
		    String linha;
		    boolean primeiraLinha = true;
		    
		    while ((linha = bufferedReader.readLine()) != null) {
			if (primeiraLinha) {
			    // Converter a primeira linha em minúsculas
			    linha = linha.toLowerCase();
			    primeiraLinha = false;
			}
			content.append(linha).append("\n");
		    }
		}
		
		fileInputStream.close();
		
		preencherTabelaComCSV(selectedFile.getAbsolutePath());
		verificaExistenciaArquivo();
				
	    } catch (IOException e) {
		System.out.println("ERRO: Impossível abrir arquivo local. " + e);
	    }
	}
    }//GEN-LAST:event_AbrirArquivoLocal

    
    /* Visualizar Arquivo no Console - Botão */
    private void VisualizarArquivoConsole(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizarArquivoConsole
        String allowedFileName = "csvImportado.csv";
	String selectedFileName = null;
	
	File file = new File(allowedFileName);
	if (file.exists()) {
	    selectedFileName = allowedFileName;
	}
	
	if (selectedFileName != null) {
	    preencherTabelaComCSV(selectedFileName);
	    
	} else {
	    newConsole.setText("Não há nenhum arquivo aberto");
	}
    }//GEN-LAST:event_VisualizarArquivoConsole
  
    
    /* Abrir arquivo da Web - Menu */
    private void AbrirArquivoWeb(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirArquivoWeb
        AbrirArquivoWeb dialogoAbrirWeb = new AbrirArquivoWeb(this, true);
	dialogoAbrirWeb.setVisible(true);
    }//GEN-LAST:event_AbrirArquivoWeb

    
    /* Sair do Aplicativo - Menu */
    private void SairDoAplicativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairDoAplicativoActionPerformed
        dispose();
    }//GEN-LAST:event_SairDoAplicativoActionPerformed

    
    /* Fechar Arquivo Aberto no momento - Menu */
    private void MenuFecharArquivoAbertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuFecharArquivoAbertoActionPerformed
	FechamentoArquivo dialogFecharArquivo = new FechamentoArquivo(this, true);
	dialogFecharArquivo.setVisible(true);
    }//GEN-LAST:event_MenuFecharArquivoAbertoActionPerformed

    
    /* Conectar ao Banco de Dados - Menu */
    private void ConectarBD(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConectarBD
        databaseConfig = new DatabaseConfig(this, true);
	databaseConfig.setVisible(true);
    }//GEN-LAST:event_ConectarBD

    
    /* Encerrar Conexão com o Banco - Botão Especial */
    private void encerrarConexaoBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encerrarConexaoBotaoActionPerformed
	encerrarConexao();
    }//GEN-LAST:event_encerrarConexaoBotaoActionPerformed

    
    /* Enviar Arquivo para o Banco de Dados - Menu */
    private void EnviarArquivoParaBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarArquivoParaBDActionPerformed
        DatabaseUploader dialogoUpload = new DatabaseUploader(this, true);
	dialogoUpload.setVisible(true);
    }//GEN-LAST:event_EnviarArquivoParaBDActionPerformed

    
    /* Informações do CSV - Menu */
    private void InformacoesDoCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InformacoesDoCSVActionPerformed
        InformacoesCSV dialogoInfoCSV = new InformacoesCSV(this, true);
	dialogoInfoCSV.setVisible(true);
    }//GEN-LAST:event_InformacoesDoCSVActionPerformed

    
    /* Filtrar dados do Arquivo - Menu */
    private void FiltrarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarDadosActionPerformed
	if (connectionManager.conexaoAtiva == true) {
	FiltragemGeralDados dialogFiltragemDados = new FiltragemGeralDados(this, true);
	dialogFiltragemDados.setVisible(true);
	} else {
	    JOptionPane.showMessageDialog(this, "A filtragem de dados requer conexão com um banco de dados e não há uma conexão no momento. Conecte-se a um banco de dados e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
	}
    }//GEN-LAST:event_FiltrarDadosActionPerformed

    
    /* Sobre - Menu */
    private void SobreOAplicativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SobreOAplicativoActionPerformed
        Sobre dialogSobre = new Sobre(this, true);
	dialogSobre.setVisible(true);
    }//GEN-LAST:event_SobreOAplicativoActionPerformed

    private void AtalhosDeTecladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtalhosDeTecladoActionPerformed
        AtalhosDeTeclado dialogAtalhos = new AtalhosDeTeclado(this, true);
	dialogAtalhos.setVisible(true);
    }//GEN-LAST:event_AtalhosDeTecladoActionPerformed

    
   
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AbrirArquivoLocal;
    private javax.swing.JMenuItem AbrirArquivoWeb;
    private javax.swing.JMenuItem AtalhosDeTeclado;
    private javax.swing.JMenuItem ConectarBD;
    private javax.swing.JMenuItem EnviarArquivoParaBD;
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JMenuItem FiltrarDados;
    private javax.swing.JMenuItem InformacoesDoCSV;
    private javax.swing.JMenuItem MenuFecharArquivoAberto;
    private javax.swing.JMenuItem SairDoAplicativo;
    private javax.swing.JMenuItem SobreOAplicativo;
    private javax.swing.JButton VisualizarArquivoConsole;
    private javax.swing.JButton encerrarConexaoBotao;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenu_Arquivo;
    private javax.swing.JMenu jMenu_Ferramentas;
    private javax.swing.JMenu jMenu_Sobre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelProcessingTime;
    public javax.swing.JLabel label_BDConnectionStatus;
    private javax.swing.JLabel newConsole;
    // End of variables declaration//GEN-END:variables
}
