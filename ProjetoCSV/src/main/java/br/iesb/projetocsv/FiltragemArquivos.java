package br.iesb.projetocsv;

import java.io.File;

class FiltragemArquivos extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File file) {
	// Allow only directories, or files with ".csv" extension
	return file.isDirectory() || file.getAbsolutePath().endsWith(".csv");
    }
    @Override
    public String getDescription() {
	return "Documentos CSV (*.csv)";
    }
}
