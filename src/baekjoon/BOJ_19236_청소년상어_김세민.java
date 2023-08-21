package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_19236_청소년상어
 * 
 * 4 * 4 크기 공간, 상어와 물고기의 번호와 방향
 * 번호가 작은 물고기가 먼저 이동, 모든 물고기의 이동이 끝나면 상어가 이동
 * 상어가 이동한 후에는 다시 물고기가 이동하는 과정을 반복
 * 상어는 (0, 0)에 있는 물고기를 먹고 (0,0)에 들어간다
 * 성어는 한 번에 여러 칸을 이동할 수 있고, 이동하는 도중 지나는 칸에 있는 물고기는 먹지 않는다.
 * 
 * 상어가 먹을 수 있는 물고기 번호의 합의 최댓값
 * @author semin.kim
 */

public class BOJ_19236_청소년상어_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static class info {
		int index;
		int dir;
		
		@Override
		public String toString() {
			return "info [index=" + index + ", dir=" + dir + "]";
		}
	}
	
	static info[][] map = new info[4][4];
	
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗  
	static int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] DC = {0, -1, -1, -1, 0, 1, 1, 1, 1};
	
	static int MAX; // 상어가 먹을 수 있는 물고기의 최댓값
	
	public static void swap(info a, info b) {
		info tmp = new info();
		tmp.index = a.index;
		tmp.dir = a.dir;
		
		a.index = b.index;
		a.dir = b.dir;
		
		b.index = tmp.index;
		b.dir = tmp.dir;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int row = 0; row < 4; row++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int col = 0; col < 4; col++) {
				map[row][col] = new info();
				map[row][col].index = Integer.parseInt(st.nextToken());
				map[row][col].dir = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(MAX);
	}
}
