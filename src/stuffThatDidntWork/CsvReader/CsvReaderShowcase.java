public class CsvReaderShowcase {
    public static void main(String[] args) {
        double[][] data = CsvReader.loadCsvFileD("src/mnist_100.csv");

        for (double[] i : data) {
            for (double j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
