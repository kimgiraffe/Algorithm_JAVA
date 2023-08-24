package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_15961_회전초밥
 * 
 * 벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다
 * 1.벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다.
 * 2.각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고, 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다. 
 * 만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공한다.
 * 위 할인 행사에 참여하여 가능한 한 다양한 종류의 초밥을 먹으려고 한다
 * 회전 초밥 음식점의 벨트 상태, 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 접시의 개수, 쿠폰 번호가 주어졌을 때, 
 * 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램을 작성
 * =====================================================================================================
 * 최대한 다양한 초밥을 먹으려면... 연속해서 k개의 접시를 먹고, 
 * 쿠폰에 쓰인 초밥 종류가 벨트 위에 업는 경우엔 + 1
 * 
 * 
 * @author semin.kim
 *
 */

public class BOJ_15961_회전초밥_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int plateCount, sushiKindCount, consecutivePlates, couponIndex; // 접시의 수, 초밥의 가짓수, 연속해서 먹는 접시의 수, 쿠폰 번호

	static int[] sushi;
	static ArrayList<Integer> plateList; // 벨트의 상태를 저장
	static int MAX; // 먹을 수 있는 초밥 가짓수의 최댓값

	private static void init() {
		int count = 0;
		for(int idx = 0; idx < consecutivePlates; idx++) {
			if(sushi[plateList.get(idx)] == 0) {
				sushi[plateList.get(idx)] = 1;
			}
		}
		
		for(int idx = 1; idx <= sushiKindCount; idx++) {
			if(sushi[idx] == 1) {
				count++;
			}
		}
		MAX = count;
	}
	
	private static void calculateMax() {

		int start = 1;

		while(start + consecutivePlates < plateCount) {
			int count = 0;
			for(int idx = start; idx <= start + consecutivePlates; idx++) {
				if(sushi[plateList.get(idx)] == 0) { // 새로운 초밥인 경우...
					sushi[plateList.get(idx)] = 1;
				}
			}

			for(int idx = 1; idx <= sushiKindCount; idx++) {
				if(sushi[idx] == 1) {
					count++;
				}
			}
			
			if(count > MAX) {
				MAX = count;
			}
			Arrays.fill(sushi, 0);
			start++;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		plateCount = Integer.parseInt(st.nextToken());
		sushiKindCount = Integer.parseInt(st.nextToken());
		consecutivePlates = Integer.parseInt(st.nextToken());
		couponIndex = Integer.parseInt(st.nextToken());

		sushi = new int[sushiKindCount + 1];

		plateList = new ArrayList<>();

		for(int idx = 0; idx < plateCount; idx++) {
			plateList.add(Integer.parseInt(br.readLine().trim()));
		}
		init();
		calculateMax();
		System.out.println(MAX);
	}

}
