package org.zjy.learn.code.google;

import org.zjy.learn.util.Application;
import org.zjy.learn.util.Util;

@Application(time = "03/13/2018 11:15")
public class CleanRobot implements Runnable {
    @Override
    public void run() {
        cleanRoom(3, 3);
    }

    private class Robot {
        private final int[] directionX = new int[]{1, 0, -1, 0}; // right->down->left->up
        private final int[] directionY = new int[]{0, 1, 0, -1};



        private int currentDirection = 0;
        public int[] move(int[] current) {
            return new int[]{current[0] + directionY[currentDirection], current[1] + directionX[currentDirection]};
        }

        public void turnRightK(int k) {
            currentDirection = (currentDirection + k) % 4;
        }

        public void turnLeftK(int k) {
            currentDirection = Math.abs(4 + currentDirection - k) % 4;
        }
    }

    public void cleanRoom(int x, int y) {
        int[][] room = new int[y][x];
        room[0][0] = 1;
        dfs(new Robot(), room, 0, new int[]{0, 0});
    }

    public void dfs(Robot robot, int[][] room, int failure, int[] current) {
        if (failure > 3) {
            return;
        }
        int[] next = robot.move(current);
        if (next[0] < 0 || next[0] >= room.length || next[1] < 0 || next[1] >= room[0].length || room[next[0]][next[1]] > 0) {
            robot.turnRightK(1);
            ++failure;
            dfs(robot, room, failure, current);
        } else {
            room[next[0]][next[1]] = 1;
            Util.printMatrix(room);
            failure = 0;
            dfs(robot, room, failure, next);
        }

    }
}
