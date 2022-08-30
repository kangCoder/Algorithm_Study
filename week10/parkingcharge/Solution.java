/**
 * 주차 요금 계산
 * https://school.programmers.co.kr/learn/courses/30/lessons/92341
 */
package week10.parkingcharge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int[] fees = new int[4]; // 0 : 기본 시간(분), 1 : 기본 요금(원), 2 : 단위 시간(분), 3 : 단위 요금(원)
    static String[] records;

    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        Map<Integer, Integer> startTime = new HashMap<>(); // 차번호, 입차or출차 시간
        Map<Integer, Integer> totalTime = new HashMap<>(); // 차번호, 누적 시간
        Set<Integer> car = new TreeSet<>();

        int std_time = fees[0];
        int std_charge = fees[1];
        int per_time = fees[2];
        int per_charge = fees[3];

        for(String record : records) {
            String[] subRecord = record.split(" ");
            String[] time = subRecord[0].split(":");
            int minute = (Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]); // 입차 or 출차 시간을 분으로 저장

            int carNumber = Integer.parseInt(subRecord[1]); // 차량 번호
            if(startTime.containsKey(carNumber)) {
               // 만약 차번호가 이미 들어있다? -> 출차라는 뜻
               totalTime.put(carNumber, totalTime.get(carNumber) + minute - startTime.get(carNumber));
               startTime.remove(carNumber);
            } else {
                // 차번호가 없다? -> 입차라는 뜻
                car.add(carNumber);
                startTime.put(carNumber, minute);
                if(!totalTime.containsKey(carNumber)) {
                    totalTime.put(carNumber, 0); // 일단 입차한 차량도 누적 시간에 넣어놓기. 끝에서 0인 경우는 출차내역이 없는 차량으로 간주.
                }
            }
        }

        for(Integer key : startTime.keySet())
            totalTime.put(key, totalTime.get(key) + ((24 * 60) - 1) - startTime.get(key)); // 입차내역에 남아있는 차량들은 23시 59분에 출차한 것으로 처리

        answer = new int[car.size()];
        List<Integer> list = new ArrayList<>(car);
        int count = 0;
        for(int number : list) {
            int total = totalTime.get(number);
            if(total <= std_time)
                answer[count] = std_charge;
            else {
                answer[count] = (int) (std_charge + (Math.ceil((double)(total - std_time) / per_time)) * per_charge);
            }
            count++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        fees[0] = Integer.parseInt(st.nextToken()); // 기본 시간(분)
        fees[1] = Integer.parseInt(st.nextToken()); // 기본 요금(원)
        fees[2] = Integer.parseInt(st.nextToken()); // 단위 시간(분)
        fees[3] = Integer.parseInt(st.nextToken()); // 단위 요금(원)

        int n = Integer.parseInt(br.readLine());
        records = new String[n];
        for (int i = 0; i < n; i++)
            records[i] = br.readLine();

        for (int i = 0; i < solution(fees, records).length; i++)
            System.out.println(solution(fees, records)[i]);
    }
}
