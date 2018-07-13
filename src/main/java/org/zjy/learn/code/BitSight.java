package org.zjy.learn.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by junyan zhang on 7/12/18.
 */


public class BitSight  {
	public static void main(String[]args){
		if (args.length < 1) {
			System.out.print("Please input file path as the first parameter");
			return;
		}
		String fileName = args[0];
		//String fileName = "/Users/junyan/Downloads/bitsight.txt";
		int n = 0;
		int m = 0;
		List<List<Integer>> adjMatrix = new ArrayList<>();
		List<Integer> indegreeCount = new ArrayList<>();
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
			String firstLine = bufferedReader.readLine();
			// read N and M from first line
			String[] split = firstLine.split("\\s+");
			n = Integer.valueOf(split[0]);
			for (int i = 0; i <= n; i++) {
				adjMatrix.add(new ArrayList<>());
				indegreeCount.add(0);
			}
			m = Integer.valueOf(split[1]);
			// fill the adjacent matrix and indegree count array
			for (int i = 0; i < m; i++) {
				String[] line = bufferedReader.readLine().split("\\s+");
				int target = Integer.valueOf(line[0]);
				for (int j = 2; j < line.length; j++) {
					int dependent = Integer.valueOf(line[j]);
					indegreeCount.set(target, indegreeCount.get(target) + 1);
					adjMatrix.get(dependent).add(target);
				}
			}
			// main logic: topological sort
			int[] result = topologicalSort(indegreeCount, adjMatrix);
			// output the result
			if (result.length > 0) {
				for (int i = 0; i < result.length - 1; i++) {
					System.out.print(result[i]);
					System.out.print(" ");
				}
			} else {
				System.out.println("Dependencies has circle!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int[] topologicalSort(List<Integer> indegreeCount, List<List<Integer>> adjMatrix){
		int[] order = new int[indegreeCount.size()];
		Queue<Integer> toVisit = new LinkedList<>();
		// find the indegree = 0 node, push into queue
		for (int i = 1; i < indegreeCount.size(); i++) {
			if (indegreeCount.get(i) == 0)
				toVisit.offer(i);
		}
		int visited = 0;
		while (!toVisit.isEmpty()) {
			int from = toVisit.poll();
			order[visited++] = from;
			for (int to : adjMatrix.get(from)) {
				// remove all edges from this node (indegree = 0)
				indegreeCount.set(to, indegreeCount.get(to) - 1);
				// put new indegree = 0 into queue
				if (indegreeCount.get(to) == 0)
					toVisit.offer(to);
			}
		}
		// if some node are not visited, means circle exists
		return visited == indegreeCount.size() - 1 ? order : new int[0];
	}
}
