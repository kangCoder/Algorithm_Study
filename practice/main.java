package practice;

public class main {
    static int[] FF;

    public static int F(int n) {
        FF[0] = 0; FF[1] = 1;
        for (int i = 2; i < FF.length; i++)
            FF[i] = FF[i - 1] + FF[i - 2];
        return FF[n];
    }

    public static void main(String[] args) {
        int n = 10;
        FF = new int[n + 1];
        System.out.println(F(n));
    }
}
