package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ_3055_탈출
 * 
 * @author semin.kim
 *
 * [문제]
 * 티떱숲의 지도는 R행 C열로 이루어져 있다. 
 * 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다.
 * 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 
 * 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 * 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다.
 * 
 * [풀이]
 * 
 * 
 */

public class BOJ_3055_탈출_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int colSize, rowSize;
	static char[][] map;
	static int[][] hedgehogDistance, waterDistance;
	static Queue<Position> queue = new ArrayDeque<>();
	static final int[] DELTA_COL = {-1, 1, 0, 0}; // 상하
	static final int[] DELTA_ROW = {0, 0, -1, 1}; // 좌우
	static int dCol, dRow; // 비버 굴의 세로, 가로 위치
	static int sCol, sRow; // 시작 위치

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
	
	private static void spreadWater() {
		while (!queue.isEmpty()) {
			Position curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				if(!isValidRange(nextCol, nextRow)) continue;
				
				if(waterDistance[nextCol][nextRow] == -1 && map[nextCol][nextRow] != 'X' && map[nextCol][nextRow] != 'D') {
					waterDistance[nextCol][nextRow] = waterDistance[curr.col][curr.row] + 1;
					queue.offer(new Position(nextCol, nextRow));
				}
			}
		}
	}

	private static boolean isValidRange(int col, int row) {
		if(col < 0 || row < 0 || col >= colSize || row >= rowSize) {
			return false;
		}
		return true;
	}

	private static void BFS() {
		hedgehogDistance[sCol][sRow] = 0;
		queue.offer(new Position(sCol, sRow));
		
		while(!queue.isEmpty()) {
			Position curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				if(!isValidRange(nextCol, nextRow)) continue;
				
				if(hedgehogDistance[nextCol][nextRow] == -1 && map[nextCol][nextRow] != 'X') {
					if(waterDistance[nextCol][nextRow] > hedgehogDistance[curr.col][curr.row] + 1 || waterDistance[nextCol][nextRow] == -1) {
						hedgehogDistance[nextCol][nextRow] = hedgehogDistance[curr.col][curr.row] + 1;
						queue.offer(new Position(nextCol, nextRow));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());

		map = new char[colSize][rowSize];
		hedgehogDistance = new int[colSize][rowSize];
		waterDistance = new int[colSize][rowSize];
		
		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			String input = br.readLine().trim();
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				map[colIdx][rowIdx] = input.charAt(rowIdx);
				hedgehogDistance[colIdx][rowIdx] = -1;
				waterDistance[colIdx][rowIdx] = -1;
				
				if(map[colIdx][rowIdx] == 'S') {
					sCol = colIdx; sRow = rowIdx;
					map[colIdx][rowIdx] = '.';
				}
				
				if(map[colIdx][rowIdx] == 'D') {
					dCol = colIdx; dRow = rowIdx;
				}
				
				if(map[colIdx][rowIdx] == '*') {
					waterDistance[colIdx][rowIdx] = 0;
				}
				
				if(map[colIdx][rowIdx] == '.') {
					boolean flag = false;
					for(int dir = 0; dir < 4; dir++) {
						int nextCol = colIdx + DELTA_COL[dir];
						int nextRow = rowIdx + DELTA_ROW[dir];
						
						if(!isValidRange(nextCol, nextRow)) continue;
						
						if(map[nextCol][nextRow] == '*') {
							flag = true;
						}
					}
					if(flag) {
						queue.offer(new Position(colIdx, rowIdx));
						waterDistance[colIdx][rowIdx] = 1;
					}
				}
			}
		}
		
		spreadWater();
		BFS();
		
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				System.out.print(waterDistance[col][row] + " ");
			}
			System.out.println();
		}
		
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				System.out.print(hedgehogDistance[col][row] + " ");
			}
			System.out.println();
		}
		
		if(hedgehogDistance[dCol][dRow] != -1) {
			System.out.println(hedgehogDistance[dCol][dRow]);
		} else {
			System.out.println("KAKTUS");
		}
	}
}
