// 타일 채우기
// 3*N의 타일을 1*2, 2*1의 타일로 채우는 경우의 수 구하기
package week2.baekjoon_2133;

import java.io.*;

public class Main {
    static int N;
    static int[] dp;

    public static int C() {
        if (N % 2 == 1) return 0;
        if (N == 2) return 3;

        dp[2] = 3;
        for (int i = 4; i <= N; i += 2) {
            dp[i] += 3 * dp[i - 2];
            for (int j = 4; j <= i; j += 2)
                dp[i] += (2 * dp[i - j]);
            dp[i] += 2;
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        System.out.println(C());
    }
}
