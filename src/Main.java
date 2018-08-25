import java.util.*;

public class Main {
    static public void main(String[] arg) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        System.out.println(f(3, 2));
    }

    //备忘录算法解题
    private static int f(int floor, int eggs, HashMap<String, Integer> hashMap) {
        if (floor == 1) {
            return 1;
        } else if (eggs == 1) {
            return floor;
        } else if (hashMap.containsKey(floor + "_" + eggs)) {
            return hashMap.get(floor + "_" + eggs);
        } else {
            int temp = -1;
            for (int i = 2; i <= floor; i++) {
                if (temp == -1) {
                    temp = 1 + Math.max(f(floor - i, eggs - 1, hashMap), f(i - 1, eggs, hashMap));
                } else {
                    temp = Math.min(1 + Math.max(f(floor - i, eggs - 1, hashMap), f(i - 1, eggs, hashMap)), temp);
                }
            }
            hashMap.put(floor + "_" + eggs, temp);
            return temp;
        }
    }

    //非递归算法
    private static int f(int floor, int eggs) {
        int[][] temp = new int[floor+1][eggs+1];
        for (int m = 1; m <= floor; m++) {
            for (int n = 1; n <= eggs; n++) {
                if (1 == m) {
                    //f(1,n) = 1
                    temp[m][n] = 1;
                } else if (1 == n) {
                    //f(m,1) = m，这里+1是因为代码中是数组从零开始计数的
                    temp[m][n] = m + 1;
                } else {
                    //f(m,n)=min(1+max(f(m-x,n-1),f(x-1,n)))
                    temp[m][n] = Integer.MAX_VALUE;
                    for (int k = 1; k <= m; k++) {
                        temp[m][n] = Math.min(temp[m][n], 1 + Math.max(temp[m - k][n - 1], temp[k - 1][n]));
                    }
                }
            }
        }
        return temp[floor][eggs];
    }
}
