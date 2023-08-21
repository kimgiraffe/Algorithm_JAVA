package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_2931_가스관
 * R행 C열, 각 칸은 비어있거나 7가지 기본 블록('|', '-', '+', '1', '2', '3', '4')
 * '+' 블록은 두 방향(수직, 수평)으로 흘러야 한다.
 * 지운 블록 한 칸의 행과 열의 위치, 어떤 블록이었는지 출력
 * 
 * 
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
	
	static int Moscow_row, Moscow_col; // 모스크바의 행과 열 위치
	static int Zagreb_row, Zagreb_col; // 자그레브의 행과 열 위치
	
	static int start_row, start_col;
	
	static final int[] DR = {-1, 1, 0, 0}; // 상하
	static final int[] DC = {0, 0, -1, 1}; // 좌우
	
	static class pos {
		int row;
		int col;

		public pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}
	
	public static void solve() {
		Queue<pos> queue = new LinkedList<>();
		
		queue.add(new pos(start_row, start_col));
		visited[start_row][start_col] = 1;
		
		while(!queue.isEmpty()) {
			
			pos curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int next_r = curr.row + DR[dir];
				int next_c = curr.col + DC[dir];
				
				// 범위를 벗어나는 경우...
				if(next_r < 0 || next_r >= row_size || next_c < 0 || next_c >= col_size) continue;
				// 블록이 '+'가 아니고 한 번 방문한 경우...
				if(map[next_r][next_c] != '+' && visited[next_r][next_c] == 1) continue;
				// 블록이 '+'이고 두 번 방문한 경우...
				if(map[next_r][next_c] == '+' && visited[next_r][next_c] == 2) continue;
				
				// 빈 칸인 경우...
				if(map[next_r][next_c] == '.') continue;
				
				if(map[curr.row][curr.col] == 'M' || map[curr.row][curr.col] == 'Z') { // 현재 블록이 모스크바 또는 자그레브인 경우...
					if(dir == 0) { // 위 방향
						if(map[next_r][next_c] == '|' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 1) { // 아래 방향
						if(map[next_r][next_c] == '|' || map[next_r][next_c] == '+' || map[next_r][next_c] == '2' || map[next_r][next_c] == '3') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
				}
				else if(map[curr.row][curr.row]=='|') { // 현재 블록이 '|'
					if(dir == 0) { // 위 방향
						if(map[next_r][next_c] == '|' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 1) { // 아래 방향
						if(map[next_r][next_c] == '|' || map[next_r][next_c] == '+' || map[next_r][next_c] == '2' || map[next_r][next_c] == '3') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 2) { // 왼쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '2') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 3) { // 오른쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '3' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
				}
				else if(map[curr.row][curr.row]== '-') { // 현재 블록이 '-'
					if(dir == 2) { // 왼쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '2') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 3) { // 오른쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '3' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
				}
				else if(map[curr.row][curr.row]== '+') { // 현재 블록이 '+'
					if(dir == 0) { // 위쪽 방향
						if(map[next_r][next_c] == '|' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 1) { // 아래 방향
						if(map[next_r][next_c] == '|' || map[next_r][next_c] == '+' || map[next_r][next_c] == '2' || map[next_r][next_c] == '3') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 2) { // 왼쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '2') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							
						}
					}
					else if(dir == 3) { // 오른쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '3' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
				}
				else if(map[curr.row][curr.row]== '1') { // 현재 블록이 '1'
					if(dir == 1) { // 아래쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '2' || map[next_r][next_c] == '3') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					
					
					else if(dir == 3) { // 오른쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '3' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					
				}
				else if(map[curr.row][curr.row]== '2') { // 현재 블록이 '2'
					if(dir == 1) { // 위쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					
					else if(dir == 3) { // 오른쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '3' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
				}
				else if(map[curr.row][curr.row]== '3') { // 현재 블록이 '3'
					if(dir == 1) { // 위쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '4') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					else if(dir == 2) { // 왼쪽 방향
						if(map[next_r][next_c] == '-' || map[next_r][next_c] == '+' || map[next_r][next_c] == '1' || map[next_r][next_c] == '2') {
							queue.add(new pos(next_r, next_c));
							visited[next_r][next_c]++;
							start_row = next_r;
							start_col = next_c;
						}
					}
					
				}
				else if(map[curr.row][curr.row]== '4') { // 현재 블록이 '4'
					
					
				}
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
		
		for(int row = 0; row < row_size; row++) {
			String tmp = br.readLine().trim();
			for(int col = 0; col < col_size; col++) {
				map[row][col] = tmp.charAt(col);
				if(map[row][col] == 'M') { // 모스크바의 위치
					Moscow_row = row;
					Moscow_col = col;
				}
				if(map[row][col] == 'Z') { // 자그레브의 위치
					Zagreb_row = row;
					Zagreb_col = col;
				}
			}
		}
		
		start_row = Moscow_row;
		start_col = Moscow_col;
		
		solve();
		System.out.println(start_row + " " + start_col);
		
		start_row = Zagreb_row;
		start_col = Zagreb_col;
		
		solve();
		System.out.println(start_row + " " + start_col);
	}
}
