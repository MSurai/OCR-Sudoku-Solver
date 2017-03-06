public class NeuralNetwork {
    /* -------------------
    CLASS VARIABLES
     ---------------------*/

    private int m;      // nr of training examples
    private int n;      // nr of features

    private int inputLayerSize;
    private int hiddenLayerSize;
    private int outputLayerSize;

    private Matrix y;   // output
    private Matrix X;   // input


    /* -------------------
    CONSTRUCTORS
     ---------------------*/

    public NeuralNetwork (Matrix data, int hiddenLayerSize, int outputLayerSize) {
        this.m = data.getRowDimension();
        this.n = data.getColumnDimension() - 1;

        this.inputLayerSize = this.n;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;

        this.y = data.getSubMatrix(0, 0, 0, this.m-1);
        this.X = data.getSubMatrix(1, this.n, 0, this.m-1);
    }


    /* -------------------
    METHODS
     ---------------------*/

    // GETTERS
    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public Matrix getX() {
        return X;
    }

    public Matrix getY() {
        return y;
    }


    // SETTERS


    // OPERATIONS
    // TODO: set public and private accordingly
    public Matrix sigmoid (Matrix matrix) {
        double tmpVal;
//        Matrix result = matrix.deepCopy();
        Matrix result = new Matrix(matrix.getRowDimension(), matrix.getColumnDimension());

        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                tmpVal = 1.0 / (1.0 + Math.exp(-1.0 * matrix.getComponent(i, j)));
                result.setComponent(i, j, tmpVal);
            }
        }

        return result;
    }

    public Matrix forwardPropagation (Matrix theta1, Matrix theta2) {
        Matrix a1, z2, a2, z3, a3;
//        Matrix theta1, theta2;


//        System.out.println("\ntheta1");
//        theta1.print();
//        System.out.println("\ntheta2");
//        theta2.print();

        a1 = this.X.deepCopy();
        a1 = new Matrix(addBiasUnits(a1));
//        System.out.println("\na1");
//        a1.print();

        z2 = a1.multiplyNew(theta1.transposeNew());
        a2 = sigmoid(z2);
        a2 = new Matrix(addBiasUnits(a2));
//        System.out.println("\na2");
//        a2.print();

        z3 = a2.multiplyNew(theta2.transposeNew());
        a3 = sigmoid(z3);
//        System.out.println("\na3");
//        a3.print();

        return a3;
    }

    private Matrix addBiasUnits (Matrix matrix) {
        double[][] givenMatrix = matrix.getVals();
        double[][] result = new double[givenMatrix.length][givenMatrix[0].length+1];

        for (int i = 0; i < result.length; i++) {
            result[i][0] = 1;
            for (int j = 0; j < givenMatrix[i].length; j++) {
                result[i][j+1] = givenMatrix[i][j];
            }
        }

        return new Matrix(result);
    }

    public double costFunction () {
        double J = 0;
        return J;
    }
}
