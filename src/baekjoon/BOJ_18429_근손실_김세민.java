package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_18429_근손실
 * 
 * @author semin.kim
 */

public class BOJ_18429_근손실_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, K;
	static int[] numList;
	static int[] selectList;
	static boolean[] visited;
	static int count;
	
	private static void Permutation(int currentIdx) {
		if(currentIdx == N) {
			int weight = 500;
			boolean flag = true;
			for(int idx = 0; idx < N; idx++) {
				weight += selectList[idx];
				weight -= K;
				if(weight < 500) {
					flag = false;
				}
			}
			if(flag) {
				count++;
			}
		}
		for(int idx = 0; idx < N; idx++) {
			if(visited[idx]) continue;
			
			visited[idx] = true;
			selectList[currentIdx] = numList[idx];
			
			Permutation(currentIdx + 1);
			visited[idx] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		numList = new int[N];
		selectList = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < N; idx++) {
			numList[idx] = Integer.parseInt(st.nextToken());
		}
		
		Permutation(0);
		System.out.println(count);
	}
}
