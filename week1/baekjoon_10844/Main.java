// 쉬운 계단 수
// 길이가 N인 계단 수(인접한 모든 자리의 차가 1인 수)의 개수를 구하기
// 오버플로우에 대한 공부가 필요할듯....
package week1.baekjoon_10844;

import java.util.Scanner;

public class Main {
    static int N;
    static long[][] D;

    public static long C(long[][] D, int N) {
        D[1][0] = 0;
        for (int i = 1; i <= 9; i++)
            D[1][i] = 1;
        // N이 1일 때 올 수 있는 수들 -> 0을 제외하고 1~9까지는 1로 초기화 (기저 상태)

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    D[i][j] = D[i - 1][j + 1] % 1000000000;
                }
                else if (j == 9) {
                    D[i][j] = D[i - 1][j - 1] % 1000000000;
                } else {
                    D[i][j] = D[i - 1][j - 1] + D[i - 1][j + 1] % 1000000000;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += (D[N][i] % 1000000000);
        }
        return sum % 1000000000;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt(); // 1<=N<=100
        D = new long[N + 1][10];

        System.out.println(C(D, N));

    }
}
