package com.zhe.javabase.algorithms;

/**
 * 八皇后问题
 * @author zhangzhe
 */
public class EightQueenSolution {

    private final static int MAX_NUM = 8;

    public static void main(String[] args) {
        int[][] arr = initArr();
        setQueen(0, arr);
        printArr(arr);
    }

    private static boolean setQueen(int y, int[][] arr) {
        if(y == MAX_NUM) {
            return true;
        }

        for(int i = 0; i < MAX_NUM; i++) {

            for (int x = 0; x < MAX_NUM; x++) {
                arr[x][y] = 0;
            }

            if(check(i, y, arr)) {
                arr[i][y] = 1;
                if(setQueen(y + 1, arr)) {
                    return true;
                }
            }
        }
        return false;
    }


    private static boolean check(int x, int y, int[][] arr) {
        for (int i = 0; i < y; i++) {
            if (arr[x][i] == 1) {
                return false;
            }

            int xl = x - 1 - i;
            int yl = y - 1 - i;
            if (xl >= 0 && arr[xl][yl] == 1) {
                return false;
            }

            int xr = x + 1 + i;
            int yr = y - 1 - i;
            if (xr < MAX_NUM && arr[xr][yr] == 1) {
                return false;
            }
        }
        return true;
    }

    private static int[][] initArr() {
        int[][] arr = new int[MAX_NUM][MAX_NUM];
        for (int i = 0; i < MAX_NUM; i++) {
            for (int j = 0; j < MAX_NUM; j++) {
                arr[i][j] = 0;
            }
        }
        return arr;
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < MAX_NUM; i++) {
            for (int j = 0; j < MAX_NUM; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }

}
