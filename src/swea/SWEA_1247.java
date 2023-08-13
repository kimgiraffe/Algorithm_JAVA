package swea;
import java.util.Scanner;

class SWEA_1247 {	
	
	static int N;
	static int[][] p;
	static boolean[] visited;
	static int min;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			
			 p = new int[N + 2][2];
			visited = new boolean[N + 1];
			min = Integer.MAX_VALUE;
			
			p[0][0] = sc.nextInt();
			p[0][1] = sc.nextInt();
			p[N+1][0] = sc.nextInt();
			p[N+1][1] = sc.nextInt();
			
			for(int i = 1; i <= N; i++) {
				p[i][0] = sc.nextInt();
				p[i][1] = sc.nextInt();
			}
			
			permutation(0, p[0][0], p[0][1], 0);
			System.out.println("#" + test_case + " " + min);
		}
		
		sc.close();
	}
	
	public static void permutation(int cnt, int x, int y, int len) {
		if(cnt == N) {
			len += cal(x, y, p[N + 1][0], p[N + 1][1]);
			if(len < min) {
				min = len;
			}
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				permutation(cnt + 1, p[i][0], p[i][1], len + cal(x, y, p[i][0], p[i][1]));
				visited[i] = false;
			}
		}
	}
	
	public static int cal(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}