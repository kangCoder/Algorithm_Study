// 가장 긴 증가하는 부분 수열 4
// LIS를 구하는데 길이가 아닌 LIS 자체를 출력하기
package week1.baekjoon_14002;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;
    static int[] A;
    static int N;

    public static int C() {
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

    public static void BackTracking(int max) {
        Stack<Integer> st = new Stack<>();
        int index = max;
        for (int i = dp.length - 1; i >= 1; i--) {
            if (dp[i] == index) {
                st.push(A[i]);
                index--;
            }
        }

        while (!st.isEmpty()) {
            System.out.print(st.lastElement() + " ");
            st.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        System.out.println(C());
        BackTracking(C());
    }
}
