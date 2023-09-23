package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_14499_주사위굴리기
 * 크기가 N×M인 지도가 존재한다. 지도의 오른쪽은 동쪽, 위쪽은 북쪽이다. 
 * 이 지도의 위에 주사위가 하나 놓여져 있으며, 주사위의 전개도는 아래와 같다. 
 * 지도의 좌표는 (r, c)로 나타내며, r는 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로부터 떨어진 칸의 개수이다. 
 * 주사위는 지도 위에 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태로 놓여져 있으며, 놓여져 있는 곳의 좌표는 (x, y) 이다. 
 * 가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.
 * 지도의 각 칸에는 정수가 하나씩 쓰여져 있다. 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 
 * 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
 * 주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어졌을 때, 주사위가 이동했을 때 마다 상단에 쓰여 있는 값을 구하는 프로그램을 작성하시오.
 * 주사위는 지도의 바깥으로 이동시킬 수 없다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.
 * 
 * 
 * @author semin.kim
 */

public class BOJ_14499_주사위굴리기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int colSize, rowSize; // 지도의 세로, 가로 크기
	static int diceCol, diceRow; // 주사위를 놓은 곳의 좌표
	static int orderCount; // 명령의 개수
	static int[][] map;
	static int[] deltaCol = {0, 0, 0, -1, 1};
	static int[] deltaRow = {0, 1, -1, 0, 0};
	static int[] dice = new int[6];
	
	private static void Simulation(int dir) {
		int nextCol = diceCol + deltaCol[dir];
		int nextRow = diceRow + deltaRow[dir];
		
		if(!isValidRange(nextCol, nextRow)) return;
		
		int temp = dice[0];
		
		switch (dir) {
		case 1:
			diceRow++;
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = temp;
			break;
		case 2:
			diceRow--;
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = temp;
			break;
		case 3:
			diceCol--;
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = temp;
			break;
		case 4:
			diceCol++;
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
			break;
		}
		
		
		if(map[diceCol][diceRow] == 0) {
			map[diceCol][diceRow] = dice[5];
		} else {
			dice[5] = map[diceCol][diceRow];
			map[diceCol][diceRow] = 0;
		}
		
		sb.append(dice[0]).append("\n");
	}

	/**
	 * 지도의 바깥을 벗어나는지 체크하는 메서드
	 * @param col
	 * @param row
	 * @return true(유효한 범위) / false (유효하지 않은 범위)
	 */
	private static boolean isValidRange(int col, int row) {
		if(col < 0 || col >= colSize || row < 0 || row >= rowSize) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		diceCol = Integer.parseInt(st.nextToken());
		diceRow = Integer.parseInt(st.nextToken());
		orderCount = Integer.parseInt(st.nextToken());
		
		map = new int[colSize][rowSize];
		
		for(int col = 0; col < colSize; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < rowSize; row++) {
				map[col][row] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int order = 1; order <= orderCount; order++) {
			int direction = Integer.parseInt(st.nextToken());
			Simulation(direction);
		}
		
		System.out.print(sb);
	}
}
