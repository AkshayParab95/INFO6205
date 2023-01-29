package edu.neu.coe.info6205.threesum;

import java.util.function.Supplier;

import edu.neu.coe.info6205.util.Stopwatch;

public class TSBenchmark {
	public static void benchmarkTSCubic(int input, int runs) {
		long millis;
		try (Stopwatch stopwatch = new Stopwatch()) {
		for(int i=0; i < runs; i++) {
			final Supplier<int[]> supplier = new Source(input, input).intsSupplier(10);
			ThreeSumCubic obj = new ThreeSumCubic(supplier.get());
				obj.getTriples();
			}        
		millis = stopwatch.lap();
		}
	    System.out.println("Average time taken by ThreeSumCubic for input size = "+input+" and total iterations = "+runs+": "+millis*1.0/runs+ " milliseconds");
	}
	
	public static void benchmarkTSQuadrithmic(int input, int runs) {
		long millis;
		try (Stopwatch stopwatch = new Stopwatch()) {
		for(int i=0; i < runs; i++) {
			final Supplier<int[]> supplier = new Source(input, input).intsSupplier(10);
			ThreeSumQuadrithmic obj = new ThreeSumQuadrithmic(supplier.get());
				obj.getTriples();
			}        
		millis = stopwatch.lap();
		}
	    System.out.println("Average time taken by ThreeSumQuadrithmic for input size = "+input + " and total iterations = "+runs+": "+millis*1.0/runs+" milliseconds");
	}
	
	public static void benchmarkTSQuadratic(int input, int runs) {
		long millis;
		try (Stopwatch stopwatch = new Stopwatch()) {
		for(int i=0; i < runs; i++) {
			final Supplier<int[]> supplier = new Source(input, input).intsSupplier(10);
			ThreeSumQuadratic obj = new ThreeSumQuadratic(supplier.get());
				obj.getTriples();
			}        
		millis = stopwatch.lap();
		}
	    System.out.println("Average time taken by ThreeSumQuadratic for input size = "+input + " and total iterations = "+runs+": "+millis*1.0/runs+" milliseconds");
	}

	public static void benchmarkTSQuadraticWithCalipers(int input, int runs) {
		long millis;
		try (Stopwatch stopwatch = new Stopwatch()) {
		for(int i=0; i < runs; i++) {
			final Supplier<int[]> supplier = new Source(input, input).intsSupplier(10);
			ThreeSumQuadraticWithCalipers obj = new ThreeSumQuadraticWithCalipers(supplier.get());
				obj.getTriples();
			}        
		millis = stopwatch.lap();
		}
	    System.out.println("Average time taken by ThreeSumQuadraticWithCalipers for input size = "+input + " and total iterations = "+runs+": "+millis*1.0/runs+" milliseconds");
	}
	
	public static void main(String[] args) {
		System.out.println("Input Size: 250");
		
		benchmarkTSCubic(250, 100);
		benchmarkTSQuadrithmic(250, 100);
		benchmarkTSQuadratic(250, 100);
		benchmarkTSQuadraticWithCalipers(250, 100);
		
		System.out.println();
		
		System.out.println("Input Size: 500");
		benchmarkTSCubic(500, 50);
		benchmarkTSQuadrithmic(500, 50);
		benchmarkTSQuadratic(500, 50);
		benchmarkTSQuadraticWithCalipers(500, 50);
		
		
		System.out.println();
		
		System.out.println("Input Size: 1000");
		benchmarkTSCubic(1000, 20);
		benchmarkTSQuadrithmic(1000, 20);
		benchmarkTSQuadratic(1000, 20);
		benchmarkTSQuadraticWithCalipers(1000, 20);
		
		System.out.println();
		
		System.out.println("Input Size: 2000");
		benchmarkTSCubic(2000, 10);
		benchmarkTSQuadrithmic(2000, 10);
		benchmarkTSQuadratic(2000, 10);
		benchmarkTSQuadraticWithCalipers(2000, 10);
		
		System.out.println();
		
		System.out.println("Input Size: 4000");
		benchmarkTSCubic(4000, 5);
		benchmarkTSQuadrithmic(4000, 5);
		benchmarkTSQuadratic(4000, 5);
		benchmarkTSQuadraticWithCalipers(4000, 5);
		
		System.out.println();
		
		System.out.println("Input Size: 8000");
//		benchmarkTSCubic(8000, 50);
		benchmarkTSQuadrithmic(8000, 3);
		benchmarkTSQuadratic(8000, 3);
		benchmarkTSQuadraticWithCalipers(8000, 3);
		
		
		System.out.println();
		
		System.out.println("Input Size: 16000");
//		benchmarkTSCubic(16000, 50);
		benchmarkTSQuadrithmic(16000, 2);
		benchmarkTSQuadratic(16000, 2);
		benchmarkTSQuadraticWithCalipers(16000, 2);
		
	}
}

