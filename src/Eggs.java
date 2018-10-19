import java.util.HashMap;

/**
 * 谷歌扔鸡蛋算法
 */
public class Eggs {
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
        for (int i = 1; i <= floor; i++) {
            for (int j = 1; j <= eggs; j++) {
                if (1 == i) {
                    //f(1,n) = 1
                    temp[i][j] = 1;
                } else if (1 == j) {
                    //f(m,1) = m
                    temp[i][j] = i;
                } else {
                    //f(m,n)=min(1+max(f(m-x,n-1),f(x-1,n)))
                    temp[i][j] = 1 + Math.max(temp[i - 2][j - 1], temp[1][j]);
                    for (int k = 2; k <= i; k++) {
                        temp[i][j] = Math.min(temp[i][j], 1 + Math.max(temp[i - k][j - 1], temp[k - 1][j]));
                    }
                }
            }
        }
        return temp[floor][eggs];
    }
}
