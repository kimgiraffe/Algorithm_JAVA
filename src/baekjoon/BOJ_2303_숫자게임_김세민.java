package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_2303_숫자게임
 * N(2이상 1000이하)명, 1부터 10사이의 수가 적인 카드 5장
 * 그 중 세 장을 골라 합을 구한 후 일의 자리 수가 가장 큰 사람이 승 
 * 일의 자리 수가 가장 큰 사람을 찾자
 * 
 * @author semin.kim
 */

public class BOJ_2303_숫자게임_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int people_cnt; // 사람의 수
	
	static class Player {
		int[] card = new int[5];

		@Override
		public String toString() {
			return "Player [card=" + Arrays.toString(card) + "]";
		}
		
		
	}
	
	static Player[] players;
	
	static int[] selected = new int[3];
	
	static int MAX;
	
	static int winner_idx, winner_max;
	
	public static void combination(int currentIdx, int selectIdx, int playerIdx) {
		if(selectIdx == 3) { // 3장의 카드를 모두 뽑은 경우...
			// 다 더해서 일의 자리수 구하기
			int sum = 0;
			for(int idx = 0; idx < 3; idx++) {
				sum += selected[idx];
			}
			sum %= 10;
			
			// 최댓값 갱신
			if(sum > MAX) MAX = sum;
			return;
		}
		
		if(currentIdx == 5) {
			return;
		}
		selected[selectIdx] = players[playerIdx].card[currentIdx];
		combination(currentIdx + 1, selectIdx + 1, playerIdx);
		
		selected[selectIdx] = 0;
		combination(currentIdx + 1, selectIdx, playerIdx);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		people_cnt = Integer.parseInt(br.readLine().trim());
		
		players = new Player[people_cnt+1]; // 번호는 1부터 시작
		
		// 각 사람이 가진 카드 입력
		for(int idx = 1; idx <= people_cnt; idx++) {
			players[idx] = new Player();
			st = new StringTokenizer(br.readLine().trim());
			for(int cardIdx = 0; cardIdx < 5; cardIdx++) {
				players[idx].card[cardIdx] = Integer.parseInt(st.nextToken());
			}
		}
		for(int idx = 1; idx <= people_cnt; idx++) {
			combination(0, 0, idx); // 각 사람의 점수 계산
			if(MAX >= winner_max) { // 점수가 크거나 같을 경우 승자의 번호를 갱신
				winner_idx = idx;
				winner_max = MAX;
			}
			MAX = 0; // 다음 사람의 점수 초기화
		}
		
		System.out.println(winner_idx);
	}
}
