/**
 * Catching
 */
public class Catching {

    public static void main(String[] args) {
        String nguoiChoi1 = "Rua";
        String nguoiChoi2 = "Tho";
        double viTri1 = 0;
        double viTri2 = 100;
        double speed1 = 20;
        double speed2 = 10;
        double timeToCatch = 0;
        while (viTri1 < viTri2) {
            System.out.println(" Tai thoi diem: " + timeToCatch);
            System.out.println(nguoiChoi1 + " dang o vi tri " + viTri1);
            System.out.println(nguoiChoi2 + " dang o vi tri " + viTri2); 

            double period = (viTri2 - viTri1) / speed1;
            timeToCatch = timeToCatch + period;
            viTri1 = viTri1 + period * speed1;
            viTri2 = viTri2 + period * speed2;
        }

    }
}