package com.ssafy.recur.swea.sol5644;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class AP {
	int r;
	int c;
	int range;
	int p;
	int[][] map;
	public AP(int r, int c, int range, int p) {
		this.r = r;
		this.c = c;
		this.range = range;
		this.p = p;
		map = new int[11][11];
	}
}
public class SWEA_5644_무선충전_2  {
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	static AP[] arrAP;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int[] arrA = new int[M];
			int[] arrB = new int[M];
			arrAP = new AP[A];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arrA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arrB[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < arrAP.length; i++) {
				st = new StringTokenizer(br.readLine());
				arrAP[i] = new AP(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			for (int i = 0; i < arrAP.length; i++) {
				mark(arrAP[i].r, arrAP[i].c, arrAP[i].range, arrAP[i].p, i);
			}
			int a1 = 1, a2 = 1;
			int b1 = 10, b2 = 10;
			int a = 0, b = 0;
			for (int j = 0; j < arrAP.length; j++) {
				a = Math.max(a, arrAP[j].map[a1][a2]);
				b = Math.max(b, arrAP[j].map[b1][b2]);
			}
			int result = a + b;
			for (int i = 0; i < M; i++) {
				a1 += dx[arrA[i]];
				a2 += dy[arrA[i]];
				
				b1 += dx[arrB[i]];
				b2 += dy[arrB[i]];
				a = 0; b = 0;
				int sum = 0;
				int plus = 0;
				for (int j = 0; j < arrAP.length; j++) {
					if(arrAP[j].map[a1][a2] != 0 && arrAP[j].map[a1][a2] == arrAP[j].map[b1][b2]) {
						sum = arrAP[j].map[a1][a2];
						int sum1 = sum;
						int sum2 = sum;
						for (int j2 = 0; j2 < arrAP.length; j2++) {
							if(j2 != j && arrAP[j2].map[a1][a2] != 0) {
								sum1 = Math.max(sum1, arrAP[j].map[b1][b2] + arrAP[j2].map[a1][a2]);
							}
							if(j2 != j && arrAP[j2].map[b1][b2] != 0) {
								sum2 = Math.max(sum2, arrAP[j].map[a1][a2] + arrAP[j2].map[b1][b2]);
							}
						}
						sum = Math.max(sum1, sum2);
					}else {
						a = Math.max(a, arrAP[j].map[a1][a2]);
						b = Math.max(b, arrAP[j].map[b1][b2]);
					}
					plus = Math.max(plus, sum);
				}
				result += Math.max(plus, a + b);
			}
			sb.append("#" + test_case + " " + result + "\n");
		}
		System.out.println(sb);
	}
	private static void mark(int r, int c, int range, int p, int idx) {	// 배터리 범위 표시
		int x1 = r, x2 = r, y1 = c, y2 = c;
		int cnt = 0;
		while(x1 > 0 && cnt <= range) {
			x1--;
			cnt++;
		}
		cnt = 0;
		while(x2 <= 10 && cnt <= range) {
			x2++;
			cnt++;
		}
		cnt = 0;
		while(y1 > 0 && cnt <= range) {
			y1--;
			cnt++;
		}
		cnt = 0;
		while(y2 <= 10 && cnt <= range) {
			y2++;
			cnt++;
		}
		
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				if(Math.abs(i - r) + Math.abs(j - c) <= range) {
					arrAP[idx].map[j][i] = p;
				}
			}
		}
	}
}
