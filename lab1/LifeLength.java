package lab1;

public class LifeLength {
	public static int recLifeLength(int x) {
		if(x == 1) {
			return 0;
		}
		else {
			return recLifeLength(f1(x)) + 1;	
		}
	}
	public static int iterLifeLength(int a0) {
		int c = 0;
		while(a0 != 1) {
			a0 = f1(a0);
			c++;
		}
		return c;
	}
	public static String intsToString(int x, int y) {
		return("The life length of " + x + " is " + y + ".");
	}
	public static int iterateF(int a0, int n) {
		for(int i = 0; i < n; i++)
			a0 = f1(a0);
		return a0;
	}
	public static int f1(int a0){
		if(a0 == 1) {
			return 1;
		}
		if(a0 % 2 == 0) {
			return(a0/2);
		}
		if(a0 == 0) {
			return -1;
		}
		else {
			return(3*a0+1);
		}
	}
	
	public static int f2(int a0){
		a0 = f1(a0);
		return(f1(a0));
	}
	public static int f4(int a0){
		a0 = f2(a0);
		return(f2(a0));
	}
	public static int f8(int a0){
		a0 = f4(a0);
		return(f4(a0));
	}
	public static int f16(int a0){
		a0 = f8(a0);
		return(f8(a0));
	}
	public static int f32(int a0){
		a0 = f16(a0);
		return(f16(a0));
	}

	public static void mf1(int x) {
		System.out.println(f1(x));
	}
	public static void mfa(int x) {
		System.out.println(f1(x));
		System.out.println(f2(x));
		System.out.println(f4(x));
		System.out.println(f8(x));
		System.out.println(f16(x));
		System.out.println(f32(x));
	}
	public static void miF(int x, int y) {
		System.out.print(iterateF(x, y));
	}
	public static void iLL() {
		int s;
		for(int i = 1; i <= 15; i++) {
			s = iterLifeLength(i);
			System.out.println(intsToString(i, s));
		}
	}
	public static void rLL(int x) {
		for(int i = 1; i <= 15; i++) {
			int s = recLifeLength(i);
			System.out.println(intsToString(i, s));
		}
	}

	public static void main(String[] args) {
		int n = 6;
		switch(n) {
		case 1:
			mf1(42);
			break; 
		case 2:
			mfa(42);
			break; 
		case 3:
			miF(1, 3);
			break; 
		case 4:
			iLL();
			break; 
		case 6:
			rLL();
			break; 
		default:
			System.out.print("ogiltigt värde på n");
		}
	}
}
