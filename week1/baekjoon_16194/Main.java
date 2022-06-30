// 카드 구매하기 2
// 최소 금액으로 구매하는 문제
// 점화식 구성하는게 상당히 까다로웠음
package week1.baekjoon_16194;

import java.util.*;

public class Main {
    static int[] cost; // cost[i] : i팩을 구입하는 가격
    static int[] mCost; // mCost[i] : 카드 i개를 구입하는 최소 가격

    public static int D(int[] cost, int[] mCost, int N) {
        for (int i = 1; i < cost.length; i++) {
            for (int j = 1; j <= i; j++) {
                // 점화식
                mCost[i] = Math.min(mCost[i], cost[j] + mCost[i - j]);
            }
        }
        return mCost[N];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); // 카드의 개수
        cost = new int[N + 1];
        mCost = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cost[i] = scan.nextInt(); // p1부터 pn까지의 카드팩에 대한 가격 입력
            mCost[i] = Integer.MAX_VALUE; // 일단 최대 정수로 초기화
        }

        System.out.println(D(cost, mCost, N));

    }
}
