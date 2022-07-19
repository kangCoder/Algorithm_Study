/**
 * A와 B
 * 두 문자열 S,T가 주어졌을 때, S를 T로 바꾸기
 * 문자열을 바꿀 때는 두 가지 연산만 가능.
 * 1. 문자열 뒤에 A를 추가한다.
 * 2. 문자열을 뒤집고 뒤에 B를 추가한다.
 * 이 조건을 이용해서 S를 T로 만들 수 있는지 없는지 구하기
 */
package week4.baekjoon_12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int solution(String S, String T) {

        while (true) {
            if (T.equals(S)) return 1;

            if (T.length() <= 1) return 0;

            if (T.charAt(T.length() - 1) == 'A') {
                T = T.substring(0, T.length() - 1);
            }
            else if (T.charAt(T.length() - 1) == 'B') {
                T = T.substring(0, T.length() - 1);
                StringBuffer sb = new StringBuffer(T);
                T = sb.reverse().toString();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        System.out.println(solution(S, T));
    }
}
