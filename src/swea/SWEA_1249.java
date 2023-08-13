package swea;
import java.util.*;

class Pair implements Comparable<Pair>{
    public int x;
    public int y;
    public int time;

    public Pair(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(this.time, other.time);
    }
}

public class SWEA_1249 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = sc.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.offer(new Pair(0, 0, map[0][0]));
            dist[0][0] = map[0][0];

            while (!pq.isEmpty()) {
                Pair current = pq.poll();

                if (current.time > dist[current.x][current.y]) {
                    continue;
                }

                if (current.x == N - 1 && current.y == N - 1) {
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        if (dist[nx][ny] > dist[current.x][current.y] + map[nx][ny]) {
                            dist[nx][ny] = dist[current.x][current.y] + map[nx][ny];
                            pq.offer(new Pair(nx, ny, dist[nx][ny]));
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + dist[N - 1][N - 1]);
        }

        sc.close();
    }
}
