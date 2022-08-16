package week8.Immigration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static long solution(int n, int[] times) {
        long answer = 0L;
        Arrays.sort(times);

        long start = 1, end = n * (long) times[times.length - 1];
        while (start <= end) {
            long sum = 0, mid = (start + end) / 2;
            for (int i = 0; i < times.length; i++)
                sum += mid / times[i];
            if (sum >= n) {
                end = mid - 1;
                answer = mid;
            } else
                start = mid + 1;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] times = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }


        System.out.println(solution(n, times));
    }
}
