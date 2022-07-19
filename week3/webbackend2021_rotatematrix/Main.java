// 행렬 테두리 회전하기
//
package week3.webbackend2021_rotatematrix;

import java.io.*;
import java.util.*;

public class Main {
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];

        int[][] Matrix = new int[rows][columns];
        int count = 1;
        for(int i=0; i<rows; i++)
            for(int j=0; j<columns; j++)
                Matrix[i][j] = count++;

        for(int i=0; i<queries.length; i++) {
            int r1 = queries[i][0];
            int c1 = queries[i][1];
            int r2 = queries[i][2];
            int c2 = queries[i][3];

            int newRow = queries[i][3] - queries[i][1];
            int newCol = queries[i][2] - queries[i][0];
            int n = newRow * newCol - (newRow-2)*(newCol-2);

            int startR = queries[i][0];
            int startC=queries[i][1];
            int tmp1 = Matrix[startR][startC];
            int tmp2 = Matrix[startR][startC];


        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int N = Integer.parseInt(br.readLine()); // query를 수행할 횟수
        int[][] query = new int[N][4];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
            query[i][2] = Integer.parseInt(st.nextToken());
            query[i][3] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(row, col, query));
    }
}
