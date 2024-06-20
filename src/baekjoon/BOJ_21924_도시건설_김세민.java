package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 도시_건설
 */
public class BOJ_21924_도시건설_김세민 {
  static BufferedReader br;
  static StringTokenizer st;

  static int N, M; // 건물의 개수, 도로의 개수
  static long answer;

  static class Edge implements Comparable<Edge> {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.cost, o.cost);
    }
  }

  static PriorityQueue<Edge> pq;
  static int[] parent;

  static void make() {
    parent = new int[N + 1];
    for (int idx = 1; idx <= N; idx++) {
      parent[idx] = idx;
    }
  }

  static int find(int a) {
    if (parent[a] == a) {
      return a;
    }

    return parent[a] = find(parent[a]);
  }

  static boolean union(int a, int b) {
    int aRoot = find(a);
    int bRoot = find(b);
    if (aRoot == bRoot) {
      return false;
    }

    parent[bRoot] = aRoot;
    return true;
  }

  static boolean kruskal() {
    int count = 0;
    while (!pq.isEmpty()) {
      Edge edge = pq.poll();
      if (union(edge.from, edge.to)) {
        answer -= edge.cost;
        count++;
        if (count == N - 1) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    pq = new PriorityQueue<>();

    for (int idx = 0; idx < M; idx++) {
      st = new StringTokenizer(br.readLine().trim());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      pq.offer(new Edge(from, to, cost));
      answer += cost;
    }

    make();

    if (kruskal()) {
      System.out.println(answer);
    } else {
      System.out.println(-1);
    }
  }
}