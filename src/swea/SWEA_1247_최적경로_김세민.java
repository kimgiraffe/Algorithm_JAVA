package swea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로_김세민 {	

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int customer_cnt; // 고객의 수
	static int[][] pos; // 좌표를 저장하는 2차원 배열
	static boolean[] visited;
	static int min; // 최단 경로의 이동거리

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력

		for(int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder();
			customer_cnt = Integer.parseInt(br.readLine().trim()); // 고객의 수 입력

			pos = new int[customer_cnt + 2][2];
			visited = new boolean[customer_cnt + 1];
			min = Integer.MAX_VALUE; // 최단 경로의 이동거리 초기화

			st = new StringTokenizer(br.readLine().trim());
			// 회사의 좌표 입력
			pos[0][0] = Integer.parseInt(st.nextToken()); 
			pos[0][1] = Integer.parseInt(st.nextToken());
			// 집의 좌표 입력
			pos[customer_cnt+1][0] = Integer.parseInt(st.nextToken());
			pos[customer_cnt+1][1] = Integer.parseInt(st.nextToken());

			// 고객의 좌표 입력
			for(int idx = 1; idx <= customer_cnt; idx++) {
				pos[idx][0] = Integer.parseInt(st.nextToken());
				pos[idx][1] = Integer.parseInt(st.nextToken());
			}

			permutation(0, pos[0][0], pos[0][1], 0);
			sb.append('#').append(test_case).append(' ').append(min);
			System.out.println(sb);
		}

	}

	public static void permutation(int cnt, int x, int y, int len) {
		if(cnt == customer_cnt) { // 고객을 모두 방문한 경우..
			len += cal(x, y, pos[customer_cnt + 1][0], pos[customer_cnt + 1][1]);
			if(len < min) { // 최단 경로의 이동거리 갱신
				min = len;
			}
			return;
		}

		for(int idx = 1; idx <= customer_cnt; idx++) {
			if(!visited[idx]) {
				visited[idx] = true;
				permutation(cnt + 1, pos[idx][0], pos[idx][1], len + cal(x, y, pos[idx][0], pos[idx][1]));
				visited[idx] = false;
			}
		}
	}

	public static int cal(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}