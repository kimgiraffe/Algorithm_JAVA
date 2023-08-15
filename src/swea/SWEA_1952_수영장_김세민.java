package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1952_수영장
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.각 이용권의 요금과 각 달의 이용 계획을 입력받는다.
 * 3.최소 비용을 1년 이용권 비용으로 초기화한다.
 * 4.깊이 우선 탐색으로 가장 적은 비용을 찾는다.
 * 	4-1. 해당 달의 이용 계획이 없는 경우... 다음 달로 이동하여 계속 탐색
 * 	4-2. 해당 달의 이용 계획을 각각 1일, 1달, 3달 이용권으로 이용하는 비용 계산
 * 	4-3. 기존의 최소 비용을 넘어서는 경우... 무시
 * 	4-4. 모든 달의 이용 계획을 탐색한 경우... 최소 비용 갱신
 * 5.가장 작은 비용으로 이용할 수 있는 비용을 출력
 * @author semin.kim
 */

public class SWEA_1952_수영장_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; // 테스트 케이스의 개수
	static int daily_fee, monthly_fee, quarter_fee, yearly_fee; // 1일, 1달, 3달, 1년 이용권 비용
	static int[] plans; // 각 달의 이용 계획을 저장하는 1차원 배열
	static int MIN_FEE; // 가장 적게 지출하는 비용
	
	public static void best_plan(int month, int fee) {
		if(MIN_FEE <= fee) return; // 기존의 최소 비용을 넘어서는 경우 
		
		if(month >= 12) { // 모든 달을 확인한 경우...
			MIN_FEE = Math.min(MIN_FEE, fee); // 최소 비용 갱신
			return;
		}
		
		if(plans[month] == 0) { // 해당 달의 이용 계획이 없는 경우...
			best_plan(month + 1, fee); // 다음 달로 이동하여 탐색
		} else {
			best_plan(month + 1, fee + daily_fee * plans[month]); // 해당 달을 1일 이용권으로 이용하는 경우...
			
			best_plan(month + 1, fee + monthly_fee); // 해당 달을 1달 이용권으로 이용하는 경우...
			
			best_plan(month + 3, fee + quarter_fee); // 해당 달을 3달 이용권으로 이용하는 경우...
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		plans = new int[12];
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			
			daily_fee = Integer.parseInt(st.nextToken()); // 1일 이용권 비용 입력
			monthly_fee = Integer.parseInt(st.nextToken()); // 1달 이용권 비용 입력
			quarter_fee = Integer.parseInt(st.nextToken()); // 3달 이용권 비용 입력
			yearly_fee = Integer.parseInt(st.nextToken()); // 1년 이용권 비용 입력
			
			MIN_FEE = yearly_fee; // 최소 비용을 1년 이용권 비용으로 초기화
			
			st = new StringTokenizer(br.readLine().trim());
			// 1월부터 12월까지의 이용 계획 입력
			for(int idx = 0; idx < 12; idx++) {
				plans[idx] = Integer.parseInt(st.nextToken());
			}
			
			best_plan(0, 0);
			
			sb = new StringBuilder();
			sb.append('#').append(test_case).append(' ').append(MIN_FEE);
			System.out.println(sb);
		}
	}
}
