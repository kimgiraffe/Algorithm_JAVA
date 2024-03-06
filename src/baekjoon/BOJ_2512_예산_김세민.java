package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_2512_예산_김세민
 */
public class BOJ_2512_예산_김세민 {
  static BufferedReader br;
  static StringTokenizer st;

  static int N; // 지방의 수
  static int[] request; // 지방의 예산요청
  static int M; // 총 예산

  static int solve() {

    int low = 0;
    int high = request[N - 1];

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int sum = 0;
      for (int idx = 0; idx < N; idx++) {
        if (mid > request[idx]) {
          sum += request[idx];
        } else {
          sum += mid;
        }
      }
      if (sum <= M) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return low - 1;

  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine().trim());

    st = new StringTokenizer(br.readLine().trim());

    request = new int[N];

    for (int idx = 0; idx < N; idx++) {
      request[idx] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(request);

    M = Integer.parseInt(br.readLine().trim());

    System.out.println(solve());
  }
}