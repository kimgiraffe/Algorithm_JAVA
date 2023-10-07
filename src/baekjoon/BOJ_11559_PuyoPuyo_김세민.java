package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_11559_PuyoPuyo
 * 
 * @author semin.kim
 * 
 * [문제]
 * 필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
 * 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
 * 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
 * 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
 * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
 * 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하자.
 * 
 * [풀이]
 * 1. 필드를 탐색하여 뿌요를 찾는다.
 * 2. 너비우선탐색을 통해 인접한 칸 4방 탐색하여 같은 색의 뿌요의 개수를 계산한다.
 * 3. 터짐이 이루어진 경우 중력의 영향을 받아 뿌요를 아래로 떨어뜨린다. 
 * 4. 연쇄 터짐의 횟수를 출력한다.
 * 
 */

public class BOJ_11559_PuyoPuyo_김세민 {

	static BufferedReader br;
	
	static final int COL_SIZE = 12; // 세로 줄의 크기는 12로 고정
	static final int ROW_SIZE = 6; // 가로 줄의 크기는 6으로 고정
	static final int[] DELTA_COL = {-1, 1, 0, 0}; // 상, 하
	static final int[] DELTA_ROW = {0, 0, -1, 1}; // 좌, 우
	
	static char[][] field = new char[COL_SIZE][ROW_SIZE]; // 필드 정보를 저장할 2차원 배열
	static boolean[][] visited;
	static boolean canExplode; // 터짐이 가능한지 여부를 나타내는 flag 변수
	static int explodeCount;
	
	static class Position {
		int col;
		int row;
		
		public Position(int col, int row) {
			this.col = col;
			this.row = row;
		}

		@Override
		public String toString() {
			return "Position [col=" + col + ", row=" + row + "]";
		}
	}
	
	private static boolean isValidRange(int col, int row) {
		return col >= 0 && row >= 0 && col < COL_SIZE && row < ROW_SIZE;
	}
	
	private static void explode(int col, int row) {
		Queue<Position> queue = new ArrayDeque<>();
		
		List<Position> puyoList = new ArrayList<>();
		puyoList.add(new Position(col, row)); // 뿌요리스트에 추가
		
		visited[col][row] = true; // 방문 처리
		queue.offer(new Position(col, row));
				
		int sameColorCount = 1; // 같은 색인 뿌요의 개수는 1로 초기화
		
		while(!queue.isEmpty()) {
			Position curr = queue.poll();
			
			// 4방향 탐색
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				// 필드의 범위를 벗어나는 경우... 무시
				if(!isValidRange(nextCol, nextRow)) continue;
				
				// 방문한 경우... 무시
				if(visited[nextCol][nextRow]) continue;
				
				if(field[nextCol][nextRow] == field[curr.col][curr.row]) {
					sameColorCount++; // 같은 색인 뿌요의 개수 1 증가
					visited[nextCol][nextRow] = true; // 방문 처리
					queue.offer(new Position(nextCol, nextRow));
					puyoList.add(new Position(nextCol, nextRow));
				}
			}
		}
		
		if(sameColorCount >= 4) { // 같은 색의 뿌요들이 4개 이상인 경우...
			for(Position puyo : puyoList) {
				field[puyo.col][puyo.row] = '.'; // 같은 색의 뿌요들이 터짐
				
				canExplode = true; // 터짐 가능
			}
		}
	}
	
	private static void drop() {
		for(int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
			for(int colIdx = COL_SIZE - 1; colIdx > 0; colIdx--) {
				if(field[colIdx][rowIdx] == '.') { // 빈 칸인 경우...
					// 빈 칸 위 칸들을 확인
					for(int tempColIdx = colIdx - 1; tempColIdx >= 0; tempColIdx--) {
						if(field[tempColIdx][rowIdx] != '.') { // 빈 칸이 아닌 경우 -> 떨어질 뿌요가 있음
							field[colIdx][rowIdx] = field[tempColIdx][rowIdx]; // 뿌요가 떨어짐
							field[tempColIdx][rowIdx] = '.'; // 뿌요가 떨어지면 해당 칸은 빈 칸이 됨
							break;
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 필드 정보 입력
		for(int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
			String line = br.readLine().trim();
			for(int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
				field[colIdx][rowIdx] = line.charAt(rowIdx);
			}
		}
		
		while (true) {
			canExplode = false;
			visited = new boolean[COL_SIZE][ROW_SIZE];
			
			for(int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
				for(int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
					// 뿌요가 있는 경우...
					if(field[colIdx][rowIdx] != '.') {
						explode(colIdx, rowIdx);
					}
				}
			}
			
			// 뿌요들 떨어뜨리기
			drop();
			
			// 더 이상 터질 수 없다면 종료
			if(canExplode == false) {
				break;
			}
			
			explodeCount++; // 연쇄 터짐 횟수 1 증가
		}
		
		System.out.println(explodeCount);
	}
}
