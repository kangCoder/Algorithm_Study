/**
 * 색 상환
 * https://www.acmicpc.net/problem/2482
 */
package week11.baekjoon_2482;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, K;
    static int[][] dp;
    static final int MOD = 1000000003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 4~1000
        K = Integer.parseInt(br.readLine()); // 1~N

        dp = new int[N + 1][N + 1];

        //Base
        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1; // 아무 색도 고르지 않는 것도 경우의 수 중 하나. 이 점을 고려를 안했네...
        }

        //Step
        // i = 2까지는 답이 나올 수 없으므로 3부터 시작
        for (int i = 3; i <= N; i++) {
            // 뽑을 수 있는 색의 개수는 최대 i / 2개 이므로 반복문도 i/2까지만 돌려도 된다.
            for (int j = 2; j <= (i + 1) / 2; j++)
                // i번쨰 색을 뽑지 않는 경우 + i번째 색을 뽑지 않는 경우
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
        }

        System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % MOD);

    }
}
