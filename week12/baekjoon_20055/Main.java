/**
 * 컨베이어 벨트 위의 로봇 https://www.acmicpc.net/problem/20055
 */
package week12.baekjoon_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, cant = 0;
    static int[][] belt;
    static boolean[] robot;

    public static void rotateMap() {
        // 벨트가 한 칸 움직인다. -> 5번 자리의 값에 4번이 들어온다.
        int tmp1 = belt[belt.length - 1][0], tmp2 = belt[belt.length - 1][1];
        for (int i = belt.length - 1; i > 0; i--) {
            belt[i][0] = belt[i - 1][0];
            belt[i][1] = belt[i - 1][1];
        }
        belt[0][0] = tmp1;
        belt[0][1] = tmp2;
    }

    public static void moveRobot() {
        for (int i = robot.length - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false; // 0번 칸에는 항상 로봇이 올라갈 자리를 비워놔야함.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        belt = new int[2 * N][2]; // 0: 벨트의 내구도, 1: 그 칸의 내구도가 0일 때, 중복으로 세지 않기 위함.
        for (int i = 0; i < 2 * N; i++) {
            belt[i][0] = Integer.parseInt(st.nextToken());
        }
        robot = new boolean[N]; // 해당 칸에 로봇이 있는지

        int answer = 1;
        while (true) {
            rotateMap();
            moveRobot();

            for (int i = robot.length - 1; i >= 0; i--) {
                if (robot[i]) {
                    if (i + 1 == N) {
                        robot[i] = false;
                        continue;
                    }
                    if (i + 1 < N && !robot[i + 1]) {
                        if (belt[i + 1][0] > 0) {
                            // 벨트의 내구도가 남아있다면
                            robot[i + 1] = true;
                            robot[i] = false;
                            belt[i + 1][0]--;

                            if (belt[i + 1][0] == 0 && belt[i + 1][1] == 0) {
                                cant++;
                                belt[i + 1][1] = 1;
                            }
                        }
                    }
                }
            }

            if (!robot[0] && belt[0][0] > 0) {
                // 첫 칸이 비어있을 때 로봇이 들어와야 함.
                belt[0][0]--;
                robot[0] = true;
                if (belt[0][0] == 0 && belt[0][1] == 0) {
                    cant++;
                    belt[0][1] = 1;
                }
            }

            if (cant >= K) {
                break;
            }

            answer++;
        }

        System.out.println(answer);
    }
}
