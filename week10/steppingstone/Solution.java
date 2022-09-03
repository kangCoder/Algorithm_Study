/**
 * 징검다리 건너기
 * https://school.programmers.co.kr/learn/courses/30/lessons/64062
 */
package week10.steppingstone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static int solution(int[] stones, int k) {
        int answer = 0;
        int max = 200000000, min = 1; // stone 은 최소 1, 최대 2억임

        while (min <= max) {
            int mid = (max + min) / 2;
            if (check(mid, stones, k))
                min = mid + 1;
            else max = mid - 1;
        }
        answer = min - 1;
        return answer;
    }

    public static boolean check(int mid, int[] stones, int k) {
        int count = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] >= mid)
                count = 0;
            else
                count++;

            if (count == k) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] stones = new int[n]; // 디딤돌
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            stones[i] = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine()); // 한 번에 건너뛸 수 있는 최대 칸

        System.out.println(solution(stones, k));
    }
}
