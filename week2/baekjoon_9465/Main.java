// 스티커
// 스티커 2n개가 2행 n열로 배치된다
// 스티커를 뗄 때, 하나를 떼면 옆에 붙어있는 스티커들은 못 쓰게 됨
// 뗄 수 있는 스티커의 점수의 최댓값 구하기
package week2.baekjoon_9465;

import java.util.*;

public class Main {
    static int[][] score;
    static int[][] dp;
    static Scanner scan = new Scanner(System.in);

    public static int calculate() {
        int n = scan.nextInt();
        score = new int[n + 1][2];
        for (int j = 0; j < 2; j++) {
            for (int k = 1; k <= n; k++)
                score[k][j] = scan.nextInt();
        }

        dp = new int[n + 1][3];
        dp[1][0] = 0;
        dp[1][1] = score[1][0];
        dp[1][2] = score[1][1];
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + score[i][0];
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + score[i][1];
        }

        return Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]);

    }

    public static void main(String[] args) {
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            System.out.println(calculate());
        }
    }
}
