package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * BOJ_15666
 */
public class BOJ_15666_N과M12_김세민 {
  static BufferedReader br;
  static StringTokenizer st;
  static StringBuilder sb;

  static int N, M;
  static int[] number;
  static int[] selected;
  static boolean[] visited;

  static Set<Integer> set = new HashSet<>();

  public static void combination(int currentIdx, int selectIdx) {
    if (currentIdx == M) {
      for (int idx = 0; idx < M; idx++) {
        sb.append(selected[idx]).append(" ");
      }
      sb.append("\n");
      return;
    }

    int last = 0;

    for (int idx = selectIdx; idx < N; idx++) {
      if (number[idx] != last) {
        selected[currentIdx] = number[idx];
        last = selected[currentIdx];
        combination(currentIdx + 1, idx);
      }
    }

    return;
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());
    sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    number = new int[N];
    selected = new int[M];

    st = new StringTokenizer(br.readLine().trim());
    for (int idx = 0; idx < N; idx++) {
      number[idx] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(number);

    combination(0, 0);

    System.out.println(sb);
  }

}