class MathFNC {
    public static double[][] rotateX(double[][] matrix, double angle) {
        double radians = Math.toRadians(angle);
        double[][] rX = {{1,0,0,0},
                         {0,Math.cos(radians),-Math.sin(radians),0},
                         {0,Math.sin(radians), Math.cos(radians),0},
                         {0,0,0,1}};
        return matrixMultiply(rX, matrix);
    }
    public static double[][] rotateY(double[][] matrix, double angle) {
        double radians = Math.toRadians(angle);
        double[][] rY = {{ Math.cos(radians),0,Math.sin(radians),0},
                         { 0,1,0,0},
                         {-Math.sin(radians),0,Math.cos(radians),0},
                         {0,0,0,1}};
        return matrixMultiply(rY, matrix);
    }
    public static double[][] rotateZ(double[][] matrix, double angle) {
        double radians = Math.toRadians(angle);
        double[][] rZ = {{Math.cos(radians),-Math.sin(radians),0,0},
                         {Math.sin(radians),Math.cos(radians),0,0},
                         {0,0,1,0},
                         {0,0,0,1}};
        return matrixMultiply(rZ, matrix);
    }
    public static double[][] matrixTranslate(double[][] matrix, double[] translate) {
        double[][] matrixT = {{1,0,0,translate[0]},
                              {0,1,0,translate[1]},
                              {0,0,1,translate[2]},
                              {0,0,0,1}};
        return matrixMultiply(matrixT, matrix);
    }
    public static double[][] matrixScale(double[][] matrix, double[] scale) {
        double[][] matrixS = {{scale[0],0,0,0},
                              {0,scale[1],0,0},
                              {0,0,scale[2],0},
                              {0,0,0,1}};
        return matrixMultiply(matrixS, matrix);
    }
    public static double[][] matrixMultiply(double[][] matrixA, double[][] matrixB) {
        double[][] matrixAB = new double[matrixA.length][matrixB[0].length];
        double sum = 0;
        for (int row=0; row<matrixA.length; row++) {
            for (int col=0; col<matrixB[0].length; col++) {
                for (int rc=0; rc<matrixB.length; rc++) {
                    sum = sum + matrixA[row][rc]*matrixB[rc][col];
                }                
                matrixAB[row][col] = sum;
                sum = 0;
            }
        }
        return matrixAB;
    }
    public static double[] matrixAverage(double[][] matrix) {
        double[] average = new double[matrix.length];
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                average[i] = average[i] + matrix[i][j];
            }
            average[i] = average[i]/matrix[0].length;
        }
        return average;
    }
    public static double matrixSum(double[][] matrix) {
        double sum = 0;
        for (int row=0; row<matrix.length; row++) {
            for (int col=0; col<matrix[0].length; col++) {
                sum = sum + matrix[row][col];
            }
        }
        return sum;
    }
}