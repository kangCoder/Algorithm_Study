// 가장 긴 증가하는 부분 수열
// 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열의 길이 구하기
// 예시) A = {10,20,10,30,20,50} 일 때, 가장 긴 증가하는 부분 수열의 길이는 4 (10,20,30,50)
package week1.baekjoon_11053;

import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static Vector<Integer> S = new Vector<>();

    // 탐색은 Binary Search로
    public static int BS(Vector<Integer> S, int start, int end, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (target <= S.get(mid)) end = mid;
            else start = mid + 1;
        }
        return start;
    }

    public static int C(int[] A, int N) {
        if (N == 1) return 1;
        S.add(A[0]);

        for (int i = 1; i < N; i++) {
            if (S.get(S.size() - 1) < A[i]) {
                S.add(A[i]);
            } else {
                int target = BS(S, 0, S.size() - 1, A[i]);
                S.set(target, A[i]);
            }
        }
        return S.size();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = scan.nextInt();

        System.out.println(C(A, N));

    }
}
