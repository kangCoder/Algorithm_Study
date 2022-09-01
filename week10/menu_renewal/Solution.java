/**
 * 메뉴 리뉴얼
 * https://school.programmers.co.kr/learn/courses/30/lessons/72411
 */
package week10.menu_renewal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static String[] orders;
    static int[] course;
    static Map<String, Integer> map;
    static int courseNum;

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        PriorityQueue<String> pq = new PriorityQueue<>();

        // 먼저 orders 를 sort
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // course 를 돌면서 course[i]의 길이에 해당하는 조합을 탐색한다.
        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            courseNum = 0;

            // course[i] 의 길이에 맞는 orders[j] 에서 조합을 구성
            for (int j = 0; j < orders.length; j++) {
                matching(0, 0, course[i], "", orders[j]);
            }

            for (String s : map.keySet()) {
                if (map.get(s) == courseNum && courseNum > 1)
                    pq.offer(s);
            }
        }

        answer = new String[pq.size()];
        int count = 0;
        while (!pq.isEmpty())
            answer[count++] = pq.poll(); // 우선순위 큐로 인해 사전순으로 answer 에 넣을 수 있음

        return answer;
    }

    public static void matching(int cnt, int idx, int targetNum, String source, String word) {
        if (cnt == targetNum) {
            // course[i]의 길이와 order[j]의 일부의 길이가 맞는 순간 체크한다.
            map.put(source, map.getOrDefault(source, 0) + 1);
            courseNum = Math.max(courseNum, map.get(source));
            return;
        }
        for (int i = idx; i < word.length(); i++) {
            char cur = word.charAt(i);
            matching(cnt + 1, i + 1, targetNum, source + cur, word);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 손님의 수
        orders = new String[N];
        for (int i = 0; i < N; i++)
            orders[i] = br.readLine();

        int M = Integer.parseInt(br.readLine()); // 코스요리의 수
        course = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            course[i] = Integer.parseInt(st.nextToken());


        String[] answer = solution(orders, course);
        for (int i = 0; i < answer.length; i++)
            System.out.println(answer[i]);
    }
}
