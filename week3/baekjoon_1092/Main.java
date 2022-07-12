// 배
// N개의 크레인으로 M개의 박스를 배에 실을 때, 최소로 움직여서 모든 박스를 실을 수 있는 것을 찾기
// 모든 크레인인 한 번에 움직여야 함
package week3.baekjoon_1092;

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> crane = new ArrayList<>();
    static List<Integer> box = new ArrayList<>();
    static int N, M;

    public static int calculate() {
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        if (crane.get(0) < box.get(0)) return -1;

        int count = 0;
        while (!box.isEmpty()) {
            int i = 0;
            for (int j = 0; j < N; ) {
                if (i == box.size()) break;
                else if (crane.get(j) >= box.get(i)) {
                    j++;
                    box.remove(i);
                }
                else i++;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            crane.add(Integer.parseInt(st.nextToken()));

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            box.add(Integer.parseInt(st.nextToken()));

        System.out.println(calculate());
    }
}
