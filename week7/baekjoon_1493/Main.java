/**
 * 박스 채우기
 */
package week7.baekjoon_1493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int length, width, height;
    static int[] cube;
    static boolean isOk = true;

    public static int solution(int l, int w, int h) {
        // l,w,h 중 하나라도 0이 되면 박스의 부피가 0
        if (l == 0 || w == 0 || h == 0) return 0;

        // 제일 작은 변의 길이를 구해 제일 작은 큐브부터 넣는다.
        int tmp = Math.min(l, Math.min(w, h));

        int t = (int) (Math.log(tmp) / Math.log(2)) + 1;
        while (t-- > 0) {
            if (N <= t || cube[t] == 0) continue;
            cube[t]--;
            int T = (int) Math.pow(2, t);

            // 하나의 큐브를 집어넣으면 박스가 3개의 부분으로 나뉜다. 그 3개의 부분 박스에 대해 재귀 돌리기
            return solution(l, w - T, h) + solution(l - T, T, h) + solution(T, T, h - T) + 1;
        }
        // 만약 위에서 return 하지 못했다면 박스를 다 못채운다는 뜻.
        isOk = false;
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());
        cube = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            cube[A] = B;
        }

        int answer = solution(length, width, height);
        if (isOk) System.out.println(answer);
        else System.out.println(-1);
    }
}
