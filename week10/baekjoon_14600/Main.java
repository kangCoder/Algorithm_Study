/**
 * 샤워실 바닥 깔기
 * https://www.acmicpc.net/problem/14600
 */
package week10.baekjoon_14600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, x, y, area;
    static int[][] floor;

    public static boolean check(int size, int row, int col) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (floor[i][j] != 0) return false;
            }
        }
        return true;
    }

    public static void solution(int side, int row, int col) {
        area++;

        // 4등분으로 나눠서 진행. 만약 한 사분면에 배수구가 있을 경우 그 사분면을 제외한 나머지 사분면이 같은 area 를 가지게 된다.
        int newSide = side / 2;
        if (check(newSide, row, col)) floor[row + newSide - 1][col + newSide - 1] = area;
        if (check(newSide, row + newSide, col)) floor[row + newSide][col + newSide - 1] = area;
        if (check(newSide, row, col + newSide)) floor[row + newSide - 1][col + newSide] = area;
        if (check(newSide, row + newSide, col + newSide)) floor[row + newSide][col + newSide] = area;

        if (side == 2) return;
        solution(newSide, row, col);
        solution(newSide, row + newSide, col);
        solution(newSide, row, col + newSide);
        solution(newSide, row + newSide, col + newSide);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine()); // 바닥의 한 변의 길이
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        // x, y : 배수구의 좌표

        double s = Math.pow(2, K);
        int side = (int) s;
        floor = new int[side + 1][side + 1];
        floor[y][x] = -1;

        solution(side, 1, 1);
        for (int i = side; i >= 1; i--) {
            for (int j = 1; j <= side; j++)
                System.out.print(floor[i][j] + " ");
            System.out.println();
        }
    }
}
