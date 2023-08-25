package baekjoon;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_17281_야구
 * 
 * 제한 시간 1초
 * 이닝 수는 2이상 50이하
 * 1번 선수는 4번 타자로 고정
 * 나머지 8명의 선수의 타순을 정하여 가장 많은 득점을 하는 타순의 총 득점을 구하자.
 * 8명을 순서대로 나열하는 경우의 수는 8! = 40,320
 * 50 * 40,320 = 2,016,000
 * 
 * @author semin.kim
 *
 */

public class BOJ_17281_야구_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int inningCount; // 이닝 수
	static final int HITTER_COUNT = 9; // 타자 수는 9명으로 고정
	static int[][] hitterResult;
	static boolean[] visited;
	static int[] numList = {1,2,3,5,6,7,8,9};
	static int[] selectList;
	static int maxScore;
	static boolean[] bases;

	private static void CalculateMax() {
		int score = 0;
		int currentInning = 1; // 1회부터 시작
		int outCount = 0; // 아웃카운트
		int currentHitter = 1;
		
		while(currentInning <= inningCount) { // 모든 이닝 진행 시 경기 종료

			// 1번 타자부터 3번 타자
			for(int idx = 0; idx < 3; idx++) {
				if(hitterResult[currentInning][selectList[idx]] == 0) {
					outCount++;
				} else {
					for(int run = 1; run <= hitterResult[currentInning][selectList[idx]]; run++) {
						for(int base = 3; base >= 1; base--) {
							bases[base] = bases[base - 1];
						}
					}
				}
				if(outCount == 3) { // 아웃카운트 3개인 경우... 이닝 종료
					bases = new boolean[4]; // 베이스 초기화
					currentInning++; // 다음 이닝 진행
				}
			}
			// 4번 타자
			if(hitterResult[currentInning][0] == 0) {
				outCount++;
			}
			if(outCount == 3) { // 아웃카운트 3개인 경우... 이닝 종료
				bases = new boolean[4]; // 베이스 초기화
				currentInning++; // 다음 이닝 진행
			}
			for(int run = 1; run <= hitterResult[currentInning][0]; run++) {
				for(int base = 3; base >= 1; base--) {
					bases[base] = bases[base-1];
				}
			}
			// 5번 타자부터 9번 타자
			for(int idx = 3; idx < HITTER_COUNT; idx++) {
				if(hitterResult[currentInning][selectList[idx]] == 0) {
					outCount++;
				} else {
					for(int run = 1; run <= hitterResult[currentInning][selectList[idx]]; run++) {
						for(int base = 3; base >= 1; base--) {
							bases[base] = bases[base - 1];
						}
					}
					
				}
				if(outCount == 3) { // 아웃카운트 3개인 경우... 이닝 종료
					bases = new boolean[4]; // 베이스 초기화
					currentInning++; // 다음 이닝 진행
				}
			}
		}
		if(score > maxScore) {
			maxScore = score;
		}
	}

	private static void Permutation(int currentIdx) {
		if(currentIdx == HITTER_COUNT - 1) { 
			for(int idx = 0; idx < 3; idx++) {
				System.out.print(selectList[idx]+ " ");
			}
			System.out.print(4 + " ");
			for(int idx = 3; idx < HITTER_COUNT - 1; idx++) {
				System.out.print(selectList[idx]+ " ");
			}
			System.out.println();
			return;
		}

		for(int idx = 0; idx < HITTER_COUNT - 1; idx++) {

			if(visited[idx]) continue;
			visited[idx] = true;

			selectList[currentIdx] = numList[idx];
			Permutation(currentIdx + 1);
			visited[idx] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		inningCount = Integer.parseInt(br.readLine().trim());
		hitterResult = new int[inningCount + 1][HITTER_COUNT + 1];
		visited = new boolean[HITTER_COUNT + 1];
		selectList = new int[HITTER_COUNT + 1];


		for(int idx = 1; idx <= inningCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int hitterIdx = 0; hitterIdx < HITTER_COUNT; hitterIdx++) {
				hitterResult[idx][hitterIdx] = Integer.parseInt(st.nextToken());
			}
		}

		Permutation(0);

	}
}
