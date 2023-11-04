/* Classe de Serviço de Arquivo */

package br.iesb.csvtoolkit.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class FileUtil {
    
    public long getStartTime() {
	return startTime;
    }

    /* Contadores do Tempo de Processamento do Arquivo */
    public long getEndTime() {
	return endTime;
    }

    public long startTime = 0;
    public long endTime = 0;

    /* Abrir arquivo local */
    public static File openLocalFile(JFileChooser fileChooser) {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        }
        return null;
    }

    /* Copiar arquivo local para a pasta raiz do programa */
    public static void copyFile(File source, File destination) throws IOException {
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    
    /* Método para fazer download de um arquivo da Web */
    public static void downloadFileFromWeb(String url, JTextArea consoleWeb) throws IOException {
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream inputStream = urlConnection.getInputStream()) {
            FileOutputStream outputStream;
            File file = new File("csvImportado.csv");
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0;
            long totalFileSize = urlConnection.getContentLengthLong();
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
                    double downloadedSizeMB = (double) totalBytesRead / (1024 * 1024);
                    SwingUtilities.invokeLater(() -> {
                        consoleWeb.setText("Fazendo download: " + String.format("%.2f", downloadedSizeMB) + " MB. \nNão feche esta janela.");
                    });
                } else {
                    int percent = (int) ((totalBytesRead * 100) / totalFileSize);
                    double totalSizeMB = (double) totalFileSize / (1024 * 1024);
                    SwingUtilities.invokeLater(() -> {
                        consoleWeb.setText("Fazendo download: " + percent + "% \nTamanho: " + String.format("%.2f", totalSizeMB) + " MB \nNão feche esta janela.");
                    });
                }
            }

            outputStream.close();
            SwingUtilities.invokeLater(() -> {
                consoleWeb.setText("Download realizado com sucesso. Local: " + System.getProperty("user.dir") + "/csvImportado.csv");
            });
        }
    }

    /* Determinar o separador do arquivo CSV */
    public static String determineFileSeparator(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();
            if (firstLine != null) {
                int[] separatorCounts = new int[3];
                separatorCounts[0] = countOccurrences(firstLine, ',');
                separatorCounts[1] = countOccurrences(firstLine, ';');
                separatorCounts[2] = countOccurrences(firstLine, '\t');

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

    private static int countOccurrences(String text, char target) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }
    
    /* Determina o Nome das Colunas do Arquivo aberto - Com separador detectado automaticamente */
    public static List<String> getFileColumnNames(File arquivoCSV) {
        List<String> columnNames = new ArrayList<>();
        String detectedSeparator = determineFileSeparator(arquivoCSV);

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                String[] columns = headerLine.split(Pattern.quote(detectedSeparator));
                columnNames.addAll(List.of(columns));
            }
        } catch (IOException e) {
            System.err.println("ERRO: Não foi possível obter o nome das colunas do arquivo. " + e);
        }
        return columnNames;
    }
    
    /* Determina o Nome das Colunas do Arquivo aberto - Com separador selecionado pelo usuário */
    public static List<String> getFileColumnNamesWithSeparator(File arquivoCSV, String selectedSeparator) {
        List<String> columnNames = new ArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                String[] columns = headerLine.split(Pattern.quote(selectedSeparator));
                columnNames.addAll(List.of(columns));
            }
        } catch (IOException e) {
            // Trate o erro apropriadamente
        }
        return columnNames;
    }
	
    
    /* Calcula a quantidade de linhas do arquivo CSV */
    public static int getFileRowCount(File arquivoCSV) {
        int rowCount = 0;

	try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
	    while (reader.readLine() != null) {
		rowCount++;
	    }
	} catch (IOException e) {
	    System.err.println("ERRO: Erro na leitura do arquivo. " + e);
	}

        return rowCount;
    }
	
    /* Calcula a quantidade de colunas do arquivo CSV */
    public static int getFileColumnCount(File arquivoCSV) {
        int columnCount = 0;
        String detectedSeparator = determineFileSeparator(arquivoCSV);

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                // Divida a linha de cabeçalho usando o separador detectado
                String[] columns = headerLine.split(Pattern.quote(detectedSeparator));
                columnCount = columns.length;
            }
        } catch (IOException e) {
            System.err.println("ERRO: Não foi possível obter a quantidade de colunas do arquivo CSV. " + e);
        }

        return columnCount;
    }

    /* Determina o tamanho do Arquivo aberto */
    public static String getFileSize(File arquivoCSV) {
        double fileSize = 0;

        try {
            if (arquivoCSV.exists()) {
                fileSize = (double) arquivoCSV.length() / (1024 * 1024);
            }
        } catch (Exception e) {
            System.err.println("ERRO: Impossível determinar o tamanho do arquivo. " + e);
        }

        return String.format("%.2f MB", fileSize);
    }
	
    
    
	
}