/**
 * 경사로
 */
package week7.baekjoon_14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L; // 지도와 경사로
    static int[][] map;

    public static int solution() {
        int answer = 0;

        for (int row = 0; row < N; row++) {
            if (left_right(row))
                answer++;
        }
        for (int col = 0; col < N; col++) {
            if (top_down(col))
                answer++;
        }

        return answer;
    }

    public static boolean left_right(int row) {
        int length = 1;
        int before = map[row][0];
        for (int col = 1; col < N; col++) {
            if (before == map[row][col]) length++;
            else if (before + 1 == map[row][col]) {
                if (length < L) return false;
                else {
                    before++;
                    length = 1;

                }
            } else if (before - 1 == map[row][col]) {
                int temp = 0;
                for (int i = col; i < N; i++) {
                    if (before - 1 != map[row][i]) break;
                    temp++;
                }
                if (temp < L) return false;
                col += (L - 1);
                before--;
                length = 0;
            }
            else {
                // 높이 차이가 2 이상이라는 뜻
                return false;
            }
        }
        return true;
    }

    public static boolean top_down(int col) {
        int length = 1;
        int before = map[0][col];
        for (int row = 1; row < N; row++) {
            if (before == map[row][col]) length++;
            else if (before + 1 == map[row][col]) {
                if (length < L) return false;
                else {
                    before++;
                    length = 1;

                }
            } else if (before - 1 == map[row][col]) {
                int temp = 0;
                for (int i = row; i < N; i++) {
                    if (before - 1 != map[i][col]) break;
                    temp++;
                }
                if (temp < L) return false;
                row += (L - 1);
                before--;
                length = 0;
            }
            else {
                // 높이 차이가 2 이상이라는 뜻
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}
