package br.iesb.csvtoolkit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    
    private final Main main;
    public Connection connection;
    public boolean conexaoAtiva = false;
    public String hostConexaoAtiva = "";
    public boolean conexaoBemSucedida = false;
    
    public boolean isConexaoAtiva() {
	return conexaoAtiva;
    }
    
    public boolean isConexaoBemSucedida() {
	return conexaoBemSucedida;
    }
    
    public ConnectionManager() {
	this.main = null;
    }
    
    
    public void estabelecerConexao(String host, String port, String database, String user, String password) {
        try {
            // Configure a conexão com o banco de dados PostgreSQL
            String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            connection = DriverManager.getConnection(jdbcUrl, user, password);
	    
	    conexaoAtiva = true; // Marque a conexão como ativa
	    hostConexaoAtiva = host; // Armazene o host da conexão ativa
	    System.out.println("Ação: Conexão com Banco de Dados " + hostConexaoAtiva + " bem-sucedida!");
	    
        } catch (SQLException e) {
	    System.out.println("ERRO: Não foi possível conectar ao banco de dados.");
        }
    }

    public void fecharConexao() {
        try {
	    if (connection != null) {
                connection.close();
		conexaoAtiva = false;
		System.out.println("Conexão com o banco encerrada com sucesso.");
	    }

        } catch (SQLException e) {
            System.out.println("ERRO: Conexão com o banco é NULL e não pode ser encerrada." + e);
        }
    }

    public boolean testarConexao(String host, String port, String database, String user, String password) {
        try {
            // Configure a conexão com o banco de dados PostgreSQL
            String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            connection = DriverManager.getConnection(jdbcUrl, user, password);
	    
	    // Se a conexão for bem-sucedida, feche-a.
	    connection.close();
	    conexaoBemSucedida = true;
	    
        } catch (SQLException e) {
	    conexaoBemSucedida = false;
	    System.err.println("Erro na conexão: " + e.getMessage());
        }
	
	return conexaoBemSucedida;
    }    
    
    
    /* GERENCIAMENTO DE SCHEMAS */
    /* Método que obtém a lista de Schemas do banco de dados */
    public List<String> getSchemas() {
	List<String> schemas = new ArrayList<>();
	try {
	    DatabaseMetaData metaData = connection.getMetaData();
	    ResultSet resultSet = metaData.getSchemas();
	    while (resultSet.next()) {
		String schemaName = resultSet.getString("TABLE_SCHEM");
		schemas.add(schemaName);
	    }
	    resultSet.close();
	} catch (SQLException e) {
	    System.out.println("ERRO: Nào foi possível obter os Schemas do banco de dados. " + e);
	}
	return schemas;
    }
    
    
    /* Método que cria um novo Schema no banco de dados */
    public void criarSchema(String nomeSchema) {
	if (isConexaoAtiva()) {
	    String createSchemaSQL = "CREATE SCHEMA " + nomeSchema;
	    try (PreparedStatement statement = connection.prepareStatement(createSchemaSQL)) {
		statement.executeUpdate();
		System.out.println("Schema criado com sucesso: " + nomeSchema);
	    } catch (SQLException e) {
		System.err.println("Erro ao criar schema: " + e.getMessage());
	    }
	} else {
	    System.err.println("Erro: A conexão com o banco de dados não está ativa.");
	}  
    }
    
    /* Verifica as tabelas disponíveis no banco de dados - FiltragemDados*/
    public List<String> getTabelasDisponiveis() {
        List<String> tabelas = new ArrayList<>();
        if (connection != null) {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
		try (ResultSet resultSet = metaData.getTables(null, null, null, new String[] { "TABLE" })) {
		    while (resultSet.next()) {
			String nomeTabela = resultSet.getString("TABLE_NAME");
			tabelas.add(nomeTabela);
		    }
		}
            } catch (SQLException e) {
            }
        }
        return tabelas;
    }
    
    /* Obtém o nome das colunas da tabela do banco de dados */
    public List<String> getColunasDaTabela(String tabela) {
        List<String> colunas = new ArrayList<>();

        if (connection == null) {
            return colunas;
        }

        try {
            DatabaseMetaData metaData = connection.getMetaData();
	    try (ResultSet resultSet = metaData.getColumns(null, null, tabela, null)) {
		while (resultSet.next()) {
		    String nomeColuna = resultSet.getString("COLUMN_NAME");
		    colunas.add(nomeColuna);
		}
	    }
        } catch (SQLException e) {
            System.out.println("ERRO: Impossível coletar o nome das colunas da tabela selecionada no banco de dados. " + e);
        }

        return colunas;
    }
    
    
    // Método para obter um PreparedStatement com uma consulta SQL
    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("A conexão com o banco de dados não está disponível.");
        }

        // Cria um PreparedStatement com a consulta fornecida
        return connection.prepareStatement(query);
    }
    
}
