/**
 * 최소 직사각형
 * N개의 명함의 크기를 입력받아
 * 모든 명함을 담을 수 있는 가장 작은 크기의 직사각형을 만들자.
 */
package week4.MinimumRectangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int solution(int[][] sizes) {
        int answer = 0;
        int maxDepth = 0;
        int maxLength = 0;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
            maxDepth = Math.max(maxDepth, sizes[i][0]);
            maxLength = Math.max(maxLength, sizes[i][1]);
        }
        answer = maxDepth * maxLength;
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N개의 명함
        int[][] card = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++)
                card[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(card));
    }
}
