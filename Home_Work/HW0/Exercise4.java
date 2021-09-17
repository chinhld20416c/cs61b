public class Exercise4 {
    public static int[] posiSum(int[] m, int n) {
        for (int i = 0; i < m.length; i++) {
            if (m[i] < 0) {
                continue;  // Nếu là số âm sẽ bỏ qua và đến với vòng lặp tiếp theo
            }

            /* Cách 1
            int j = i + 1;
            while (j <= (i + n) && j < m.length) {  // Đảm bảo j chạy đến i + n và (i + n) < len(m)
                
                m[i] += m[j];  // m[i] = m[i] + m[j]
                j++;
            }
            */
            for (int j = i + 1; j <= i + n; j++) {
                if (j == m.length) {
                    break;  // indext j tối đa là len(m) - 1
                }
                m[i] += m[j];
            }
        }
        return m;
        
    }
    public static void main(String[] args) {
        int[] m = {1, -1, -1, 10, 5, -1};
        int n = 2;
        int [] res = posiSum(m, n);
        System.out.println(java.util.Arrays.toString(res));
    }
}
