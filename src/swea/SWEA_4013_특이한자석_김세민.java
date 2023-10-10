package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_4013_특이한자석
 * 
 * 1. 회전하려는 자석의 오른쪽 자석과 왼쪽 자석에 대한 회전 방향 정보를 저장한다.
 * 2. 자석을 순회하며 자석을 회전한다.
 * 3. 총 점수 합을 비트 연산을 통해 계산한다.
 * 4. 총 점수 합을 출력한다.
 * 
 * @author semin.kim
 *
 */

public class SWEA_4013_특이한자석_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testcaseCount; // 테스트케이스 수
	static int rotateCount; // 회전 횟수
	static final int MAGNET_COUNT = 4; // 자석의 개수는 4로 고정
	static final int BLADE_COUNT = 8; // 날의 개수는 8로 고정
	static int[][] magnet;
	static int[] rotate;
	static final int CLOCK_WISE = 1;
	static final int ANTI_CLOCK_WISE = -1;
	static int score; // 점수의 총 합

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		testcaseCount = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수 입력

		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			rotateCount = Integer.parseInt(br.readLine().trim()); // 회전 횟수 입력

			magnet = new int[MAGNET_COUNT][BLADE_COUNT];
			score = 0; // 점수 총 합 초기화

			// 자석의 자성 정보 입력
			for(int magnetIdx = 0; magnetIdx < MAGNET_COUNT; magnetIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int bladeIdx = 0; bladeIdx < BLADE_COUNT; bladeIdx++) {
					magnet[magnetIdx][bladeIdx] = Integer.parseInt(st.nextToken());
				}
			}

			for(int rotateIdx = 0; rotateIdx < rotateCount; rotateIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				int magnetNum = Integer.parseInt(st.nextToken()) - 1; // 회전시키려는 자석의 번호 입력
				int dir = Integer.parseInt(st.nextToken()); // 회전 방향 입력
				
				rotate = new int[MAGNET_COUNT]; // 자석의 회전하려는 방향을 저장하는 1차원 배열

				rotate[magnetNum] = dir;
				
				// 오른쪽 자석 확인
				for(int idx = magnetNum + 1; idx < MAGNET_COUNT; idx++) {
					// 같은 극인 경우...
					if(magnet[idx-1][2] == magnet[idx][BLADE_COUNT - 2]) {
						break;
					}
					else { // 다른 극인 경우...
						rotate[idx] = -rotate[idx-1]; // 반대 방향으로 회전 방향 설정
					}
				}
				
				// 왼쪽 자석 확인
				for(int idx = magnetNum - 1; idx >= 0; idx--) {
					// 같은 극인 경우...
					if(magnet[idx][2] == magnet[idx+1][BLADE_COUNT - 2]) {
						break;
					}
					else { // 다른 극인 경우...
						rotate[idx] = -rotate[idx+1]; // 반대 방향으로 회전 방향 설정
					}
				}
				
				for(int idx = 0; idx < MAGNET_COUNT; idx++) {
					if(rotate[idx] == 0) continue; // 회전할 필요 없는 경우...
					else if(rotate[idx] == CLOCK_WISE) { // 시계 방향
						int temp = magnet[idx][BLADE_COUNT - 1];
						for(int j = BLADE_COUNT - 1; j > 0; j--) {
							magnet[idx][j] = magnet[idx][j-1]; 
						}
						magnet[idx][0] = temp;
					}
					else if(rotate[idx] == ANTI_CLOCK_WISE) { // 반시계 방향
						int temp = magnet[idx][0];
						for(int j = 0; j < BLADE_COUNT - 1; j++) {
							magnet[idx][j] = magnet[idx][j+1]; 
						}
						magnet[idx][BLADE_COUNT-1] = temp;
					}
				}
				
			}

			// 총 점수 합 계산
			for(int idx = 0; idx < MAGNET_COUNT; idx++) {
				if(magnet[idx][0] == 1) {
					score += (1 << idx);
				}
			}

			sb.append("#").append(testcase).append(" ").append(score).append("\n");
		}
		
		System.out.print(sb);
	}
}
