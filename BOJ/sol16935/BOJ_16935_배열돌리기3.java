package com.ssafy.recur.BOJ.sol16935;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {
	static int[][] map;
	static int N;
	static int M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		st= new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int num = Integer.parseInt(st.nextToken());
			switch (num) {
			case 1:
				map = check1();
				break;
			case 2:
				map = check2();
				break;
			case 3:
				map = check3();
				break;
			case 4:
				map = check4();
				break;
			case 5:
				map = check5();
				break;
			case 6:
				map = check6();
				break;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0 ; j <M ; j ++) {
				bw.append(map[i][j] + " ");
			}
			bw.append("\n");
		}
		bw.flush();
	}

	static int[][] check1() {
		int[][] map2 = new int[N][M];
		for (int i = 0; i < N; i++) {
			map2[i] = map[N - i - 1].clone();
		}
		return map2;
	}

	static int[][] check2() {
		int[][] map2 = new int[N][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map2[j][i] = map[j][M-i-1];
			}
		}
		return map2;
	}

	static int[][] check3() {
		int temp=M;
		M = N;
		N = temp;
		int[][] map2 = new int[N][M];
		for (int i = 0 ; i <N ; i++) {
			for (int j=0; j <M ; j ++) {
				map2[i][j]= map[M-j-1][i];
			}
		}
		return map2;
	}

	static int[][] check4() {
		int temp=M;
		M = N;
		N= temp;
		int[][] map2 = new int[N][M];
		for (int i = 0 ; i <N ; i++) {
			for (int j=0; j <M ; j ++) {
				map2[i][j]= map[j][N-i-1];
			}
		}
		return map2;
	}

	static int[][] check5() {
		int[][] map2 = new int[N][M];
		for(int i =N/2; i<N ; i ++) {
			for (int j=0 ; j<M/2 ; j++) {
				map2[i-(N/2)][j]=map[i][j];
			}
		}
		for(int i =0; i<N/2 ; i ++) {
			for (int j=0 ; j<M/2 ; j++) {
				map2[i][j+(M/2)]=map[i][j];
			}
		}
		for(int i =0; i<N/2 ; i ++) {
			for (int j=M/2 ; j<M ; j++) {
				map2[i+(N/2)][j]=map[i][j];
			}
		}
		for(int i =N/2; i<N ; i ++) {
			for (int j=M/2 ; j<M ; j++) {
				map2[i][j-(M/2)]=map[i][j];
			}
		}
		return map2;
	}

	static int[][] check6() {
		int[][] map2 = new int[N][M];
		for(int i =N/2; i<N ; i ++) {
			for (int j=0 ; j<M/2 ; j++) {
				map2[i][j+(M/2)]=map[i][j];
			}
		}
		for(int i =0; i<N/2 ; i ++) {
			for (int j=0 ; j<M/2 ; j++) {
				map2[i+(N/2)][j]=map[i][j];
			}
		}
		for(int i =0; i<N/2 ; i ++) {
			for (int j=M/2 ; j<M ; j++) {
				map2[i][j-(M/2)]=map[i][j];
			}
		}
		for(int i =N/2; i<N ; i ++) {
			for (int j=M/2 ; j<M ; j++) {
				map2[i-(N/2)][j]=map[i][j];
			}
		}
		return map2;
	}
}
