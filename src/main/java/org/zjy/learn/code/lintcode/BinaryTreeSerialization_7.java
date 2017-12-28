package org.zjy.learn.code.lintcode;


import org.zjy.learn.util.Application;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by junyan zhang on 12/25/17.
 *
 * Not finish. Online test not pass
 */

@Application(time = "12/25/2017 14:25")
public class BinaryTreeSerialization_7 implements Runnable {
	private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

	@Override
	public void run() {
		// {1,2,#,3,#,4}
		TreeNode root = new TreeNode(1);
		TreeNode l = new TreeNode(2);
		TreeNode r = null;
		TreeNode ll = new TreeNode(3);

		TreeNode rl = new TreeNode(15);
		TreeNode rr = new TreeNode(7);

		root.left = l;
		root.right = r;
		r.left = rl;
		r.right = rr;

		String temp = serialize(root);
		logger.info("before: " + temp);
		TreeNode node = deserialize(temp);
		logger.info("after: " + serialize(node));
	}

	public class TreeNode {
	    public int val;
	    public TreeNode left, right;
	    public TreeNode(int val) {
	        this.val = val;
	        this.left = this.right = null;
	    }
	}

	/**
	 * This method will be invoked first, you should design your own algorithm
	 * to serialize a binary tree which denote by a root node to a string which
	 * can be easily deserialized by your own "deserialize" method later.
	 */
	public String serialize(TreeNode root) {
		// write your code here
		if (root == null) {
			return "{}";
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		while (!queue.isEmpty()){
			TreeNode node = queue.poll();
			if (node != null) {
				stringBuilder.append(node.val).append(",");
				queue.offer(node.left);
				queue.offer(node.right);
			} else {
				stringBuilder.append("#,");
			}
		}
		stringBuilder.append("}");
		return stringBuilder.toString();
	}

	/**
	 * This method will be invoked second, the argument data is what exactly
	 * you serialized at method "serialize", that means the data is not given by
	 * system, it's given by your own serialize method. So the format of data is
	 * designed by yourself, and deserialize it here as you serialize it in
	 * "serialize" method.
	 */
	public TreeNode deserialize(String data) {
		// write your code here
		if (data.equals("{}")) {
			return null;
		}
		String[] values = data.substring(1, data.length() - 1).split(",");
		TreeNode[] allNodes = new TreeNode[values.length + 1];
		for (int i = 0; i < values.length; i++)
			allNodes[i+1] = !values[i].equals("#") ? new TreeNode(Integer.parseInt(values[i])) : null;

		for (int i = 1; i < values.length + 1; i++) {
			if (allNodes[i] != null) {
				int leftIndex = 2 * i;
				if (leftIndex < values.length)
					allNodes[i].left = allNodes[leftIndex];

				int rightIndex = 2 * i + 1;
				if (rightIndex < values.length)
					allNodes[i].right = allNodes[rightIndex];
			}
		}
		return allNodes[1];
	}
}
