public class Main {
    static public void main(String[] arg) {
        System.out.print(getEgs());
    }

    private static int getEgs() {
        int floor = 100;
        int[] temp = new int[floor];
        temp[0] = 1;
        temp[1] = 2;
        for (int i = 2; i < floor; i++) {
            temp[i] = 1 + Math.max(i - 1, temp[0]);
            for (int j = 2; j <= i; j++)
                temp[i] = Math.min(1 + Math.max(i - j, temp[j - 1]), temp[i]);
        }
        return temp[floor - 1];
    }
}
