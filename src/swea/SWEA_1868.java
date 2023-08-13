package swea;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class SWEA_1868 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int dx[] = {0, 1, 0, -1, 1, 1, -1, -1};
	static int dy[] = {1, 0, -1, 0, 1, -1, 1, -1};
	static Queue<int[]> q = new LinkedList<int[]>();
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			
			map = new char[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				String tmp = sc.next();
				for(int j = 0 ; j < N; j++) {
					map[i][j] = tmp.charAt(j);
					if(map[i][j] == '*') visited[i][j] = true;
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int cnt = 0;
					for(int k = 0; k < 8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						if(map[nx][ny] == '*') cnt++;
					}
					
					if(map[i][j] == '.') map[i][j] = (char)(cnt + '0');
				}
			}
			
			int total = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == '0' && visited[i][j] == false) {
						bfs(i, j);
						total++;
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == false) {
						total++;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + total);
		}
		
		sc.close();
	}
	
	public static void bfs(int i, int j) {
		q.add(new int[] {i, j});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			int x = curr[0];
			int y = curr[1];
			for(int k = 0; k < 8; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				if(visited[nx][ny] == false && map[nx][ny] == '0') {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
				else if(visited[nx][ny] == false && map[nx][ny] != '0') {
					visited[nx][ny] = true;
				}
			}
		}
	}
}