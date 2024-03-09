package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1072_게임
 * 
 * @author semin.kim
 *
 */
public class BOJ_1072_게임_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int x, y, z;
	static int answer = -1;
	
	static void solve() {
		if(z >= 99) {
			return;
		}
		int low = 0;
		int high = Integer.MAX_VALUE;
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			
			if((int)((long)(y + mid) * 100 / (x + mid)) != z) {
				answer = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		z = (int)((long) y * 100 / x);
		
		solve();
		
		System.out.println(answer);
	}

}
