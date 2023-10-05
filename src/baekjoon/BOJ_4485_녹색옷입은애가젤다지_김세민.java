package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ_4485_녹색옷입은애가젤다지
 * 
 * @author semin.kim
 *
 * 1. 지도의 크기를 입력받는다.
 * 2. 지도 정보를 입력받아 2차원 배열에 저장한다.
 * 3. 지도를 상하좌우 4방향 탐색한다.
 * 4. 해당 위치까지 이동하는데 잃는 최소 루피를 갱신하여 loss 2차원 배열에 저장한다.
 * 5. 시작 위치에서 잃은 루피와 제일 오른쪽 아래 칸까지 이동하는데 잃는 최소 루피를 더하여 출력한다.
 */
public class BOJ_4485_녹색옷입은애가젤다지_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] DELTA_COL = {1, 0, -1, 0}; // 하 상
	static final int[] DELTA_ROW = {0, 1, 0, -1}; // 우 좌
	static int[][] map, loss;
	static int mapSize; // 지도의 크기
	static PriorityQueue<Position> pq;
	static int testcase = 1; // 테스트케이스 번호
	
	static class Position implements Comparable<Position>{
		int col; int row; int loss;
		
		public Position(int col, int row, int loss) {
			this.col = col;
			this.row = row;
			this.loss = loss;
		}

		@Override
		public int compareTo(Position o) {
			return this.loss - o.loss;
		}

		@Override
		public String toString() {
			return "Position [col=" + col + ", row=" + row + ", loss=" + loss + "]";
		}
	}
	
	/**
	 * 지도의 범위를 벗어나는지 확인하는 메서드
	 * @param col
	 * @param row
	 * @return
	 */
	private static boolean isValidRange(int col, int row) {
		return col >= 0 && row >= 0 && col < mapSize && row < mapSize;
	}
	
	private static void CalculateMinimumLoss() {
		pq = new PriorityQueue<>();
		// 우선순위 큐에 시작 위치 삽입
		pq.offer(new Position(0, 0, 0));
		loss[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Position curr = pq.poll();
			
			if(loss[curr.col][curr.row] < curr.loss) {
				continue;
			}
			
			// 4방향 탐색
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				// 지도의 범위를 벗어나는 경우...무시
				if(!isValidRange(nextCol, nextRow)) continue;
	
				if(loss[nextCol][nextRow] > curr.loss + map[nextCol][nextRow]) {
					// 해당 위치까지 가는데 잃는 최소 루피 갱신
					loss[nextCol][nextRow] = curr.loss + map[nextCol][nextRow];
					// 우선순위 큐에 삽입
					pq.offer(new Position(nextCol, nextRow, loss[nextCol][nextRow]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while(true) {
			// 지도의 크기 입력
			mapSize = Integer.parseInt(br.readLine().trim());
			
			// 지도의 크기가 0인 경우...
			if(mapSize == 0) {
				break; // 전체 입력 종료
			}
			
			map = new int[mapSize][mapSize];
			loss = new int[mapSize][mapSize];
			
			// 지도 정보 입력
			for(int colIdx = 0; colIdx < mapSize; colIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
					map[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
					loss[colIdx][rowIdx] = Integer.MAX_VALUE;
				}
			}
			
			CalculateMinimumLoss();
			
//			for(int colIdx = 0; colIdx < mapSize; colIdx++) {
//				for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
//					System.out.print(loss[colIdx][rowIdx] + " ");
//				}
//				System.out.println();
//			}
			
			sb.append("Problem ").append(testcase++).append(": ").append(map[0][0]+loss[mapSize-1][mapSize-1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
