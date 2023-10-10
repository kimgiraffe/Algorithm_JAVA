package swea;
import java.util.Scanner;

class SWEA_7465_창용마을무리의개수_김세민 {
	static boolean visited[];
	static int[][] R;
	static int N, cnt;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			int M = sc.nextInt();
			
			R = new int[N+1][N+1];
			visited = new boolean[N+1];
			
			for(int i = 0; i < N; i++) {
				visited[i] = false;
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					R[i][j] = 0;
				}
			}
			
			for(int i = 1; i <= M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				R[a][b] = 1;
				R[b][a] = 1;
			}
			
			cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) {
					DFS(i);
					cnt++;
				}
			}
			
			System.out.println("#" + test_case + " " + cnt);
		}
		
		sc.close();
	}
	
	public static void DFS(int node) {
		if(visited[node]) {
			return;
		}
		visited[node] = true;
		
		for(int i = 1; i <= N; i++) {
			if(R[node][i] == 1) {
				DFS(i);
			}
		}
	}
}