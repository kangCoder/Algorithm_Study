/**
 * 양과 늑대
 * https://school.programmers.co.kr/learn/courses/30/lessons/92343
 */
package week11.sheepandwolf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static ArrayList<Integer>[] graph;
    static int answer = 0;
    static int[] copyInfo;

    public static int solution(int[] info, int[][] edges) {
        copyInfo = info.clone();
        graph = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        boolean[] visited = new boolean[info.length];
        DFS(0, 0, 0, visited);

        return answer;
    }

    public static void DFS(int cur, int sheep, int wolf, boolean[] visited) {
        if (copyInfo[cur] == 0)
            sheep++;
        else if (copyInfo[cur] == 1)
            wolf++;

        if (sheep <= wolf) return;

        // 새롭게 올 때마다 visited 를 초기화 해줘야 함.
        boolean[] copyVisited = visited.clone();
        copyVisited[cur] = true;
        answer = Math.max(answer, sheep);

        for (int i = 0; i < copyVisited.length; i++) {
            if (copyVisited[i]) {
                for (int next = 0; next < graph[i].size(); next++) {
                    int tmp = graph[i].get(next);
                    if (!copyVisited[tmp])
                        DFS(tmp, sheep, wolf, copyVisited);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] info = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            info[i] = Integer.parseInt(st.nextToken()); // 0: 양, 1: 늑대

        int[][] edges = new int[N - 1][2];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++)
                edges[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(info, edges));
    }
}
