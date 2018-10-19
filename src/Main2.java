import java.util.ArrayList;
import java.util.List;

public class Main2 {

    private static final String RESULT_DATA = getStringData(new int[][]{{1, 2, 3}
            , {4, 5, 6}
            , {7, 8, 0}});
    private static final String ERROR_DATA = getStringData(new int[][]{{1, 2, 3}
            , {4, 5, 6}
            , {8, 7, 0}});
    private static final String ERROR_MESSAGE = "error";

    static public void main(String[] arg) {
        int[][] hrd = new int[][]{{1, 2, 3}
                , {4, 5, 6}
                , {0, 7, 8}};
        List<String> cache = new ArrayList<>();
        System.out.println("深度优先遍历：" + getDepthHrD(hrd, cache, 5));
    }

    /**
     * 深度优先遍历,部分谜题会Stack Overflow
     *
     * @param hrd           谜面
     * @param cache         缓存
     * @param moveDirection 移动方向
     * @return
     */
    private static String getDepthHrD(int[][] hrd, List<String> cache, int moveDirection) {
        String hrdString = getStringData(hrd);
        if (hrdString.equals(RESULT_DATA)) {
            return "结束";
        } else if (hrdString.equals(ERROR_DATA)) {
            return ERROR_MESSAGE;
        } else if (cache.contains(hrdString + "-" + moveDirection)) {
            return null;
        } else {
//            System.out.println(hrdString + "-" + moveDirection);
            cache.add(hrdString + "-" + moveDirection);
            for (int i = 0; i < hrd.length; i++) {
                for (int j = 0; j < hrd[i].length; j++) {
                    if (hrd[i][j] == 0) {
                        if (i > 0) {
                            int[][] temp = doChange(hrd, i, j, i - 1, j);
                            String subResult = getDepthHrD(temp, cache, 0);
                            if (subResult != null) {
                                return getResult(temp[i][j], subResult);
                            }
                        }
                        if (j > 0) {
                            int[][] temp = doChange(hrd, i, j, i, j - 1);
                            String subResult = getDepthHrD(temp, cache, 1);
                            if (subResult != null) {
                                return getResult(temp[i][j], subResult);
                            }

                        }
                        if (i < hrd.length - 1) {
                            int[][] temp = doChange(hrd, i, j, i + 1, j);
                            String subResult = getDepthHrD(temp, cache, 2);
                            if (subResult != null) {
                                return getResult(temp[i][j], subResult);
                            }
                        }
                        if (j < hrd.length - 1) {
                            int[][] temp = doChange(hrd, i, j, i, j + 1);
                            String subResult = getDepthHrD(temp, cache, 3);
                            if (subResult != null) {
                                return getResult(temp[i][j], subResult);
                            }

                        }
                        return null;

                    }
                }
            }
            return null;
        }
    }

    /**
     * 获取结果
     *
     * @param current   当前移动位置
     * @param subResult
     * @return
     */
    private static String getResult(int current, String subResult) {
        //如果上次返回错误值，则继续返回错误值
        if (subResult.equals(ERROR_MESSAGE))
            return ERROR_MESSAGE;
        else if (subResult.substring(0, 1).equals("" + current))
            return subResult.substring(3);
        else
            return "" + current + "->" + subResult;
    }

    /**
     * 移动数字
     *
     * @param array
     * @param i
     * @param j
     * @param i2
     * @param j2
     * @return
     */
    private static int[][] doChange(int[][] array, int i, int j, int i2, int j2) {
        int[][] temp = arraycopy(array);
        temp[i][j] = temp[i2][j2];
        temp[i2][j2] = 0;
        return temp;
    }

    /**
     * 复制数组
     *
     * @param array
     * @return
     */
    private static int[][] arraycopy(int[][] array) {
        int[][] temp = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            temp[i] = new int[array[i].length];
            if (array[i].length >= 0) System.arraycopy(array[i], 0, temp[i], 0, array[i].length);
        }
        return temp;
    }

    /**
     * 将数组转化为字符串以“,”号隔开
     *
     * @param data 数组
     * @return 字符串
     */
    private static String getStringData(int[][] data) {
        StringBuilder result = new StringBuilder();
        for (int[] aData : data) {
            for (int anAData : aData) {
                result.append(anAData).append(",");
            }
        }
        return result.toString().substring(0, 17);
    }
}


