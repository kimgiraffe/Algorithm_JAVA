package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1531_투명
 * 
 * 1. 불투명한 종이의 수와 그림이 보이는 최대 종이 수를 입력받는다. 
 * 2. 종이의 왼쪽 아래 모서리 좌표와 오른쪽 위 모서리 좌표를 입력받는다. 
 * 3. 모자이크를 종이로 가린다.
 * 4. 보이지 않는 그림의 개수를 출력한다.
 * 
 * @author semin.kim
 *
 */

public class BOJ_1531_투명_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int paperCount, limit;
	static int x1, x2, y1, y2, result;
	
	static int[][] grid = new int[101][101];

	/**
	 * 모자이크를 불투명한 종이로 가린다.
	 */
	private static void solve() {
		for(int x = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				grid[x][y]++; 
			}
		}
	}
	
	/**
	 * 모자이크 중 보이지 않는 그림의 개수를 센다.
	 */
	private static void count() {
		for(int x = 1; x <= 100; x++) {
			for(int y = 1; y <= 100; y++) {
				if(grid[x][y] > limit) { // 그림이 보이지 않는 경우...
					result++; // 보이지 않는 그림의 개수 1 증가
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		paperCount = Integer.parseInt(st.nextToken()); // 불투명한 종이의 개수 입력
		limit = Integer.parseInt(st.nextToken()); // 그림이 보이는 최대 종이의 개수 입력
		
		for(int paper = 0; paper < paperCount; paper++) {
			st = new StringTokenizer(br.readLine().trim());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			solve();
		}
		count(); // 보이지 않는 그림의 개수 세기
		System.out.println(result);
		
	}
}
