public class Ve_Tam_Giac {
    public static void TamGiac() {
        int a = 5;

        for (int i = 0; i < a; i = i + 1) {
            for (int j = 0; j <= i; j = j + 1) {
                System.out.print("*");
            }
            System.out.println();
          }
    }
    public static void main(String[] args) {
        TamGiac();
    }
}