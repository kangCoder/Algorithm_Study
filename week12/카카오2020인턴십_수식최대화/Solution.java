package week12.카카오2020인턴십_수식최대화;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static long answer = 0L;
    static ArrayList<Long> numbers = new ArrayList<>();
    static ArrayList<Character> operators = new ArrayList<>();
    static char[] opAll = {'*', '+', '-'};
    static char[] priorityOpers = new char[3];

    public static long solution(String expression) {

        String num = "";
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                num += expression.charAt(i);
            } else {
                numbers.add(Long.parseLong(num));
                operators.add(expression.charAt(i));
                num = "";
            }
        }
        numbers.add(Long.parseLong(num)); // 마지막 숫자 추가해줘야 함

        boolean[] visited = new boolean[3];
        permutation(0, visited);

        return answer;
    }

    public static void permutation(int idx, boolean[] visited) {
        if (idx == 3) {
            calculate(priorityOpers);
        } else {
            for (int i = 0; i < 3; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    priorityOpers[idx] = opAll[i];
                    permutation(idx + 1, visited);
                    visited[i] = false;
                }
            }
        }
    }

    public static void calculate(char[] priority) {
        ArrayList<Long> nums = new ArrayList<>(numbers);
        ArrayList<Character> ops = new ArrayList<>(operators);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < ops.size(); j++) {
                if (ops.get(j) == priority[i]) {
                    long result = calResult(nums.remove(j), nums.remove(j), ops.remove(j)); // 아니 이거 메소드로 안빼고 하면 답이 틀린데 왜인지 아는 사람???????
                    nums.add(j--, result);
                }
            }
        }
        answer = Math.max(answer, Math.abs(nums.get(0))); // 계산 결과 남은 숫자는 0번밖에 없다
    }

    public static long calResult(long a, long b, char op) {
        switch (op) {
            case '*':
                return a * b;
            case '+':
                return a + b;
            default:
                return a - b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        System.out.println(solution(expression));
    }

}
