// 회의실 배정
// 회의 N개 중 가장 많이 배정할 수 있는 최대 회의의 개수
package week3.baekjoon_1931;

import java.io.*;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.end > o.end) return 1;
            else if (this.end < o.end) return -1;
            else return this.start - o.start; // end가 동일할 경우 start가 더 작은 순으로 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Pair(start, end));
        }

        Collections.sort(list);

        int count = 0;
        int end = 0;
        for (Pair p : list) {
            if (end <= p.start) {
                count++;
                end = p.end;
            }
        }

        System.out.println(count);
    }
}
