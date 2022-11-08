package week15.카카오2021인턴십_문자열과영단어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static Map<String, String> format = new HashMap<>();

    public static void setMap() {
        format.put("zero", "0");
        format.put("one", "1");
        format.put("two", "2");
        format.put("three", "3");
        format.put("four", "4");
        format.put("five", "5");
        format.put("six", "6");
        format.put("seven", "7");
        format.put("eight", "8");
        format.put("nine", "9");
    }

    public static int solution(String s) {
        setMap();
        String answer = "";
        String number = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                answer += s.charAt(i);
            } else {
                number += s.charAt(i);
                for (String key : format.keySet()) {
                    if (key.equals(number)) {
                        answer += format.get(number);
                        number = "";
                    }
                }
            }
        }
        return Integer.parseInt(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(solution(s));
    }
}
