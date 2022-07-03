package week1.baekjoon_11053;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;

    public static int C(int[] A, int N) {
        int max = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j] && dp[i] <= dp[j])
                    dp[i] = dp[j] + 1;
            }
            if (dp[max] < dp[i])
                max = i;
        }
        return dp[max];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        System.out.println(C(A, N));
    }
}
