package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_4013_특이한자석
 * 
 * 1. 시계방향 회전시킬 경우 right shift, 반시계방향 회전시킬 경우 left shift
 * 2. 1번 자석의 2번째 bit과 2번 자석의 6번째 bit이 다른 경우 반대 방향으로 1칸 shift
 * 3. 모든 자석의 회전이 끝난 후 자석의 0번째 bit 확인 
 * 
 * @author semin.kim
 *
 */

public class SWEA_4013_특이한자석_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testcaseCount;
	static int rotateCount;
	static final int MAGNET_COUNT = 4; // 자석의 개수는 4로 고정
	static final int BLADE_COUNT = 8; // 날의 개수는 8로 고정
	static int[] magnet;
	static final int CLOCK_WISE = 1;
	static final int ANTI_CLOCK_WISE = -1;
	static int score;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testcaseCount = Integer.parseInt(br.readLine().trim());
		
		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			rotateCount = Integer.parseInt(br.readLine().trim());
			
			magnet = new int[MAGNET_COUNT];
			score = 0;
			
			for(int magnetIdx = 0; magnetIdx < MAGNET_COUNT; magnetIdx++) {
				String input = br.readLine().replaceAll(" ", "");
				int[] tmp = new int[BLADE_COUNT];
				for(int bladeIdx = 0; bladeIdx < BLADE_COUNT; bladeIdx++) {
					tmp[bladeIdx] = input.charAt(bladeIdx) - '0';
					magnet[magnetIdx] += tmp[bladeIdx] * Math.pow(2, BLADE_COUNT - bladeIdx - 1);
				}
			}
			
			for(int rotateIdx = 0; rotateIdx < rotateCount; rotateIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				int magnetNum = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				if(dir == CLOCK_WISE) {
					magnet[magnetNum-1] = magnet[magnetNum-1] >> 1;
				} else if(dir == ANTI_CLOCK_WISE) {
					magnet[magnetNum-1] = magnet[magnetNum-1] << 1;
				}
				
				for(int idx = 0; idx < MAGNET_COUNT - 1; idx++) {
					if(((magnet[idx] & (1 << 2)) ^ (magnet[idx + 1] & (1 << BLADE_COUNT - 2))) == 1) {
						
					}
				}
			}
			
			for(int idx = 0; idx < MAGNET_COUNT; idx++) {
				score += Math.pow(2, (magnet[idx] & (1 << 0)));
			}
			
			sb.append("#").append(testcase).append(" ").append(score);
			
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
