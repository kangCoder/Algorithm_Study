// 가장 긴 바이토닉 부분 수열
// LIS인데 바이토닉한 LIS를 구하기
// 바이토닉 : 그 수를 기준으로 왼쪽 오른쪽의 증가->감소나 감소->증가로 바뀌는 것이 없음
package week2.baekjoon_11054;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;

    public static int LIS(int k) {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++)
            dp[i] = 1;

        int max = 1;
        for (int i = 2; i <= k; i++) {
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j] && dp[i] <= dp[j])
                    dp[i] = dp[j] + 1;
            }
            if (dp[max] < dp[i])
                max = i;
        }
        return dp[max];
    }

    public static int LDS(int k) {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++)
            dp[i] = 1;

        int max = 1;
        for (int i = k + 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (A[i] < A[j] && dp[i] <= dp[j])
                    dp[i] = dp[j] + 1;
            }
            if (dp[max] < dp[i])
                max = i;
        }
        return dp[max];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        int[] lbs = new int[N + 1];
        int max = 1;
        for (int i = 1; i <= N; i++) {
            int k = i;
            if (k == 1) {
                lbs[k] = Math.max(LIS(k), LDS(k));
            } else if (k == N)
                lbs[k] = LDS(k);
            else {
                lbs[k] = LIS(k) + LDS(k) - 1;
            }
            if (lbs[max] < lbs[k])
                max = k;
        }

        System.out.println(lbs[max]);
    }
}
