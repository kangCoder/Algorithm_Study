// 1, 2, 3 더하기
// 점화식 구하기 쉬웠던 문제
package week1.baekjoon_9095;

import java.util.*;

public class Main {
    static int[] dp;

    public static int C(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        if (dp[n] != 0) return dp[n];
        dp[n] = C(n - 1) + C(n - 2) + C(n - 3);
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for (int i = 0; i < T; i++) {
            int n = scan.nextInt(); // 1 <= n <= 11
            dp = new int[n + 1];
            System.out.println(C(n));
        }
    }
}
