package week3.kakao2021_newid;

import java.io.*;

public class Main {
    public static String solution(String new_id) {
        String answer = "";

        answer = new_id.toLowerCase(); // 1. 대문자 -> 소문자
        answer = answer.replaceAll("[^a-z\\d\\-_.]*", ""); // 2. 소문자, 숫자, -_. 제외 제거
        answer = answer.replaceAll("\\.{2,}", "."); // 3. 마침표가 2번 이상 연속된 부분 하나로 치환
        answer = answer.replaceAll("^[.]|[.]$", ""); // 4. 처음이나 끝이 마침표이면 제거
        if(answer.isEmpty()) answer = "a"; // 5. 빈 문자열이면 a 대입

        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$", "");
            // 6. 아이디의 길이가 16자 이상이면 처음 15자 제외하고 제거하고, 마지막이 마침표이면 제거
        }

        // 7. 아이디의 길이가 2자 이하면 마지막 글자를 길이가 3이 될 때까지 추가
        while (answer.length() < 3)
            answer += answer.charAt(answer.length() - 1);

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String new_id = br.readLine();
        System.out.println(solution(new_id));
    }
}
