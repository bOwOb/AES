
public class Invmixtest {

	public static void main(String[] args) {
		String[][] m1 = {
//				{"91","94","9d","9e"},
//				{"0a","0c","0c","08"},
//				{"0f","08","09","0c"},
//				{"1c","14","16","10"}
				{"cb","3f","60","c8"},
				{"af","40","5e","d2"},
				{"38","84","97","c5"},
				{"e8","45","4b","c4"}
		};
		int[][] x = new int[4][4];
		x = hextodec(m1);
		x = Mixcolumn(x);
		printblock(x);
	}
	static int[][] Mixcolumn(int x[][]){
		int mat[][] = {
				{2,3,1,1},
				{1,2,3,1},
				{1,1,2,3},
				{3,1,1,2}
		};
		
		int invmat[][] = {
				{14,11,13,9},
				{9,14,11,13},
				{13,9,14,11},
				{11,13,9,14}
		};
		int a= 0;
		int b= 0;
		int[][] y = new int[x.length][x[0].length];
		int gf = 283;
		int Q =0;
		for(int i = 0; i<x.length; i++) {			
			for(int j = 0; j<x[i].length; j++) {
				for(int k = 0; k<mat.length; k++) {
					Q = x[k][j];
					
					y[i][j] ^= columnscal(x[k][j],invmat[i][k]);


				}
			}
		

		}
		return y;
	}
	static void printblock(int x[][]) {
		System.out.println();
		for(int i = 0; i<x.length; i++) {
			for(int j = 0; j<x[i].length; j++) {
				System.out.printf("%02x ", x[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	static int[][] hextodec(String x[][]){
		int[][] t = new int[x.length][x[0].length]; 
		for(int i = 0; i<x.length; i++) {
			for(int j = 0; j<x[i].length;j++) {
				t[i][j] = Integer.parseInt(x[i][j], 16);
			}			
		}
		
		return t;
	}
	static int columnscal(int mat, int c) {
		int a = 0;
		if((c & (int)Math.pow(2, 0)) !=0) {
			a ^= mat;
		}
		for(int i =1; i<4; i++) {
			mat<<=1;
			if(mat>255) {
				mat ^= 283;
			}
			if((c & (int)Math.pow(2, i)) !=0) {
				a ^= mat;
				
			}
		}
		return a;
	}
	static int overflow(int x) {
		if(x > 255) {
			x ^= 283;
		}
		return x;
	}
	
}

//case 9:
//	Q *=2;
//	Q =overflow(Q);
//	Q *=2;
//	Q =overflow(Q);
//	Q *=2;
//	Q =overflow(Q);
//	Q^=x[k][j];
//	break;
//case 11:
//	Q *=2;
//	Q =overflow(Q);
//	Q *=2;
//	Q =overflow(Q);
//	Q^=x[k][j];
//	Q *=2;
//	Q =overflow(Q);
//	Q^=x[k][j];
//	break;
//case 13:
//	Q *=2;
//	Q =overflow(Q);
//	Q^=x[k][j];
//	Q *=2;
//	Q =overflow(Q);
//	Q *=2;
//	Q =overflow(Q);
//	Q^=x[k][j];
//	break;
//case 14:
//	Q *=2;
//	Q =overflow(Q);
//	Q^=x[k][j];
//	Q *=2;
//	Q =overflow(Q);
//	Q^=x[k][j];
//	Q *=2;
//	Q =overflow(Q);
//	break;
