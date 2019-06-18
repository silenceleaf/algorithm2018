package org.zjy.learn.code.amazon;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@Application(time = "06/17/2019 22:08")
public class TwoSumClosest implements Runnable {
    /**
     * two sum closest
     * 给两个int的list，一个capacity，从两个list中各选一个item把num加和，返回所有pair里小于capacity并且最大的id，有多个就返回多个
     * list是这个格式的：[[id1, num1], [id2, num2], [id3, num3]]
     * 返回的格式是：[[id, id], ...]
     */
    @Override
    public void run() {
//        List<Integer[]> result1 = twoSumClosest(new int[][]{{1, 3}, {2, 4}, {3, 1}, {4, 2}}, new int[][]{{5, 3}, {6, 4}}, 8);
//        for (Integer[] pair : result1) {
//            System.out.println(pair[0] + "  " + pair[1]);
//        }

        int deviceCapacity1 = 20;
        List<List<Integer>> foreground1 = new ArrayList<>();
        foreground1.add(Arrays.asList(1, 8));
        foreground1.add(Arrays.asList(2, 7));
        foreground1.add(Arrays.asList(3, 14));
        List<List<Integer>> background1 = new ArrayList<>();
        background1.add(Arrays.asList(1, 5));
        background1.add(Arrays.asList(2, 10));
        background1.add(Arrays.asList(3, 14));
        // expect: 3 1

        int deviceCapacity2 = 20;
        List<List<Integer>> foreground2 = new ArrayList<>();
        foreground2.add(Arrays.asList(1, 8));
        foreground2.add(Arrays.asList(2, 15));
        foreground2.add(Arrays.asList(3, 9));
        List<List<Integer>> background2 = new ArrayList<>();
        background2.add(Arrays.asList(1, 8));
        background2.add(Arrays.asList(2, 11));
        background2.add(Arrays.asList(3, 12));
        // expect:
        // 1 3
        // 3 2

        System.out.println("1:");
        output(optimalUtilization(deviceCapacity1, foreground1, background1));
        System.out.println("2:");
        output(optimalUtilization(deviceCapacity2, foreground2, background2));
    }

    private List<List<Integer>> optimalUtilization(
            int deviceCapacity,
            List<List<Integer>> foregroundAppList,
            List<List<Integer>> backgroundAppList)
    {
        // WRITE YOUR CODE HERE
        TreeMap<Integer, List<Integer>> tree = new TreeMap<>();
        for (List<Integer> pair : backgroundAppList) {
            List<Integer> list = tree.getOrDefault(pair.get(1), new ArrayList<>());
            list.add(pair.get(0));
            tree.put(pair.get(1), list);
        }
        int diff = Integer.MAX_VALUE;
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> pair : foregroundAppList) {
            Integer floorKey = tree.floorKey(deviceCapacity - pair.get(1));
            if (floorKey != null) {
                if (Math.abs(deviceCapacity - pair.get(1) - floorKey) < diff) {
                    result.clear();
                    diff = Math.abs(deviceCapacity - pair.get(1) - floorKey);
                } else if (Math.abs(deviceCapacity - pair.get(1) - floorKey) > diff) {
                    continue;
                }
                for (int id : tree.get(floorKey)) {
                    List<Integer> match = new ArrayList<>();
                    match.add(pair.get(0));
                    match.add(id);
                    result.add(match);
                }
            }
        }
        return result;
    }

    private void output(List<List<Integer>> matrix) {
        for (List<Integer> row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
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
