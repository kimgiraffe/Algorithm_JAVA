import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_4193 {
	static class Player{
		int row, col, cnt;
		public Player(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
	public static int[] dx = {-1,0,1,0};
	public static int[] dy = {0,1,0,-1};
	static int[][] map;
	static boolean[][] visit;
	static int N,ans;
	static int SROW, SCOL, FROW, FCOL;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] =  Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			SROW = Integer.parseInt(st.nextToken());
			SCOL = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			FROW = Integer.parseInt(st.nextToken());
			FCOL = Integer.parseInt(st.nextToken());
			
			if(SROW == FROW && SCOL == FCOL) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}
			
			System.out.println("#" + tc + " " + (bfs() ? ans : -1));
		}
	}
	private static boolean bfs() {
		Queue<Player> q = new LinkedList<>();
		q.add(new Player(SROW, SCOL,0));
		visit[SROW][SCOL] = true;
		
		while(!q.isEmpty()) {
			Player player = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = player.row + dx[i];
				int ny = player.col + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(nx == FROW && ny == FCOL) {
					ans = player.cnt+1;
					return true;
				}
				if(map[nx][ny] == 1 || visit[nx][ny]) continue;
				
				if(map[nx][ny] == 2) {
					if(player.cnt%3 == 2) {
						visit[nx][ny] = true;
						q.add(new Player(nx,ny,player.cnt+1));
					}else {
						visit[player.row][player.col] = true;
						q.add(new Player(player.row,player.col,player.cnt+1));
					}
				}else {
					visit[nx][ny] = true;
					q.add(new Player(nx,ny,player.cnt+1));
				}
			}
		}
		return false;
	}
}