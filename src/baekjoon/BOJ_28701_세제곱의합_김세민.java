package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_28701_세제곱의합
 * 1.1부터 N까지 수의 합을 출력한다. -> N * (N + 1) / 2
 * 2.1부터 N까지 수의 합의 제곱한 수를 출력한다. -> 1의 제곱
 * 3.1부터 N까지 수의 세제곱의 합을 출력한다. -> 2와 동일
 * @author semin.kim
 */

public class BOJ_28701_세제곱의합_김세민 {

	static BufferedReader br;
	static StringBuilder sb;
	static int N;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine().trim());
		sb.append(N*(N+1)/2).append("\n").append((N*(N+1)/2)*(N*(N+1)/2)).append("\n").append((N*(N+1)/2)*(N*(N+1)/2));
		System.out.println(sb);
	}
}
