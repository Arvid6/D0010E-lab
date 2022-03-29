package lab1;


public class Raise {
	private static int c = 0;
	
	public static double recRaiseOne(double x, int k) {
		if (k == 0) {
			return 1.0;
		}
		else {
			c++;
			return(x * recRaiseOne (x, k-1));
		}
	}

	public static double recRaiseHalf(double x, int k) {
		if(k == 0) {
			return 1;
		}
			double xk = recRaiseHalf(x, k/2);
			
		if(k % 2 == 0) {
			c++;
			return(xk * xk);
		}
		else {
			c++;
			return(x * xk * xk);
		}
	}

	public static void main(String[] args) {
		double x = 1.5;
		int k = 8192;
		int n = 1;
		switch(n) {
		case 1:
			c = 0;
			System.out.println(recRaiseHalf(x, k));
			System.out.println(c);
			break;
		case 2:
			for(int i = 1; i <= 15; i++) {
				c = 0;
				System.out.println(recRaiseHalf(x, i));
				System.out.println(i + " Half "+ c);
				c = 0;
				System.out.println(recRaiseOne(x, i));
				System.out.println(i + " One " + c);
			}
			break;
		}
		

	}

}
