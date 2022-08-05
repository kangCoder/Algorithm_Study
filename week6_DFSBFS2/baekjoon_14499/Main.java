/**
 * 주사위 굴리기
 * 지도에서 주사위 굴려서 굴릴 때마다 주사위 윗면 출력하기
 */
package week6_DFSBFS2.baekjoon_14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // N : 지도의 가로 크기, M : 지도의 세로 크기
    static int x, y; // 주사위의 좌표
    static int[][] map; // 지도
    static int[] order; // 명령 모음
    static int[] dice = new int[7]; // 주사위 초기화 -> 초기 상태 : 모든 면 0

    public static void moveDice(int dir) {
        // 1: 동쪽, 2: 서쪽, 3: 북쪽, 4: 남쪽
        switch (dir) {
            case 1:
                if (y + 1 > M - 1) break;
                east();
                System.out.println(dice[1]);
                break;
            case 2:
                if (y - 1 < 0) break;
                west();
                System.out.println(dice[1]);
                break;
            case 3:
                if (x - 1 < 0) break;
                north();
                System.out.println(dice[1]);
                break;
            case 4:
                if (x + 1 > N - 1) break;
                south();
                System.out.println(dice[1]);
                break;
        }
    }

    public static void east() {
        y = y + 1;
        int tmp1 = dice[1], tmp3 = dice[3], tmp4 = dice[4], tmp6 = dice[6];
        dice[1] = tmp4;
        dice[3] = tmp1;
        dice[4] = tmp6;
        dice[6] = tmp3;

        if (map[x][y] != 0) {
            dice[6] = map[x][y];
            map[x][y] = 0;
        } else {
            map[x][y] = dice[6];
        }
    }

    public static void west() {
        y = y - 1;
        int tmp1 = dice[1], tmp3 = dice[3], tmp4 = dice[4], tmp6 = dice[6];
        dice[1] = tmp3;
        dice[3] = tmp6;
        dice[4] = tmp1;
        dice[6] = tmp4;

        if (map[x][y] != 0) {
            dice[6] = map[x][y];
            map[x][y] = 0;
        } else {
            map[x][y] = dice[6];
        }
    }

    public static void north() {
        x = x - 1;
        int tmp1 = dice[1], tmp2 = dice[2], tmp5 = dice[5], tmp6 = dice[6];
        dice[1] = tmp5;
        dice[2] = tmp1;
        dice[5] = tmp6;
        dice[6] = tmp2;

        if (map[x][y] != 0) {
            dice[6] = map[x][y];
            map[x][y] = 0;
        } else {
            map[x][y] = dice[6];
        }
    }

    public static void south() {
        x = x + 1;
        int tmp1 = dice[1], tmp2 = dice[2], tmp5 = dice[5], tmp6 = dice[6];
        dice[1] = tmp2;
        dice[2] = tmp6;
        dice[5] = tmp1;
        dice[6] = tmp5;

        if (map[x][y] != 0) {
            dice[6] = map[x][y];
            map[x][y] = 0;
        } else {
            map[x][y] = dice[6];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 주사위의 초기 좌표
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken()); // 명령의 개수
        order = new int[K];

        // 지도에 숫자 채우기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            order[i] = Integer.parseInt(st.nextToken());

        // 초기 상태 : 초기 좌표가 0이 아니면 주사위의 바닥면(6)에 좌표가 복사되고 좌표는 0이 된다.
        // 반대로 초기 좌표가 0이면 주사위의 바닥면(6)의 숫자가 칸에 복사된다.
        if (map[x][y] != 0) {
            dice[6] = map[x][y];
            map[x][y] = 0;
        } else {
            map[x][y] = dice[6];
        }

        for (int i = 0; i < K; i++) {
            //System.out.println("x좌표 : " + x + ", y좌표 : " + y);
            moveDice(order[i]);
        }
    }
}
