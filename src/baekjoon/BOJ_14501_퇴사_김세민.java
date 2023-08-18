package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_14501_퇴사
 * 1. N개의 상담일 중 순서대로 마지막 날까지 순회한다.
 * 2. 1일부터 N일까지 i일부터 상담을 진행하였을 때 얻을 수 있는 최대 이익을 각각 구한다.
 * 	2-1. 우선, 1일부터 N일까지 i일의 최대 이익에 P[i]를 저장한다.
 * 	2-2. 다음 상담을 진행할 수 있는 경우...다음 날짜부터 상담을 시작하였을 때의 이익과 기존의 이익에 추가로 상담을 진행하여 받을 수 있는 이익을 비교한다.
 * 3. 회사에 남아있는 경우 중 얻을 수 있는 최대 이익을 출력한다.
 * 
 * @author semin.kim
 *
 */

public class BOJ_14501_퇴사_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int day_cnt; // 총 상담일 수 

	static class day {
		int time;
		int pay;


		@Override
		public String toString() {
			return "day [time=" + time + ", pay=" + pay + "]";
		}

	}

	static day[] days;
	static int[] profits; // i번째 일까지 상담을 하셨을 때 얻을 수 있는 최대 이익을 저장하는 1차원 배열

	public static int calculateMaxProfit() {

		int MAX = 0; // 최대 이익

		for(int nextIdx = 2; nextIdx <= day_cnt; nextIdx++) {
			for(int currIdx = 1; currIdx < nextIdx; currIdx++) {
				if(nextIdx - currIdx >= days[currIdx].time) { // 다음 상담일과 현재 상담일의 차이가 현재 상담에 걸리는 시간 이상인 경우...
					profits[nextIdx] = Math.max(days[nextIdx].pay + profits[currIdx], profits[nextIdx]);
				}
			}
		}

		
		for(int idx = 1; idx <= day_cnt; idx++) {
			if(idx + days[idx].time <= day_cnt + 1) { // 회사에 남아있는 경우...
				if(MAX < profits[idx]) MAX = profits[idx]; // 최대 이익 갱신
			}
		}

		return MAX;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		day_cnt = Integer.parseInt(br.readLine().trim()); // 총 상담일 수 입력
		days = new day[day_cnt+1];
		profits = new int[day_cnt+1];

		// 상담을 하는데 걸리는 기간과 금액 입력
		for(int day_idx = 1; day_idx <= day_cnt; day_idx++) {
			st = new StringTokenizer(br.readLine().trim());
			days[day_idx] = new day();
			days[day_idx].time = Integer.parseInt(st.nextToken());
			days[day_idx].pay = Integer.parseInt(st.nextToken());
			profits[day_idx] = days[day_idx].pay;
		}


		calculateMaxProfit();
		System.out.println(calculateMaxProfit());
	}
}
