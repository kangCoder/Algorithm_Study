/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/118668
 */
package week13.카카오2022인턴십_코딩테스트공부;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int alp, cop;
    static int[][] problems;
    // [i][0]: alp_req, [i][1]: cop_req, [i][2]: alp_rwd, [i][3]: cop_rwd, [i][4]: cost
    static final int INF = 1000000;

    public static int solution(int alp, int cop, int[][] problems) {
        int max_alp = alp, max_cop = cop;
        for (int i = 0; i < problems.length; i++) {
            max_alp = Math.max(max_alp, problems[i][0]);
            max_cop = Math.max(max_cop, problems[i][1]);
        }

        if (alp >= max_alp && cop >= max_cop) {
            return 0;
        }
        if (alp >= max_alp) {
            alp = max_alp;
        }
        if (cop >= max_cop) {
            cop = max_cop;
        }

        int[][] dp = new int[max_alp + 2][max_cop + 2];
        // dp[i][j]: 알고력 i, 코딩력 j 상태에 도달하는 시간
        for (int i = alp; i <= max_alp; i++) {
            for (int j = cop; j <= max_cop; j++) {
                dp[i][j] = INF;
            }
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= max_alp; i++) {
            for (int j = cop; j <= max_cop; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        if (i + problem[2] > max_alp && j + problem[3] > max_cop) {
                            dp[max_alp][max_cop] = Math.min(dp[max_alp][max_cop],
                                    dp[i][j] + problem[4]);
                        } else if (i + problem[2] > max_alp) {
                            dp[max_alp][j + problem[3]] = Math.min(dp[max_alp][j + problem[3]],
                                    dp[i][j] + problem[4]);
                        } else if (j + problem[3] > max_cop) {
                            dp[i + problem[2]][max_cop] = Math.min(dp[i + problem[2]][max_cop],
                                    dp[i][j] + problem[4]);
                        } else if (i + problem[2] <= max_alp && j + problem[3] <= max_cop) {
                            dp[i + problem[2]][j + problem[3]] = Math.min(
                                    dp[i + problem[2]][j + problem[3]], dp[i][j] + problem[4]);
                        }
                    }
                }
            }
        }

        return dp[max_alp][max_cop];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        alp = Integer.parseInt(st.nextToken());
        cop = Integer.parseInt(st.nextToken());
        problems = new int[4][5];

        System.out.println(solution(alp, cop, problems));
    }
}
