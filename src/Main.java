import java.util.*;

public class Main {
    static public void main(String[] arg) {
        int[][] hrd = new int[][]{{4, 6, 1}
                , {8, 3, 7}
                , {2, 5, 0}};
        List<String> cache = new ArrayList<>();
        String result = "";
        System.out.print(getHrD(hrd, cache, result));

    }

    private static String getHrD(int[][] hrd, List<String> cache, String result) {
        if (hrd == new int[][]{{1, 2, 3}
                , {4, 5, 6}
                , {7, 8, 9}}) {
            return result;
        }
        for (int i = 0; i < hrd.length; i++) {
            for (int j = 0; j < hrd[i].length; j++) {
                if (hrd[i][j] == 0) {
                    if (i > 0) {
                        int[][] temp = arraycopy(hrd);
                        temp[i][j] = temp[i - 1][j];
                        temp[i - 1][j] = 0;
                        String tempString = getStringData(temp);
                        if (!cache.contains(tempString)) {
                            cache.add(tempString);
                            result = getHrD(temp, cache, result + temp[i][j] + "->");
                        }
                    }
                    if (j > 0) {
                        int[][] temp = arraycopy(hrd);
                        temp[i][j] = temp[i][j - 1];
                        temp[i][j - 1] = 0;
                        String tempString = getStringData(temp);
                        if (!cache.contains(tempString)) {
                            cache.add(tempString);
                            result = getHrD(temp, cache, result + temp[i][j] + "->");
                        }
                    }
                    if (i < hrd.length - 1) {
                        int[][] temp = arraycopy(hrd);
                        temp[i][j] = temp[i + 1][j];
                        temp[i + 1][j] = 0;
                        String tempString = getStringData(temp);
                        if (!cache.contains(tempString)) {
                            cache.add(tempString);
                            result = getHrD(temp, cache, result + temp[i][j] + "->");
                        }
                    }
                    if (j < hrd[i].length - 1) {
                        int[][] temp = arraycopy(hrd);
                        temp[i][j] = temp[i][j + 1];
                        temp[i][j + 1] = 0;
                        String tempString = getStringData(temp);
                        if (!cache.contains(tempString)) {
                            cache.add(tempString);
                            result = getHrD(temp, cache, result + temp[i][j] + "->");
                        } else {
                            return result;
                        }
                    }
                    return result;
                }
            }
        }
        return result;
    }

    private static int[][] arraycopy(int[][] hrd) {
        int[][] temp = new int[hrd.length][];
        for (int i = 0; i < hrd.length; i++) {
            temp[i] = new int[hrd[i].length];
            for (int j = 0; j < hrd[i].length; j++) {
                temp[i][j] = hrd[i][j];
            }
        }
        return temp;
    }

    private static String getStringData(int[][] data) {

        StringBuilder result = new StringBuilder();
        for (int[] aData : data) {
            for (int anAData : aData) {
                result.append(",").append(anAData);
            }
        }
        return result.toString();
    }
}


