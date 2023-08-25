package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * SWEA_16858_로봇청소기
 * N * N 크기의 맵 (N은 5이상 10이하의 정수)
 * 로봇 청소기는 (0, 0) 즉, 좌측 상단으로 고정되어있고 오른쪽 방향을 바라보고있다.
 * 먼지에 붙여진 번호 순서대로 청소해야한다.
 * 먼지의 개수는 2이상 10이하의 정수
 * 한 자리에서는 한 번만 회전할 수 있고, 먼지를 청소함과 동시에 회전 가능
 * 
 * 먼지의 위치는 먼지를 청소한 후 + 모양 위치에 다음 먼지가 나타나지 않는다. => row, col 중 어느 하나도 같은 먼지는 존재하지 않는다.
 * 또한 맵의 가장자리에는 먼지가 나타나지 않는다.
 * 회전 횟수를 최소화하여 순서대로 모든 먼지를 청소하려면, 최소 몇 번의 회전이 필요한지 구하는 프로그램을 작성하자.
 * 
 * 
 * @author semin.kim
 *
 */

public class SWEA_16858_로봇청소기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T; // 테스트케이스의 개수
	static int mapSize; // 맵의 크기
	static int[][] map;
	static final int[] DELTA_ROW = {0, 1, 0, -1}; // 하상
	static final int[] DELTA_COL = {1, 0, -1, 0}; // 우좌
	static int minRotateCount; // 최소 회전 횟수

	static class Dust implements Comparable<Dust>{
		int row;
		int col;
		int num;

		public Dust(int row, int col, int num) {
			this.row = row;
			this.col = col;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Dust [row=" + row + ", col=" + col + ", num=" + num + "]";
		}

		@Override
		public int compareTo(Dust o) {
			return Integer.compare(this.num, o.num);
		}
	}

	static ArrayList<Dust> dustList;
	
	static class Pos {
		int row;
		int col;
		int dir;
		public Pos(int row, int col, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Pos [row=" + row + ", col=" + col + ", dir=" + dir + "]";
		}
		
		
	}

	private static void solve(int row, int col, int dir, int currentIdx) {

		if(currentIdx == dustList.size() - 1) {
			sb.append(minRotateCount);
			return;
		}
		
		
		int currentRow = dustList.get(currentIdx).row;
		int currentCol = dustList.get(currentIdx).col;
		int nextRow = dustList.get(currentIdx + 1).row;
		int nextCol = dustList.get(currentIdx + 1).col;
		
//		System.out.println(nextRow +" "+nextCol);
		
		if(dir == 0) {
			if(nextRow < currentRow) {
				if(nextCol < currentCol) {
					minRotateCount += 3;
					solve(nextRow, nextCol, 3, currentIdx + 1);
				}
				else if(nextCol > currentCol){
					minRotateCount += 4;
					solve(nextRow, nextCol, 4, currentIdx + 1);
				}
			}
			else {
				if(nextCol > currentCol) {
					minRotateCount += 1;
					solve(nextRow, nextCol, 1, currentIdx + 1);
				}
				else if(nextCol < currentCol){
					minRotateCount += 2;
					solve(nextRow, nextCol, 2, currentIdx + 1);
				}
			}
		}
		else if(dir == 1) {
			if(nextRow < currentRow) {
				if(nextCol < currentCol) {
					minRotateCount += 2;
					solve(nextRow, nextCol, 3, currentIdx + 1);
				}
				else if(nextCol > currentCol){
					minRotateCount += 3;
					solve(nextRow, nextCol, 0, currentIdx + 1);
				}
			}
			else {
				if(nextCol > currentCol) {
					minRotateCount += 4;
					solve(nextRow, nextCol, 1, currentIdx + 1);
				}
				else if(nextCol < currentCol){
					minRotateCount += 1;
					solve(nextRow, nextCol, 2, currentIdx + 1);
				}
			}
		}
		else if(dir == 2) {
			if(nextRow < currentRow) {
				if(nextCol < currentCol) {
					minRotateCount += 1;
					solve(nextRow, nextCol, 3, currentIdx + 1);
				}
				else if(nextCol > currentCol){
					minRotateCount += 2;
					solve(nextRow, nextCol, 4, currentIdx + 1);
				}
			}
			else {
				if(nextCol > currentCol) {
					minRotateCount += 3;
					solve(nextRow, nextCol, 1, currentIdx + 1);
				}
				else if(nextCol < currentCol){
					minRotateCount += 4;
					solve(nextRow, nextCol, 2, currentIdx + 1);
				}
			}
		}
		else if(dir == 3) {
			if(nextRow < currentRow) {
				if(nextCol < currentCol) {
					minRotateCount += 4;
					solve(nextRow, nextCol, 3, currentIdx + 1);
				}
				else if(nextCol > currentCol){
					minRotateCount += 1;
					solve(nextRow, nextCol, 4, currentIdx + 1);
				}
			}
			else {
				if(nextCol > currentCol) {
					minRotateCount += 2;
					solve(nextRow, nextCol, 1, currentIdx + 1);
				}
				else if(nextCol < currentCol){
					minRotateCount += 3;
					solve(nextRow, nextCol, 2, currentIdx + 1);
				}
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim());

		for(int testCase = 1; testCase <= T; testCase++) {
			sb = new StringBuilder();
			mapSize = Integer.parseInt(br.readLine().trim());

			map = new int[mapSize][mapSize];
			dustList = new ArrayList<>();
//			dustList.add(new Dust(0, 0, 0));
			minRotateCount = 0;
			// 맵의 각 칸의 정보 입력
			for(int row = 0; row < mapSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0; col < mapSize; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
					if(map[row][col] != 0) {
						dustList.add(new Dust(row, col, map[row][col]));
					}
				}
			}
			Collections.sort(dustList);
			sb.append('#').append(testCase).append(' ');
//			System.out.println(dustList.toString());
			solve(0, 0, 0, 0);

			System.out.println(sb);
		}
	}
}
