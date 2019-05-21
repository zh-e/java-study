package com.zhe.javabase.algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 二叉树遍历
 *
 * @author zhangzhe
 */
public class BinTreeTraversing {


    public static void main(String[] args) {
        BinTreeTraversing bt = new BinTreeTraversing();
        TreeNode node = bt.getTreeNode();
        //层次遍历
        layerTraver(node);
        System.out.println();
        //递归前序遍历
        preOrderTraversal(node);
        System.out.println();
        //非递归前序遍历
        preOrderTraversal2(node);
        System.out.println();
        //递归中序遍历
        inOrderTraversal(node);
        System.out.println();
        inOrderTraversal2(node);
        System.out.println();
        //递归后序遍历
        postOrderTraversal(node);
        System.out.println();
        //非递归后序遍历
        postOrderTraversal2(node);
    }

    /**
     * 递归实现前序遍历
     */
    private static void preOrderTraversal(TreeNode node) {

        System.out.print(node.value + "    ");

        if (node.leftNode != null) {
            preOrderTraversal(node.leftNode);
        }

        if (node.rightNode != null) {
            preOrderTraversal(node.rightNode);
        }

    }

    /**
     * 非递归实现前序遍历
     */
    private static void preOrderTraversal2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);

        while (stack.size() > 0) {
            TreeNode n = stack.pop();
            System.out.print(n.value + "    ");
            if (n.rightNode != null) {
                stack.add(n.rightNode);
            }
            if (n.leftNode != null) {
                stack.add(n.leftNode);
            }
        }
    }

    /**
     * 递归实现中序遍历
     */
    private static void inOrderTraversal(TreeNode node) {
        if (node.leftNode != null) {
            inOrderTraversal(node.leftNode);
        }

        System.out.print(node.value + "    ");

        if (node.rightNode != null) {
            inOrderTraversal(node.rightNode);
        }
    }

    /**
     * 非递归实现中序遍历
     */
    private static void inOrderTraversal2(TreeNode node) {
        //左中右
        Stack<TreeNode> stack = new Stack<>();

        while (node != null || !stack.empty()) {

            while (node != null) {
                stack.push(node);
                node = node.leftNode;
            }

            if (!stack.empty()) {
                node = stack.pop();
                System.out.print(node.value + "    ");
                node = node.rightNode;
            }
        }

    }

    /**
     * 递归实现后序遍历
     */
    private static void postOrderTraversal(TreeNode node) {
        if (node.leftNode != null) {
            postOrderTraversal(node.leftNode);
        }

        if (node.rightNode != null) {
            postOrderTraversal(node.rightNode);
        }

        System.out.print(node.value + "    ");
    }

    /**
     * 非递归实现后序遍历
     */
    private static void postOrderTraversal2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prior = null;

        while (node != null || !stack.empty()) {
            if(node != null) {
                stack.push(node);
                node = node.leftNode;
            } else {
                node = stack.pop();
                if(node.rightNode == null || node.rightNode == prior) {
                    System.out.print(node.value + "    ");
                    prior = node;
                    node = null;
                } else {
                    stack.push(node);
                    node = node.rightNode;
                    stack.push(node);
                    node = node.leftNode;
                }
            }
        }

    }

    /**
     * 层次遍历
     */
    private static void layerTraver(TreeNode node) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);

        while (queue.size() > 0) {
            TreeNode n = queue.poll();
            System.out.print(n.value + "    ");
            if (n.leftNode != null) {
                queue.add(n.leftNode);
            }
            if (n.rightNode != null) {
                queue.add(n.rightNode);
            }
        }
    }


    private TreeNode getTreeNode() {

        TreeNode n1 = new TreeNode();
        n1.setValue(1);

        TreeNode n2 = new TreeNode();
        n2.setValue(2);

        TreeNode n3 = new TreeNode();
        n3.setValue(3);

        TreeNode n4 = new TreeNode();
        n4.setValue(4);

        TreeNode n5 = new TreeNode();
        n5.setValue(5);

        TreeNode n6 = new TreeNode();
        n6.setValue(6);

        TreeNode n7 = new TreeNode();
        n7.setValue(7);

        TreeNode n8 = new TreeNode();
        n8.setValue(8);

        TreeNode n9 = new TreeNode();
        n9.setValue(9);

        TreeNode n10 = new TreeNode();
        n10.setValue(10);

        TreeNode n11 = new TreeNode();
        n11.setValue(11);

        TreeNode n12 = new TreeNode();
        n12.setValue(12);

        n1.setLeftNode(n2);
        n1.setRightNode(n3);

        n2.setLeftNode(n4);
        n2.setRightNode(n5);

        n3.setLeftNode(n6);
        n3.setRightNode(n7);

        n4.setLeftNode(n8);

        n5.setRightNode(n9);

        n6.setLeftNode(n10);
        n6.setRightNode(n11);

        n9.setLeftNode(n12);

        return n1;

    }

    private class TreeNode {

        private int value;

        private TreeNode leftNode;

        private TreeNode rightNode;

        private void setValue(int value) {
            this.value = value;
        }

        private void setLeftNode(TreeNode leftNode) {
            this.leftNode = leftNode;
        }

        private void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }
    }


}
