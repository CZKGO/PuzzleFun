import java.util.*;

public class Main {

    private static final String RESULT_DATA = getStringData(new int[][]{{1, 2, 3}
            , {4, 5, 6}
            , {7, 8, 0}});

    static public void main(String[] arg) {
        int[][] hrd = new int[][]{{1, 2, 3}
                , {4, 5, 6}
                , {7, 0, 8}};
        Map<String, String> cache = new HashMap<>();
        System.out.print(getHrD(hrd, cache, -1));

    }

    private static String getHrD(int[][] hrd, Map<String, String> cache, int lastMoveDirection) {
        String result = "";
        String result1 = "";
        String result2 = "";
        String result3 = "";
        for (int i = 0; i < hrd.length; i++) {
            for (int j = 0; j < hrd[i].length; j++) {
                if (hrd[i][j] == 0) {
                    if (lastMoveDirection != 2) {
                        if (i > 0) {
                            int[][] temp = doChange(hrd, i, j, i - 1, j);
                            String tempString = getStringData(temp);
                            if (tempString.equals(RESULT_DATA)) {
                                return "" + temp[i][j];
                            } else {
                                tempString = tempString + "0";
                                String subresult = cache.get(tempString);
                                if (null == subresult) {
                                    subresult = getHrD(temp, cache, 0);
                                    cache.put(tempString, subresult);
                                }
                                result = temp[i][j] + "-> " + subresult;
                            }
                        }
                    }
                    if (lastMoveDirection != 3) {
                        if (j > 0) {
                            int[][] temp = doChange(hrd, i, j, i, j - 1);
                            String tempString = getStringData(temp);
                            if (tempString.equals(RESULT_DATA)) {
                                return "" + temp[i][j];
                            } else {
                                tempString = tempString + "1";
                                String subresult = cache.get(tempString);
                                if (null == subresult) {
                                    subresult = getHrD(temp, cache, 1);
                                    cache.put(tempString, subresult);
                                }
                                result1 = temp[i][j] + "-> " + subresult;
                            }
                        }
                    }
                    if (lastMoveDirection != 0) {
                        if (i < hrd.length - 1) {
                            int[][] temp = doChange(hrd, i, j, i + 1, j);
                            String tempString = getStringData(temp);
                            if (tempString.equals(RESULT_DATA)) {
                                return "" + temp[i][j];
                            } else {
                                tempString = tempString + "2";
                                String subresult = cache.get(tempString);
                                if (null == subresult) {
                                    subresult = getHrD(temp, cache, 2);
                                    cache.put(tempString, subresult);
                                }
                                result2 = temp[i][j] + "-> " + subresult;
                            }
                        }
                    }
                    if (lastMoveDirection != 1) {
                        if (j < hrd[i].length - 1) {
                            int[][] temp = doChange(hrd, i, j, i, j + 1);
                            String tempString = getStringData(temp);
                            if (tempString.equals(RESULT_DATA)) {
                                return "" + temp[i][j];
                            } else {
                                tempString = tempString + "3";
                                String subresult = cache.get(tempString);
                                if (null == subresult) {
                                    subresult = getHrD(temp, cache, 3);
                                    cache.put(tempString, subresult);
                                }
                                result3 = temp[i][j] + "-> " + subresult;
                            }
                        }
                    }
                    if (result.length() < result1.length()) {
                        if (result.length() < result2.length()) {
                            if (result.length() < result3.length()) {
                                return result;
                            }
                        }
                    } else if (result1.length() < result2.length()) {
                        if (result1.length() < result3.length()) {
                            return result1;
                        }
                    } else if (result2.length() < result3.length()) {
                        return result2;
                    } else {
                        return result3;
                    }
                }
            }
        }
        return "";
    }

    private static int[][] doChange(int[][] hrd, int i, int j, int i2, int j2) {
        int[][] temp = arraycopy(hrd);
        temp[i][j] = temp[i2][j2];
        temp[i2][j2] = 0;
        return temp;
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


