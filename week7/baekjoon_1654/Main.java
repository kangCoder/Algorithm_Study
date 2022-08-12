/**
 * 랜선 자르기
 * 길이가 제각각인 K개의 랜선에서 길이가 모두 같은 N개의 랜선으로 만든다.
 * N개의 랜선에서 랜선 하나의 최대 길이?
 */
package week7.baekjoon_1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] haveLan;
    static int K, N;

    public static boolean solution(long x) {
        // 랜선의 길이가 x일 때, 랜선이 N개 이상일 수 있는지 판별
        long cur = 0;
        for (int i = 0; i < K; i++)
            cur += haveLan[i] / x; // 현재 가지고 있는 랜선을 길이 x로 다 잘라본다.
        return cur >= N; // N개가 넘는지 판별
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선의 수
        haveLan = new int[K];
        N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 수

        for (int i = 0; i < K; i++)
            haveLan[i] = Integer.parseInt(br.readLine());

        long start = 1, end = 0x7fffffff;
        while (start < end) {
            long mid = (start + end + 1) / 2; // start와 end의 차이가 1이 날 경우 무한루프에 빠질 수 있어서 1을 더해줘야 함
            if (solution(mid)) start = mid;
            else end = mid - 1;
        }

        System.out.println(start);
    }
}
