/**
 * 11724 연결 요소 개수 구하기
 * 무방향 그래프가 주어졌을 때, 그 그래프의 Connected Component의 개수 구하기
 * 서로 연결돼어있는, 즉 서로에게 갈 수 있는 path가 있는 노드들의 묶음의 개수 구하기
 */
package practice.BFSDFS.baekjoon_11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer>[] list;
    static boolean[] visited;
    static int count = 0;

    public static void DFS(int start) {
        if (visited[start]) return;
        visited[start] = true;
        for (int i = 0; i < list[start].size(); i++) {
            int next = list[start].get(i);
            DFS(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 노드의 개수
        int M = Integer.parseInt(st.nextToken()); // 엣지의 개수
        if (M == 0)
            System.out.println(N);
        else {
            list = new LinkedList[N + 1];
            for (int i = 0; i <= N; i++)
                list[i] = new LinkedList<>();
            visited = new boolean[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list[u].add(v);
                list[v].add(u);
            }

            for (int i = 1; i < visited.length; i++) {
                if (!visited[i]) {
                    DFS(i);
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
