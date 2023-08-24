package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SWEA_1767_프로세서연결하기
 * 가로 N개 x 세로 N개의 cell로 구성
 * 1개의 cell에는 1개의 Core 혹은 1개의 전선
 * 가장 자리에는 전원이 흐르고 있다.
 * Core와 전원을 연결하는 전선은 직선으로만 설치가 가능, 전선은 절대로 교차해서는 안 된다.
 * 가장자리에 위치한 Core는 이미 전원이 연결된 것으로 간주
 * 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구하고자 한다.
 * 단, 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.
 * 
 * Core의 개수는 최소 1개 이상 12개 이하
 * 
 * 가장자리에 위치하지않은 모든 Core에 대하여 깊이우선탐색으로 최대한 많은 Core에 전원을 연결한다.
 * 이 때, 전선 길이의 합을 구한다.
 * 만약, 그러한 방법이 여러가지일 경우, 전선 길이의 합을 비교하여 갱신한다.
 * 
 * @author semin.kim
 *
 */

public class SWEA_1767_프로세서연결하기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testcaseCount; // 테스트 케이스의 개수
	static int cellSize;
	static int[][] cell;

	static final int[] DELTA_ROW = {-1, 1, 0, 0};
	static final int[] DELTA_COL = {0, 0, -1, 1};

	static class Core {
		int row;
		int col;

		public Core(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Core [row=" + row + ", col=" + col + "]";
		}
	}

	static ArrayList<Core> coreList; 

	static int maxConnected; // 전원에 연결된 Core의 수(이미 연결된 Core제외)
	static int minWireLength; // 전선 길이의 합의 최소

	private static void dfs(int coreIdx, int connectedCore, int wireLength) { 
		// coreIdx: 전원에 연결할 Core의 번호, connectedCore: 전원에 연결된 Core수, wireLength: 사용한 전선 길이 합 
		if(coreIdx == coreList.size()) { // 모든 Core를 탐색한 경우...
			if(maxConnected < connectedCore) { // 전웬에 연결된 Core 수가 기존값보다 큰 경우...
				maxConnected = connectedCore;
				minWireLength = wireLength;
			} else if(maxConnected == connectedCore) { // 전원에 연결된 Core 수가 기존값과 동일한 경우...
				if(minWireLength > wireLength) { // 전선 길이의 합 비교
					minWireLength = wireLength; // 전선 길이의 합 갱신
				}
			}
			return;
		}

		// 선택된 Core의 위치 가져오기
		int row = coreList.get(coreIdx).row;
		int col = coreList.get(coreIdx).col;

		// 4방향 탐색
		for(int dir = 0; dir < 4; dir++) {
			int count = 0;
			int nextRow = row;
			int nextCol = col;

			while(true) {
				nextRow += DELTA_ROW[dir];
				nextCol += DELTA_COL[dir];

				if(nextRow < 0 || nextRow >= cellSize || nextCol < 0 || nextCol >= cellSize)
				{
					break;
				}

				// Core가 존재하는 경우...전원에 연결 불가능
				if(cell[nextRow][nextCol] == 1) {
					count = 0;
					break;
				}
				// Core를 전원에 연결할 수 있는 경우... 사용한 전선의 길이만큼 추가
				count++;
			}

			int fillRow = row;
			int fillCol = col;

			// Core에 전원을 연결한 후에는 같은 위치에 전선을 둘 수 없음
			for(int idx = 0; idx < count; idx++) {
				fillRow += DELTA_ROW[dir];
				fillCol += DELTA_COL[dir];
				cell[fillRow][fillCol] = 1;
			}

			if(count == 0) { // Core를 전원에 연결할 수 없는 경우...
				dfs(coreIdx + 1, connectedCore, wireLength);
			} else { // Core를 전원에 연결할 수 있는 경우...
				dfs(coreIdx + 1, connectedCore + 1, wireLength + count);

				// 재사용을 위해 돌려주기
				fillRow = row;
				fillCol = col;

				for(int idx = 0; idx < count; idx++) {
					fillRow += DELTA_ROW[dir];
					fillCol += DELTA_COL[dir];
					cell[fillRow][fillCol] = 0;
				}

			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testcaseCount = Integer.parseInt(br.readLine().trim());

		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			sb = new StringBuilder();
			coreList = new ArrayList<>();
			cellSize = Integer.parseInt(br.readLine().trim());
			cell = new int[cellSize][cellSize];

			for(int row = 0; row < cellSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0; col < cellSize; col++) {
					cell[row][col] = Integer.parseInt(st.nextToken());
					if(cell[row][col] == 1) {
						if(row == 0 || row == cellSize - 1 || col == 0 || col == cellSize - 1) continue;
						coreList.add(new Core(row, col));
					}
				}
			}

			// 전원에 연결된 Core의 개수와 이 때 사용한 전선의 길이 합 초기화
			maxConnected = 0;
			minWireLength = Integer.MAX_VALUE;
			
			dfs(0, 0, 0);

			sb.append('#').append(testcase).append(' ').append(minWireLength);
			System.out.println(sb);
		}
	}
}
