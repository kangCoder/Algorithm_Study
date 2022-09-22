/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42890
 */
package week13.카카오2019블라인드_후보키;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    static boolean[] visited;
    static int count;

    static LinkedList<String> list = new LinkedList<>();
    static LinkedList<String> candidateList = new LinkedList<>();

    public static int solution(String[][] relation) {
        visited = new boolean[relation[0].length];
        count = relation.length;

        for (int i = 1; i <= relation[0].length; i++) {
            combination(0, i, relation);
            check(relation);
            list.clear();
        }

        return candidateList.size();
    }

    public static void combination(int r, int n, String[][] relation) {
        if (n == 0) {
            String tmp = "";
            for (int i = 0; i < relation[0].length; i++) {
                if (visited[i]) {
                    tmp += i;
                }
            }
            list.add(tmp);
        }
        for (int i = r; i < relation[0].length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(r + 1, n - 1, relation);
                visited[i] = false;
            }
        }
    }

    public static void check(String[][] relation) {
        // 유일성 판단
        for (String data : list) {
            HashSet<String> set = new HashSet<>();
            String[] tmp = data.split("");
            for (int i = 0; i < count; i++) {
                StringBuilder candidate = new StringBuilder();
                for (String a : tmp) {
                    int col = Integer.parseInt(a);
                    candidate.append(relation[i][col]);
                }
                set.add(candidate.toString());
            }

            // 유일성을 만족한다. -> 최소성 검사
            if (set.size() == count) {
                boolean isOK = false;
                for (String b : candidateList) {
                    int cnt = 0;
                    String[] tmp2 = b.split("");
                    for (String c : tmp2) {
                        if (data.contains(c)) {
                            cnt++;
                        }
                    }
                    if (cnt == b.length()) {
                        isOK = true;
                    }
                }
                if (!isOK) {
                    candidateList.add(data);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        String[][] relation = new String[row][col];

        System.out.println(solution(relation));
    }

}
