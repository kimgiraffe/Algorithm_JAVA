package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1010_다리놓기
 * 동쪽의 m개 사이트에서 중복을 허용하지 않고 순서를 구분하지 않고 n개의 다리를 짓기
 * mCn
 * 동적계획법을 이용한 이항계수 계산
 * n!/k!(n-k)! = (n-1)!/(k-1)!(n-k)! + (n-1)!/k!(n-k-1)!
 * @author semin.kim
 *
 */

public class BOJ_1010_다리놓기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int testcaseCount;
	static int westSiteCount, eastSiteCount;
	static int[][] Bino;
	
	private static void Binomial() {
		for(int westIdx = 0; westIdx <= westSiteCount; westIdx++) {
			for(int eastIdx = 0; eastIdx <= Math.min(westIdx, eastSiteCount); eastIdx++) {
				if(eastIdx == 0 || eastIdx == westIdx) {
					Bino[westIdx][eastIdx] = 1; 
				}
				else {
					Bino[westIdx][eastIdx] = Bino[westIdx-1][eastIdx-1] + Bino[westIdx-1][eastIdx]; 
				}
			}
		}
		sb.append(Bino[westSiteCount][eastSiteCount]);
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
	
		testcaseCount = Integer.parseInt(br.readLine().trim());
		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine().trim());
			eastSiteCount = Integer.parseInt(st.nextToken());
			westSiteCount = Integer.parseInt(st.nextToken());

			Bino = new int[westSiteCount+1][eastSiteCount+1];
			
			Binomial();
		}
	}
}
