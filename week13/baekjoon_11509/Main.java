/**
 * 풍선 맞추기 https://www.acmicpc.net/problem/11509
 */
package week13.baekjoon_11509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1000000 * 1000000 이라서 그냥 리스트 반복하면 시간초과.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] H = new int[N];
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        int[] arrowHeight = new int[Arrays.stream(H).max().getAsInt() + 2]; // 화살의 높이를 저장하는 배열
        int height = 0, count = 0;
        for (int i = 0; i < N; i++) {
            height = H[i];
            // 현재 높이보다 한 칸 위에 풍선이 있는지 없는지 판단
            if (arrowHeight[height + 1] == 0) {
                // 없다면 현재 높이가 가장 높은 풍선의 높이
                arrowHeight[height]++;
                count++;
            } else {
                arrowHeight[height + 1]--;
                arrowHeight[height]++;
            }
        }

        System.out.println(count);
    }
}
