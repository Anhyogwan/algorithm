package com.ssafy.recur.swea.sol1238;
import java.io.*;
import java.util.*;

class Pair {
	int first, second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

class Solution {
	public static void main(String args[]) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] stu_list = new ArrayList[101];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 101; i++) {
				stu_list[i] = new ArrayList<>();
			}
			for (int i = 0; i < N; i += 2) {
				int d = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				stu_list[d].add(c);
			}

			boolean[] check = new boolean[101];
			Arrays.fill(check, false);
			Queue<Integer> queue = new LinkedList<>();
			queue.add(start);
			int answer = 0;
			while (queue.size() > 0) {
				int size=queue.size();
				int max_value = 0;
				for (int j = 0; j < size; j++) {
					int student = queue.poll();
					if (check[student]==true) {
						continue;
					}
					check[student] = true;
					if (max_value < student) {
						max_value = student;
					}
					for (int i = 0; i < stu_list[student].size(); i++) {
						if (check[stu_list[student].get(i)] == true) {
							continue;
						}
						queue.add(stu_list[student].get(i));
					}
				}
				answer = max_value;
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}
