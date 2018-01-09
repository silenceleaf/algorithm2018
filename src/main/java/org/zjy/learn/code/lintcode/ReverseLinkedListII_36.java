package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Application(time = "12/29/2017 13:11")
public class ReverseLinkedListII_36 implements Runnable {
    private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

    /**
     * lintcode passed
     */
    @Override
    public void run() {
        // 3760->2881->7595->3904->5069->4421->8560->8879->8488->5040->5792->56->1007->2270->3403->6062->null
        ListNode head = new ListNode(3760);
        ListNode node1 = new ListNode(2881);
        ListNode node2 = new ListNode(7595);
        ListNode node3 = new ListNode(3904);
        ListNode node4 = new ListNode(5069);
        ListNode node5 = new ListNode(4421);
        ListNode node6 = new ListNode(8560);
        ListNode node7 = new ListNode(8879);
        ListNode node8 = new ListNode(8488);
        ListNode node9 = new ListNode(5040);
        ListNode node10 = new ListNode(5792);
        ListNode node11 = new ListNode(56);
        ListNode node12 = new ListNode(1007);
        ListNode node13 = new ListNode(2270);
        ListNode node14 = new ListNode(3403);
        ListNode node15 = new ListNode(6062);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;

        output(head);
        ListNode newHead = reverseBetween(head, 2, 7);
        output(newHead);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        int beginIndex = m;
        int endIndex = n;
        ListNode begin = dummyHead;
        ListNode end;
        for (int i = 1; i < beginIndex; i++) {
            begin = begin.next;
        }

        while (beginIndex < endIndex) {
            end = dummyHead;
            for (int i = 1; i < endIndex; i++) {
                end = end.next;
            }
            ListNode temp;
            temp = begin.next;
            begin.next = end.next;
            end.next = temp;

            temp = begin.next.next; // previous end.next.next
            begin.next.next = end.next.next;
            end.next.next = temp;


            begin = begin.next;
            beginIndex++;
            endIndex--;
        }
        return dummyHead.next;
    }

    public void output(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            stringBuilder.append(temp.val).append("->");
            temp = temp.next;
        }
        stringBuilder.append("NULL");
        logger.info("answer: " + stringBuilder.toString());
    }
}
