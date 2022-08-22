package week9.farfromnode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static int solution(int n, int[][] edge) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }

        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int a = queue.poll();
            for (int b : graph[a]) {
                if (visited[b])
                    continue;
                queue.add(b);
                visited[b] = true;
                dist[b] = dist[a] + 1;
            }
        }

        Arrays.sort(dist);
        int max = dist[n];
        for (int i = 1; i < dist.length; i++) {
            if (max == dist[i])
                answer++;
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] edge = new int[k][2];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[i][0] = a;
            edge[i][1] = b;
        }

        System.out.println(solution(n, edge));
    }
}
