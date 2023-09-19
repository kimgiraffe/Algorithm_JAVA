package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_16943_숫자재배치_김세민
 * 두 정수 A와 B가 있을 때, A에 포함된 숫자의 순서를 섞어서 새로운 수 C를 만들려고 한다. 즉, C는 A의 순열 중 하나가 되어야 한다. 
 * 가능한 C 중에서 B보다 작으면서, 가장 큰 값을 구해보자. C는 0으로 시작하면 안 된다.
 * 1 ≤ A, B < 10^9
 * 
 * 정수 A의 각 자릿수를 numList에 저장한다.
 * 순열을 통해 새로 만든 수가 B보다 작은 경우... 최댓값을 갱신한다.
 * 0으로 시작하는 경우... 무시
 * @author semin.kim
 */

public class BOJ_16943_숫자재배치_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int A, B;
	static int[] numList;
	static int[] selectList;
	static boolean[] visited;
	static int size;
	static int numIdx;
	static int MAX = -1;
	
	private static void Permutation(int currentIdx) {
		if(currentIdx == size) { // 순열 생성 완료
			if(selectList[0] == 0) { // 0으로 시작하는 경우...
				return;
			}
			int num = 0; // 새로 만든 수
			for(int idx = 0; idx < size; idx++) {
				num += selectList[idx] * Math.pow(10, size - idx - 1);
			}
			if(num < B) { // 새로 만든 수가 B보다 작은 경우...
				MAX = Math.max(MAX, num); // 최댓값 갱신
			}
			return;
		}
		
		for(int idx = 0; idx < size; idx++) {
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
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		int temp = A;
		while(true) {
			if(temp == 0) break;
			temp /= 10;
			size++;
		}
		numList = new int[size];
		
		// numList에 A의 각 자리에 있는 숫자 저장
		while(true) {
			if(A == 0) break;
			numList[numIdx++] = A % 10;
			A /= 10;
		}
		
		selectList = new int[size];
		visited = new boolean[size];
		
		Permutation(0);
		System.out.println(MAX);
	}
}
