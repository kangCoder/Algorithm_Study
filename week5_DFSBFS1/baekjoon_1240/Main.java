/**
 * 노드사이의 거리
 * N개의 노드로 이루어진 노드가 주어지고, M개의 두 노드 쌍을 입력하면
 * 두 노드 사이의 거리를 출력하기
 */
package week5_DFSBFS1.baekjoon_1240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static LinkedList<Integer>[] list;
    static int[][] weighted;
    static boolean[] visited;
    static int answer;

    public static void DFS(int start, int end, int sum) {
        if (start == end) {
            answer = sum;
            return;
        }
        visited[start] = true;

        for (int i = 0; i < list[start].size(); i++) {
            int next = list[start].get(i);
            if (!visited[next]) {
                DFS(next, end, sum + weighted[start][next]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 첫째 줄에 노드의 개수 N과 거리를 알고 싶은 M개의 노드 쌍을 입력
        // 다음 N-1개의 줄에 트리 상의 연결된 두 점과 거리를 입력 (총 3개)
        // 그 다음 줄에는 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        list = new LinkedList[N + 1];
        for (int i = 0; i < N + 1; i++)
            list[i] = new LinkedList<>();
        weighted = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
            weighted[a][b] = value;
            weighted[b][a] = value;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            DFS(start, end, weighted[start][start]);
            System.out.println(answer);
            answer = 0;
            Arrays.fill(visited, false);
        }

    }
}
