/**
 * 운동
 * https://www.acmicpc.net/problem/1956
 */
package week9.baekjoon_1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int[][] city;
    static final int INF = 987654321;

    public static int solution() {
        // i에서 j로 가는 길이 없는 경우 특정 숫자로 초기화
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (city[i][j] == 0)
                    city[i][j] = INF;
            }
        }

        // 일단 각 노드까지의 최소 거리를 구한다.
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (i == j) continue;
                    if (city[i][j] > city[i][k] + city[k][j])
                        city[i][j] = city[i][k] + city[k][j];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (city[i][j] != INF && city[j][i] != INF) {
                    answer = Math.min(answer, city[i][j] + city[j][i]);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        city = new int[V + 1][V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            city[a][b] = c;
        }

        if (solution() == INF)
            System.out.println(-1);
        else
            System.out.println(solution());
    }
}
