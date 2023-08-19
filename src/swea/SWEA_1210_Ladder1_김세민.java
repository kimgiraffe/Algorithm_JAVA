package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_1210_Ladder1_D4
 * 1. 사다리를 2차원 배열에 저장한다.
 * 2. 도착점부터 시작하여 좌우로 이동할 수 있다면
 * 3. 역 방향으로 이동한다.
 * 4. 이동할 수 없다면 위로 이동하여 시작위치를 찾는다.
 * @author semin.kim
 */

public class SWEA_1210_Ladder1_김세민 {

	static BufferedReader br;
	static final int T = 10; //테스트 케이스의 개수
	static final int LadderSize = 100; // 사다리 크기
	static int[][] ladder; // 2차원 배열 사다리
	
	/**
	 * findStart method 구현
	 * 1. 도착 좌표를 저장한다.
	 * 2. 반복문을 통해 사다리를 타고 좌, 우로 이동할 수 있다면 각각 우, 좌로 이동하고
	 * 3. 그렇지 않다면 위로 이동한다.
	 * 4. 맨 윗줄(col == 0)이라면 출발점의 row 좌표를 반환한다.
	 * @return
	 */
	public static int findStart() {
		for(int endRow = 0; endRow < LadderSize; endRow++) {
			if(ladder[LadderSize-1][endRow] != 2) continue;
			int row = endRow;
			int col = LadderSize - 1;
			
			while(col < LadderSize) {
				if(row > 0 && ladder[col][row-1] == 1) { // 오른쪽으로 이동할 수 있는 경우
					while(row > 0 && ladder[col][row-1] == 1) {
						row--; //왼쪽으로 이동
					}
				} else if(row < LadderSize - 1 && ladder[col][row+1] == 1) { // 왼쪽으로 이동할 수 있는 경우
					while(row < LadderSize - 1 && ladder[col][row+1] == 1) {
						row++; //오른쪽으로 이동
					}
				}
				// 위로 이동
				col--;
				
				// 출발 지점에 도착했는지 확인
				if(col == 0) {
					return row;
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= T; test_case++) {
			br.readLine();
			ladder = new int[LadderSize][LadderSize];
			
			for(int col = 0; col < LadderSize; col++) {
				String[] line = br.readLine().split(" ");
				for(int row = 0; row < LadderSize; row++) {
					ladder[col][row] = Integer.parseInt(line[row]);
				}
			}
			
			System.out.println("#" + test_case + " " + findStart());
		}
		
		br.close();
	}

}
