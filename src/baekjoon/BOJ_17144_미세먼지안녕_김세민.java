package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ_17144_미세먼지안녕
 * 
 * 1. 격자판의 정보를 입력받아 2차원 배열에 저장하고 공기청정기와 미세먼지의 위치를 저장한다.
 * 2. 4방향 탐색하여 미세먼지의 확산이 일어난 후 격자판의 정보를 갱신한다.
 * 3. 공기청정기가 작동한 후 격자판의 정보를 갱신한다.
 * 4. T초동안 2~3을 반복한다.
 * 5. 격자판 정보를 저장한 2차원 배열을 순회하며 남아있는 미세먼지의 양을  구한다.
 * 
 * @author semin.kim
 *
 */

public class BOJ_17144_미세먼지안녕_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int colSize, rowSize, time;
	static int[][] map, copiedMap;
	static final int[] DELTA_COL = {-1, 1, 0, 0}; // 상,하
	static final int[] DELTA_ROW = {0, 0, -1, 1}; // 좌,우
	static List<Position> fineDustList = new ArrayList<>();
	static Position[] purifier = new Position[2];
	static int remainingFindDust = 0;

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
		return col >= 1 && row >= 1 && col <= colSize && row <= rowSize;
	}

	private static void copyMap() {
		for(int colIdx = 1; colIdx <= colSize; colIdx++) {
			for(int rowIdx = 1; rowIdx <= rowSize; rowIdx++) {
				copiedMap[colIdx][rowIdx] = map[colIdx][rowIdx];
			}
		}
	}

	private static void spreadFineDust(int col, int row) {

		int spreadDirCount = 0; // 확산된 방향의 개수
		int value = copiedMap[col][row] / 5;

		for(int dir = 0; dir < 4; dir++) {
			int nextCol = col + DELTA_COL[dir];
			int nextRow = row + DELTA_ROW[dir];

			// 범위를 벗어나지 않고 공기청정기가 없는 경우...
			if(isValidRange(nextCol, nextRow) && map[nextCol][nextRow] != -1) {
				spreadDirCount++;

				map[nextCol][nextRow] += value;
			}
		}

		map[col][row] -= value * (spreadDirCount);
	}

	private static void purify() {
		int antiClockWiseCol = purifier[0].col; // 위쪽 공기청정기 바람이 부는 시작 세로 위치
		int clockWiseCol = purifier[1].col; // 아래쪽 공기청정기 바람이 부는 시작 세로 위치

		// 위쪽 공기청정기의 바람은 반시계방향으로 순환
		map[antiClockWiseCol][2] = 0;

		// right
		for(int rowIdx = 3; rowIdx <= rowSize; rowIdx++) {
			map[antiClockWiseCol][rowIdx] = copiedMap[antiClockWiseCol][rowIdx - 1];
		}

		// up
		for(int colIdx = antiClockWiseCol - 1; colIdx >= 1; colIdx--) {
			map[colIdx][rowSize] = copiedMap[colIdx + 1][rowSize];
		}

		// left
		for(int rowIdx = rowSize - 1; rowIdx >= 1; rowIdx--) {
			map[1][rowIdx] = copiedMap[1][rowIdx + 1];
		}

		// down
		for(int colIdx = 2; colIdx < antiClockWiseCol; colIdx++) {
			map[colIdx][1] = copiedMap[colIdx - 1][1];
		}

		// 아래쪽 공기청정기의 바람은 시계방향으로 순환
		map[clockWiseCol][2] = 0;

		// right
		for(int rowIdx = 3; rowIdx <= rowSize; rowIdx++) {
			map[clockWiseCol][rowIdx] = copiedMap[clockWiseCol][rowIdx - 1];
		}

		// down
		for(int colIdx = clockWiseCol + 1; colIdx <= colSize; colIdx++) {
			map[colIdx][rowSize] = copiedMap[colIdx - 1][rowSize];
		}

		// left
		for(int rowIdx = rowSize - 1; rowIdx >= 1; rowIdx--) {
			map[colSize][rowIdx] = copiedMap[colSize][rowIdx + 1];
		}

		// up
		for(int colIdx = colSize - 1; colIdx > clockWiseCol; colIdx--) {
			map[colIdx][1] = copiedMap[colIdx + 1][1];
		}
	}


	private static void simulation() {
		while(time-- != 0) {

			// 미세먼지 확산
			for(Position position : fineDustList) {
				spreadFineDust(position.col, position.row);
			}
			// 맵 복사
			copyMap();
			
			// 공기청정기 작동
			purify();

			copyMap();
			
			// 미세먼지 리스트 초기화
			fineDustList = new ArrayList<>();

			for(int colIdx = 1; colIdx <= colSize; colIdx++) {
				for(int rowIdx = 1; rowIdx <= rowSize; rowIdx++) {
					if(map[colIdx][rowIdx] > 0) {
						fineDustList.add(new Position(colIdx, rowIdx));
					}
				}
			}

		}
	}

	private static void calculateRemainingFineDust() {
		for(int colIdx = 1; colIdx <= colSize; colIdx++) {
			for(int rowIdx = 1; rowIdx <= rowSize; rowIdx++) {
				if(map[colIdx][rowIdx] > 0) {
					remainingFindDust += map[colIdx][rowIdx];
				}
			}
		}

		System.out.println(remainingFindDust);
	}


	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());

		map = new int[colSize + 1][rowSize + 1]; // 1번 인덱스부터 사용
		copiedMap = new int[colSize + 1][rowSize + 1];

		for(int colIdx = 1; colIdx <= colSize; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 1; rowIdx <= rowSize; rowIdx++) {
				int info = Integer.parseInt(st.nextToken());
				copiedMap[colIdx][rowIdx] = map[colIdx][rowIdx] = info;
				if(info > 0) { //미세먼지
					fineDustList.add(new Position(colIdx, rowIdx));
				}
				else if(info == - 1 && map[colIdx - 1][rowIdx] == - 1) { // 공기청정기
					purifier[0] = new Position(colIdx - 1, rowIdx);
					purifier[1] = new Position(colIdx, rowIdx);
				}
			}
		}

		simulation();

		calculateRemainingFineDust();
	}
}
