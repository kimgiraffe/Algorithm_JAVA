package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_25379_피하자
 * 짝수는 짝수끼리, 홀수는 홀수끼리 안접해야 훌수와 짝수가 인접하는 횟수가 최대 한 번 등장함
 * 짝수 ... 홀수 or 홀수 ... 짝수
 * @author semin.kim
 */

public class BOJ_25379_피하자_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int arr_length; // 배열의 길이
	static long[] arr;
	static long odd_left_cnt, even_left_cnt;
	static long odd_cnt, even_cnt;

	public static void solve() {
		for(int idx = 0; idx < arr_length; idx++) {
			
			if(arr[idx] % 2 == 0) {
				even_cnt++;
				odd_left_cnt += odd_cnt;
			}
			else {
				odd_cnt++;
				even_left_cnt += even_cnt;
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		arr_length = Integer.parseInt(br.readLine().trim());
		arr = new long[arr_length];

		st = new StringTokenizer(br.readLine().trim());

		for(int idx = 0; idx < arr_length; idx++) {
			arr[idx] = Long.parseLong(st.nextToken());
		}

		solve();
		System.out.println(Math.min(even_left_cnt, odd_left_cnt));
	}
}
