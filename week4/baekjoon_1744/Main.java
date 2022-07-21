// 수 묶기
package week4.baekjoon_1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        List<Integer> isone = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 1) isone.add(n);
            if (n > 1) positive.add(n);
            else if (n <= 0) negative.add(n);
        }

        Collections.sort(positive, Collections.reverseOrder()); // 양수는 내림차순 정렬
        Collections.sort(negative); // 음수는 오름차순 정렬

        int sum = 0;

        if(positive.size() % 2 == 0) {
            for(int i=0; i<positive.size() - 1; i+=2)
                sum += positive.get(i) * positive.get(i+1);
        } else {
            for(int i=0; i<positive.size() - 2; i+=2)
                sum += positive.get(i) * positive.get(i+1);
            sum += positive.get(positive.size() - 1);
        }

        if(negative.size() % 2 == 0) {
            for(int i=0; i<negative.size() - 1; i+=2)
                sum += negative.get(i) * negative.get(i+1);
        } else {
            for(int i=0; i<negative.size() - 2; i+=2)
                sum += negative.get(i) * negative.get(i+1);
            sum += negative.get(negative.size() - 1);
        }

        sum += isone.size();

        System.out.println(sum);
    }
}
