package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 가스의 흐름은 유일하다.
 * '+' 블록의 경우... 수직, 수평으로 한 번씩 지나야한다.
 * @author semin.kim
 *
 */


public class BOJ_2931_가스관_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int rowSize, colSize; // 세로, 가로 크기
	static char[][] map;
	static int[][] visited;
	static int startRowIdx, startColIdx; // 시작점(모스크바)의 세로, 가로 위치
	static int endRowIdx, endColIdx; // 끝점(자그레브)의 세로, 가로 위치

	static final int[] D_ROW = {-1, 1, 0, 0}; // 상하
	static final int[] D_COL = {0, 0, -1, 1}; // 좌우

	static class Pos {
		int row;
		int col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Pos [row=" + row + ", col=" + col + "]";
		}
	}

	static class Block implements Comparable<Block>{
		int row;
		int col;
		char type;

		public Block(int row, int col, char type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Block [row=" + row + ", col=" + col + ", type=" + type + "]";
		}

		@Override
		public int compareTo(Block o) {
			if(this.row >= o.row) {
				if(this.row == o.row) {
					return this.col - o.col;
				}
				else {
					return 1;
				}
			}
			else {
				return -1;
			}
		}
	}

	static ArrayList<Block> blockList;
	static ArrayList<Block> problemBlockList;

	public static void findProblemBlocks() {
		for(int idx = 0; idx < blockList.size(); idx++) {
			int row = blockList.get(idx).row;
			int col = blockList.get(idx).col;
			char type = blockList.get(idx).type;
			//			System.out.println(row + " "+col + " "+type);
			if(type == '+') {
				int count = 0;
				for(int dir = 0; dir < 4; dir++) {
					int nextRow = row + D_ROW[dir];
					int nextCol = col + D_COL[dir];

					if(nextRow < 1 || nextRow > rowSize || nextCol < 1 || nextCol > colSize) continue;
					if(map[nextRow][nextCol] != '.') {
						count++;
					}
				}
				//				System.out.println(count);
				if(count != 4) {
					problemBlockList.add(new Block(row, col, type));
				}
			}
			else {
				int count = 0;
				for(int dir = 0; dir < 4; dir++) {
					int nextRow = row + D_ROW[dir];
					int nextCol = col + D_COL[dir];

					if(nextRow < 1 || nextRow > rowSize || nextCol < 1 || nextCol > colSize) continue;
					if(map[nextRow][nextCol] != '.') {
						count++;
					}
				}
				//				System.out.println(count);
				if(count < 2) {
					problemBlockList.add(new Block(row, col, type));
					//					System.out.println(row +" "+col+ " "+type);
				}
			}
		}
		Collections.sort(problemBlockList);
	}

	public static void findEmpty() {
		int size = problemBlockList.size();
		
		if(size == 2) {
			int row0 = problemBlockList.get(0).row;
			int col0 = problemBlockList.get(0).col;
			char type0 = problemBlockList.get(0).type;
			
			int row1 = problemBlockList.get(1).row;
			int col1 = problemBlockList.get(1).col;
			char type1 = problemBlockList.get(1).type;
			
			if(row0 + 2 == row1) {
				sb.append(row0+1).append(" ").append(col0).append(" ").append('|');
			}
			else if(col0 + 2 == col1) {
				sb.append(row0).append(" ").append(col0+1).append(" ").append('-');
			}
			else if(row0 + 1 == row1 && col0 + 1 == col1) {
				if(map[row0+1][col0] != '.') {
					sb.append(row0).append(" ").append(col0+1).append(" ").append('4');
				}
				else if(map[row0][col0+1] != '.'){
					sb.append(row0+1).append(" ").append(col0).append(" ").append('2');
				}
			}
			else if(row0 + 1 == row1 && col0 - 1 == col1) {
				if(map[row0+1][col0] != '.') {
					sb.append(row0+1).append(" ").append(col0-1).append(" ").append('1');
				}
				else if(map[row0][col0-1] != '.'){
					sb.append(row0).append(" ").append(col0).append(" ").append('3');
				}
			}

		} else if(size == 3) {
			
		} else if(size == 4) {
			sb.append(problemBlockList.get(0).row+1).append(" ").append(problemBlockList.get(0).col+1).append(" ");
		}

		
		System.out.println(sb);

	}


	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();

		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new char[rowSize + 1][colSize + 1];
		visited = new int[rowSize + 1][colSize + 1];
		blockList = new ArrayList<>();
		problemBlockList = new ArrayList<>();

		for(int row = 1; row <= rowSize; row++) {
			String tmp = br.readLine().trim();
			for(int col = 1; col <= colSize; col++) {
				map[row][col] = tmp.charAt(col-1);
				if(map[row][col] == 'M') {
					startRowIdx = row;
					startColIdx = col;
				}
				else if(map[row][col] == 'Z') {
					endRowIdx = row;
					endColIdx = col;
				}
				else if(map[row][col] != '.') {
					blockList.add(new Block(row, col, map[row][col]));
				}
			}
		}
		findProblemBlocks();
		System.out.println(problemBlockList.toString());
		findEmpty();
		//		System.out.println(blockList.toString());
	}
}