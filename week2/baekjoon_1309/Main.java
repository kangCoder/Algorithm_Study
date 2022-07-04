// 동물원
// 2xN 우리에 사자를 가로 세로 안 겹치게 가두는 경우의 수
// 마지막에 9901 안나눠준 것 때문에 한 번 틀림
package week2.baekjoon_1309;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][3];

        dp[1][0] = dp[1][1] = dp[1][2] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }

        long sum = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
        System.out.println(sum);
    }
}
