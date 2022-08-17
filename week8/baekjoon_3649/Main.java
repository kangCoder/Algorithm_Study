/**
 * 로봇 프로젝트
 */
package week8.baekjoon_3649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tc = null;
        while ((tc = br.readLine()) != null) {
            int x = Integer.parseInt(tc) * 10000000; // 구멍의 길이
            int n = Integer.parseInt(br.readLine()); // 레고의 개수

            int[] lego = new int[n]; // 레고 하나당 길이
            for (int i = 0; i < n; i++)
                lego[i] = Integer.parseInt(br.readLine());
            Arrays.sort(lego);

            int start = 0, end = n - 1;
            boolean check = false;
            while (start < end) {
                int sum = lego[start] + lego[end];
                if (sum == x) {
                    System.out.println("yes " + lego[start] + " " + lego[end]);
                    check = true;
                    break;
                } else if (sum < x) {
                    start++;
                } else
                    end--;
            }

            if (!check) System.out.println("danger");

            tc = null;
        }
    }
}
