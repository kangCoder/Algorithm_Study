// 잃어버린 괄호
// 0~9, '-', '+' 만 있는 식을 가지고 적당히 괄호를 쳐서 가장 작은 결과를 만들기
// 처음 -이 나온 뒤부터는 다 -를 붙인 연산을 하면 된다
package week3.baekjoon_1541;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split("-");

        int result = Integer.MAX_VALUE; // 초기값을 설정해줘야 맨 처음 수를 넣을 수 있음
        for (int i = 0; i < arr.length; i++) {
            int sum = 0; // -를 기준으로 나눈 것들의 각자 합
            String[] add = arr[i].split("\\+"); // \\를 붙여줘야 +가 먹던데.. 왜지
            for (int j = 0; j < add.length; j++)
                sum += Integer.parseInt(add[j]);

            if (result == Integer.MAX_VALUE) result = sum;
            else result -= sum;

        }

        System.out.println(result);
    }
}
