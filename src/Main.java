public class Main {

    static public void main(String[] arg) {
        int[] arry = new int[]{81, 69, 80, 79, 93, 7, 36, 86, 80, 23, 32, 21, 23, 32, 36, 48, 56, 49, 55, 58, 89, 36, 97, 28, 89, 60, 39, 99, 21, 15};
        int[] linkedArry = iterationMergeSort(new int[]{81, 69, 80});
        for (int num : linkedArry) {
            System.out.print(num + " ");
        }
    }

    /**
     * 迭代形式的归并排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] iterationMergeSort(int[] arry) {
        int itemNumbers = 1;
        int couple = arry.length>> 1;
        int[] resultArry = new int[arry.length];
        while (itemNumbers < arry.length) {
            int start = 0;
            for (int i = 0; i < couple; i++) {
                int start1 = start, end1 = start1 + itemNumbers - 1;
                int start2 = end1 + 1, end2 = start2 + itemNumbers - 1;
                int index = start1;
                while (start1 <= end1 && start2 <= end2) {
                    if (arry[start1] < arry[start2]) {
                        resultArry[index] = arry[start1++];
                    } else {
                        resultArry[index] = arry[start2++];
                    }
                    index++;
                }
                while (start1 <= end1) {
                    resultArry[index++] = arry[start1++];
                }
                while (start2 <= end2) {
                    resultArry[index++] = arry[start2++];
                }
                System.arraycopy(resultArry, start, arry, start, end2 - start + 1);
                start = end2 + 1;
            }
            itemNumbers = itemNumbers * 2;
            couple = couple >> 1;
        }
        return arry;
    }

    /**
     * 归并排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] recursiveMergeSort(int[] arry) {
        int start = 0;
        int end = arry.length - 1;
        int[] resultArry = new int[arry.length];
        return mergeSortRecursive(arry, start, end, resultArry);
    }

    /**
     * 递归形式归并排序
     *
     * @param arry       无序数组
     * @param start      排序开始位置
     * @param end        排序结束位置
     * @param resultArry 局部有序数组
     * @return 局部有序数组
     */
    private static int[] mergeSortRecursive(int[] arry, int start, int end, int[] resultArry) {
        if (start < end) {
            int linkedLength = end - start;
            int start1 = start, end1 = (linkedLength >> 1) + start;
            int start2 = end1 + 1;
            mergeSortRecursive(arry, start1, end1, resultArry);
            mergeSortRecursive(arry, start2, end, resultArry);
            int index = start;
            while (start1 <= end1 && start2 <= end) {
                if (arry[start1] < arry[start2]) {
                    resultArry[index] = arry[start1++];
                } else {
                    resultArry[index] = arry[start2++];
                }
                index++;
            }
            while (start1 <= end1) {
                resultArry[index++] = arry[start1++];
            }
            while (start2 <= end) {
                resultArry[index++] = arry[start2++];
            }
            System.arraycopy(resultArry, start, arry, start, linkedLength + 1);
        }
        return arry;
    }

    /**
     * 鸡尾酒排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] cocktailSort(int[] arry) {
        int left = 0;
        int right = arry.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (arry[i + 1] < arry[i]) {
                    int temp = arry[i + 1];
                    arry[i + 1] = arry[i];
                    arry[i] = temp;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (arry[i - 1] > arry[i]) {
                    int temp = arry[i - 1];
                    arry[i - 1] = arry[i];
                    arry[i] = temp;
                }
            }
            left++;
        }
        return arry;
    }

    /**
     * 计数排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] countingSort(int[] arry) {
        int max = arry[0];
        int min = arry[0];
        for (int i = 1; i < arry.length; i++) {
            if (arry[i] > max) {
                max = arry[i];
            }
            if (arry[i] < min) {
                min = arry[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int num : arry) {
            count[num - min]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int[] tempArry = new int[arry.length];
        for (int number : arry) {
            tempArry[--count[number - min]] = number;
        }
        return tempArry;
    }

    /**
     * 插入排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] insertionSort(int[] arry) {
        for (int i = 1; i < arry.length; i++) {
            int temp = arry[i];
            int j = i - 1;
            while (j >= 0 && temp < arry[j]) {
                arry[j + 1] = arry[j];
                j--;
            }
            arry[j + 1] = temp;

        }
        return arry;
    }

    /**
     * 冒泡排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] bubbleSort(int[] arry) {
        for (int i = 0; i < arry.length; i++) {
            for (int j = 1; j < arry.length - i; j++) {
                if (arry[j] < arry[j - 1]) {
                    int temp = arry[j];
                    arry[j] = arry[j - 1];
                    arry[j - 1] = temp;
                }
            }
        }
        return arry;
    }

}


