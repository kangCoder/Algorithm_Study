/**
 * 2017 카카오코드 예선
 * 카카오프렌즈 컬러링북
 * 그림의 영역의 개수와 가장 큰 영역의 넓이
 */
package week6_DFSBFS2.kakao2017_coloringbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] picture;
    static boolean[][] visited;
    static int count = 0;

    public static void DFS(int x, int y, int[][] picture) {
        if (x > picture.length - 1 || y > picture[x].length - 1 || x < 0 || y < 0) return;


        visited[x][y] = true;
        count++;

        int curIndex = picture[x][y];
        if (y < visited[x].length - 1 && !visited[x][y + 1] && curIndex == picture[x][y + 1]) DFS(x, y + 1, picture);
        if (y > 0 && !visited[x][y - 1] && curIndex == picture[x][y - 1]) DFS(x, y - 1, picture);
        if (x < visited.length - 1 && !visited[x + 1][y] && curIndex == picture[x + 1][y]) DFS(x + 1, y, picture);
        if (x > 0 && !visited[x - 1][y] && curIndex == picture[x - 1][y]) DFS(x - 1, y, picture);
    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0)
                    visited[i][j] = true;

                if (!visited[i][j]) {
                    DFS(i, j, picture);
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(count, maxSizeOfOneArea);
                    count = 0;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        picture = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                picture[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(m, n, picture)[0] + " " + solution(m, n, picture)[1]);
    }
}
