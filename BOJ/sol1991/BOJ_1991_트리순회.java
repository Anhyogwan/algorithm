package com.ssafy.recur.BOJ.sol1991;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
class Node {
	char data;
	Node left;
	Node right;
	public Node(char data,Node left,Node right) {
		this.data=data;
		this.left = left;
		this.right = right;
	}
}

public class BOJ_1991_트리순회 {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N= Integer.parseInt(br.readLine());
		Node[] tree = new Node[N];
		for (int i = 0 ; i < N ; i ++) {
			tree[i] = new Node((char)('A'+i),null,null); 
		}
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			char alpa=st.nextToken().charAt(0);
			char ch =st.nextToken().charAt(0);
			if (ch != '.') {
				tree[alpa-'A'].left=tree[ch-'A'];
			}
			ch =st.nextToken().charAt(0);
			if (ch != '.') {
				tree[alpa-'A'].right=tree[ch-'A'];
			}
		}
		PreOrder(tree[0]);
		System.out.println();
		InOrder(tree[0]);
		System.out.println();
		PostOrder(tree[0]);
	}
	
	static void PreOrder(Node tree) {
		if (tree==null) return;
		System.out.print(tree.data);
		PreOrder(tree.left);
		PreOrder(tree.right);
	}
	static void InOrder(Node tree) {
		if (tree==null) return;
		InOrder(tree.left);
		System.out.print(tree.data);
		InOrder(tree.right);
	}
	static void PostOrder(Node tree) {
		if (tree==null) return;
		PostOrder(tree.left);
		PostOrder(tree.right);
		System.out.print(tree.data);
	}
}
