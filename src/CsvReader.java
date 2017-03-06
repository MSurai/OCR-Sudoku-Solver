import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static int[][] loadCsvFileI (String fileName) {
        return loadCsvFileI(fileName, ',');
    }

    // get csvValues from double method, typecast to int
    public static int[][] loadCsvFileI (String fileName, char splitBy) {
        double[][] csvValues = loadCsvFileD(fileName, splitBy);
        int[][] tmp = new int[csvValues.length][csvValues[0].length];

        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = (int) csvValues[i][j];
            }
        }

        return tmp;
    }

    public static double[][] loadCsvFileD (String fileName) {
        return loadCsvFileD(fileName, ',');
    }

    public static double[][] loadCsvFileD (String fileName, char splitBy) {
        List<String> csvLines = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                csvLines.add(line);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        int nrOfColumns = 1;
        for (char c : csvLines.get(0).toCharArray()) {
            if (c == splitBy) {
                nrOfColumns++;
            }
        }

        double[][] csvValues = new double[csvLines.size()][nrOfColumns];
        String[] tmp;
        for (int i = 0; i < csvLines.size(); i++) {
            tmp = csvLines.get(i).split(String.valueOf(splitBy));
            for (int j = 0; j < tmp.length; j++) {
                csvValues[i][j] = Double.parseDouble(tmp[j]);
            }
        }

        return csvValues;
    }
}
