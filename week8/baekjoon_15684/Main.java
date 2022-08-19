/**
 * 사다리 조작
 * 구현 + 백트래킹(완전탐색)
 */
package week8.baekjoon_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static boolean[][] list;
    static int answer = Integer.MAX_VALUE;
    static boolean flag = false;

    public static void backTracking(int count, int max) {
        if (count == max) {
            if (check())
                flag = true;
            return;
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (!list[i][j] && !list[i][j - 1] && !list[i][j + 1]) {
                    // 현재 위치를 기준으로 좌우에 가로줄이 설치되지 않은 경우 그 위치에 가로줄을 깔고 count 올린 다음 다시 백트래킹
                    list[i][j] = true;
                    backTracking(count + 1, max);
                    list[i][j] = false;
                }
            }
        }
    }

    public static boolean check() {
        // i행에서 시작해서 i행으로 끝나는지 확인
        for (int i = 1; i <= N; i++) {
            int garo = 1, sero = i;
            while (garo <= H) {
                if (list[garo][sero])
                    sero++; // sero ~ sero+1을 잇는 garo줄이 존재하므로 sero를 오른쪽으로 한 칸 이동
                else if (list[garo][sero - 1])
                    sero--; // sero-1 ~ sero를 잇는 garo줄이 존재하므로 sero를 왼쪽으로 한 칸 이동
                garo++;
            }

            if (sero != i)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로선의 수
        M = Integer.parseInt(st.nextToken()); // 가로선의 수
        H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 수

        list = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a][b] = true; // b~b+1를 잇는 a번째 가로줄에 가로선을 놓는다.
        }

        if (M == 0)
            answer = 0;
        else {
            for (int i = 0; i <= 3; i++) {
                flag = false;
                backTracking(0, i);
                if (flag) {
                    // flag가 true라는 것은 성공했다는 뜻.
                    answer = i;
                    break;
                }
            }
        }

        if (answer > 3)
            answer = -1;

        System.out.println(answer);
    }
}
