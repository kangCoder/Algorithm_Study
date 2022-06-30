// 1로 만들기
/*
조심해야할 부분
3 다음 2를 else if로 쓰면 안된다 -> 2로 나누는게 더 빠른 경우도 있기 때문
*/
package week1.baekjoon_1463;

import java.util.*;

public class Main {
    static int[] arr;

    public static int calculate(int N) {
        if (arr.length > 2) {
            arr[2] = 1;
            if (arr.length > 3) {
                arr[3] = 1;
                for (int i = 4; i < arr.length; i++) {
                    if (i % 3 == 0 && i % 2 == 0) {
                        if (arr[i / 3] >= arr[i / 2] && arr[i / 2] <= arr[i - 1]) {
                            arr[i] = arr[i / 2] + 1;
                            continue;
                        }
                    }
                    if (i % 3 == 0) {
                        if (arr[i / 3] <= arr[i - 1]) {
                            arr[i] = arr[i / 3] + 1;
                            continue;
                        }
                    }
                    if (i % 2 == 0) {
                        if (arr[i / 2] < arr[i - 1]) {
                            arr[i] = arr[i / 2] + 1;
                            continue;
                        }
                    }
                    arr[i] = arr[i - 1] + 1;
                }
            }
        }
        return arr[N];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        arr = new int[N + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        System.out.println(calculate(N));
    }
}
