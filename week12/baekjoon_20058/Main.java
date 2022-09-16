/**
 * 마법사 상어와 파이어스톰 https://www.acmicpc.net/problem/20058
 */
package week12.baekjoon_20058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N, Q, size, totalIce = 0, bigIce = 0;
    static int[][] A;
    static int[] L;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};


    public static int[][] subAndRotate(int l) {
        int sizeL = (int) Math.pow(2, l);
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i += sizeL) {
            for (int j = 0; j < size; j += sizeL) {
                rotate(i, j, sizeL, tmp);
            }
        }
        return tmp;
    }

    public static void rotate(int x, int y, int l, int[][] a) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                a[i + x][j + y] = A[l + x - 1 - j][i + y];
            }
        }
    }

    public static int[][] meltA() {
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            tmp[i] = Arrays.copyOf(A[i], size);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = 0;
                if (A[i][j] == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && x < size && y >= 0 && y < size) {
                        if (A[x][y] > 0) {
                            count++;
                        }
                    }
                }
                if (count < 3) {
                    tmp[i][j]--; // 얼음이 한 번에 녹아야 하는데 A를 녹여버리면 이후의 값에 영향을 준다.
                }
            }
        }

        return tmp;
    }

    public static void find() {
        boolean[][] visited = new boolean[size][size];
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totalIce += A[i][j];
                if (A[i][j] > 0 && !visited[i][j]) {
                    stack.push(new int[] {i, j});
                    visited[i][j] = true;
                    int count = 1;
                    while (!stack.isEmpty()) {
                        int[] tmp = stack.pop();
                        int x = tmp[0], y = tmp[1];
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                                if (A[nx][ny] > 0 && !visited[nx][ny]) {
                                    stack.push(new int[] {nx, ny});
                                    visited[nx][ny] = true;
                                    count++;
                                }
                            }
                        }
                    }
                    bigIce = Math.max(bigIce, count);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);
        A = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        L = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            A = subAndRotate(L[i]);
            A = meltA();
        }
        find();

        System.out.println(totalIce);
        System.out.println(bigIce);
    }
}
