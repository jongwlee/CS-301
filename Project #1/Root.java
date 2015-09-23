// 
// Name: Lee, Jong 
// Project: # 1
// Due: 5/12/14 
// Course: CS 301
// 
// Description: 
// Implement a program for methods like (Bisection, Newton-Raphson, Secant, False-Position and 
// Modified Secant) to locate roots.


public class Root {
	
	public final static double e = 2.718281828;
	public final static double trueroot = 0.56714329;
	public static boolean secondEquation = false;
	
	public static double regularFunction(double number){
		if(secondEquation){
			return Math.pow(e, -number) - number;
		}
		return ((2 * Math.pow(number, 3)) - (11.7 * Math.pow(number, 2)) + (17.7 * number - 5));
	}
	
	public static double functionPrime(double number){
		if(secondEquation){
			return -1 * Math.pow(e, -number) - 1;
		}
		return ((6 * Math.pow(number, 2) - (23.4 * number) + 17.7));
	}
	
	public static void bisection(double a, double b, boolean approx, double errorT, int maxIteration){
		double functionA = regularFunction(a);
		double functionB = regularFunction(b);
		double functionC = 0;
		double c = 0;
		double previous = 0;
		double error = 0;
		
		System.out.println("BISECTION METHOD");
		System.out.println(" n   a   b   c   f(a)   f(b)   f(c)   error");
		System.out.println("-------------------------------------------");
		
		if(functionA == 0 || functionB == 0){
			System.out.println("The variable is the root.");
			return;
		}
		
		else if((functionA * functionB) > 0){
			System.out.println("a: " + a + " b: " + b + " F(a)" + functionA + " F(b)" + functionB);
			System.out.println("The function has same signs at a and b");
			return;
		}
		
		for(int i = 0; i <= maxIteration; ++i){
			c = (a + b) / 2;
			functionC = regularFunction(c);
			
			if(Double.isNaN(functionC)){
				System.out.println("It is a divergent solution");
				return;
			}
			
			if(approx){
				error = Math.abs((c - previous) / c);
			}
			else{
				error = Math.abs((trueroot - c) / trueroot);
			}
			
			if(i == 0 && approx){
				System.out.println(" " + i + "  " + a + " "  + b + "  " + c + "  " + functionA + "  " + functionB + "  " + functionC + "  " + "None");
			}
			else{
				System.out.println(" " + i + "  " + a + " " + b + "  " + c + "  " + functionA + "  " + functionB + "  " + functionC + "  " + error);
			}
			
			previous = c;
			
			if(Math.abs(error) < errorT){
				System.out.println("It is converging at: " + c);
				return;
			}
			
			if((functionA * functionC) < 0){
				b = c;
				functionB = functionC;
			}
			
			else{
				a = c;
				functionA = functionC;
			}
			
			if(i == maxIteration){
				System.out.println("It is slowly converging");
			}
			
		}
		
	}
	
	public static void newtonRaphson(double x, boolean approx, double errorT, int maxIteration){
		double previous = 0;
		double functionX = 0;
		double functionxPrime = 0;
		double error = 0;
		
		System.out.println("NEWTON-RAPHSON METHOD");
		System.out.println(" n    x    f(x)    f'(x)   error");
		System.out.println("--------------------------------");
		
		for(int i = 0; i <= maxIteration; ++i){
			functionX = regularFunction(x);
			functionxPrime = functionPrime(x);
			
			if(functionxPrime == 0){
				System.out.println("This function is not continuous");
				return;
			}
			
			if(Double.isNaN(functionX)){
				System.out.println("The function is a divergent solution");
			}
			
			if(approx){
				error = Math.abs((x - previous) / x );
			}
			
			if(Math.abs(error) < errorT){
				System.out.println("The solution is convergent at " + x);
				return;
			}
			
			if(i == 0 && approx){
				System.out.println(" " + i + "    " + x + "    " + functionX + "     " + functionxPrime + "   " + "None");
			}
			
			else{
				System.out.println(" " + i + "    " + x + "    " + functionX + "     " + functionxPrime + "   " + error);
			}
			
			if(i == maxIteration){
				System.out.println("It is slowly converging");
			}
			
			previous = x;
			x = x - (functionX / functionxPrime);
			functionX = regularFunction(x);
			functionxPrime = functionPrime(x);
			
			
		}
	}
	
	public static void swap(double number1, double number2){
		double temp = number1;
		number1 = number2;
		number2 = temp;
	}
	
	public static void secant(double a, double b, boolean approx, double errorT, int maxIteration){
		double functionA = regularFunction(a);
		double functionB = regularFunction(b);
		double error = 0;
		double d = 0;
		double previous = 0;
		
		
		System.out.println("SECANT METHOD");
		System.out.println(" n    x    f(x)    error");
		System.out.println("------------------------");
		
		if(Math.abs(functionA) > Math.abs(functionB)){
			swap(a, b);
			swap(functionA, functionB);
		}
		
		if(approx){
			error = Math.abs((b - a) / b);
			System.out.println(" " + 0 + "    " + a + "    " + functionA +"      None");
		}
		
		else{
			error = Math.abs((trueroot - a) / trueroot);
			System.out.println(" " + 0 + "    " + a + "    " + functionA +"      " + error);
		}
		
		error = Math.abs((trueroot - b) / trueroot);
		System.out.println(" " + 1 + "    " + b + "    " + functionB +"      " + error);
		previous = b;
		
		
		for(int i = 2; i <= maxIteration; ++i){
			if(Math.abs(functionA) > Math.abs(functionB)){
				swap(a, b);
				swap(functionA, functionB);
				previous = b;
			}
			
			d = (b - a) / (functionB - functionA);
			b = a;
			functionB = functionA;
			d = d * functionA;
			
			
			if(Math.abs(error) < errorT){
				System.out.println("It is converging at " + b);
				return;
			}
			
			a = a - d;
			functionA = regularFunction(a);
			
			
			if(approx){
				error = Math.abs((a - previous) / a );
			}
			
			else{
				error = Math.abs((trueroot - a) / trueroot);
			}
			System.out.println(" " + i + "    " + a + "    " + functionA +"      " + error);
			previous = a;
			
			if(Double.isNaN(functionA)){
				System.out.println("This function is a divergent solution");
				return;
			}
			
			if(i == maxIteration){
				System.out.println("The function is slowly converging");
			}	
		}
		
	}
	
