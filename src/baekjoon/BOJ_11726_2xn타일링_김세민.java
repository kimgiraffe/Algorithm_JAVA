package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_11726_2xn타일링
 * 2×n 직사각형을 1×2, 2×1타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 * (1<=n<=1000)
 * 
 * n = 1인 경우... 2*1 타일 1개를 사용하여 타일을 채울 수 있다.
 * n = 2인 경우... 2*1 타일 2개 또는 1*2 타일 2개로 총 2가지 방법으로 타일을 채울 수 있다.
 * n >= 3인 경우... 
 * 맨 왼쪽 2*1 타일을 2*1 타일 1개를 사용하여 채웠다면 나머지 타일을 채우는 방법은 n-1일 때 방법의 수와 같다.
 * 맨 왼쪽 2*2 타일을 1*2 타일 2개를 사용하여 채웠다면 나머지 타일을 채우는 방법은 n-2일 때 방법의 수와 같다.
 * f(n) = f(n-1) + f(n-2) 
 * @author semin.kim
 *
 */


public class BOJ_11726_2xn타일링_김세민 {

	static BufferedReader br;
	
	static int tileSize; // 타일의 크기(n)
	static int[] fillTiles; // index : n, fillTiles[n]: 2 * n 타일을 채우는 방법의 수
	static final int MOD = 10007; // 방법의 수를 나누는 수는 10007로 고정
	static final int MAX_TILE_COUNT = 1000; // n의 최댓값은 1000으로 고정
	
	private static void fill() {
		fillTiles[1] = 1; // n = 1인 경우... 2 * 1타일 1개를 사용하여 타일을 채울 수 있다.
		fillTiles[2] = 2; // n = 2인 경우... 2*1 타일 2개 또는 1*2 타일 2개로 총 2가지 방법으로 타일을 채울 수 있다.

		
		// f(n) = f(n-1) + f(n-2) 
		for(int idx = 3; idx <= MAX_TILE_COUNT; idx++) {
			if(fillTiles[idx] == 0) {
				fillTiles[idx] = (fillTiles[idx-1] % MOD + fillTiles[idx-2] % MOD) % MOD; 
			}
		}
		System.out.println(fillTiles[tileSize]);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		tileSize = Integer.parseInt(br.readLine().trim());
		fillTiles = new int[MAX_TILE_COUNT+1];

		fill();
	}
}
