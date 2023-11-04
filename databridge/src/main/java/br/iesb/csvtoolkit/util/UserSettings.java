package br.iesb.csvtoolkit.util;


public class UserSettings {
    
    private String selectedSeparator;
    private String selectedSchema;
    String nomeDaTabela;
    private String chavePrimaria;
    

    /* Getters e Setters para as configurações escolhidas pelo usuário no DatabaseUploader */
    public String getSelectedSeparator() {
        return selectedSeparator;
    }

    public void setSelectedSeparator(String selectedSeparator) {
        this.selectedSeparator = selectedSeparator;
	System.out.println(selectedSeparator);
    }
    
    public String getSelectedSchema() {
        return selectedSchema;
    }

    public void setSelectedSchema(String selectedSchema) {
        this.selectedSchema = selectedSchema;
	System.out.println(selectedSchema);
    }
    
    public String getNomeDaTabela() {
	return nomeDaTabela;
    }
    
    public void setNomeDaTabela(String nomeDaTabela) {
	this.nomeDaTabela = nomeDaTabela;
	System.out.println(nomeDaTabela);
    }
    
    public String getChavePrimaria() {
	return chavePrimaria;
    }

    public void setChavePrimaria(String chavePrimaria) {
	this.chavePrimaria = chavePrimaria;
	System.out.println(chavePrimaria);
    }
    
}

