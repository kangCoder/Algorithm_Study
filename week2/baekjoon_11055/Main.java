// 가장 큰 증가 부분 수열
// 수열 A의 증가 부분 수열 중 합이 제일 큰 것
package week2.baekjoon_11055;

import java.io.*;
import java.util.*;

public class Main {
    static int[] A, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        dp[1] = A[1];
        int max = dp[1];
        for (int i = 2; i <= N; i++) {
            dp[i] = A[i];
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j])
                    dp[i] = Math.max(dp[j] + A[i], dp[i]);
                if (dp[i] > max)
                    max = dp[i];
            }
        }

        System.out.println(max);
    }
}
