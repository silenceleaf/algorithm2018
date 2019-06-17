package org.zjy.learn.code.amazon;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Application(time = "06/17/2019 13:00")
public class TwoSumClosest implements Runnable {
    /**
     * two sum closest
     * 给两个int的list，一个capacity，从两个list中各选一个item把num加和，返回所有pair里小于capacity并且最大的id，有多个就返回多个
     * list是这个格式的：[[id1, num1], [id2, num2], [id3, num3]]
     * 返回的格式是：[[id, id], ...]
     */
    @Override
    public void run() {
        List<Integer[]> result1 = twoSumClosest(new int[][]{{1, 3}, {2, 4}, {3, 1}, {4, 2}}, new int[][]{{5, 3}, {6, 4}}, 8);
        for (Integer[] pair : result1) {
            System.out.println(pair[0] + "  " + pair[1]);
        }

    }

    /**
     * 1, put all A items into tree map cost: O(sizeA * log sizeA)
     * 2, query tree map and generate result cost:
     * 3, O(sizeA * log sizeA) + O(sizeB * log sizeA) = (sizeA + sizeB) * log sizeA
     * @param a a list
     * @param b b list
     * @param target target
     * @return result
     */
    private List<Integer[]> twoSumClosest(int[][] a, int[][] b, int target) {
        TreeMap<Integer, List<Integer>> tree = new TreeMap<>();
        for (int[] pair : a) {
            List<Integer> list = tree.getOrDefault(pair[1], new ArrayList<>());
            list.add(pair[0]);
            tree.put(pair[1], list);
        }

        List<Integer[]> result = new ArrayList<>();
        for (int[] pair : b) {
             Integer key = tree.floorKey(target - pair[1]);
             if (key != null) {
                 for (int index : tree.get(key)) {
                     result.add(new Integer[]{pair[0], index});
                 }
             }
        }
        return result;
    }
}
