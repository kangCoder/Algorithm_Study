package week8.baekjoon_4256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] pre, in;
    static StringBuilder sb;

    /**
     * preorder : root -> left -> right
     * inorder : left -> root -> right
     * postorder :
     */

    public static void traversal(int root, int start, int end) {
        int rootIdx = pre[root]; // 전위순회의 제일 맨 앞 값은 루트 값임.
        for (int i = start; i < end; i++) {
            if (in[i] == rootIdx) {
                traversal(root + 1, start, i);
                traversal((root + i + 1) - start, i + 1, end);
                sb.append(rootIdx + " ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            pre = new int[n + 1];
            in = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                pre[j] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                in[j] = Integer.parseInt(st.nextToken());

            sb = new StringBuilder();
            traversal(0, 0, n);
            System.out.println(sb.toString());
        }
    }
}
