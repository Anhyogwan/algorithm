package com.ssafy.recur.BOJ.sol13458;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] list = new int[N];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			list[i] = num;
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long total = 0;
		for (int i = 0; i < N; i++) {
			if (list[i] - B > 0) {
				if ((list[i] - B) % C != 0) {
					total += ((list[i] - B) / C + 1);
				} else {
					total += ((list[i] - B) / C);
				}
			}
		}
		total += N;
		sb.append(total);

		System.out.println(sb);
	}
}
