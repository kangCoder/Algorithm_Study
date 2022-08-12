/**
 * 개똥벌레
 */
package week7.baekjoon_3020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, H;
    static int[] stonePillar, stalactite;

    public static int lowerBound(int[] arr, int height) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= height)
                end = mid;
            else
                start = mid + 1;
        }
        return arr.length - end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 동굴의 길이, 항상 짝수
        H = Integer.parseInt(st.nextToken()); // 동굴의 높이

        stonePillar = new int[N / 2];
        stalactite = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            int down = Integer.parseInt(br.readLine());
            int up = Integer.parseInt(br.readLine());
            stonePillar[i] = down;
            stalactite[i] = up;
        }

        Arrays.sort(stonePillar);
        Arrays.sort(stalactite);

        int count = 0;
        int min = N;
        for (int i = 1; i < H + 1; i++) {
            int conflict = lowerBound(stonePillar, i) + lowerBound(stalactite, H - i + 1);
            if (min == conflict) {
                count++;
                continue;
            }
            if (min > conflict) {
                min = conflict;
                count = 1;
            }
        }

        System.out.println(min + " " + count);
    }
}
