package com.ssafy.recur.BOJ.sol1074;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 4등분 한 후에 크기를 구해서 범위 안에 있을경우 거기로 들어가고 + 4등분 사이즈 그래서 거기서 탐색
// N 만큼 쪼개서 탐색
// N만큼 쪼갰으면 해당 범위를 탐색 후 r행 c열 초과되면 다음걸로 넘어감?
public class BOJ_1074_Z {
	static int[][] map;
	static int cnt, r, c;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];
		int x = 0;
		int y = 0;
		
		dfs(map.length / 2, x, y);
		cnt += (map.length * map.length) / 4;
		dfs(map.length / 2, x, y + map.length / 2);
		cnt += (map.length * map.length) / 4;
		dfs(map.length / 2, x + map.length / 2, y);
		cnt += (map.length * map.length) / 4;
		dfs(map.length / 2, x + map.length / 2, y + map.length / 2);
		System.out.println(result);
	}

	static void dfs(int N, int x, int y) {
		if ( result != 0 ) return;
		if (r  >= x && r  < N + x && c  >= y && c  < y + N) {
			if (N == 2 && r  >= x && r  < N + x && c  >= y && c  < y + N) {
				for (int i = x; i < x + N; i++) {
					for (int j = y; j < y + N; j++) {
						cnt++;
						if (i == r  && j == c ) {
							result=--cnt;
							return;
						}
					}
				}
			}
			if (r>=x && r<N/2+x && c>=y && c<y+N/2) dfs(N / 2, x, y);
			cnt += (N * N) / 4;
			if (r>=x && r<N/2+x && c>=y+N/2 && c<y+N/2) dfs(N / 2, x, y);
			dfs(N / 2, x, y + N / 2);
			cnt += (N * N) / 4;
			dfs(N / 2, x + N / 2, y);
			cnt += (N * N) / 4;
			dfs(N / 2, x + N / 2, y + N / 2);
		}

	}
}
