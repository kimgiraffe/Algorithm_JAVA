package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_28234_스케이트연습
 * 코스는 시작 지점, N(1이상 50만 이하)개의 중간 지점, 도착 지점으로 구성
 * 시작 지점에서 0의 속력으로 출발, 0의 속력으로 도착 지점에 도달
 * 속력을 높이는 경우, 제한 없음
 * 속력을 낮추는 경우, 마지막 방문 지점에서 1만큼만 낮출 수 있음
 * 출발 지점과 도착 지점을 제외한 위치에서 속력은 0이 될 수 없음
 * 각 지점에서의 속력의 합이 최대가 되려면?
 * 1. 마지막 중간 지점에서의 속력은 무조건 1
 * 2. 현재 속력이 다음 속력보다 큰 경우... 현재 속력은 최대 다음 속력 + 1.
 * 3. 중간 지점을 역순으로 순회하자.
 * @author semin.kim
 */

public class BOJ_28324_스케이트연습_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int point_cnt; // 중간 지점의 개수
	static int[] points; // 각 중간 지점의 속력 제한을 저장하는 1차원 배열
	static long sum; // 얻을 수 있는 가장 큰 연습 성과(속력의 합)
	
	public static void solve() {
		// 역순으로 순회
		for(int idx = point_cnt + 1; idx > 0; idx--) {
			if(points[idx] < points[idx-1]) { // 이전 지점의 속력 제한이 더 큰 경우...
				points[idx-1] = points[idx] + 1; // 가능한 속력을 + 1
			}
			sum += points[idx]; // 속력의 합에 더함
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		point_cnt = Integer.parseInt(br.readLine().trim());
		points = new int[point_cnt+2]; // 출발 지점, 중간 지점, 도착 지점의 속력 제한
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 1; idx <= point_cnt; idx++) {
			points[idx] = Integer.parseInt(st.nextToken());
		}
		
		solve();
		System.out.println(sum);
	}
}
