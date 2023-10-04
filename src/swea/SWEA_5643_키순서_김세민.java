package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_5643_키순서
 * 
 * @author semin.kim
 *
 * 1. 테스트케이스 수를 입력받느다.
 * 2. 학생 수와 키를 비교한 횟수를 입력받는다.
 * 3. 두 학생의 키를 비교한 결과를 입력받아  2차원 배열에 저장한다.
 * 4. 1번 학생부터 N번 학생까지 순회하며 각각 자신보다 키가 작은 학생과 큰 학생의 수를 구한다.
 * 5. 4에서 구한 값(자신보다 키가 큰 학생 수와 키가 큰 학생의 수)을 더한 값이 전체 학생 수 - 1 이라면 자신의 키를 몇 번째인지 알 수 있다.
 * 6. 자신의 키를 몇 번째인지 알 수 있는 학생의 수를 출력한다.
 */

public class SWEA_5643_키순서_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testcaseCount;
	static int studentCount, compareCount;

	static boolean[][] resultArray;
	static int[] shorter, taller;
	static int shorterCount, tallerCount, answer;
	
	/**
	 * 자신보다 키가 더 작은 학생의 수를 구하는 메서드
	 * @param to
	 * @param visited
	 */
	private static void shorter(int to, boolean[] visited) {
		visited[to] = true;
		
		for(int idx = 1; idx <= studentCount; idx++) {
			if(!visited[idx] && resultArray[idx][to]) {
				shorter(idx, visited);
				visited[idx] = true;
				shorterCount++;
			}
		}
		
	}
	
	/**
	 * 자신보다 키가 더 큰 학생의 수를 구하는 메서드
	 * @param from
	 * @param visited
	 */
	private static void taller(int from, boolean[] visited) {
		visited[from] = true;
		
		for(int idx = 1; idx <= studentCount; idx++) {
			if(!visited[idx] && resultArray[from][idx]) {
				taller(idx, visited);
				visited[idx] = true;
				tallerCount++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		testcaseCount = Integer.parseInt(br.readLine().trim());
		
		for(int testcase = 1; testcase <= testcaseCount; ++testcase) {
			sb = new StringBuilder();
			answer = 0;
			studentCount = Integer.parseInt(br.readLine().trim());
			compareCount = Integer.parseInt(br.readLine().trim());
			
			resultArray = new boolean[studentCount + 1][studentCount + 1];
			shorter = new int[studentCount + 1];
			taller = new int[studentCount + 1];
			
			for(int compare = 1; compare <= compareCount; compare++) {
				st = new StringTokenizer(br.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				resultArray[from][to] = true;
			}
			
			for(int idx = 1; idx <= studentCount; idx++) {
				tallerCount = 0; shorterCount = 0;
				shorter(idx, new boolean[studentCount + 1]);
				taller(idx, new boolean[studentCount + 1]);
				
				// 자신보다 키가 작은 학생의 수와 키가 큰 학생의 수의 합이 전체 학생의 수 - 1인 경우...
				if(tallerCount + shorterCount == studentCount - 1) {
					// 자신의 키가 몇 번째인지 알 수 있는 학생의 수 1 증가
					answer++;
				}
			}
			sb.append("#").append(testcase).append(" ").append(answer);
			System.out.println(sb);
		}
	}
}