	public static void falsePosition(double a, double b, boolean approx, double errorT, int maxIteration){
		double functionA = regularFunction(a);
		double functionB = regularFunction(b);
		double c = 0;
		double functionC = 0;
		double previous = 0;
		double error = 0;
		
		System.out.println("FALSE POSITION METHOD");
		System.out.println(" n   a   b   c   f(a)   f(b)   f(c)   error");
		System.out.println("-------------------------------------------");
		
		
		if((functionA * functionB) > 0){
			System.out.println(a + " " + b + " " + functionA + " " + functionB);
			System.out.println("They have the same signs");
			return;
		}
		
		for(int i = 0; i <= maxIteration; ++i){
			c = ((a * functionB) - (b * functionA)) / (functionB - functionA);
			functionC = regularFunction(c);
			
			if(approx){
				error = Math.abs((c - previous) / c);
			}
			
			else{
				error = Math.abs((trueroot - c) / trueroot);
			}
			
			if(Double.isNaN(functionC)){
				System.out.println("The function is a divergent solution");
				return;
			}
			
			if(i == 0 && approx){
				System.out.println(" " + i  + "  " + a + "  " + b + "  " + c + "  " + functionA + "  " + functionB + "  " + functionC + "  " + "None");
			}
			else{
				System.out.println(" " + i  + "  " + a + "  " + b + "  " + c + "  " + functionA + "  " + functionB + "  " + functionC + "  " + "  " + error);
			}
			
			previous = c;
			if(Math.abs(error) < errorT){
				System.out.println("The function is converging at " + c);
				return;
			}
			
			if((functionA * functionC) < 0){
				b = c;
				functionB = functionC;
			}
			
			else{
				a = c;
				functionA = functionC;
			}
			
			if(i == maxIteration){
				System.out.println("The function is slowly converging");
			}
		}
	}
	
	public static void modifiedSecant(double x, double delta, boolean approx, double errorT, int maxIteration){
		double functionX = regularFunction(x);
		double a = 0;
		double b = 0;
		double error = 0;
		double previous = 0;
		
		
		System.out.println("MODIFIED SECANT METHOD");
		System.out.println(" n    x    f(x)    error");
		System.out.println("------------------------");
		
		if(approx){
			System.out.println(" " + 0 + "    " + a + "    " + functionX +"      None");
		}
		
		else{
			error = Math.abs((trueroot - x) / trueroot);
			System.out.println(" " + 0 + "    " + a + "    " + functionX +"          " + error);
		}
		
		previous = x;
		
		for(int i = 1; i <= maxIteration; ++i){
			a = delta * x;
			b = regularFunction(x + a) - functionX;
			x = x - (functionX * (a / b ));
			functionX = regularFunction(x);
			
			
			if(approx){
				error = Math.abs((x - previous) / x);
			}
			
			else{
				error = Math.abs((trueroot - x) / trueroot);
			}
			
			System.out.println(" " + i + "    " + a + "    " + functionX +"      " + error);
			previous = x;
			
			if(i == maxIteration){
				System.out.println("The function is slowly converging");
			}
			
			if(Math.abs(error) < errorT){
				System.out.println("It is converging at" + x);
				return;
			}
			
		}
	}
	
	
	public static void main(String[] args){
		System.out.println("Part A");
		System.out.println("First Root: ");
		bisection(0 , 1, true, 0.01, 100);
		newtonRaphson(0 , true, 0.01, 100);
		secant(0 , 1, true, 0.01, 100);
		falsePosition(0 , 1 , true, 0.01, 100);
		modifiedSecant(1 , 0.01 , true, 0.01, 100);
	
		System.out.println("Part A");
		System.out.println("Second Root: ");
		bisection(1 , 2, true, 0.01, 100);
		newtonRaphson(2 , true, 0.01, 100);
		secant(1 , 2, true, 0.01, 100);
		falsePosition(1 , 2 , true, 0.01, 100);
		modifiedSecant(2 , 0.01 , true, 0.01, 100);

		System.out.println("Part A");
		System.out.println("Third Root: ");
		bisection(2 , 4, true, 0.01, 100);
		newtonRaphson(4 , true, 0.01, 100);
		secant(2 , 4, true, 0.01, 100);
		falsePosition(2 , 4 , true, 0.01, 100);
		modifiedSecant(4 , 0.01 , true, 0.01, 100);

		System.out.println("Part B");
		System.out.println("First Root, True: ");
		secondEquation = true;
		bisection(0 , 1, false, 0.01, 100);
		newtonRaphson(0 , false, 0.01, 100);
		secant(0 , 1, false, 0.01, 100);
		falsePosition(0 , 1 , false, 0.01, 100);
		modifiedSecant(1 , 0.01 , false, 0.01, 100);

		System.out.println("Part A");
		System.out.println("First Root, Approx: ");
		bisection(0 , 1, true, 0.01, 100);
		newtonRaphson(0 , true, 0.01, 100);
		secant(0 , 1, true, 0.01, 100);
		falsePosition(0 , 1 , true, 0.01, 100);
		modifiedSecant(1 , 0.01 , true, 0.01, 100);

	}
	
	
}
