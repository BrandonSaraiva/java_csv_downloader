package br.iesb.csvtoolkit;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DatabaseUploader extends javax.swing.JDialog {
    
    private final Main main;
    private final ConnectionManager connectionManager;
    private final DatabaseConfig databaseConfig;
   	
    UploadUserSettings userSettings = new UploadUserSettings();
    
    private final CardLayout cardLayout;
    private final List<String> cardNames;
    private int currentIndex;
    
    boolean dadosEnviados = false;
    
    public DatabaseUploader(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	
	/* Instâncias */
	main = (Main) parent;
        connectionManager = main.connectionManager;
	databaseConfig = main.databaseConfig;
	
	/* Definições gerais */
	cardLayout = (CardLayout) mainPanel.getLayout();
        cardNames = new ArrayList<>();
        cardNames.add("panelOne");
        cardNames.add("panelTwo");
        cardNames.add("panelThree");
        currentIndex = 0;
	
	int quantLinhas = main.getQuantLinhasCSV();
	labelRowCount.setText(Integer.toString(quantLinhas));
	var tamanhoArquivo = main.getTamanhoArquivoCSV();
	labelFileSize.setText(tamanhoArquivo);
	
	visualizarArquivoNoRevisaoConsole("csvImportado.csv", 10);
	
	ProximoButton.setEnabled(true);
	AnteriorButton.setEnabled(false);
	
	updateTextFieldFromDatabaseConfig();
	
        populateSchemasComboBox();
	
	
	/* Obtém os nomes das colunas do arquivo CSV e exibe na TextArea */
	List<String> nomesColunas = main.getCSVColumnNames();
	StringBuilder textoNomeColuna = new StringBuilder();
	for (String nomeColuna : nomesColunas) {
	    textoNomeColuna.append(nomeColuna).append("\n");
	}
	
	textarea_VisualizarColunas.setText(textoNomeColuna.toString());
	
	// Preenche o combo_PrimaryKeyColumn com os nomes das colunas
	List<String> columnNames = main.getCSVColumnNames();
	combo_PrimaryKeyColumn.addItem("Não definir uma Chave Primária");
        for (String columnName : columnNames) {
            combo_PrimaryKeyColumn.addItem(columnName);
	    
        }
	
	
	
	
/* OUVINTES DE EVENTOS - Registram em UploadUserSettings os valores escolhidos pelo usuário */
	
	/* Card 1: Ouvinte de Eventos do ComboBox 'separadorDados' */
	separadorDados.addActionListener((var e) -> {
	    String selectedOption = separadorDados.getSelectedItem().toString();
	    userSettings.setSelectedSeparator(selectedOption);

	    // Verifique se a opção selecionada é "Definir automaticamente"
	    if (selectedOption.equals("Definir automaticamente")) {
		// Chame a função detectarSeparadorDados() para obter o separador
		String separadorSelecionado = main.detectarSeparadorDados();
		// Atribua o separador detectado à variável selectedSeparator
		userSettings.setSelectedSeparator(separadorSelecionado);
		atualiza_textarea_VisualizarColunas(separadorSelecionado);
	    } else {
		atualiza_textarea_VisualizarColunas(selectedOption);
	    }
	});
	
	
	/* Card 2: Ouvinte de Eventos do Combobox 'comboBoxSchemas' */
        comboBoxSchemas.addActionListener((ActionEvent e) -> {
	    String selectedSchema = (String) comboBoxSchemas.getSelectedItem(); /* Obtém a seleção do usuário na ComboBox */
	    userSettings.setSelectedSchema(selectedSchema); /* Atualiza a escolha do usuário na classe UploadUserSettings */
	    updateSchemaLabel();
	});
	
	/* Card 2: Ouvinte de Eventos do Combobox 'combo_PrimaryKeyColumn' */
	combo_PrimaryKeyColumn.addActionListener((ActionEvent e) -> {
	   String chavePrimaria = combo_PrimaryKeyColumn.getSelectedItem().toString();
	   userSettings.setChavePrimaria(chavePrimaria);
	   updateChavePrimariaLabel();
	   
	});
	
	/* Card 2: Ouvinte de Eventos do TextField 'campo_NomeTabela' */
	validarNomeDaTabela.addActionListener((java.awt.event.ActionEvent evt) -> {

	    String tableName = campo_NomeTabela.getText();
	    
	    // Verifica se o nome da tabela atende às restrições (apenas letras minúsculas, números e underscores)
	    if (validateTableName(tableName)) {
		userSettings.setNomeDaTabela(tableName);
		updateTableNameLabel();
		labelError.setText("");
		labelValid.setText("O nome da tabela é válido.");
	    } else {
		labelValid.setText("");
		labelError.setText("Nome inválido! Use apenas letras minúsculas, números e underscores.");
	    }
	});

    }
    

    
/* MÉTODOS GERAIS */
    
    /* Card 1: Função para atualizar o conteúdo da TextArea com base na seleção do ComboBox */
    private void atualiza_textarea_VisualizarColunas(String separadorSelecionado) {
	
	String selectedOption = separadorDados.getSelectedItem().toString();

	switch (selectedOption) {
	    case "Definir automaticamente" -> /* Usa a função detectarSeparadorDados */
		separadorSelecionado = main.detectarSeparadorDados();
	    case "Vírgula" -> separadorSelecionado = ",";
	    case "Ponto-e-Vírgula ( ; )" -> separadorSelecionado = ";";
	    case "Tabulação (Tab)" -> separadorSelecionado = "\t";
	    default -> {
	    }
	}

	/* Obtém os nomes das colunas com base no separador selecionado */
	List<String> columnNames = main.getCSVColumnNames(separadorSelecionado);

	/* Atualiza a textArea com os nomes das colunas */
	StringBuilder textoNomesColunas = new StringBuilder();
	for (String columnName : columnNames) {
	    textoNomesColunas.append(columnName).append("\n");
	}
	
	textarea_VisualizarColunas.setText(textoNomesColunas.toString());
	// Conta as linhas no textarea_VisualizarColunas
	int numberOfLines = countLinesInTextArea(textarea_VisualizarColunas);
	labelColumnCount.setText(Integer.toString(numberOfLines));
    }
    
    public final int countLinesInTextArea(JTextArea textarea_VisualizarColunas) {
	String text = textarea_VisualizarColunas.getText();
	String[] lines = text.split("\n");
	return lines.length;
    }
    
    
    // Card 2: Método para preencher o TextField com o valor de "databaseAtivo"
    private void updateTextFieldFromDatabaseConfig() {
        if (databaseConfig != null) {
            campo_NomeDatabase.setText(databaseConfig.databaseAtivo);
        }
    }
    
    /* Card 2: Função para popular o combobox 'comboBoxSchemas' com os schemas existentes no banco de dados */
    private void populateSchemasComboBox() {
	// Obtém a lista de schemas do ConnectionManager
	List<String> schemas = connectionManager.getSchemas();

	// Adicione a opção "Selecione uma opção..." como a primeira entrada
	schemas.add(0, "Selecione uma opção...");

	// Preenche o ComboBox com os schemas
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	for (String schema : schemas) {
	    model.addElement(schema);
	}
	comboBoxSchemas.setModel(model);

	// Selecione a opção "Selecione uma opção..." por padrão
	comboBoxSchemas.setSelectedIndex(0);
    }
    
    /* Card 2: Função para validar o nome da tabela digitado com uma expressão regular */
    private boolean validateTableName(String tableName) {
        return tableName.matches("^[a-z0-9_]+$"); // A expressão regular permite apenas letras minúsculas, números e underscores
    }

    
    /* Card 3: Função para atualizar o label com o nome da tabela */
    public void updateTableNameLabel() {
        label_nomeTabela.setText(userSettings.getNomeDaTabela());
    }
    
    /* Card 3: Função para atualizar o label com o Schema */
    public void updateSchemaLabel() {
	label_nomeSchema.setText(userSettings.getSelectedSchema());
    }
    
    /* Card 3: Função para atualizar o label com o Separador */
    public void updateChavePrimariaLabel() {
	label_primaryKey.setText(userSettings.getChavePrimaria());
    }
    
    /* Card 3: Enviar os dados para o banco de dados */
    public final void visualizarArquivoNoRevisaoConsole(String fileName, int numLinhas) {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    String linha;
	    StringBuilder conteudoCSV = new StringBuilder();

	    int linhasLidas = 0;
	    boolean primeiraLinha = true;
	    while ((linha = reader.readLine()) != null && linhasLidas < numLinhas) {
		if (primeiraLinha) {
		    // Converter a primeira linha em minúsculas
		    linha = linha.toLowerCase();
		    primeiraLinha = false;
		}
		conteudoCSV.append(linha).append("\n");
		linhasLidas++;
	    }

	    RevisaoConsole.setText(conteudoCSV.toString());

	    reader.close();

	} catch (FileNotFoundException e) {
	    RevisaoConsole.setText("Erro: arquivo não encontrado.");
	} catch (IOException e) {
	    System.err.println("Falha ao visualizar o CSV no Console.");
	}
    }
    
    /* Compartilhável: Define se os dados foram enviados com sucesso para o banco de dados */
    public boolean isDadosEnviados() {
	return dadosEnviados;
    }
 
    
    public static void main(String args[]) {
	try {
	    UIManager.setLookAndFeel( new FlatMacDarkLaf() );
	} catch( UnsupportedLookAndFeelException ex ) {
	    System.err.println( "Falha ao carregar o tema do aplicativo. Usando o fallback." );
	}

	java.awt.EventQueue.invokeLater(() -> {
	    DatabaseUploader dialog = new DatabaseUploader(new javax.swing.JFrame(), true);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        onePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        nomesColunasDoArquivo = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        separadorDados = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea_VisualizarColunas = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        twoPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        campo_NomeDatabase = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboBoxSchemas = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        combo_PrimaryKeyColumn = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        labelError = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        validarNomeDaTabela = new javax.swing.JButton();
        campo_NomeTabela = new javax.swing.JTextField();
        labelValid = new javax.swing.JLabel();
        threePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        labelRowCount = new javax.swing.JLabel();
        labelColumnCount = new javax.swing.JLabel();
        labelFileSize = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        label_nomeTabela = new javax.swing.JLabel();
        label_nomeSchema = new javax.swing.JLabel();
        label_primaryKey = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        RevisaoConsole = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        enviarDados_Final = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        CancelarButton = new javax.swing.JButton();
        ProximoButton = new javax.swing.JButton();
        AnteriorButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Enviar para Banco de Dados");
        setResizable(false);

        mainPanel.setLayout(new java.awt.CardLayout());

        onePanel.setPreferredSize(new java.awt.Dimension(677, 496));

        jLabel4.setText("Nomes das Colunas:");

        nomesColunasDoArquivo.setSelected(true);
        nomesColunasDoArquivo.setText("Usar os nomes definidos pelo arquivo");
        nomesColunasDoArquivo.setEnabled(false);

        jRadioButton2.setText("Definir manualmente");
        jRadioButton2.setEnabled(false);

        jLabel5.setText("Separador dos Dados:");

        separadorDados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção...", "Vírgula", "Ponto-e-Vírgula ( ; )", "Tabulação (Tab)", "Definir automaticamente" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("1. Configuração dos Dados");

        textarea_VisualizarColunas.setColumns(20);
        textarea_VisualizarColunas.setForeground(new java.awt.Color(0, 204, 0));
        textarea_VisualizarColunas.setLineWrap(true);
        textarea_VisualizarColunas.setRows(5);
        textarea_VisualizarColunas.setWrapStyleWord(true);
        textarea_VisualizarColunas.setBorder(null);
        textarea_VisualizarColunas.setEnabled(false);
        jScrollPane1.setViewportView(textarea_VisualizarColunas);

        jLabel12.setText("Visualizar Colunas:");

        jLabel17.setText("<html>Defina o separador dos dados contidos no arquivo.");
        jLabel17.setEnabled(false);
        jLabel17.setFocusable(false);
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout onePanelLayout = new javax.swing.GroupLayout(onePanel);
        onePanel.setLayout(onePanelLayout);
        onePanelLayout.setHorizontalGroup(
            onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(onePanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomesColunasDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, onePanelLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(separadorDados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(onePanelLayout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        onePanelLayout.setVerticalGroup(
            onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onePanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addGroup(onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nomesColunasDoArquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addGroup(onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(separadorDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(onePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        mainPanel.add(onePanel, "panel0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("2. Configuração do Banco de Dados");

        campo_NomeDatabase.setEnabled(false);

        jLabel8.setText("Database:");

        jLabel10.setText("Schema:");

        comboBoxSchemas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção..." }));

        jLabel11.setText("Chave Primária:");

        combo_PrimaryKeyColumn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção..." }));

        jLabel9.setText("Nome da Tabela:");

        labelError.setForeground(new java.awt.Color(255, 255, 0));
        labelError.setToolTipText("");

        jLabel14.setText("<html>Selecione a coluna que será usada como Chave Primária (PK) na tabela. <b>A coluna selecionada como PK não pode conter valores nulos e também não pode possuir valores duplicados.</b>");
        jLabel14.setEnabled(false);
        jLabel14.setFocusable(false);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel15.setText("Selecione o Schema que será usado para criar a nova tabela.");
        jLabel15.setEnabled(false);
        jLabel15.setFocusable(false);
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel16.setText("<html>Digite o nome da tabela a ser criada. Valide o nome para confirmá-lo.");
        jLabel16.setEnabled(false);
        jLabel16.setFocusable(false);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        validarNomeDaTabela.setText("Validar");

        labelValid.setForeground(new java.awt.Color(102, 204, 0));
        labelValid.setToolTipText("");

        javax.swing.GroupLayout twoPanelLayout = new javax.swing.GroupLayout(twoPanel);
        twoPanel.setLayout(twoPanelLayout);
        twoPanelLayout.setHorizontalGroup(
            twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(twoPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(twoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(twoPanelLayout.createSequentialGroup()
                                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                    .addComponent(campo_NomeTabela, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(validarNomeDaTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelValid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(twoPanelLayout.createSequentialGroup()
                        .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(twoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combo_PrimaryKeyColumn, 0, 386, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(jLabel6)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(twoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxSchemas, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(twoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(campo_NomeDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        twoPanelLayout.setVerticalGroup(
            twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(twoPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addGap(30, 30, 30)
                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campo_NomeDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(comboBoxSchemas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_PrimaryKeyColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(twoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campo_NomeTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(validarNomeDaTabela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(labelValid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelError)
                .addContainerGap())
        );

        mainPanel.add(twoPanel, "panel1");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("3. Revisão");

        jLabel18.setText("Quantidade de linhas:");

        jLabel19.setText("Quantidade de colunas:");

        jLabel20.setText("Tamanho do upload:");

        labelRowCount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelRowCount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelRowCount.setText("NaN");

        labelColumnCount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelColumnCount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelColumnCount.setText("NaN");

        labelFileSize.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelFileSize.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelFileSize.setText("NaN");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel21.setText("PK:");

        jLabel22.setText("Schema:");

        jLabel23.setText("Tabela:");

        label_nomeTabela.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label_nomeTabela.setForeground(new java.awt.Color(102, 153, 255));
        label_nomeTabela.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_nomeTabela.setText("Preencha Nome da Tabela");

        label_nomeSchema.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label_nomeSchema.setForeground(new java.awt.Color(102, 153, 255));
        label_nomeSchema.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_nomeSchema.setText("Selecione Schema");

        label_primaryKey.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label_primaryKey.setForeground(new java.awt.Color(102, 153, 255));
        label_primaryKey.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_primaryKey.setText("Selecione Chave Primária");

        RevisaoConsole.setEditable(false);
        RevisaoConsole.setColumns(20);
        RevisaoConsole.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        RevisaoConsole.setForeground(new java.awt.Color(51, 204, 0));
        RevisaoConsole.setRows(5);
        RevisaoConsole.setFocusable(false);
        jScrollPane2.setViewportView(RevisaoConsole);

        jLabel27.setText("Visualização do conteúdo da tabela:");

        enviarDados_Final.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        enviarDados_Final.setText("Enviar Dados");
        enviarDados_Final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarDados_FinalActionPerformed(evt);
            }
        });

        jLabel24.setText("<html>Antes de enviar os dados para o banco de dados, certifique-se de que as todas as configurações foram realizadas. <b> O processo de upload poderá durar vários minutos e não pode ser interrompido. </b>");
        jLabel24.setEnabled(false);
        jLabel24.setFocusable(false);
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout threePanelLayout = new javax.swing.GroupLayout(threePanel);
        threePanel.setLayout(threePanelLayout);
        threePanelLayout.setHorizontalGroup(
            threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(threePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(threePanelLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(enviarDados_Final, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(threePanelLayout.createSequentialGroup()
                            .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addGroup(threePanelLayout.createSequentialGroup()
                                    .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(threePanelLayout.createSequentialGroup()
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(labelFileSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(threePanelLayout.createSequentialGroup()
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(labelColumnCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(threePanelLayout.createSequentialGroup()
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(labelRowCount, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(28, 28, 28)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(threePanelLayout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label_primaryKey, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, threePanelLayout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label_nomeSchema, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, threePanelLayout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(label_nomeTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4)
                        .addComponent(jScrollPane2)))
                .addGap(14, 14, 14))
        );
        threePanelLayout.setVerticalGroup(
            threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(threePanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(threePanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(threePanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(threePanelLayout.createSequentialGroup()
                                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(label_nomeSchema))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(label_primaryKey))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(label_nomeTabela)))
                            .addGroup(threePanelLayout.createSequentialGroup()
                                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(labelRowCount))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(labelColumnCount))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(labelFileSize))))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(threePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(threePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(threePanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(enviarDados_Final, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        mainPanel.add(threePanel, "panel2");

        CancelarButton.setText("Cancelar");
        CancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarButtonActionPerformed(evt);
            }
        });

        ProximoButton.setText("Próximo");
        ProximoButton.setFocusCycleRoot(true);
        ProximoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProximoButtonActionPerformed(evt);
            }
        });

        AnteriorButton.setText("Anterior");
        AnteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnteriorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(CancelarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(AnteriorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProximoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelarButton)
                    .addComponent(ProximoButton)
                    .addComponent(AnteriorButton))
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void ProximoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProximoButtonActionPerformed
        cardLayout.next(mainPanel);
	if (currentIndex < cardNames.size() - 1) {
            currentIndex++;
            cardLayout.show(mainPanel, cardNames.get(currentIndex));
            AnteriorButton.setEnabled(true);
        }
        if (currentIndex == cardNames.size() - 1) {
            ProximoButton.setEnabled(false);
        }
    }//GEN-LAST:event_ProximoButtonActionPerformed

    private void AnteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnteriorButtonActionPerformed
        cardLayout.previous(mainPanel);
	if (currentIndex > 0) {
            currentIndex--;
            cardLayout.show(mainPanel, cardNames.get(currentIndex));
            ProximoButton.setEnabled(true);
        }
        if (currentIndex == 0) {
            AnteriorButton.setEnabled(false);
        }
    }//GEN-LAST:event_AnteriorButtonActionPerformed

    private void CancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarButtonActionPerformed
        dispose();
    }//GEN-LAST:event_CancelarButtonActionPerformed

    private void enviarDados_FinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarDados_FinalActionPerformed
	enviarCSVParaBancoDeDados();
    }//GEN-LAST:event_enviarDados_FinalActionPerformed

    
    
    
    
/* MÉTODOS PARA ENVIO DOS DADOS PARA O BANCO DE DADOS */
    
    private void enviarCSVParaBancoDeDados() {
	// Verifique se as configurações do usuário estão definidas
	if (userSettings.getSelectedSeparator() == null || userSettings.getSelectedSchema() == null || userSettings.getNomeDaTabela() == null) {
	    JOptionPane.showMessageDialog(this, "Certifique-se de configurar todas as opções antes de enviar para o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	    return;
	}

	// Crie um JDialog para o diálogo de progresso
	JDialog progressDialog = new JDialog(this, "Enviando Dados para o Banco de Dados", true);
	progressDialog.setResizable(false);

	// Crie e configure uma JProgressBar
	JProgressBar progressBar = new JProgressBar(0, 100);
	progressBar.setStringPainted(true);
	progressDialog.add(progressBar, BorderLayout.CENTER);

	progressDialog.pack();
	progressDialog.setLocationRelativeTo(this);

	// Crie um SwingWorker para realizar o envio de dados em segundo plano
	SwingWorker<Void, Integer> sendDataWorker = new SwingWorker<Void, Integer>() {
	    @Override
	    protected Void doInBackground() {
		int progress = 0; // Defina o progresso inicial
		try {
		    // Obtenha o caminho absoluto para o arquivo CSV
		    String caminhoAbsoluto = "csvImportado.csv"; // Substitua pelo caminho real do arquivo CSV
		    String separador = userSettings.getSelectedSeparator();

		    // Crie um leitor CSV com o separador selecionado
		    CSVReader csvReader = new CSVReaderBuilder(new FileReader(caminhoAbsoluto))
			    .withCSVParser(new CSVParserBuilder().withSeparator(separador.charAt(0)).build())
			    .build();

		    String[] colunas = csvReader.readNext();

		    if (colunas != null) {
			// Converter as colunas para minúscula, para evitar erros
			for (int i = 0; i < colunas.length; i++) {
			    colunas[i] = colunas[i].toLowerCase();
			}
			// Crie a tabela no banco de dados
			criarTabelaNoBancoDeDados(colunas);

			// Obtenha o número total de linhas do CSV para calcular o progresso
			int totalLinhas = calcularTotalLinhasDoCSV(caminhoAbsoluto);

			String insertSQL = gerarSQLInsercao(colunas);

			// Crie um objeto PreparedStatement para executar a inserção
			PreparedStatement preparedStatement = connectionManager.connection.prepareStatement(insertSQL);

			String[] linha;
			int linhaAtual = 0;
			try {
			    while ((linha = csvReader.readNext()) != null) {
				for (int i = 0; i < linha.length; i++) {
				    preparedStatement.setString(i + 1, linha[i]);
				}
				preparedStatement.executeUpdate();

				linhaAtual++;
				// Calcule o progresso com base no número de linhas processadas
				progress = (int) (((double) linhaAtual / totalLinhas) * 100);
				publish(progress); // Publique o progresso para a atualização da barra de progresso
			    }
			} catch (CsvValidationException ex) {
			    System.out.println("ERRO: Falha no carregamento dos dados. 1 " + ex);
			}

			// Feche o PreparedStatement
			preparedStatement.close();
		    } else {
			JOptionPane.showMessageDialog(DatabaseUploader.this, "O arquivo CSV não contém colunas.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		} catch (IOException | SQLException e) {
		    JOptionPane.showMessageDialog(DatabaseUploader.this, "Erro ao enviar dados para o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (CsvValidationException ex) {
		    Logger.getLogger(DatabaseUploader.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	    }

	    @Override
	    protected void done() {
		// Feche o diálogo de progresso quando a tarefa estiver concluída
		progressDialog.dispose();
		JOptionPane.showMessageDialog(DatabaseUploader.this, "Dados enviados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		dadosEnviados = true;
		dispose();
	    }

	    @Override
	    protected void process(List<Integer> chunks) {
		// Atualize a barra de progresso à medida que os chunks são publicados
		for (Integer progress : chunks) {
		     SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
		}
	    }
	};

	// Inicie o SwingWorker para executar o envio de dados em segundo plano
	sendDataWorker.execute();

	progressDialog.setVisible(true); // Mostre o diálogo de progresso
    }

    
    private int calcularTotalLinhasDoCSV(String caminhoArquivo) throws IOException {
	BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
	int totalLinhas = 0;
	while (reader.readLine() != null) {
	    totalLinhas++;
	}
	reader.close();
	 System.out.println("Total de linhas no arquivo CSV: " + totalLinhas);
	return totalLinhas;
    }

    
    private String gerarSQLInsercao(String[] colunas) {
	StringBuilder insertSQL = new StringBuilder("INSERT INTO ");
	insertSQL.append(userSettings.getSelectedSchema());
	insertSQL.append(".");
	insertSQL.append(userSettings.getNomeDaTabela());
	insertSQL.append(" (");
	insertSQL.append(String.join(", ", colunas));
	insertSQL.append(") VALUES (");
	for (int i = 0; i < colunas.length; i++) {
	    insertSQL.append("?");
	    if (i < colunas.length - 1) {
		insertSQL.append(", ");
	    }
	}
	insertSQL.append(")");
	return insertSQL.toString();
    }
    
    
    private void criarTabelaNoBancoDeDados(String[] colunas) throws SQLException {
	try {
	    // Execute a instrução SQL para criar a tabela
	    StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
	    createTableSQL.append(userSettings.getSelectedSchema()); // Use o schema selecionado
	    createTableSQL.append(".");
	    createTableSQL.append(userSettings.getNomeDaTabela()); // Use o nome da tabela definido pelo usuário
	    createTableSQL.append(" (");

	    for (int i = 0; i < colunas.length; i++) {
		String columnName = colunas[i].trim(); // Nome da coluna
		String dataType = "VARCHAR"; // Defina todos os tipos como VARCHAR

		createTableSQL.append("\"").append(columnName).append("\"").append(" ").append(dataType);

		if (i < colunas.length - 1) {
		    createTableSQL.append(", ");
		}
	    }

	    createTableSQL.append(")");

	    Statement statement = connectionManager.connection.createStatement(); // Use a conexão da classe DatabaseManager
	    statement.executeUpdate(createTableSQL.toString());

	    // Feche a declaração
	    statement.close();
	} catch (SQLException e) {
	    System.out.println("ERRO: Falha na criação da tabela. " + e);
	}
    }

    /* **************************************** */
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnteriorButton;
    private javax.swing.JButton CancelarButton;
    private javax.swing.JButton ProximoButton;
    private javax.swing.JTextArea RevisaoConsole;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField campo_NomeDatabase;
    private javax.swing.JTextField campo_NomeTabela;
    private javax.swing.JComboBox<String> comboBoxSchemas;
    private javax.swing.JComboBox<String> combo_PrimaryKeyColumn;
    private javax.swing.JButton enviarDados_Final;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel labelColumnCount;
    private javax.swing.JLabel labelError;
    private javax.swing.JLabel labelFileSize;
    private javax.swing.JLabel labelRowCount;
    private javax.swing.JLabel labelValid;
    private javax.swing.JLabel label_nomeSchema;
    private javax.swing.JLabel label_nomeTabela;
    private javax.swing.JLabel label_primaryKey;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton nomesColunasDoArquivo;
    private javax.swing.JPanel onePanel;
    private javax.swing.JComboBox<String> separadorDados;
    private javax.swing.JTextArea textarea_VisualizarColunas;
    private javax.swing.JPanel threePanel;
    private javax.swing.JPanel twoPanel;
    private javax.swing.JButton validarNomeDaTabela;
    // End of variables declaration//GEN-END:variables
}
