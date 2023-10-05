package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1266_소수완제품확률
 * 
 * @author semin.kim
 *
 * 1. 테스트케이스 수 입력
 * 2. 각 장인이 5분 안에 완제품을 만들 확률 입력
 * 3. r개의 완제품을 만들 확률 : 18Cr * p^r * (1-p)^(18-r)
 * 
 */

public class SWEA_1266_소수완제품확률_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testcaseCount; // 테스트케이스의 수
	static int skillOfMasterA, skillOfMasterB; // 각 장인이 5분 안에 완제품을 만들 확률

	static final int[] PRIME_NUMBERS = {2, 3, 5, 7, 11, 13, 17}; // 18이하의 소수
	static final int MAX = 18; // 90분동안 최대로 만들 수 있는 완제품의 수
	static int[][] pascal = new int[MAX + 1][MAX + 1];
	static double probA, probB;

	private static void makePascal() {
		for(int i = 0; i <= MAX; i++) {
			for(int j = i; j <= MAX; j++) {
				if(i == j || j == 0 || i == 0) {
					pascal[i][j] = 1; 
				}
			}
		}

		for(int i = 1; i <= MAX; i++) {
			for(int j = i + 1; j <= MAX; j++) {
				pascal[i][j] = pascal[i][j-1] + pascal[i-1][j-1]; 
			}
		}
		
	}

	private static void calculateProb() {
		double pA = skillOfMasterA / 100.0;
		double pB = skillOfMasterB / 100.0;
		
		for(int idx = 0; idx < PRIME_NUMBERS.length; idx++) {
			int prime = PRIME_NUMBERS[idx];
			probA += pascal[prime][MAX] * Math.pow(pA, prime) * Math.pow((1.0 - pA), MAX - prime);
			probB += pascal[prime][MAX] * Math.pow(pB, prime) * Math.pow((1.0 - pB), MAX - prime);
		}
		System.out.printf("%.6f\n", 1.0 - (1.0 - probA) * (1.0 - probB));
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		makePascal();

		testcaseCount = Integer.parseInt(br.readLine().trim());

		for(int testcase = 1; testcase <= testcaseCount; ++testcase) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			
			probA = 0.0; probB = 0.0;

			skillOfMasterA = Integer.parseInt(st.nextToken());
			skillOfMasterB = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(testcase).append(" ");
			System.out.print(sb);
			calculateProb();
		}
	}
}
