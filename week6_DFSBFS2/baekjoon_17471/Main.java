/**
 * 게리맨더링
 * N개의 구역을 2개의 선거구로 나눈다.
 * 각 선거구는 적어도 1개의 구역이 있어야 하고, 각 구역은 하나의 선거구에 포함되어야 한다. + 같은 선거구에 있는 구역은 반드시 이어져 있어야 함.
 * 두 선거구의 구역들의 인구차가 최소가 되게?
 */
package week6_DFSBFS2.baekjoon_17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N; // 구역의 개수
    static int[] pNumber; // 각 구역의 인구수
    static boolean[][] list; // 각 구역이 어떻게 구성되어 있는지
    static boolean[] visited, visitedA; // 전체가 이어지는지 확인. A구역과 B구역으로 나누는 것 (true : A구역, false : B구역)
    static int minDiff = Integer.MAX_VALUE; // A구역 인구수와 B구역 인구수의 차

    public static void DFS(int start, boolean isA) {
        visited[start] = true;

        for (int next = 1; next <= N; next++) {
            if (list[start][next] && visitedA[next] == isA && !visited[next])
                DFS(next, isA);
        }
    }

    public static void combination(int cnt, int start, int r) {
        if (cnt == r) {
            if (!checkConnect()) return;

            int diff = getDiff();
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = start; i <= N; i++) {
            if (visitedA[i]) continue;

            visitedA[i] = true;
            combination(cnt + 1, i, r);
            visitedA[i] = false;
        }
    }

    public static boolean checkConnect() {
        visited = new boolean[N + 1];
        // A구역 이 다 연결되어 있는지 확인
        for (int i = 1; i <= N; i++) {
            if (visitedA[i]) {
                DFS(i, true);
                break;
            }
        }

        // B구역 이 다 연결되어 있는지 확인
        for (int i = 1; i <= N; i++) {
            if (!visitedA[i]) {
                DFS(i, false);
                break;
            }
        }

        // 어디에도 연결되어 있지 않았다 -> 구역들이 3개 이상으로 쪼개진다는 뜻.
        for (int i = 1; i <= N; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    public static int getDiff() {
        int aPerson = 0, bPerson = 0;
        for (int i = 1; i <= N; i++) {
            if (visitedA[i])
                aPerson += pNumber[i];
            else
                bPerson += pNumber[i];
        }

        return Math.abs(aPerson - bPerson);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 구역의 개수

        pNumber = new int[N + 1]; // 각 구역의 인구수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            pNumber[i] = Integer.parseInt(st.nextToken());

        list = new boolean[N + 1][N + 1]; // 각 구역에 연결된 구역들의 리스트
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int link = Integer.parseInt(st.nextToken());
            for (int j = 0; j < link; j++) {
                int a = Integer.parseInt(st.nextToken());
                list[i][a] = true;
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            visitedA = new boolean[N + 1];
            combination(0, 1, i);
        }

        if (minDiff == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minDiff);
    }
}
