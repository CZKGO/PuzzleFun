import java.util.ArrayList;
import java.util.List;

public class Main {

    static public void main(String[] arg) {
        int[] arry = new int[]{81, 69, 80, 79, 93, 7, 36, 86, 80, 23, 32, 21, 23, 32, 36, 48, 56, 49, 55, 58, 89, 36, 97, 28, 89, 60, 39, 99, 21, 15};
        int[] linkedArry = shellSort(arry);
        for (int num : linkedArry) {
            System.out.print(num + " ");
        }
    }

    /**
     * 希尔排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] shellSort(int[] arry) {
        for (int gap = arry.length / 2; gap >= 1; gap /= 2) {
            for (int i = 1; i < gap; i++) {
                for (int j = i; j < arry.length; j += gap) {
                    int temp = arry[i];
                    int k = j - i;
                    while (k >= i - 1 && temp < arry[k]) {
                        arry[k + i] = arry[k];
                        k -= i;
                    }

                    arry[k + i] = temp;
                }

            }

            for (int num : arry) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        return arry;
    }

    /**
     * 选择排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] selectionSort(int[] arry) {
        for (int i = 0; i < arry.length; i++) {
            int minIndex = i;
            for (int j = i; j < arry.length; j++) {
                if (arry[j] < arry[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arry[i];
            arry[i] = arry[minIndex];
            arry[minIndex] = temp;
        }
        return arry;
    }

    /**
     * 块排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] blockSort(int[] arry) {
        return new int[0];
    }

    /**
     * 图书馆排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] librarySort(int[] arry) {
//        int[] tempArry = new int[arry.length * 2];
//        for (int i = 1; i < arry.length; i *= 2) {
//
//        }
        return arry;

    }

    /**
     * 侏儒排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] gnomeSort(int[] arry) {
        for (int i = 0; i < arry.length - 1; ) {
            if (i >= 0 && arry[i + 1] < arry[i]) {
                int temp = arry[i];
                arry[i] = arry[i + 1];
                arry[i + 1] = temp;
                i--;
            } else {
                i++;
            }
        }
        return arry;
    }

    /**
     * 基数排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] radixSort(int[] arry) {
        int max = Integer.MIN_VALUE;
        for (int num : arry) {
            if (num > max) {
                max = num;
            }
        }
        for (int n = 1; max / n > 0; n = n * 10) {
            int indexArry[] = new int[10];
            for (int anArry : arry) {
                indexArry[anArry / n % 10]++;
            }
            for (int i = 1; i < 10; i++) {
                indexArry[i] += indexArry[i - 1];
            }
            int tempArry[] = new int[arry.length];
            for (int i = arry.length - 1; i >= 0; i--) {
                int num = arry[i] / n % 10;
                tempArry[--indexArry[num]] = arry[i];
            }
            System.arraycopy(tempArry, 0, arry, 0, arry.length);
        }
        return arry;
    }

    /**
     * 鸽巢排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] pigeonholeSort(int[] arry) {
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
     * 排序二叉树排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] binaryTreeSort(int[] arry) {
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
        binaryTreeNode.node = arry[0];
        for (int i = 1; i < arry.length; i++) {
            insertNode(binaryTreeNode, arry[i]);
        }
        sortBinaryTree(arry, 0, binaryTreeNode);
        return arry;
    }

    private static class BinaryTreeNode {
        BinaryTreeNode leftTree;
        int node;
        BinaryTreeNode rightTree;
    }

    private static void insertNode(BinaryTreeNode binaryTreeNode, int num) {
        if (binaryTreeNode.node > num) {
            if (binaryTreeNode.leftTree == null) {
                BinaryTreeNode subTreeNode = new BinaryTreeNode();
                subTreeNode.node = num;
                binaryTreeNode.leftTree = subTreeNode;
            } else {
                insertNode(binaryTreeNode.leftTree, num);
            }
        } else {
            if (binaryTreeNode.rightTree == null) {
                BinaryTreeNode subTreeNode = new BinaryTreeNode();
                subTreeNode.node = num;
                binaryTreeNode.rightTree = subTreeNode;
            } else {
                insertNode(binaryTreeNode.rightTree, num);
            }
        }
    }

    private static int sortBinaryTree(int[] arry, int index, BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.leftTree != null) {
            index = sortBinaryTree(arry, index, binaryTreeNode.leftTree);
        }
        arry[index++] = binaryTreeNode.node;
        if (binaryTreeNode.rightTree != null) {
            index = sortBinaryTree(arry, index, binaryTreeNode.rightTree);
        }
        return index;
    }

    /**
     * 原地归并算法
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] inPlaceMergeSort(int[] arry) {
        for (int itemNumbers = 1; itemNumbers < arry.length; itemNumbers = itemNumbers * 2) {
            int couple = (arry.length + itemNumbers) / 2;
            for (int i = 0; i < couple; i++) {
                int start = itemNumbers * i * 2;
                if (start < arry.length) {
                    int start1 = start;
                    int end1 = start1 + itemNumbers - 1;
                    int start2 = end1 + 1;
                    if (end1 >= arry.length || start2 >= arry.length) {
                        break;
                    }
                    int end2 = start2 + itemNumbers - 1 >= arry.length ? arry.length - 1 : start2 + itemNumbers - 1;
                    while (start1 <= end1 && start2 <= end2) {
                        while (start1 <= end1 && arry[start1] < arry[start2]) {
                            start1++;
                        }
                        while (start2 <= end2 && arry[start1] >= arry[start2]) {
                            start2++;
                        }
                        if (start1 < end1) {
                            exchange(arry, start1, end1, start2 - 1);
                            start1 = start1 + start2 - end1;
                            end1 = end1 + start2 - end1 - 1;
                        }
                    }
                }
            }
        }
        return arry;
    }

    private static void exchange(int[] arry, int start, int mid, int end) {
        reverse(arry, start, mid);
        reverse(arry, mid + 1, end);
        reverse(arry, start, end);
    }

    private static void reverse(int[] arry, int start, int end) {
        while (start < end) {
            int temp = arry[start];
            arry[start++] = arry[end];
            arry[end--] = temp;
        }
    }

    /**
     * 迭代形式的归并排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] iterationMergeSort(int[] arry) {
        int[] resultArry = new int[arry.length];
        for (int itemNumbers = 1; itemNumbers < arry.length; itemNumbers = itemNumbers * 2) {
            int couple = (arry.length + itemNumbers) / 2;
            for (int i = 0; i < couple; i++) {
                int start = itemNumbers * i * 2;
                if (start < arry.length) {
                    int start1 = start;
                    int end1 = start1 + itemNumbers - 1;
                    int start2 = end1 + 1;
                    if (end1 > arry.length || start2 > arry.length) {
                        break;
                    }
                    int end2 = start2 + itemNumbers - 1 > arry.length ? arry.length - 1 : start2 + itemNumbers - 1;
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
                }
            }
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
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                arry[index++] = i + min;
            }
        }
        return arry;
    }

    /**
     * 桶排序
     *
     * @param arry 无序数组
     * @return 有序数组
     */
    private static int[] bucketSort(int[] arry) {
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
        int bucketSize = (max - min) / arry.length + 1;
        List<List<Integer>> bucketList = new ArrayList<>();
        for (int ignored : arry) {
            bucketList.add(new ArrayList<>());
        }
        for (int anArry : arry) {
            List<Integer> bucket = bucketList.get((anArry - min) / bucketSize);
            for (int i = 0; i <= bucket.size(); i++) {
                if (i == bucket.size()) {
                    bucket.add(anArry);
                    break;
                } else if (anArry < bucket.get(i)) {
                    bucket.add(i, anArry);
                    break;
                }
            }

        }
        int index = 0;
        for (List<Integer> bucket : bucketList) {
            for (int num : bucket) {
                arry[index++] = num;
            }
        }
        return arry;
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


