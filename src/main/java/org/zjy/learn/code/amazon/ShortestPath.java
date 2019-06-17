package org.zjy.learn.code.amazon;

import org.zjy.learn.util.Application;

import java.util.LinkedList;
import java.util.Queue;

@Application(time = "06/17/2019 15:08")
public class ShortestPath implements Runnable {
    /**
     * BFS 走迷宫，要求return最短路线。从【0，0】出发，1可以走，0不能走，找到数值为9的终点
     * in-place更新当前格子距离出发点的距离，用负数，为了不影响数值为1的格子。
     * 一个坑，如果出发点为9，直接return 0
     */
    @Override
    public void run() {
        int[][] world = new int[][] {
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,9}
        };

        int result = path(world);
        System.out.println("result: " + result);
    }

    /**
     * complexity: O(m * n)
     * @param world maze
     * @return result
     */
    private int path(int[][] world) {
        if (world[0][0] == 9 || world[0][0] == 0) {
            return 0;
        }
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Queue<Integer[]> queue = new LinkedList<>();
        int length = world[0].length;
        int height = world.length;
        int[][] distance = new int[height][length];
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[0][0] = 0;
        int[] target = new int[]{0, 0};
        queue.offer(new Integer[]{0, 0});
        while (!queue.isEmpty()) {
            Integer[] current = queue.poll();
            for (int[] dir : directions) {
                Integer[] next = new Integer[]{dir[0] + current[0], dir[1] + current[1]};
                if (next[0] >= 0 && next[0] < length && next[1] >= 0 && next[1] < height && world[next[1]][next[0]] != 0) { // valid next point
                    if (world[next[1]][next[0]] == 9) { // first time find destination write down its coordinate and mark it to 1
                        target[0] = next[0];
                        target[1] = next[1];
                        world[next[1]][next[0]] = 1;
                    }
                    if (distance[current[1]][current[0]] + 1 < distance[next[1]][next[0]]) { // current + 1 < current distance
                        distance[next[1]][next[0]] = distance[current[1]][current[0]] + 1;
                        queue.offer(next);
                    }
                }
            }
        }
        return distance[target[1]][target[0]];
    }


}
