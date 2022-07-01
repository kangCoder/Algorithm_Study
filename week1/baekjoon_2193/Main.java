// 이친수
// 이진수 중 특별한 성질을 갖는 수 -> 이친수
// 1. 0으로 시작하지 않음
// 2. 1이 연속으로 나타나지 않음
// 예시) 1, 10, 100, 1001, 10010 등
// N자리 이친수 개수 구하기
// 이것도 long으로 안해서 한 번 틀렸음
package week1.baekjoon_2193;

import java.util.*;

public class Main {
    static int N;
    static long[][] D;

    public static long C(long[][] D, int N) {
        D[1][0] = 0;
        D[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 1; j++) {
                if (j == 0) {
                    D[i][j] = D[i - 1][0] + D[i - 1][1];
                } else {
                    D[i][j] = D[i - 1][0];
                }
            }
        }
        long sum = D[N][0] + D[N][1];
        return sum;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        D = new long[N + 1][2];

        System.out.println(C(D, N));
    }
}
