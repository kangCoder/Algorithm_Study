package practice.DivideandConquer.baekjoon_1974;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, R, C;
    static int answer = 0;

    public static void solution(int size, int r, int c) {
        if (size == 1) {
            System.out.println(answer);
            return;
        }
        int newSize = size / 2;
        if (R < r + newSize && C < c + newSize) {
            // 1사분면에 있다는 뜻
            solution(newSize, r, c);
        }
        if (R < r + newSize && C >= c + newSize) {
            // 2사분면에 있다는 뜻
            answer += (size * size) / 4;
            solution(newSize, r, c + newSize);
        }
        if (R >= r + newSize && C < c + newSize) {
            // 3사분면에 있다는 뜻
            answer += ((size * size) / 4) * 2;
            solution(newSize, r + newSize, c);
        }
        if (R >= r + newSize && C >= c + newSize) {
            // 4사분면에 있다는 뜻
            answer += ((size * size) / 4) * 3;
            solution(newSize, r + newSize, c + newSize);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        solution((int) Math.pow(2, N), 0, 0);
    }
}
