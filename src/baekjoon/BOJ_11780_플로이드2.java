package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 플로이드_2
 */
public class BOJ_11780_플로이드2 {
  static BufferedReader br;
  static StringTokenizer st;
  static StringBuilder sb;

  static int n, m;
  static int[][] cost;
  static int[][] route;
  static List<Integer> list;
  static final int INF = 987654321;

  static void floyd() {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        for (int k = 1; k <= n; k++) {
          if (j == k) {
            continue;
          }
          if (cost[j][k] > cost[j][i] + cost[i][k]) {
            cost[j][k] = cost[j][i] + cost[i][k];
            route[j][k] = i;
          }
        }
      }
    }
  }

  static void findRoute(int from, int to) {
    if (route[from][to] == 0) {
      list.add(from);
      list.add(to);
      return;
    }

    findRoute(from, route[from][to]);
    list.remove(list.size() - 1);
    findRoute(route[from][to], to);
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    sb = new StringBuilder();
    n = Integer.parseInt(br.readLine().trim());
    m = Integer.parseInt(br.readLine().trim());

    cost = new int[n + 1][n + 1];
    route = new int[n + 1][n + 1];

    for (int from = 1; from <= n; from++) {
      for (int to = 1; to <= n; to++) {
        cost[from][to] = INF;
      }
    }

    for (int idx = 0; idx < m; idx++) {
      st = new StringTokenizer(br.readLine().trim());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      cost[from][to] = Math.min(cost[from][to], weight);
    }

    floyd();

    for (int from = 1; from <= n; from++) {
      for (int to = 1; to <= n; to++) {
        if (cost[from][to] == INF) {
          sb.append(0).append(" ");
        } else {
          sb.append(cost[from][to]).append(" ");
        }
      }
      sb.append("\n");
    }

    for (int from = 1; from <= n; from++) {
      for (int to = 1; to <= n; to++) {
        if (cost[from][to] == INF) {
          sb.append(0);
        } else {
          list = new ArrayList<>();
          findRoute(from, to);
          sb.append(list.size()).append(" ");
          for (int idx = 0; idx < list.size(); idx++) {
            sb.append(list.get(idx)).append(" ");
          }
        }
        sb.append("\n");
      }
    }

    System.out.print(sb);
  }
}