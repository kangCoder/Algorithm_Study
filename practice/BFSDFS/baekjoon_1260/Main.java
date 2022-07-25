/**
 * DFS와 BFS
 * 단순 그래프 탐색 구현
 */
package practice.BFSDFS.baekjoon_1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static LinkedList<Integer> list[];
    static boolean[] visitedDFS;
    static boolean[] visitedBFS;

    public static void DFS(int V) {
        if (visitedDFS[V]) return; // 노드 V를 이미 방문했다면 돌아가기
        visitedDFS[V] = true; // 방문하지 않았다면 방문 표시
        System.out.print(V + " ");
        for (int i = 0; i < list[V].size(); i++) {
            int next = list[V].get(i); // 노드 V와 연결돼있는 다음 노드
            DFS(next); // 재귀 호출로 V와 연결돼있는 next와 연결돼있는 .... 끝까지 타고 간다
        }
    }

    public static void BFS(int V) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(V);
        visitedBFS[V] = true;
        // 큐에 V를 담고 방문 표시 해주기 -> 큐의 초기 상태 : 시작 노드만 들어가있음

        while (!queue.isEmpty()) {
            int cur = queue.peek();
            queue.remove();
            System.out.print(cur + " ");
            // 큐의 맨 앞 노드를 빼면서 출력

            for (int i = 0; i < list[cur].size(); i++) {
                int next = list[cur].get(i); // 현재 노드 cur과 연결돼있는 노드 next
                if (!visitedBFS[next]) {
                    queue.add(next);
                    visitedBFS[next] = true;
                    // next를 방문하지 않았다면 큐에 넣고 방문 표시
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 노드의 개수

        list = new LinkedList[N + 1];
        for (int i = 0; i < N + 1; i++)
            list[i] = new LinkedList();

        visitedDFS = new boolean[N + 1];
        visitedBFS = new boolean[N + 1];

        int M = Integer.parseInt(st.nextToken()); // 엣지의 개수
        int V = Integer.parseInt(st.nextToken()); // 시작 노드의 번호

        // 노드의 번호는 1부터 N까지, 방문할 수 있는 노드가 여러 개면 번호가 작은 것부터, 양방향 그래프.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        // 노드의 번호가 작은 것부터 방문해야 하므로 정렬을 따로 해줘야 함
        for (int i = 0; i <= N; i++)
            Collections.sort(list[i]);

        DFS(V);
        System.out.println();
        BFS(V);
        System.out.println();
    }
}
