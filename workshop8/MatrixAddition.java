package workshop8;

public class MatrixAddition {
	
    private static double[][] result;	

    public static double[][] parallelAddMatrix(double[][] a, double[][] b) {
        result = new double[a.length][b.length];
        addMatrix(a, b);
        return result;
    }

    public static void addMatrix(double[][] a, double[][] b) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < b.length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
    }

    public static double[][] sequentialAddMatrix(double[][] c, double[][] d) {
        result = new double[c.length][d.length];
        addMatrix(c, d);
        return result;
    }
   
}