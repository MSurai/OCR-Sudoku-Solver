public class Matrix {
    /* -------------------
    CLASS VARIABLES
     ---------------------*/

    private double[][] vals;    // values of matrix
    private int m, n;           // row and column dimensions


    /* -------------------
    CONSTRUCTORS
     ---------------------*/

    // empty m x n matrix
    public Matrix(int m, int n) {
        this(m, n, 0);
    }

    // m x n matrix filled with scalar s
    public Matrix(int m, int n, double s) {
        this.m = m;
        this.n = n;
        this.vals = new double[m][n];
        for (int  i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.vals[i][j] = s;
            }
        }
    }

    // matrix created with given array
    public Matrix(double[][] vals) {
        this.m = vals.length;
        this.n = vals[0].length;
        this.vals = vals;
    }

    // matrix created from matrix, deep copy
    public Matrix(Matrix matrix) {
        double[][] tmp = new double[matrix.getM()][matrix.getN()];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = matrix.getComponent(i, j);
            }
        }
        this.m = tmp.length;
        this.n = tmp[0].length;
        this.vals = tmp;
    }


    /* -------------------
    METHODS
     ---------------------*/

    // GETTERS
    public int getM () {
        return this.m;
    }

    public int getN () {
        return this.n;
    }

    public double[][] getVals () {
        return this.vals;
    }

    public double[] getColumnPackedArray () {
        double[] tmp = new double[m*n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i + j * m] = this.vals[i][j];
            }
        }
        return tmp;
    }

    public double[] getRowPackedArray () {
        double[] tmp = new double[m*n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i * n + j] = this.vals[i][j];
            }
        }
        return tmp;
    }

    public double getComponent (int i, int j) {
        return this.vals[i][j];
    }

    public Matrix copy () {
        return new Matrix(this);
    }

    public Matrix deepCopy () {
        double[][] tmp = new double[this.m][this.n];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                tmp[i][j] = this.vals[i][j];
            }
        }
        return new Matrix(tmp);
    }

    public Matrix getSubMatrix (int x1, int x2, int y1, int y2) {
        double[][] tmp = new double[y2-y1+1][x2-x1+1];
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                tmp[y - y1][x - x1] = this.vals[y][x];
            }
        }
        return new Matrix(tmp);
    }

    public double[] getRowwiseMax () {
        double[] tmp = new double[this.m];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = this.vals[i][0];
            for (int j = 0; j < this.n; j++) {
                tmp[i] = tmp[i] < this.vals[i][j] ? this.vals[i][j] : tmp[i];
            }
        }
        return tmp;
    }

    public double[] getColwiseMax () {
        double[] tmp = new double[this.n];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = this.vals[0][i];
            for (int j = 0; j < this.m; j++) {
                tmp[i] = tmp[i] < this.vals[j][i] ? this.vals[j][i] : tmp[i];
            }
        }
        return tmp;
    }

    public double getMax () {
        double tmp = this.vals[0][0];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                tmp = tmp < this.vals[i][j] ? this.vals[i][j] : tmp;
            }
        }
        return tmp;
    }


    // SETTERS
    public void setComponent (int i, int j, double s) {
        this.vals[i][j] = s;
    }

    public void setVals (double[][] vals) {
        this.m = vals.length;
        this.n = vals[0].length;
        this.vals = new double[this.m][this.n];
        this.vals = vals;
    }

    public void fillRandomly () {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] = Math.random();
            }
        }
    }


    // OPERATIONS
    public void unroll () {
        double[][] tmp = new double[this.m * this.n][1];

        int counter = 0;
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                tmp[counter][0] = this.vals[i][j];
                counter++;
            }
        }

        setVals(tmp);
    }

    public Matrix unrollNew () {
        Matrix tmp = this.deepCopy();
        tmp.unroll();
        return tmp;
    }

    public void reshape (int m, int n) {
        double[][] tmp = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = this.vals[i + j * m][0];
            }
        }

        setVals(tmp);
    }

    public Matrix reshapeNew (int m, int n) {
        Matrix tmp = this.deepCopy();
        tmp.reshape(m, n);
        return tmp;
    }

    public void transpose () {
        double [][] tmp = new double[this.n][this.m];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                tmp[j][i] = this.vals[i][j];
            }
        }
        setVals(tmp);
    }

    public Matrix transposeNew () {
        Matrix tmp = this.copy();
        tmp.transpose();
        return tmp;
    }

    public void add (double s) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] += s;
            }
        }
    }

    public Matrix addNew (double s) {
        Matrix tmp = this.deepCopy();
        tmp.add(s);
        return tmp;
    }

    public void add (Matrix B) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] += B.getComponent(i, j);
            }
        }
    }

    public Matrix addNew (Matrix B) {
        Matrix tmp = this.deepCopy();
        tmp.add(B);
        return tmp;
    }

    public void subtract (double s) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] -= s;
            }
        }
    }

    public Matrix subtractNew (double s) {
        Matrix tmp = this.deepCopy();
        tmp.subtract(s);
        return tmp;
    }

    public void subtract (Matrix B) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] -= B.getComponent(i, j);
            }
        }
    }

    public Matrix subtractNew (Matrix B) {
        Matrix tmp = this.deepCopy();
        tmp.subtract(B);
        return tmp;
    }

    public void multiply (double s) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] *= s;
            }
        }
    }

    public Matrix multiplyNew (double s) {
        Matrix tmp = this.deepCopy();
        tmp.multiply(s);
        return tmp;
    }

    public void multiply (Matrix B) {
        double[][] tmp = new double[this.m][B.getN()];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                for (int k = 0; k < this.n; k++) {
                    tmp[i][j] += this.vals[i][k] * B.getComponent(k, j);
                }
            }
        }
        setVals(tmp);
    }

    public Matrix multiplyNew (Matrix B) {
        Matrix tmp = this.deepCopy();
        tmp.multiply(B);
        return tmp;
    }

    public void multiplyEach (Matrix B) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] *= B.getComponent(i, j);
            }
        }
    }

    public Matrix multiplyEachNew (Matrix B) {
        Matrix tmp = this.deepCopy();
        tmp.multiplyEach(B);
        return tmp;
    }

    public void divide (double s) {
        for (int i = 0; i < this.vals.length; i++) {
            for (int j = 0; j < this.vals[i].length; j++) {
                this.vals[i][j] /= s;
            }
        }
    }

    public Matrix divideNew (double s) {
        Matrix tmp = this.deepCopy();
        tmp.divide(s);
        return tmp;
    }

    public void divideEach (Matrix B) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] /= B.getComponent(i, j);
            }
        }
    }

    public Matrix divideEachNew (Matrix B) {
        Matrix tmp = this.deepCopy();
        tmp.divideEach(B);
        return tmp;
    }

    public void logN () {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] = Math.log(this.vals[i][j]);
            }
        }
    }

    public Matrix logNNew () {
        Matrix tmp = this.deepCopy();
        tmp.logN();
        return tmp;
    }

    public void sum () {
        double[][] tmp = new double[1][this.vals[0].length];

        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                tmp[0][j] += this.vals[i][j];
            }
        }

        setVals(tmp);
    }

    public Matrix sumNew () {
        Matrix tmp = this.deepCopy();
        tmp.sum();
        return tmp;
    }



    // just testing
    public void print() {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                System.out.print(this.vals[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
