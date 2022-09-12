/**
 * 치킨 배달 https://www.acmicpc.net/problem/15686
 */
package week12.baekjoon_15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, minDist = Integer.MAX_VALUE;
    static int[][] city;
    static ArrayList<Node> house = new ArrayList<>();
    static ArrayList<Node> chicken = new ArrayList<>();
    static boolean[] chickenVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 크기
        M = Integer.parseInt(st.nextToken()); // 살려둘 치킨집의 수

        city = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                // 집과 치킨집의 좌표 저장
                if (city[i][j] == 1) {
                    house.add(new Node(i, j));
                } else if (city[i][j] == 2) {
                    chicken.add(new Node(i, j));
                }
            }
        }

        chickenVisited = new boolean[chicken.size()];
        backTracking(0, 0);
        System.out.println(minDist);
    }

    public static void backTracking(int cnt, int start) {
        if (cnt == M) {
            int totalChickenDist = 0;
            for (int i = 0; i < house.size(); i++) {
                int chickenDist = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (chickenVisited[j]) {
                        int dist = Math.abs(house.get(i).x - chicken.get(j).x) + Math.abs(
                                house.get(i).y - chicken.get(j).y);
                        chickenDist = Math.min(chickenDist, dist); // 각 집마다의 치킨거리 갱신
                    }
                }
                totalChickenDist += chickenDist;
            }
            minDist = Math.min(minDist, totalChickenDist);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!chickenVisited[i]) {
                chickenVisited[i] = true;
                backTracking(cnt + 1, i + 1); // start 가 없으면 최대 13!의 경우의 수를 다 돌리게 된다.
                chickenVisited[i] = false; // 해당 치킨집을 살려둘 치킨집으로 한 경우의 수가 끝나면 다시 초기화해준다.
            }
        }
    }
}
