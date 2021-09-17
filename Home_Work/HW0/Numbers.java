public class Numbers {
    /** Return the maximum value from m */
    public static int max(int[] m) {
        int maxNum = m[0];
        /** for (initialization; termination; increment) {
         *      statements;
         *      Dòng lệnh ở initialliztion chỉ chạy một lần ở lần lặp đầu tiên
         * } */
        for (int i = 1; i < m.length; i++) {
            
            if (maxNum < m[i]) {
                maxNum = m[i];
            }
        }
        // System.out.println(i);  variable local in for loop
        // String j = "ok";
        // System.out.println(j);
        return maxNum;
    }
    /** Return the sum using while loop */
    public static int sum(int[] m) {
        int i = 0;
        int sum = 0;
        while (i < m.length) {
            sum = sum + m[i];
            i = i +1;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        int[] arrNum = new int[]{9, 2, 15, 2, 22, 44, 6};  // Khai bao mang kieu int va khoi tao cac phan tu cua mang
        int res = max(arrNum);
        System.out.println(res);
        System.out.println(sum(arrNum));

    }
}
