/**
 * 222-풀링
 */
package week8.baekjoon_17829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] CNN;
    static int answer = 0;

    public static void solution(int[][] arr, int N) {
        if (N == 1) {
            answer = arr[0][0];
            return;
        }

        int[][] newArr = new int[N / 2][N / 2];
        for (int i = 0; i < N; i += 2) {
            for (int j = 0; j < N; j += 2) {
                newArr[i / 2][j / 2] = checkSecond(arr, i, j);
            }
        }

        solution(newArr, N / 2);
    }

    public static int checkSecond(int[][] arr, int i, int j) {
        int[] tmp = new int[4];
        int count = 0;

        for (int k = i; k < i + 2; k++)
            for (int l = j; l < j + 2; l++)
                tmp[count++] = arr[k][l];

        Arrays.sort(tmp);
        return tmp[2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        CNN = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                CNN[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(CNN, N);
        System.out.println(answer);
    }
}
