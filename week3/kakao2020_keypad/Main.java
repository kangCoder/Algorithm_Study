// 키패드 누르기
// 1,4,7은 왼손 | 3,6,9는 오른손
// 2,5,8,0은 현재 더 가까운 손 -> 거리가 같을 경우 손잡이에 해당하는 손이 누름
package week3.kakao2020_keypad;

import java.io.*;

public class Main {
    public static String solution(int[] numbers, String hand) {
        String answer = "";
        int currentLeft = 10;
        int currentRight = 12;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                answer += "L";
                currentLeft = numbers[i];
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                answer += "R";
                currentRight = numbers[i];
            } else {
                if (numbers[i] == 0) numbers[i] = 11;
                int d_left = Math.abs(numbers[i] - currentLeft) / 3 + Math.abs(numbers[i] - currentLeft) % 3;
                int d_right = Math.abs(numbers[i] - currentRight) / 3 + Math.abs(numbers[i] - currentRight) % 3;
                if (d_left < d_right) {
                    answer += "L";
                    currentLeft = numbers[i];
                } else if (d_left > d_right) {
                    answer += "R";
                    currentRight = numbers[i];
                } else {
                    if (hand.equals("left")) {
                        answer += "L";
                        currentLeft = numbers[i];
                    } else {
                        answer += "R";
                        currentRight = numbers[i];
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] arr = str.split(" ");

        int[] numbers = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            numbers[i] = Integer.parseInt(arr[i]);

        String hand = br.readLine();

        System.out.println(solution(numbers, hand));
    }
}
