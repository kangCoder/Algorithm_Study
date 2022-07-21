/**
 * 야구
 * 1: 안타, 2: 2루타, 3: 3루타, 4: 홈런
 * 1번 선수부터 9번 선수까지 있고, 1번 선수를 4번 타자로 고정하고 라인업 구성
 * 각 선수들의 각 이닝에서의 결과는 정해져 있음
 * N이닝 경기에서 가장 많은 점수를 낼 수 있는 라인업을 짜서 최대 점수 출력하기
 */
package week4.baekjoon_17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] innings; // 전체 이닝에 해당하는 배열
    static int[] lineUp = new int[10]; // 1번~9번 선수들의 타순을 저장하는 배열
    static int[] player = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // 1번~9번 선수
    static boolean[] visited = new boolean[10]; // 1번~9번 선수들의 i번째 타순에 들어갔는지 여부
    static int max = 0; // 최대 득점 수를 저장, 갱신


    public static void dfs(int number) {
        if (number == 9) {
            int tmp = 45;
            for (int i = 1; i <= 8; i++)
                tmp -= lineUp[i];
            lineUp[number] = tmp;
            max = Math.max(max, playBall());
            return;
        }
        for (int i = 2; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (number != 4)
                    lineUp[number] = player[i];
                dfs(number + 1);
                visited[i] = false;
            }
        }
    }

    public static int playBall() {
        boolean[] inBase = new boolean[4]; // 각 베이스가 차있는지 확인하는 배열
        int inning = 1; // 1이닝부터 시작
        int order = 1; // 타자의 순서. 1번 타자부터 시작하니까

        int out = 0, score = 0; // 아웃개수. 3아웃이면 이닝 증가
        while (inning <= N) {
            switch (innings[inning][lineUp[order]]) {
                case 0: // 아웃
                    out++;
                    break;
                case 1: // 안타
                    if (inBase[3]) {
                        score++;
                        inBase[3] = false;
                    }
                    if (inBase[2]) {
                        inBase[2] = false;
                        inBase[3] = true;
                    }
                    if (inBase[1]) {
                        inBase[1] = false;
                        inBase[2] = true;
                    }
                    inBase[1] = true;
                    break;
                case 2: // 2루타
                    if (inBase[3]) {
                        score++;
                        inBase[3] = false;
                    }
                    if (inBase[2]) {
                        score++;
                        inBase[2] = false;
                    }
                    if (inBase[1]) {
                        inBase[1] = false;
                        inBase[3] = true;
                    }
                    inBase[2] = true;
                    break;
                case 3: // 3루타
                    if (inBase[3]) {
                        score++;
                        inBase[3] = false;
                    }
                    if (inBase[2]) {
                        score++;
                        inBase[2] = false;
                    }
                    if (inBase[1]) {
                        score++;
                        inBase[1] = false;
                    }
                    inBase[3] = true;
                    break;
                case 4: // 홈런
                    if (inBase[3]) {
                        score++;
                        inBase[3] = false;
                    }
                    if (inBase[2]) {
                        score++;
                        inBase[2] = false;
                    }
                    if (inBase[1]) {
                        score++;
                        inBase[1] = false;
                    }
                    score++;
                    break;
            }
            if (out >= 3) {
                inning++;
                out = 0;
                inBase[1] = inBase[2] = inBase[3] = false;
            }
            order = order % 9 + 1;
        }
        return score;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine()); // 이닝
        innings = new int[N + 1][10];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++)
                innings[i][j] = Integer.parseInt(st.nextToken());
        }

        lineUp[4] = player[1]; // 1번 선수는 4번 타자 고정
        visited[1] = true; // 1번 선수는 4번으로 고정이니까 타순에 들어갔음
        dfs(1);

        System.out.println(max);
    }
}
