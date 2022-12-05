package com.ssafy.recur.BOJ.sol1697;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class time {
	int x;
	int t;

	public time(int x, int t) {
		this.x = x;
		this.t = t;
	}
}

public class BOJ_1697_숨바꼭질 {
	// 수빈이는 걷거나 순간이동 가능
	// 걸을 땐 앞이나 뒤
	// 순간이동을 하는 경우 2*X
	// 남은 거리의 차가 적은것을 넣어줌
	static int K;
	static int min_value = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[220000];
		bfs(N, 0, visited);
		System.out.println(min_value);
	}

	private static void bfs(int start, int cnt, boolean[] visited) {
		Queue<time> queue = new LinkedList<>();
		queue.offer(new time(start, cnt));
		while (!queue.isEmpty()) {
			time num = queue.poll();
			if (num.x == K) {
				min_value=Math.min(min_value, num.t);
			}
			if (num.x>=0 && num.x<visited.length && !visited[num.x]) {
				visited[num.x] = true;
				queue.offer(new time(num.x + 1, num.t + 1));
				queue.offer(new time(num.x - 1, num.t + 1));
				queue.offer(new time(num.x * 2, num.t + 1));
			}
		}
	}

}
