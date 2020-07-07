package org.zjy.learn.code.amazon;

import org.zjy.learn.util.Application;

import java.util.*;
import java.util.stream.Collectors;

@Application(time = "05/13/2020 18:08")
public class FindSongs implements Runnable {
    /**
     * Two sum. 要求找到sum是target-30，并且其中一首歌曲是符合要求的pairs里单首最长的pair
     * 一个坑，随时update结果pair，保存在一个variable里。因为如果两首歌一样长并且这两首诗最终答案，如果只keep合格的最长的歌曲，最后再retrieve index，会导致这种test case出错。
     */
    @Override
    public void run() {
//    List<String> result = popularNFeatures(
//                5,
//                2,
//                new ArrayList<>(Arrays.asList("anacell", "betacellular", "cetracular", "deltacellular", "eurocell")),
//                3,
//                new ArrayList<>(Arrays.asList("Best services provided by anacell", "betacellular has great services", "anacell provides much better services than all other")));
//
//        System.out.println(result);
    }

    private int[] findSongs(int[] songs) {
        return null;
    }

    private class Node {
        String word;
        int sum;

        Node(String s, int size) {
            word = s;
            sum = size;
        }
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public ArrayList<String> popularNFeatures(int numFeatures,
                                              int topFeatures,
                                              List<String> possibleFeatures,
                                              int numFeatureRequests,
                                              List<String> featureRequests)
    {
        // WRITE YOUR CODE HERE
        Set<String> possibleSet = new HashSet<>(possibleFeatures);
        Map<String, Node> map = new HashMap<>();
        for (String request : featureRequests) {
            String[] split = request.split("\\s*[^a-zA-Z]+\\s*");
            if (split.length == 0)
                continue;
            Set<String> set = new HashSet<>(Arrays.asList(split));
            for (String word : set) {
                if (possibleSet.contains(word)) {
                    Node node = map.getOrDefault(word, new Node(word, 0));
                    ++node.sum;
                    map.put(word, node);
                }
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                if (n1.sum == n2.sum)
                    return n2.word.compareTo(n1.word);
                return n1.sum - n2.sum;
            }
        });
        for (String s : map.keySet()) {
            pq.offer(map.get(s));
            if (pq.size() > topFeatures)
                pq.poll();
        }
        ArrayList<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            result.add(node.word);
        }
        Collections.reverse(result);
        return result.stream()
                .map(String::toLowerCase).collect(Collectors.toCollection(ArrayList::new));
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<String> reorderLines(int logFileSize, List<String> logLines)
    {
        // WRITE YOUR CODE HERE
        Collections.sort(logLines, (a, b) -> {
            String[] split1 = a.split(" ", 2);
            String[] split2 = b.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if(!isDigit1 && !isDigit2) {
                int comp = split1[1].compareTo(split2[1]);
                if(comp != 0)
                    return comp;
                else
                    return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logLines;
    }
}
