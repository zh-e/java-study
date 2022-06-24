package com.zhe.javabase;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().multiply("123", "456"));
    }

    /**
     * 字符串相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i > 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }

        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }

        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    /**
     * 基本计算器
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                    default:
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length ==0 || matrix[0].length == 0) {
            return order;
        }

        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        while (left <= right && top <= bottom) {

            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }

            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }

            if (left < right && top < bottom) {
                for (int column = right -1; column > left; column --) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row --) {
                    order.add(matrix[row][left]);
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    public int search(int[] arr, int target) {
        if (arr[0] == target) {
            return 0;
        }

        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + (r - 1) / 2;
            if (arr[mid] == target) {
                while (mid > 0 && arr[mid - 1] == arr[mid]) {
                    mid --;
                }
                return mid;
            }

            if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else if (arr[mid] > arr[r]) {
                if (arr[l] <= target && target < arr[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                r--;
            }
        }

        return -1;
    }


}
