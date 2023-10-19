package projeto;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CSVDownloader {

    private static String csvFilePath;
    private static int totalRecords;

    public static void downloadAndAnalyzeCSV(JFrame frame, String url, String fileName) {
        try {
            long startTime = System.currentTimeMillis();
            downloadCSV(url, fileName);
            long endTime = System.currentTimeMillis();
            int count = analyzeCSV(fileName);

            long elapsedTime = endTime - startTime;

            String message = "Download concluído com sucesso. O arquivo foi salvo em:\n" + csvFilePath
                    + "\nTempo consumido: " + (elapsedTime / 1000) + " segundos"
                    + "\nQuantidade total de registros: " + totalRecords
                    + "\nQuantidade de registros com year = 2020: " + count;

            JOptionPane.showMessageDialog(frame, message);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao baixar o CSV: " + ex.getMessage());
        }
    }

    private static void downloadCSV(String url, String fileName) throws IOException {
        URL csvUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) csvUrl.openConnection();
        connection.setRequestMethod("GET");

        File file = new File(fileName + ".csv");
        file.createNewFile();

        try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        }

        csvFilePath = file.getAbsolutePath();
    }

    private static int analyzeCSV(String fileName) throws IOException {
        File csvFile = new File(fileName + ".csv");
        totalRecords = 0;
        int count = 0;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(csvFile));
            String line;
            boolean header = true;

            while ((line = reader.readLine()) != null) {
                if (header) {
                    header = false;
                } else {
                    String[] values = line.split(",");
                    totalRecords++;

                    int yearIndex = 0;
                    for (int i = 0; i < values.length; i++) {
                        if ("year".equalsIgnoreCase(values[i].trim())) {
                            yearIndex = i;
                            break;
                        }
                    }

                    if (yearIndex >= 0) {
                        String yearValue = values[yearIndex].trim();
                        if ("2020".equals(yearValue)) {
                            count++;
                        }
                    }
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return count;
    }
}





