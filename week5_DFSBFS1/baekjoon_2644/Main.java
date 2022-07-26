/**
 * 촌수 계산
 * 서로 다른 두 사람의 촌수를 계산
 * 촌수를 계산할 수 없으면 -1 반환
 */
package week5_DFSBFS1.baekjoon_2644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer>[] list;
    static boolean[] visited;
    static int result = -1;

    public static void DFS(int start, int end, int count) {
        if (start == end) {
            result = count;
            return;
        }
        visited[start] = true;

        for (int i = 0; i < list[start].size(); i++) {
            int next = list[start].get(i);
            if (!visited[next])
                DFS(next, end, count + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 사람의 수
        list = new LinkedList[n + 1];
        for (int i = 0; i < n + 1; i++)
            list[i] = new LinkedList<>();
        visited = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int child = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            list[child].add(parent);
            list[parent].add(child);
        }

        DFS(start, end, 0);
        System.out.println(result);
    }
}
