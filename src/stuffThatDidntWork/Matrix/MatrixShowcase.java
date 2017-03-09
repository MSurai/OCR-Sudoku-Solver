public class MatrixShowcase {
    public static void main(String[] args) {
        double[][] values = {{1, 2, 3}, {4, 5, 6}};

        Matrix A = new Matrix(values);
        Matrix B = A.deepCopy();


        System.out.println("\nMatrix A:");
        A.print();

        System.out.println("\nMatrix B:");
        B.print();

        System.out.println("\nA * B':");
        A.multiplyNew(B.transposeNew()).print();

        System.out.println("\nA .* B:");
        A.multiplyEachNew(B).print();

        System.out.println("\nA * 2 + B");
        A.multiplyNew(2).addNew(B).print();

        System.out.println("\nln(A)");
        A.logNNew().print();

        System.out.println("\nA unrolled:");
        A.unrollNew().print();

        System.out.println("\nsum(A)");
        A.sumNew().print();
    }
}
