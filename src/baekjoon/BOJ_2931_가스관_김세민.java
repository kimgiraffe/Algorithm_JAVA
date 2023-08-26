package baekjoon;

import java.io.*;
import java.util.*;

/**
 * BOJ_2931_가스관
 * 이 게임에서 유럽은 R행 C열로 나누어져 있다. 
 * 각 칸은 비어있거나, 아래 그림과 같은 일곱가지 기본 블록으로 이루어져 있다.
 * 가스는 모스크바에서 자그레브로 흐른다. 가스는 블록을 통해 양방향으로 흐를 수 있다. 
 * '+'는 특별한 블록으로, 아래 예시처럼 두 방향 (수직, 수평)으로 흘러야 한다.
 * 해커가 침임해 블록 하나를 지웠다. 지운 블록은 빈 칸이 되어있다.
 * 해커가 어떤 칸을 지웠고, 그 칸에는 원래 어떤 블록이 있었는지 구하는 프로그램을 작성
 * 항상 답이 존재하고, 가스의 흐름이 유일한 경우만 입력으로 주어진다, 
 * 또, 모스크바와 자그레브가 하나의 블록과 인접해 있는 입력만 주어진다.
 * 또, 불필요한 블록이 존재하지 않는다. 즉, 없어진 블록을 추가하면, 모든 블록에 가스가 흐르게 된다.
 * 
 * 파이프를 입력받으면, 연결 가능한 곳을 확인한다.
 * 모스크바부터 깊이 우선 탐색을 통해 파이프가 이어져 있는데 빈 칸을 만나는 경우 지워진 블록
 * 지워진 블록을 4방향 탐색으로 파이프의 모양을 결정한다.
 * 가스의 흐름은 유일하다는 사실에 유의
 * @author semin.kim
 */

public class BOJ_2931_가스관_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int DELTA_ROW[] = {-1, 0, 1, 0};
	static int DELTA_COL[] = {0, 1, 0, -1};
	static char gas[] = {'|', '-', '+', '1', '2', '3', '4'};
	static char[][] map;
	static boolean[][][] pipe;
	static int startRow, startCol, rowSize, colSize,N;
	static int[] dir = new int[4];

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map   = new char[rowSize][colSize];
		pipe  = new boolean[4][rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {
			char temp[] = br.readLine().toCharArray();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = temp[col];
				if(map[row][col] != '.') {
					//파이프 방향대로 true로 바꿔줌
					checkPipe(row, col,true);
				}
				if(map[row][col] == 'M') {
					startRow = row;
					startCol = col;
				}
			}
		}
		dfs(startRow,startCol);

	}
	private static void checkPipe(int i, int j, boolean flag) {
		switch (map[i][j]) {
		case '|': 
			pipe[0][i][j] = flag;
			pipe[2][i][j] = flag;
			break;
		case '-': 
			pipe[1][i][j] = flag;
			pipe[3][i][j] = flag;
			break;
		case '1': 
			pipe[1][i][j] = flag;
			pipe[2][i][j] = flag;
			break;
		case '2': 
			pipe[0][i][j] = flag;
			pipe[1][i][j] = flag;
			break;
		case '3': 
			pipe[0][i][j] = flag;
			pipe[3][i][j] = flag;
			break;
		case '4': 
			pipe[2][i][j] = flag;
			pipe[3][i][j] = flag;
			break;
		case 'M':
			pipe[0][i][j] = flag;
			pipe[1][i][j] = flag;
			pipe[2][i][j] = flag;
			pipe[3][i][j] = flag;
			break;
		case 'Z': 
			pipe[0][i][j] = flag;
			pipe[1][i][j] = flag;
			pipe[2][i][j] = flag;
			pipe[3][i][j] = flag;
			break;
		case '+': 
			pipe[0][i][j] = flag;
			pipe[1][i][j] = flag;
			pipe[2][i][j] = flag;
			pipe[3][i][j] = flag;
			break;
		}

	}
	private static void dfs(int row, int col) {
		for (int idx = 0; idx < 4; idx++) {
			int nextRow = row + DELTA_ROW[idx];
			int nextCol = col + DELTA_COL[idx];

			//범위를 넘는 경우...
			if(nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) continue;

			//앞이 비어있는데 연결하는 파이프가 있는경우(해커가 훔쳐간 곳)...
			if(map[nextRow][nextCol] == '.' && pipe[idx][row][col]
					&& map[row][col] != 'M' && map[row][col] != 'Z') {

				//4방향 탐색 하면서 이어질 수 있는 파이프를 찾기
				for (int p = 0; p < 4; p++) {
					int pnx = nextRow + DELTA_ROW[p];
					int pny = nextCol + DELTA_COL[p];
					//범위를 넘는 경우...
					if(pnx < 0 || pnx >= rowSize || pny < 0 || pny >= colSize) continue;
					//모스크바와 지그레브가 아닌 경우...
					if(map[pnx][pny] == 'M' || map[pnx][pny] == 'Z') continue;
					//해당 방향의 파이프 ++
					if(pipe[(p+2)%4][pnx][pny]) dir[p]++;
				}

				findPipe(nextRow,nextCol);
			}
			//빈 칸인 경우...
			else if(map[nextRow][nextCol] == '.') continue;

			//파이프가 이어질 수 있는 경우...
			if(pipe[idx][row][col] && pipe[(idx+2)%4][nextRow][nextCol]) {
				//파이프 사용 불가능
				pipe[idx][row][col] = false;
				pipe[(idx+2)%4][nextRow][nextCol] = false;
				dfs(nextRow,nextCol);
				//파이프 사용 가능
				pipe[idx][row][col] = true;
				pipe[(idx+2)%4][nextRow][nextCol] = true;
			}
		}
	}
	private static void findPipe(int row , int col) {
		char ch = 'n';
		if(dir[0] == 1 && dir[1] == 1 && dir[2] == 1 && dir[3] == 1) ch = '+';
		else if(dir[0] == 1 && dir[2] == 1) ch = '|';
		else if(dir[1] == 1 && dir[3] == 1) ch = '-';
		else if(dir[1] == 1 && dir[2] == 1) ch = '1';
		else if(dir[0] == 1 && dir[1] == 1) ch = '2';
		else if(dir[0] == 1 && dir[3] == 1) ch = '3';
		else if(dir[2] == 1 && dir[3] == 1) ch = '4';

		System.out.println((row+1)+" "+(col+1)+" "+ch);
		System.exit(0);
	}
}