package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * BOJ_16918_봄버맨
 * 
 * 1. 격자판의 크기와 시간을 입력받는다.
 * 2. 격자판의 초기 상태를 입력받는다.
 * 3. 시간에 따라 폭탄을 설치할지 폭탄이 폭발할지 결정한다.
 * 
 * @author semin.kim
 * 
 */

public class BOJ_16918_봄버맨_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int rowSize, colSize, time;
	static Bomb[][] map;
	static final int[] DELTA_ROW = {-1, 1, 0, 0};
	static final int[] DELTA_COL = {0, 0, -1, 1};
	
	static boolean button;
	
	static class Bomb {
		char ch; int t;

		public Bomb(char ch, int t) {
			this.ch = ch;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Bomb [ch=" + ch + ", t=" + t + "]";
		}
	}
	
	private static boolean isValidRange(int row, int col) {
		return row >= 0 && col >= 0 && row < rowSize && col < colSize;
	}
	
	private static void explode(int t) {
		boolean visited[][] = new boolean[rowSize][colSize]; // 파괴할 폭탄 여부를 저장하는 2차원 배열
		
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				// 폭탄이 설치되어 있고 3초 경과한 경우...
				if(map[rowIdx][colIdx].ch == 'O' && t - map[rowIdx][colIdx].t >= 3) {
					visited[rowIdx][colIdx] = true; // 파괴할 폭탄이다.
					
					// 4방향 탐색하여 파괴될 폭탄 확인하기
					for(int dir = 0; dir < 4; dir++) {
						int nextRow = rowIdx + DELTA_ROW[dir];
						int nextCol = colIdx + DELTA_COL[dir];
						
						if(isValidRange(nextRow, nextCol)) {
							visited[nextRow][nextCol] = true;
						}
					}
				}
			}
		}
		
		// 폭탄이 폭발
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				if(visited[rowIdx][colIdx]) {
					map[rowIdx][colIdx] = new Bomb('.', 0);
				}
			}
		}
	}
	
	private static void plant(int t) {
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				if(map[rowIdx][colIdx].ch == '.') { // 빈 칸인 경우...
					map[rowIdx][colIdx] = new Bomb('O', t); // 폭탄 설치
				}
			}
		}
	}
	
	private static void print() {
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				sb.append(map[rowIdx][colIdx].ch);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		map = new Bomb[rowSize][colSize];
		
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			String input = br.readLine().trim();
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = new Bomb(input.charAt(colIdx), 0);
			}
		}

		int sec = 1;
		while (sec < time) {
			sec++;
			if(!button) {
				plant(sec);
			}
			else if(button) {
				explode(sec);
			}
			button = !button;
		}
		
		print();
	}
}
