package org.zjy.learn.util;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Util {
    private static Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

    public static void printMatrix(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int[] row : matrix) {
            stringBuilder.append("[");
            for (int col : row) {
                stringBuilder.append(col).append(",");
            }
            stringBuilder.append("]\n");
        }
        stringBuilder.append("\n");
        logger.info(stringBuilder.toString());
    }

    public static void printList(List list) {
        list.forEach(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.print(o);
                System.out.print(" ");
            }
        });
        System.out.println();
    }
}
