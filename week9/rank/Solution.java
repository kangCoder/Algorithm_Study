/**
 * 순위
 * https://school.programmers.co.kr/learn/courses/30/lessons/49191
 */
package week9.rank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < results.length; i++)
            graph[results[i][0]][results[i][1]] = 1;

        // a가 b를 이기고 b가 c를 이기면 a는 c를 이긴다.
        // j : 거쳐가는 노드, i : 시작 노드, k : 도착 노드
        for (int j = 0; j <= n; j++) {
            for (int i = 0; i <= n; i++) {
                for (int k = 0; k <= n; k++) {
                    if (graph[i][j] == 1 && graph[j][k] == 1)
                        graph[i][k] = 1;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int game = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == 1 || graph[j][i] == 1)
                    game++; // i와 j 사이의 결과가 존재한다는 뜻
            }
            if (game == n - 1)
                answer++; // 자기 자신을 제외한 모든 상대와 결과가 났다는 뜻이므로 순위가 확정이다.
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] results = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int win = Integer.parseInt(st.nextToken());
            int lose = Integer.parseInt(st.nextToken());
            results[i][0] = win;
            results[i][1] = lose;
        }

        System.out.println(solution(n, results));
    }
}
