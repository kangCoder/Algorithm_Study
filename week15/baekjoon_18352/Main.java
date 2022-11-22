/**
 * https://www.acmicpc.net/problem/18352
 */
package week15.baekjoon_18352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, X;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //도시의 개수
        M = Integer.parseInt(st.nextToken()); //도로의 개수
        K = Integer.parseInt(st.nextToken()); //거리 정보
        X = Integer.parseInt(st.nextToken()); //출발 도시 번호
        dist = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
            dist[i] = -1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
        }

        bfs();

        boolean check = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                check = true;
                System.out.println(i);
            }
        }

        if (!check) {
            System.out.println(-1);
        }
    }

    public static void bfs() {
        dist[X] = 0; //출발도시는 거리가 0

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(X);
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < map.get(cur).size(); i++) {
                int next = map.get(cur).get(i);

                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}
