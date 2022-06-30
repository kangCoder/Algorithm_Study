// 2xn 타일링
// 마지막에 올 타일을 생각했을 때 올 수 있는 경우의 수는 n-1개와 n-2개밖에 없음
package week1.baekjoon_11726;

import java.util.*;

public class Main {
    static int[] dp;

    public static int C(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (dp[n] != 0) return dp[n];
        dp[n] = (C(n - 1) + C(n - 2)) % 10007;
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        dp = new int[n + 1];

        System.out.println(C(n));
    }
}
