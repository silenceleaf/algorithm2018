package org.zjy.learn.code.redundant_tree;

/**
 * Created by Junyan Zhang on 12/20/2017.
 */
public class RedundantTree {
    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length;
        int[] parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        int l, r, father_l, father_r;
        for (int[] now_edge : edges) {
            l = now_edge[0];
            r = now_edge[1];
            father_l = find_father(parent, l);
            father_r = find_father(parent, r);
            if (father_l == father_r) {
                return now_edge;
            } else {
                parent[father_l] = father_r;
            }
        }
        return new int[2];
    }

    private int find_father(int[] parent, int now) {
        if (now != parent[now]) {
            parent[now] = find_father(parent, parent[now]);
        }
        return parent[now];
    }
}
