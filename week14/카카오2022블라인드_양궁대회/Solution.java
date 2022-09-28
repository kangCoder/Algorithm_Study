/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/92342
 */
package week14.카카오2022블라인드_양궁대회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] lion;
    static int[] answer = {-1};
    static int maxDiff = -10000;

    public static int[] solution(int n, int[] info) {
        // info, 정답 배열 주의할 점 -> 순서가 0 ~ 10이 아니라 10 ~ 0임.
        lion = new int[11];
        dfs(info, 0, n);
        return answer;
    }

    public static void dfs(int[] info, int count, int n) {
        if (count == n) {
            int lionPoint = 0, apeachPoint = 0;
            for (int i = 0; i <= 10; i++) {
                if(lion[i] != 0 || info[i] != 0) {
                    if (lion[i] > info[i]) {
                        lionPoint += 10 - i;
                    } else {
                        apeachPoint += 10 - i;
                    }
                }
            }
            if (lionPoint > apeachPoint) {
                if (lionPoint - apeachPoint >= maxDiff) {
                    answer = lion.clone();
                    maxDiff = lionPoint - apeachPoint;
                }
            }
            return;
        }

        for (int i = 0; i <= 10; i++) {
            if (lion[i] > info[i]) {
                break;
            } else {
                lion[i]++;
                dfs(info, count + 1, n);
                lion[i]--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] info = new int[11];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= 10; i++) {
            info[i] = Integer.parseInt(st.nextToken());
        }

        int[] ans = solution(N, info);
        for (int i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
