/**
 * 다리 만들기
 * https://www.acmicpc.net/problem/2146
 */
package week9.baekjoon_2146;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N; // 지도의 한 변의 길이
    static int[][] map; // 지도
    static boolean[][] visited; // 지도 방문 여부
    static int areaNum = 2;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = Integer.MAX_VALUE;

    public static class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static void makeArea(int i, int j) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i, j, 0));
        visited[i][j] = true;
        map[i][j] = areaNum;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int x = node.x + dx[dir];
                int y = node.y + dy[dir];
                if (x >= 0 && x < N && y >= 0 && y < N && !visited[x][y] && map[x][y] == 1) {
                    visited[x][y] = true;
                    map[x][y] = areaNum;
                    queue.add(new Node(x, y, 0));
                }
            }
        }
        areaNum++;
    }

    public static void BFS(int i, int j) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i, j, 0));
        visited[i][j] = true;
        int curArea = map[i][j];

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int x = node.x + dx[dir];
                int y = node.y + dy[dir];
                if (x >= 0 && x < N && y >= 0 && y < N && !visited[x][y]) {
                    visited[x][y] = true;
                    if (map[x][y] == 0) {
                        // 바다를 만남
                        queue.add(new Node(x, y, node.distance + 1));
                    } else if (map[x][y] == curArea) {
                        // 동일한 지역을 만남
                        queue.add(new Node(x, y, 0));
                    } else {
                        // 새로운 지역을 만남
                        answer = Math.min(answer, node.distance);
                    }
                }
            }
        }
    }

    public static void solution() {
        // 영역 구분
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1)
                    makeArea(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2) {
                    visited = new boolean[N][N]; // 지도 방문 초기화
                    BFS(i, j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        solution();
        System.out.println(answer);
    }
}
