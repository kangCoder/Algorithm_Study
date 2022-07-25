/**
 * 자물쇠와 열쇠
 * 열쇠를 회전하고 움직여서 자물쇠의 빈 칸에 맞게 맞출 수 있는가?
 */
package week4.kakao2020_lockandkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static boolean solution(int[][] key, int[][] lock) {
        int K = lock.length + (key.length - 1) * 2; // lock을 확장한 배열의 크기
        int[][] newLock = new int[K][K];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                if (i >= key.length - 1 && i <= key.length - 1 + lock.length - 1) {
                    if (j >= key.length - 1 && j <= key.length - 1 + lock.length - 1)
                        newLock[i][j] = lock[i - (key.length - 1)][j - (key.length - 1)];
                    else newLock[i][j] = -1;
                } else newLock[i][j] = -1;
            }
        }

        int count = 0;
        // rotate는 3번, 총 4번 검사
        while (count < 4) {
            // i, j => key를 (0,0)부터 (K-key.length, K-key.length)까지 움직이면서
            // k, l => key의 내부를 돌리면서 lock과 매칭시키기
            for (int i = 0; i < K - (key.length - 1); i++) {
                for (int j = 0; j < K - (key.length - 1); j++) {
                    if (matching(newLock, lock, key, i, j)) return true;
                }
            }
            key = rotate(key).clone();
            count++;
        }
        return false;
    }

    public static boolean matching(int[][] newLock, int[][] lock, int[][] key, int i, int j) {
        int[][] tmp = new int[newLock.length][newLock.length];
        for (int k = 0; k < newLock.length; k++)
            for (int l = 0; l < newLock.length; l++)
                tmp[k][l] = newLock[k][l];
        boolean result = true;

        for (int k = i; k < i + key.length; k++) {
            for (int l = j; l < j + key.length; l++) {
                // k와 l이 lock의 위치 안에 존재해야 비교 가능
                if (key[k - i][l - j] == 1 && tmp[k][l] == 1)
                    result = false;
                else if (key[k - i][l - j] == 1 && tmp[k][l] == 0)
                    tmp[k][l] = 1;
            }
        }

        int[][] lock2 = new int[lock.length][lock.length];
        for (int t = 0; t < newLock.length; t++) {
            for (int s = 0; s < newLock.length; s++) {
                if (t >= key.length - 1 && t <= key.length - 1 + lock.length - 1) {
                    if (s >= key.length - 1 && s <= key.length - 1 + lock.length - 1)
                        lock2[t - (key.length - 1)][s - (key.length - 1)] = tmp[t][s];
                }
            }
        }

        if (!result) return false;

        if (check(lock2)) return true;
        else return false;
    }

    public static boolean check(int[][] lock) {
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] != 1) return false;
            }
        }
        return true;
    }

    public static int[][] rotate(int[][] key) {
        int[][] tmp = new int[key.length][key.length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                tmp[j][key.length - 1 - i] = key[i][j];
            }
        }
        return tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int M = Integer.parseInt(br.readLine()); // key
        int N = Integer.parseInt(br.readLine()); // lock

        int[][] key = new int[M][M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                key[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] lock = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                lock[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(key, lock));
    }
}
