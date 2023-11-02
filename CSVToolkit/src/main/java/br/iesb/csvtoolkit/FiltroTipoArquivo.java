package br.iesb.csvtoolkit;

import java.io.File;

/* Filtro de Tipo de Arquivo Permitido */

class FiltroTipoArquivo extends javax.swing.filechooser.FileFilter {
    
    @Override
    public boolean accept(File file) {
	// Permite apenas diretórios e arquivos com extensão ".csv
	return file.isDirectory() || file.getAbsolutePath().endsWith(".csv");
    }
    @Override
    public String getDescription() {
	return "Documentos CSV (*.csv)";
    }
}

