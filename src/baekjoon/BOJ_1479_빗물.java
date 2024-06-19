package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1479_빗물
 */
public class BOJ_1479_빗물 {
  static BufferedReader br;
  static StringTokenizer st;

  static int H, W;
  static int[] height;
  static int answer;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());

    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());

    height = new int[W];

    st = new StringTokenizer(br.readLine().trim());
    for (int idx = 0; idx < W; idx++) {
      height[idx] = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i < W - 1; i++) {
      int left = 0, right = 0;

      for (int j = 0; j < i; j++) {
        left = Math.max(left, height[j]);
      }

      for (int j = W - 1; j > i; j--) {
        right = Math.max(right, height[j]);
      }

      answer += Math.max(0, Math.min(left, right) - height[i]);
    }

    System.out.println(answer);
  }
}