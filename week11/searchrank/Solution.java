/**
 * 순위 검색
 * https://school.programmers.co.kr/learn/courses/30/lessons/72412
 */
package week11.searchrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static Map<String, List<Integer>> infoMap = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (String i : info) {
            String[] subInfo = i.split(" ");
            combination(subInfo, "", 0);
        }

        // key 값 (score) 에 대해 sort 를 해놔야 후에 BS 를 할 수 있다.
        for (String key : infoMap.keySet())
            Collections.sort(infoMap.get(key));


        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] subQuery = query[i].split(" ");
            if (infoMap.containsKey(subQuery[0]))
                answer[i] = binarySearch(subQuery[0], Integer.parseInt(subQuery[1]));
            else answer[i] = 0;
        }
        return answer;
    }

    public static int binarySearch(String query, int score) {
        List<Integer> list = infoMap.get(query);
        int start = 0, end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) >= score) end = mid - 1;
            else start = mid + 1;
        }
        return list.size() - start;
    }

    public static void combination(String[] info, String str, int cnt) {
        if (cnt == 4) {
            if (!infoMap.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                infoMap.put(str, list);
            }
            infoMap.get(str).add(Integer.parseInt(info[4]));
            return;
        }
        combination(info, str + "-", cnt + 1);
        combination(info, str + info[cnt], cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] info = new String[N];
        for (int i = 0; i < N; i++)
            info[i] = br.readLine();

        int M = Integer.parseInt(br.readLine());
        String[] query = new String[M];
        for (int i = 0; i < M; i++)
            query[i] = br.readLine();

        int[] answer = solution(info, query);
        for (int i = 0; i < answer.length; i++)
            System.out.print(answer[i] + " ");
    }
}
