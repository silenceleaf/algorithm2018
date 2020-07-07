package org.zjy.learn.code.google;

import org.zjy.learn.util.Application;

@Application(time = "06/04/2020 13:15")
public class MatrixWiredIterator implements Runnable {
    @Override
    public void run() {
        int[][] matrix = new int[][]{
                {1,2,3,4},
                {5,1,2,3},
                {6,5,1,2},
                {7,6,5,1},
        };
        matrixIterator(matrix);
    }
    // 矩阵斜着遍历
    private void matrixIterator(int[][] matrix) {
        int n = matrix[0].length;
        int m = matrix.length;
        for (int d = 1; d < n; d++) {
            for (int l = 0; l < m - d; l++) {
                int r = l + d;
                System.out.println(matrix[l][r]);
            }
        }
//        for (int d = 1; d < n; d++) {
//            for (int r = m - d; r > 0; r--) {
//                int l = r - d + 1;
//                System.out.println(matrix[l][r]);
//            }
//        }
    }
}
