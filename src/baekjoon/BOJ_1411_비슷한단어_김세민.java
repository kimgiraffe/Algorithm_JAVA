package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 비슷한_단어
 */
public class BOJ_1411_비슷한단어_김세민 {
  static BufferedReader br;
  static int N; // 단어의 개수
  static String[] words;
  static int answer;
  static String[] selected;

  static String mapping(String word) {
    String result = "";
    Map<Character, Character> map = new HashMap<>();

    char ch = 'a';

    for (int idx = 0; idx < word.length(); idx++) {
      if (!map.containsKey(word.charAt(idx))) {
        map.put(word.charAt(idx), (char) ch++);
      }
      result += map.get(word.charAt(idx));
    }

    return result;
  }

  static void combination(int selectIdx, int curIdx) {
    if (selectIdx == 2) {
      if (selected[0].equals(selected[1])) {
        answer++;
      }
      return;
    }

    if (curIdx == N) {
      return;
    }

    selected[selectIdx] = words[curIdx];
    combination(selectIdx + 1, curIdx + 1);

    combination(selectIdx, curIdx + 1);
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine().trim());
    words = new String[N];
    selected = new String[2];
    for (int idx = 0; idx < N; idx++) {
      words[idx] = mapping(br.readLine().trim());
    }

    combination(0, 0);

    System.out.println(answer);
  }
}