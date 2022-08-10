/**
 * 색종이 만들기
 * 하얀색과 파란색으로 이루어진 N*N 색종이를 같은 색으로만 이루어진 크기로 자르기
 */
package week7.baekjoon2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] colorPaper; // 파란색은 1, 하얀색은 0
    static int white = 0, blue = 0;

    public static void solution(int size, int x, int y) {
        if (check(size, x, y)) {
            if (colorPaper[x][y] == 0) white++;
            else blue++;
            return;
        }
        int newSize = size / 2;
        solution(newSize, x, y); // 1사분면
        solution(newSize, x, y + newSize); // 2사분면
        solution(newSize, x + newSize, y); // 3사분면
        solution(newSize, x + newSize, y + newSize); // 4사분면
    }

    public static boolean check(int n, int row, int col) {
        int cur = colorPaper[row][col];
        for (int i = row; i < n + row; i++) {
            for (int j = col; j < n + col; j++) {
                if (colorPaper[i][j] != cur)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 한 변의 길이 (2,4,8,16,32,64,128)
        colorPaper = new int[N][N];

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colorPaper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(N, 0, 0);
        System.out.println(white);
        System.out.println(blue);
    }
}
