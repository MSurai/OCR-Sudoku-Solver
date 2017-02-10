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
    public Matrix (int m, int n) {
        this(m, n, 0);
    }

    // m x n matrix filled with scalar s
    public Matrix (int m, int n, double s) {
        this.m = n;
        this.n = n;
        this.vals = new double[m][n];
        for (int  i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vals[i][j] = s;
            }
        }
    }

    // matrix created with given array
    public Matrix (double[][] vals) {
        this.m = vals.length;
        this.n = vals[0].length;
        this.vals = vals;
    }

    // matrix from one-dimensional packed array

    // matrix created from matrix
    public Matrix (Matrix matrix) {
        this.m = matrix.getRowDimension();
        this.n = matrix.getColumnDimension();
        this.vals = matrix.getVals();
    }


    /* -------------------
    METHODS
     ---------------------*/

    // GETTERS
    public int getRowDimension () {
        return this.m;
    }

    public int getColumnDimension () {
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

    public Matrix getSubMatrix (int i0, int i1, int j0, int j1) {
        double[][] tmp = new double[i1-i0+1][j1-j0+1];
        for (int i = i0; i <= i1; i++) {
            for (int j = j0; j <= j1; j++) {
                tmp[i - i0][j - j0] = this.vals[i][j];
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


    // OPERATIONS
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

    public void add (Matrix B) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.vals[i][j] += B.getComponent(i, j);
            }
        }
    }

    public Matrix addNew (Matrix B) {
        Matrix tmp = this.copy();
        tmp.add(B);
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
        Matrix tmp = this.copy();
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

    public void multiply (Matrix B) {
        double[][] tmp = new double[this.m][B.getColumnDimension()];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                for (int k = 0; k < tmp.length; k++) {
                    tmp[i][j] += this.vals[i][k] * B.getComponent(k, j);
                }
            }
        }
        setVals(tmp);
    }

    public Matrix multiplyNew (Matrix B) {
        Matrix tmp = this.copy();
        tmp.multiply(B);
        return tmp;
    }
}
