/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sk.umb.fpv.columnarsearch;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas
 */
public class PrintPrep {
    public List<Integer> isSubMatrixPresent(int[][] matrix, int[][] subMatrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int subRows = subMatrix.length;
        int subCols = subMatrix[0].length;

        for (int i = 0; i <= rows - subRows; i++) {
            for (int j = 0; j <= cols - subCols; j++) {
                if (isSubMatrixAt(matrix, subMatrix, i, j)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    list.add(i+ subRows);
                    list.add(subCols);
                    return list;
                }
            }
        }

        return null;
    }

    private static boolean isSubMatrixAt(int[][] matrix, int[][] subMatrix, int row, int col) {
        int subRows = subMatrix.length;
        int subCols = subMatrix[0].length;

        for (int i = 0; i < subRows; i++) {
            for (int j = 0; j < subCols; j++) {
                if (matrix[row + i][col + j] != subMatrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
