/**
 * 바닥 장식
 * '-'와 '|'로 이루어진 나무 판자로 바닥 장식 꾸미기
 * '-'가 같은 행에 연속적으로 있다면 같은 나무 판자로 취급
 * '|'가 같은 열에 연속적으로 있다면 같은 나무 판자로 취급
 */
package week5_DFSBFS1.baekjoon_1388;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] list;
    static boolean[][] visited;

    public static void DFS(int row, int col, char tile) {
        if (visited[row][col]) return;
        visited[row][col] = true;

        if (tile == '-') {
            if (col >= list[row].length - 1) return;
            for (int i = col + 1; i < list[row].length; i++) {
                char next = list[row][i];
                if (next == '|') return;
                DFS(row, i, next);
            }
        }
        if (tile == '|') {
            if (row >= list.length - 1) return;
            for (int i = row + 1; i < list.length; i++) {
                char next = list[i][col];
                if (next == '-') return;
                DFS(i, col, next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 바닥의 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 바닥의 가로 크기
        visited = new boolean[N][M];

        list = new char[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++)
                list[i][j] = row.charAt(j);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    DFS(i, j, list[i][j]);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
