/**
 * 이분 그래프
 * 그래프가 주어졌을 때, 이것을 이분 그래프로 나타낼 수 있는가?
 */
package practice.BFSDFS.baekjoon_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer>[] list;
    static int[] visited;
    static boolean ok;

    public static void DFS(int start, int group) {
        if (ok)
            return;

        visited[start] = group;
        for (int next : list[start]) {
            if (visited[start] == visited[next]) {
                ok = true;
                return;
            } else if (visited[next] == 0) {
                if (visited[start] == 1)
                    DFS(next, -1);
                else
                    DFS(next, 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            visited = new int[V + 1];
            list = new LinkedList[V + 1];
            for (int k = 0; k < V + 1; k++)
                list[k] = new LinkedList<>();

            int E = Integer.parseInt(st.nextToken());
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list[u].add(v);
                list[v].add(u);
            }

            boolean end = false;
            for (int j = 1; j < visited.length; j++) {
                if (ok) {
                    System.out.println("NO");
                    end = true;
                    break;
                }
                if (visited[j] == 0) {
                    DFS(j, 1);
                }
            }
            if (!end)
                System.out.println("YES");

            Arrays.fill(visited, 0);
            ok = false;
        }
    }
}
