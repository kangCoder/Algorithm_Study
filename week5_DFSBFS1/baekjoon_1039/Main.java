/**
 * 교환
 * 0으로 시작하지 않는 자릿수가 M인 정수 N
 * 1~M 사이의 i, j를 골라 N의 i번째와 j번째 숫자를 교체 (바뀐 수가 0으로 시작하면 안됨)
 * 위의 연산을 K번 했을 때, 나올 수 있는 수의 최대?
 */
package week5_DFSBFS1.baekjoon_1039;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int num;
    int count;

    public Pair(int num, int count) {
        this.num = num;
        this.count = count;
    }
}

public class Main {
    static Queue<Pair> queue = new LinkedList<>();
    static boolean[][] visited;
    static int answer = -1;

    public static char[] swap(int N, int i, int j) {
        StringBuilder sb = new StringBuilder(String.valueOf(N));
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);

        return sb.toString().toCharArray();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        visited = new boolean[1000001][11];
        int K = Integer.parseInt(st.nextToken());

        queue.add(new Pair(N, 0));
        visited[N][0] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (cur.count == K) {
                answer = Math.max(answer, cur.num);
                continue;
            }

            String NtoString = String.valueOf(N);
            for (int i = 0; i < NtoString.length() - 1; i++) {
                for (int j = i + 1; j < NtoString.length(); j++) {
                    char[] c = swap(cur.num, i, j);
                    int n = Integer.parseInt(String.valueOf(c));

                    if (c[0] == '0' || visited[n][cur.count + 1])
                        continue;

                    visited[n][cur.count + 1] = true;
                    queue.add(new Pair(n, cur.count + 1));
                }
            }
        }

        System.out.println(answer);
    }
}
