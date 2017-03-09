/**
 * basic neural network with one hidden layer
 */
public class NeuralNetwork {
    /* -------------------
    CLASS VARIABLES
     ---------------------*/

    private final int m;      // nr of training examples
    private final int n;      // nr of features

    private final int inputLayerSize;
    private final int hiddenLayerSize;
    private final int outputLayerSize;

    private Matrix y;   // output
    private Matrix X;   // input

    private Matrix theta1;  // weights
    private Matrix theta2;  // weights


    /* -------------------
    CONSTRUCTORS
     ---------------------*/

    public NeuralNetwork (Matrix data, int hiddenLayerSize, int outputLayerSize) {
        this.m = data.getM();
        this.n = data.getN() - 1;

        this.inputLayerSize = this.n;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;

        this.y = data.getSubMatrix(0, 0, 0, this.m-1);
        this.X = data.getSubMatrix(1, this.n, 0, this.m-1);

        this.theta1 = new Matrix(this.hiddenLayerSize, this.inputLayerSize+1);
        this.theta2 = new Matrix(this.outputLayerSize, this.hiddenLayerSize+1);

        randInitializeTheta();
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
    private void randInitializeTheta () {
        double epsilonInit = 0.12;
        theta1.fillRandomly();
        theta1.multiply(2 * epsilonInit);
        theta1.subtract(epsilonInit);
        theta2.fillRandomly();
        theta2.multiply(2 * epsilonInit);
        theta2.subtract(epsilonInit);
    }

    private Matrix sigmoid (Matrix matrix) {
        double tmpVal;
//        Matrix result = matrix.deepCopy();
        Matrix result = new Matrix(matrix.getM(), matrix.getN());

        for (int i = 0; i < matrix.getM(); i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                tmpVal = 1.0 / (1.0 + Math.exp(-1.0 * matrix.getComponent(i, j)));
                result.setComponent(i, j, tmpVal);
            }
        }

        return result;
    }

    public Matrix forwardPropagation () {
        Matrix a1, z2, a2, z3, a3;

        a1 = this.X.deepCopy();
        a1 = new Matrix(addBiasUnits(a1));

        z2 = a1.multiplyNew(this.theta1.transposeNew());
        a2 = sigmoid(z2);
        a2 = new Matrix(addBiasUnits(a2));

        z3 = a2.multiplyNew(this.theta2.transposeNew());
        a3 = sigmoid(z3);

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

        Matrix yd = new Matrix(this.m, this.outputLayerSize);
        for (int i = 0; i < this.y.getN(); i++) {
            yd.setComponent(i, (int) this.y.getComponent(i, 0), 1);
        }

        double[][] t1 = {{7, 8, 9},             // REMOVE
                        {10, 11, 12},           // REMOVE
                        {13, 14, 15}};          // REMOVE
        double[][] t2 = {{16, 17, 18, 19},      // REMOVE
                        {20, 21, 22, 23}};      // REMOVE
        Matrix theta1 = new Matrix(t1);         // REMOVE
        Matrix theta2 = new Matrix(t2);         // REMOVE

        Matrix a3 = forwardPropagation();

//        Matrix leftPart = yd.multiplyEachNew(a3.logNNew());
//        Matrix rightPart = yd.multiplyNew(-1).addNew(1).multiplyEachNew(a3.multiplyNew(-1).addNew(1));
//        Matrix costOfEachOutputNode = leftPart.addNew(rightPart).sumNew().multiplyNew(-1/this.m);
//
//        costOfEachOutputNode.print();
        return J;
    }
}