/* Output

Input Size: 250
Average time taken by ThreeSumCubic for input size = 250 and total iterations = 100: 4.69 milliseconds
Average time taken by ThreeSumQuadrithmic for input size = 250 and total iterations = 100: 0.86 milliseconds
Average time taken by ThreeSumQuadratic for input size = 250 and total iterations = 100: 0.66 milliseconds
Average time taken by ThreeSumQuadraticWithCalipers for input size = 250 and total iterations = 100: 0.55 milliseconds

Input Size: 500
Average time taken by ThreeSumCubic for input size = 500 and total iterations = 50: 25.14 milliseconds
Average time taken by ThreeSumQuadrithmic for input size = 500 and total iterations = 50: 2.58 milliseconds
Average time taken by ThreeSumQuadratic for input size = 500 and total iterations = 50: 1.26 milliseconds
Average time taken by ThreeSumQuadraticWithCalipers for input size = 500 and total iterations = 50: 0.92 milliseconds

Input Size: 1000
Average time taken by ThreeSumCubic for input size = 1000 and total iterations = 20: 199.5 milliseconds
Average time taken by ThreeSumQuadrithmic for input size = 1000 and total iterations = 20: 14.4 milliseconds
Average time taken by ThreeSumQuadratic for input size = 1000 and total iterations = 20: 4.55 milliseconds
Average time taken by ThreeSumQuadraticWithCalipers for input size = 1000 and total iterations = 20: 3.2 milliseconds

Input Size: 2000
Average time taken by ThreeSumCubic for input size = 2000 and total iterations = 10: 1590.9 milliseconds
Average time taken by ThreeSumQuadrithmic for input size = 2000 and total iterations = 10: 59.9 milliseconds
Average time taken by ThreeSumQuadratic for input size = 2000 and total iterations = 10: 14.3 milliseconds
Average time taken by ThreeSumQuadraticWithCalipers for input size = 2000 and total iterations = 10: 9.6 milliseconds

Input Size: 4000
Average time taken by ThreeSumCubic for input size = 4000 and total iterations = 5: 13400.4 milliseconds
Average time taken by ThreeSumQuadrithmic for input size = 4000 and total iterations = 5: 319.2 milliseconds
Average time taken by ThreeSumQuadratic for input size = 4000 and total iterations = 5: 79.2 milliseconds
Average time taken by ThreeSumQuadraticWithCalipers for input size = 4000 and total iterations = 5: 81.6 milliseconds

Input Size: 8000
Average time taken by ThreeSumQuadrithmic for input size = 8000 and total iterations = 3: 1491.0 milliseconds
Average time taken by ThreeSumQuadratic for input size = 8000 and total iterations = 3: 419.0 milliseconds
Average time taken by ThreeSumQuadraticWithCalipers for input size = 8000 and total iterations = 3: 366.6666666666667 milliseconds

Input Size: 16000
Average time taken by ThreeSumQuadrithmic for input size = 16000 and total iterations = 2: 6248.0 milliseconds
Average time taken by ThreeSumQuadratic for input size = 16000 and total iterations = 2: 2251.5 milliseconds
Average time taken by ThreeSumQuadraticWithCalipers for input size = 16000 and total iterations = 2: 1476.5 milliseconds
 */
