package com.ssafy.recur.BOJ.sol2961;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가만든맛있는음식 {
	static int[][] food;
	static int N;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int min_value = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		food = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			food[i][0] = Integer.parseInt(st.nextToken());
			food[i][1] = Integer.parseInt(st.nextToken());
		}
		check(0, 0);
		bw.write(String.valueOf(min_value));
		bw.flush();
	}

	static void check(int flag, int idx) {
		if (idx == N) {
			if (flag > 0) {
				int bitter = 0;
				int sour = 1;
				for (int i = 0; i < N; i++) {
					if ((flag & 1 << i) == 0)
						continue;
					sour *= food[i][0];
					bitter+= food[i][1];
				}
				if (Math.abs(bitter - sour) < min_value) {
					min_value = Math.abs(bitter - sour);
				}
			}
			return;
		}
		check(flag, idx + 1);
		check(flag | 1 << idx, idx + 1);
	}
}
