package com.zhe.javabase;

public class test {

    public static void main(String[] args) {
       int[] arr = new int[]{1,2,3,1,4,3};
       int target = 11;
        System.out.println(new test().get(arr, target));
    }

    public int get(int[] arr, int target) {
        int length = arr.length;
        Integer ans = null;
        int i = 0, j = 0;
        int sum = 0;
        while (j < length) {
            if (sum < target) {
                sum += arr[j];
                j++;
            }
            if (sum > target) {
                sum -= arr[i];
                i++;
            }
            if (sum == target) {
                if (ans == null) {
                    ans = j - i;
                } else {
                    ans = Math.min(ans, j - i);
                }
                sum -= arr[i];
                i++;
            }
        }
        return ans == null ? 0 : ans;
    }


}
