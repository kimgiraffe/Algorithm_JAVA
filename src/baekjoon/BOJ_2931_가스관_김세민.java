package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * BOJ_2931_가스관
 * R행 C열, 각 칸은 비어있거나 7가지 기본 블록('|', '-', '+', '1', '2', '3', '4')
 * '+' 블록은 두 방향(수직, 수평)으로 흘러야 한다.
 * 지운 블록 한 칸의 행과 열의 위치, 어떤 블록이었는지 출력
 * 
 * @author semin.kim
 *
 */

public class BOJ_2931_가스관_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int row_size, col_size;
	static char[][] map;
	static int[][] visited;
	
	static final int[] DR = {-1, 1, 0, 0}; // 좌우
	static final int[] DC = {0, 0, -1, 1}; // 상하
	static final boolean[][] pipes = {{true, true, false, false}, {false, false, true, true}, {true, true, true, true},
			{false, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}
	};
	
	static Map<Character, Integer> pipe_idx;
	
	static class pos {
		int row;
		int col;

		public pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void init() {
		pipe_idx = new HashMap<>();
		pipe_idx.put('|', 0);
		pipe_idx.put('-', 1);
		pipe_idx.put('+', 2);
		pipe_idx.put('1', 3);
		pipe_idx.put('2', 4);
		pipe_idx.put('3', 5);
		pipe_idx.put('4', 6);
	}
	
	public static char toPipe(int num) {
		switch(num) {
		case 0:
			return '|';
		case 1:
			return '-';
		case 2:
			return '+';
		case 3:
			return '1';
		case 4:
			return '2';
		case 5:
			return '3';
		case 6:
			return '4';
		}
		return 0;
	}
	
	// 반대 방향 찾는 메서드
	public static int getOpposite(int dir) {
		switch (dir) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 2;
		case 3:
			return 3;
		}
		return dir;
	}
	
	public static void printResult(int r, int c, boolean[] re) {
		for(int idx = 0; idx < pipes.length; idx++) {
			if(idx == 2) continue;
			boolean flag = true;
			for(int dir = 0; dir < 4; dir++) {
				if(pipes[idx][dir] != re[dir]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println(r + " " + c + " " + toPipe(idx));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		row_size = Integer.parseInt(st.nextToken());
		col_size = Integer.parseInt(st.nextToken());
		map = new char[row_size][col_size];
		visited = new int[row_size][col_size];
		init();
		
		for(int row = 0; row < row_size; row++) {
			String tmp = br.readLine().trim();
			for(int col = 0; col < col_size; col++) {
				map[row][col] = tmp.charAt(col);
				
			}
		}
		
		for(int row = 0; row < row_size; row++) {
			for(int col = 0; col < col_size; col++) {
				if(map[row][col] == '.') { // 빈 칸인 경우...
					boolean[] re = new boolean[4];
					int cnt = 0;
					for(int dir = 0; dir < 4; dir++) {
						int next_r = row + DR[dir];
						int next_c = col + DC[dir];
						
						if(next_r >= 0 && next_c >= 0 && next_r < row_size && next_c < col_size && map[next_r][next_c] != '.'
								&& map[next_r][next_c] != 'M' && map[next_r][next_c] != 'Z' && pipes[pipe_idx.get(map[next_r][next_c])][getOpposite(dir)]) {
							cnt++;
							re[dir] = true;
						}
					}
					if(cnt == 4) {
						System.out.println((row + 1) + " " + (col+1) + " +");
						return;
					} else if(cnt == 2) {
						printResult(row + 1, col + 1, re);
						return;
					}
				}
			}
		}
		
	}
}