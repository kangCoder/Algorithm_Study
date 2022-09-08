/**
 * 휴게소 세우기
 * https://www.acmicpc.net/problem/1477
 */
package week11.baekjoon_1477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, L;
    static int[] serviceStation;

    public static int binarySearch() {
        int start = 1, end = L; // start 를 0으로 잡으면 mid 가 0이 되는 경우가 발생.

        while (start <= end) {
            int mid = (start + end) / 2; // 휴게소 사이의 거리

            int sum = 0; // mid 가 휴게소 사이의 거리라고 하면 sum 은 기존의 휴게소 사이에 mid 거리 간격으로 휴게소를 몇 개 놓을 수 있는지
            for (int i = 1; i < serviceStation.length; i++)
                sum += (serviceStation[i] - serviceStation[i - 1] - 1) / mid;

            if (sum > M)
                // M보다 크다면
                start = mid + 1;
            else
                end = mid - 1;
        }

        return start;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 현재 휴게소의 개수
        M = Integer.parseInt(st.nextToken()); // 더 지으려는 휴게소의 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        serviceStation = new int[N + 2]; // 휴게소의 개수 + (0, L)
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            serviceStation[i] = Integer.parseInt(st.nextToken());
        serviceStation[0] = 0;
        serviceStation[N + 1] = L;

        Arrays.sort(serviceStation);

        System.out.println(binarySearch());
    }
}
