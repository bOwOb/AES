
public class Mixcolumn {
	int[][] Mixcolumn(int x[][], boolean inverse){
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
		int[][] mixmat=new int[4][4];
		if(inverse) {
			mixmat = invmat;
		}
		else{
			mixmat = mat;
		}
		int[][] y = new int[x.length][x[0].length];
		int gf = 283;
		for(int i = 0; i<x.length; i++) {			
			for(int j = 0; j<x[i].length; j++) {
				for(int k = 0; k<mat.length; k++) {
//					y[i][j] ^= x[k][j]*(mat[i][k]&(int)Math.pow(2, 0)) ^ x[k][j]*(mat[i][k]&(int)Math.pow(2, 1));
//					if(y[i][j]>255) {
//						y[i][j] ^= gf;
//					}
					y[i][j] ^= columnscal(x[k][j],mixmat[i][k]);
				}
			}
		}
		
		return y;
	}
	int columnscal(int mat, int c) {
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
}


