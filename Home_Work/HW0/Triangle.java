public class Triangle {

    public static void makeTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        makeTriangle(9);
        /*
        int i = 0;
        while (i < 7) {
            int j = 0;
            while (j <= i) {
                System.out.print("*");
                j++;
            }
            i++;
            System.out.println();
        }
        */
    }
}
