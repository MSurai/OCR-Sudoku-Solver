public class NeuralNetworkShowcase {
    public static void main(String[] args) {

        double[][] tmpData = CsvReader.loadCsvFileD("src/mnist_100.csv");
        Matrix data = new Matrix(tmpData);

        int hiddenSize = 25;
        int outSize = 10;

        NeuralNetwork neuralNetwork = new NeuralNetwork(data, hiddenSize, outSize);
        neuralNetwork.forwardPropagation().print();
    }
}
