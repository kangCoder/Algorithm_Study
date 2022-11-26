/**
 * https://www.acmicpc.net/problem/3184
 */
package week18.baekjoon_3184;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C; //행, 열
    static char[][] garden;
    static int safeSheep = 0, safeWolf = 0;
    static int localSheep, localWolf;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        garden = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();
            for (int j = 0; j < C; j++) {
                garden[i][j] = c[j];
            }
        }
        // o: 양, v: 늑대

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (garden[i][j] == 'o') {
                    safeSheep++;
                } else if (garden[i][j] == 'v') {
                    safeWolf++;
                } else if (garden[i][j] == '#') {
                    visited[i][j] = true;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(!visited[i][j] && (garden[i][j] == 'o' || garden[i][j] == 'v')) {
                    localSheep = localWolf = 0;
                    dfs(i, j);
                    if(localSheep > localWolf) {
                        safeWolf -= localWolf;
                    } else {
                        safeSheep -= localSheep;
                    }
                }
            }
        }

        System.out.println(safeSheep + " " + safeWolf);
    }

    public static void dfs(int i, int j) {
        if (i >= R || i < 0 || j >= C || j < 0 || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        if (garden[i][j] == 'o') {
            localSheep++;
        }
        if (garden[i][j] == 'v') {
            localWolf++;
        }

        dfs(i, j + 1);
        dfs(i, j - 1);
        dfs(i + 1, j);
        dfs(i - 1, j);
    }
}
