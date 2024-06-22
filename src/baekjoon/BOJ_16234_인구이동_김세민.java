package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 인구_이동
 */
public class BOJ_16234_인구이동_김세민 {
  static BufferedReader br;
  static StringTokenizer st;

  static int N, L, R;
  static int answer;
  static int[][] p;
  static boolean[][] visited;
  static final int[] DELTA_ROW = { -1, 0, 1, 0 };
  static final int[] DELTA_COL = { 0, 1, 0, -1 };
  static List<Pos> list;

  static class Pos {
    int row;
    int col;

    public Pos(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  static boolean isInRange(int row, int col) {
    return row >= 0 && row < N && col >= 0 && col < N;
  }

  static int bfs(int row, int col) {
    int sum = p[row][col];
    Queue<Pos> queue = new ArrayDeque<>();
    list = new ArrayList<>();
    queue.offer(new Pos(row, col));
    list.add(new Pos(row, col));
    visited[row][col] = true;

    while (!queue.isEmpty()) {
      Pos cur = queue.poll();

      for (int dir = 0; dir < 4; dir++) {
        int nextRow = cur.row + DELTA_ROW[dir];
        int nextCol = cur.col + DELTA_COL[dir];

        if (isInRange(nextRow, nextCol) && !visited[nextRow][nextCol]) {
          int diff = Math.abs(p[cur.row][cur.col] - p[nextRow][nextCol]);
          if (L <= diff && diff <= R) {
            queue.offer(new Pos(nextRow, nextCol));
            list.add(new Pos(nextRow, nextCol));
            sum += p[nextRow][nextCol];
            visited[nextRow][nextCol] = true;
          }
        }
      }
    }

    return sum;
  }

  static void move(int sum) {
    int avg = sum / list.size();
    for (Pos pos : list) {
      p[pos.row][pos.col] = avg;
    }
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());

    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    p = new int[N][N];

    for (int row = 0; row < N; row++) {
      st = new StringTokenizer(br.readLine().trim());
      for (int col = 0; col < N; col++) {
        p[row][col] = Integer.parseInt(st.nextToken());
      }
    }

    while (true) {
      visited = new boolean[N][N];
      boolean flag = false;
      for (int row = 0; row < N; row++) {
        for (int col = 0; col < N; col++) {
          if (!visited[row][col]) {
            int sum = bfs(row, col);
            if (list.size() > 1) {
              move(sum);
              flag = true;
            }
          }
        }
      }

      if (!flag) {
        break;
      }

      answer++;
    }

    System.out.println(answer);

  }
}