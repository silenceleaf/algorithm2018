package org.zjy.learn.code.amazon;


import org.zjy.learn.util.Application;

import java.util.PriorityQueue;

@Application(time = "06/17/2019 13:52")
public class MergeItems implements Runnable {
    /**
     * 有一个list货物，拼装时间是两个部件的和，两个东西拼装完后成为一个大的东西（以后的拼装时间就是它们的和），求拼成一个大件的总和
     * 比如[8, 6, 4, 12] ----- (拼装4和6，用时10， 总用时10)--> [8, 10, 12] --- (拼装8和10，用时18， 总用时28)--> [12, 18] ----- (拼装12和18，用时30， 总用时58).
     * 58是最小结果
     */
    @Override
    public void run() {
        int result = mergeItems(new int[] {8, 6, 4, 12});
        System.out.println("result: " + result);
    }

    /**
     * 1, out all items into priority queue cost: n log n
     * 2, take out and put back all items cost: take out cost O(1), put back cost: n log n
     * , total: 2n log n = n log n
     * @param items item array
     * @return result
     */
    private int mergeItems(int[] items) {
        if (items.length == 0) {
            return 0;
        }
        if (items.length == 1) {
            return items[0];
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int item : items) {
            pq.offer(item);
        }
        int result = 0;
        while (pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();
            int sum = a + b;
            pq.offer(sum);
            result += sum;
        }
        return result;
    }

}
