// 합분해
// 0~N까지의 정수 K개의 합으로 N을 표현할 수 있는 경우의 수
package week1.baekjoon_2225;

import java.util.*;

public class Main {
    static int N, K;
    static long[][] dp;

    public static long C() {
        dp = new long[K + 1][N + 1];
        for (int i = 1; i <= K; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++)
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
        }

        return dp[K][N];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        K = scan.nextInt();

        System.out.println(C());
    }
}
