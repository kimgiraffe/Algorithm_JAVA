package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_17425_약수의합
 * 
 * 자연수 A의 약수의 합은 A의 모든 약수를 더한 값이고, f(A)로 표현한다. 
 * x보다 작거나 같은 모든 자연수 y의 f(y)값을 더한 값은 g(x)로 표현한다.
 * 자연수 N이 주어졌을 때, g(N)을 구해보자.
 * 
 * @author semin.kim
 */

public class BOJ_17425_약수의합_김세민 {

	static BufferedReader br;
	static StringBuilder sb;
	static int T, N;
	static final int MAX = 1000000;
	static long[] S = new long[MAX + 1];
	
	private static void solve() {
		for(int i = 1; i <= MAX; i++) {
			for(int j = 1; i * j <= MAX; j++) {
				S[i * j] += i;
			}
			S[i] += S[i - 1]; // 부분합을 이용하여 g(x) 구하기
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine().trim());

		solve();
		
		for(int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine().trim());
			sb.append(S[N]).append("\n");
		}
		System.out.print(sb);
	}
}
