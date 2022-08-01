/**
 * 영역 구하기
 */
package week6_DFSBFS2.baekjoon_2583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] graph;
    static int count = 0;

    public static void DFS(int x, int y) {
        if (x > graph.length - 1 || y > graph[x].length - 1 || x < 0 || y < 0) return;

        graph[x][y] = true;
        count++;
        if (y < graph[x].length - 1 && !graph[x][y + 1]) DFS(x, y + 1);
        if (y > 0 && !graph[x][y - 1]) DFS(x, y - 1);
        if (x < graph.length - 1 && !graph[x + 1][y]) DFS(x + 1, y);
        if (x > 0 && !graph[x - 1][y]) DFS(x - 1, y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        graph = new boolean[M][N];

        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            for (int k = y1; k <= y2; k++)
                for (int l = x1; l <= x2; l++)
                    graph[k][l] = true;
        }
        // x와 y의 자리가 반대임

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!graph[i][j]) {
                    DFS(i, j);
                    result.add(count);
                    count = 0;
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for(int i : result) {
            System.out.print(i + " ");
        }
    }
}
