package edu.neu.coe.info6205.sort.par;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        processArgs(args);
        Random random = new Random();
        int inputArrLength[] = {
        		(int)Math.pow(2, 20), 
        		(int)Math.pow(2, 21), 
        		(int)Math.pow(2, 22),
        		(int)Math.pow(2, 23),
        		(int)Math.pow(2, 24),
        	};
        int threads[] = {
        		(int)Math.pow(2, 2), 
        		(int)Math.pow(2, 3), 
        		(int)Math.pow(2, 4), 
        		(int)Math.pow(2, 5)
        	};
        for(int k = 0; k < inputArrLength.length; k++) {
        	int currentLength = inputArrLength[k];
        	int array[] = new int[currentLength];
        	int cutOffArr[] = {
        			currentLength + 1,
        			(currentLength/(int)Math.pow(2, 1) + 1),
        			(currentLength/(int)Math.pow(2, 2) + 1),
        			(currentLength/(int)Math.pow(2, 3) + 1),
        			(currentLength/(int)Math.pow(2, 4) + 1),
        			(currentLength/(int)Math.pow(2, 5) + 1),
        			(currentLength/(int)Math.pow(2, 6) + 1),
        			(currentLength/(int)Math.pow(2, 7) + 1),
        			(currentLength/(int)Math.pow(2, 8) + 1),
        			(currentLength/(int)Math.pow(2, 9) + 1),
        			(currentLength/(int)Math.pow(2, 10) + 1),
        			(currentLength/(int)Math.pow(2, 11) + 1),
        			(currentLength/(int)Math.pow(2, 12) + 1),
        	};
        	for(int cof = 0; cof < cutOffArr.length; cof++) {
        		int currentCutOff = cutOffArr[cof];
        		ParSort.cutoff = currentCutOff;
        		for(int th = 0; th < threads.length; th++) {
        			int currentThread = threads[th];
        			ForkJoinPool customForkPool = new ForkJoinPool(currentThread); 
        			long time;
                    long startTime = System.currentTimeMillis();
                    
                    for (int t = 0; t < 10; t++) {
                        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                        ParSort.sort(array, 0, array.length, customForkPool);
                    }
                    long endTime = System.currentTimeMillis();
                    time = (endTime - startTime);
                    
                    System.out.println("Array Length: "+currentLength+" Thread Count: "+currentThread+" Cutoff At: "+currentCutOff+" Duration(ms):"+ (time/10));
        		}
        	}
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();
}
/* Output
Array Length: 1048576 Thread Count: 4 Cutoff At: 1048577 Duration(ms):73
Array Length: 1048576 Thread Count: 4 Cutoff At: 524289 Duration(ms):82
Array Length: 1048576 Thread Count: 4 Cutoff At: 262145 Duration(ms):43
Array Length: 1048576 Thread Count: 4 Cutoff At: 131073 Duration(ms):47
Array Length: 1048576 Thread Count: 4 Cutoff At: 65537 Duration(ms):39
Array Length: 1048576 Thread Count: 4 Cutoff At: 32769 Duration(ms):38
Array Length: 1048576 Thread Count: 4 Cutoff At: 16385 Duration(ms):37
Array Length: 1048576 Thread Count: 4 Cutoff At: 8193 Duration(ms):47
Array Length: 1048576 Thread Count: 4 Cutoff At: 4097 Duration(ms):34
Array Length: 1048576 Thread Count: 4 Cutoff At: 2049 Duration(ms):35
Array Length: 1048576 Thread Count: 4 Cutoff At: 1025 Duration(ms):47
Array Length: 1048576 Thread Count: 4 Cutoff At: 513 Duration(ms):91
Array Length: 1048576 Thread Count: 4 Cutoff At: 257 Duration(ms):325

Array Length: 1048576 Thread Count: 8 Cutoff At: 1048577 Duration(ms):72
Array Length: 1048576 Thread Count: 8 Cutoff At: 524289 Duration(ms):42
Array Length: 1048576 Thread Count: 8 Cutoff At: 262145 Duration(ms):29
Array Length: 1048576 Thread Count: 8 Cutoff At: 131073 Duration(ms):38
Array Length: 1048576 Thread Count: 8 Cutoff At: 65537 Duration(ms):41
Array Length: 1048576 Thread Count: 8 Cutoff At: 32769 Duration(ms):37
Array Length: 1048576 Thread Count: 8 Cutoff At: 16385 Duration(ms):32
Array Length: 1048576 Thread Count: 8 Cutoff At: 8193 Duration(ms):31
Array Length: 1048576 Thread Count: 8 Cutoff At: 4097 Duration(ms):31
Array Length: 1048576 Thread Count: 8 Cutoff At: 2049 Duration(ms):33
Array Length: 1048576 Thread Count: 8 Cutoff At: 1025 Duration(ms):47
Array Length: 1048576 Thread Count: 8 Cutoff At: 513 Duration(ms):92
Array Length: 1048576 Thread Count: 8 Cutoff At: 257 Duration(ms):256

Array Length: 1048576 Thread Count: 16 Cutoff At: 1048577 Duration(ms):69
Array Length: 1048576 Thread Count: 16 Cutoff At: 524289 Duration(ms):42
Array Length: 1048576 Thread Count: 16 Cutoff At: 262145 Duration(ms):29
Array Length: 1048576 Thread Count: 16 Cutoff At: 131073 Duration(ms):28
Array Length: 1048576 Thread Count: 16 Cutoff At: 65537 Duration(ms):32
Array Length: 1048576 Thread Count: 16 Cutoff At: 32769 Duration(ms):33
Array Length: 1048576 Thread Count: 16 Cutoff At: 16385 Duration(ms):31
Array Length: 1048576 Thread Count: 16 Cutoff At: 8193 Duration(ms):30
Array Length: 1048576 Thread Count: 16 Cutoff At: 4097 Duration(ms):32
Array Length: 1048576 Thread Count: 16 Cutoff At: 2049 Duration(ms):33
Array Length: 1048576 Thread Count: 16 Cutoff At: 1025 Duration(ms):44
Array Length: 1048576 Thread Count: 16 Cutoff At: 513 Duration(ms):93
Array Length: 1048576 Thread Count: 16 Cutoff At: 257 Duration(ms):274

Array Length: 1048576 Thread Count: 32 Cutoff At: 1048577 Duration(ms):71
Array Length: 1048576 Thread Count: 32 Cutoff At: 524289 Duration(ms):42
Array Length: 1048576 Thread Count: 32 Cutoff At: 262145 Duration(ms):29
Array Length: 1048576 Thread Count: 32 Cutoff At: 131073 Duration(ms):27
Array Length: 1048576 Thread Count: 32 Cutoff At: 65537 Duration(ms):27
Array Length: 1048576 Thread Count: 32 Cutoff At: 32769 Duration(ms):27
Array Length: 1048576 Thread Count: 32 Cutoff At: 16385 Duration(ms):32
Array Length: 1048576 Thread Count: 32 Cutoff At: 8193 Duration(ms):29
Array Length: 1048576 Thread Count: 32 Cutoff At: 4097 Duration(ms):31
Array Length: 1048576 Thread Count: 32 Cutoff At: 2049 Duration(ms):32
Array Length: 1048576 Thread Count: 32 Cutoff At: 1025 Duration(ms):45
Array Length: 1048576 Thread Count: 32 Cutoff At: 513 Duration(ms):101
Array Length: 1048576 Thread Count: 32 Cutoff At: 257 Duration(ms):272

Array Length: 2097152 Thread Count: 4 Cutoff At: 2097153 Duration(ms):140
Array Length: 2097152 Thread Count: 4 Cutoff At: 1048577 Duration(ms):86
Array Length: 2097152 Thread Count: 4 Cutoff At: 524289 Duration(ms):89
Array Length: 2097152 Thread Count: 4 Cutoff At: 262145 Duration(ms):98
Array Length: 2097152 Thread Count: 4 Cutoff At: 131073 Duration(ms):84
Array Length: 2097152 Thread Count: 4 Cutoff At: 65537 Duration(ms):68
Array Length: 2097152 Thread Count: 4 Cutoff At: 32769 Duration(ms):72
Array Length: 2097152 Thread Count: 4 Cutoff At: 16385 Duration(ms):63
Array Length: 2097152 Thread Count: 4 Cutoff At: 8193 Duration(ms):62
Array Length: 2097152 Thread Count: 4 Cutoff At: 4097 Duration(ms):66
Array Length: 2097152 Thread Count: 4 Cutoff At: 2049 Duration(ms):72
Array Length: 2097152 Thread Count: 4 Cutoff At: 1025 Duration(ms):115
Array Length: 2097152 Thread Count: 4 Cutoff At: 513 Duration(ms):292

Array Length: 2097152 Thread Count: 8 Cutoff At: 2097153 Duration(ms):144
Array Length: 2097152 Thread Count: 8 Cutoff At: 1048577 Duration(ms):88
Array Length: 2097152 Thread Count: 8 Cutoff At: 524289 Duration(ms):59
Array Length: 2097152 Thread Count: 8 Cutoff At: 262145 Duration(ms):83
Array Length: 2097152 Thread Count: 8 Cutoff At: 131073 Duration(ms):85
Array Length: 2097152 Thread Count: 8 Cutoff At: 65537 Duration(ms):77
Array Length: 2097152 Thread Count: 8 Cutoff At: 32769 Duration(ms):65
Array Length: 2097152 Thread Count: 8 Cutoff At: 16385 Duration(ms):63
Array Length: 2097152 Thread Count: 8 Cutoff At: 8193 Duration(ms):62
Array Length: 2097152 Thread Count: 8 Cutoff At: 4097 Duration(ms):64
Array Length: 2097152 Thread Count: 8 Cutoff At: 2049 Duration(ms):74
Array Length: 2097152 Thread Count: 8 Cutoff At: 1025 Duration(ms):119
Array Length: 2097152 Thread Count: 8 Cutoff At: 513 Duration(ms):297

Array Length: 2097152 Thread Count: 16 Cutoff At: 2097153 Duration(ms):135
Array Length: 2097152 Thread Count: 16 Cutoff At: 1048577 Duration(ms):86
Array Length: 2097152 Thread Count: 16 Cutoff At: 524289 Duration(ms):59
Array Length: 2097152 Thread Count: 16 Cutoff At: 262145 Duration(ms):55
Array Length: 2097152 Thread Count: 16 Cutoff At: 131073 Duration(ms):67
Array Length: 2097152 Thread Count: 16 Cutoff At: 65537 Duration(ms):74
Array Length: 2097152 Thread Count: 16 Cutoff At: 32769 Duration(ms):70
Array Length: 2097152 Thread Count: 16 Cutoff At: 16385 Duration(ms):85
Array Length: 2097152 Thread Count: 16 Cutoff At: 8193 Duration(ms):67
Array Length: 2097152 Thread Count: 16 Cutoff At: 4097 Duration(ms):81
Array Length: 2097152 Thread Count: 16 Cutoff At: 2049 Duration(ms):80
Array Length: 2097152 Thread Count: 16 Cutoff At: 1025 Duration(ms):119
Array Length: 2097152 Thread Count: 16 Cutoff At: 513 Duration(ms):283

Array Length: 2097152 Thread Count: 32 Cutoff At: 2097153 Duration(ms):143
Array Length: 2097152 Thread Count: 32 Cutoff At: 1048577 Duration(ms):85
Array Length: 2097152 Thread Count: 32 Cutoff At: 524289 Duration(ms):59
Array Length: 2097152 Thread Count: 32 Cutoff At: 262145 Duration(ms):55
Array Length: 2097152 Thread Count: 32 Cutoff At: 131073 Duration(ms):56
Array Length: 2097152 Thread Count: 32 Cutoff At: 65537 Duration(ms):56
Array Length: 2097152 Thread Count: 32 Cutoff At: 32769 Duration(ms):62
Array Length: 2097152 Thread Count: 32 Cutoff At: 16385 Duration(ms):60
Array Length: 2097152 Thread Count: 32 Cutoff At: 8193 Duration(ms):62
Array Length: 2097152 Thread Count: 32 Cutoff At: 4097 Duration(ms):71
Array Length: 2097152 Thread Count: 32 Cutoff At: 2049 Duration(ms):85
Array Length: 2097152 Thread Count: 32 Cutoff At: 1025 Duration(ms):121
Array Length: 2097152 Thread Count: 32 Cutoff At: 513 Duration(ms):284

Array Length: 4194304 Thread Count: 4 Cutoff At: 4194305 Duration(ms):283
Array Length: 4194304 Thread Count: 4 Cutoff At: 2097153 Duration(ms):172
Array Length: 4194304 Thread Count: 4 Cutoff At: 1048577 Duration(ms):177
Array Length: 4194304 Thread Count: 4 Cutoff At: 524289 Duration(ms):205
Array Length: 4194304 Thread Count: 4 Cutoff At: 262145 Duration(ms):183
Array Length: 4194304 Thread Count: 4 Cutoff At: 131073 Duration(ms):174
Array Length: 4194304 Thread Count: 4 Cutoff At: 65537 Duration(ms):134
Array Length: 4194304 Thread Count: 4 Cutoff At: 32769 Duration(ms):127
Array Length: 4194304 Thread Count: 4 Cutoff At: 16385 Duration(ms):130
Array Length: 4194304 Thread Count: 4 Cutoff At: 8193 Duration(ms):140
Array Length: 4194304 Thread Count: 4 Cutoff At: 4097 Duration(ms):132
Array Length: 4194304 Thread Count: 4 Cutoff At: 2049 Duration(ms):168
Array Length: 4194304 Thread Count: 4 Cutoff At: 1025 Duration(ms):367

Array Length: 4194304 Thread Count: 8 Cutoff At: 4194305 Duration(ms):294
Array Length: 4194304 Thread Count: 8 Cutoff At: 2097153 Duration(ms):175
Array Length: 4194304 Thread Count: 8 Cutoff At: 1048577 Duration(ms):122
Array Length: 4194304 Thread Count: 8 Cutoff At: 524289 Duration(ms):159
Array Length: 4194304 Thread Count: 8 Cutoff At: 262145 Duration(ms):157
Array Length: 4194304 Thread Count: 8 Cutoff At: 131073 Duration(ms):175
Array Length: 4194304 Thread Count: 8 Cutoff At: 65537 Duration(ms):128
Array Length: 4194304 Thread Count: 8 Cutoff At: 32769 Duration(ms):127
Array Length: 4194304 Thread Count: 8 Cutoff At: 16385 Duration(ms):125
Array Length: 4194304 Thread Count: 8 Cutoff At: 8193 Duration(ms):122
Array Length: 4194304 Thread Count: 8 Cutoff At: 4097 Duration(ms):138
Array Length: 4194304 Thread Count: 8 Cutoff At: 2049 Duration(ms):185
Array Length: 4194304 Thread Count: 8 Cutoff At: 1025 Duration(ms):338

Array Length: 4194304 Thread Count: 16 Cutoff At: 4194305 Duration(ms):294
Array Length: 4194304 Thread Count: 16 Cutoff At: 2097153 Duration(ms):176
Array Length: 4194304 Thread Count: 16 Cutoff At: 1048577 Duration(ms):123
Array Length: 4194304 Thread Count: 16 Cutoff At: 524289 Duration(ms):108
Array Length: 4194304 Thread Count: 16 Cutoff At: 262145 Duration(ms):138
Array Length: 4194304 Thread Count: 16 Cutoff At: 131073 Duration(ms):160
Array Length: 4194304 Thread Count: 16 Cutoff At: 65537 Duration(ms):151
Array Length: 4194304 Thread Count: 16 Cutoff At: 32769 Duration(ms):135
Array Length: 4194304 Thread Count: 16 Cutoff At: 16385 Duration(ms):126
Array Length: 4194304 Thread Count: 16 Cutoff At: 8193 Duration(ms):130
Array Length: 4194304 Thread Count: 16 Cutoff At: 4097 Duration(ms):156
Array Length: 4194304 Thread Count: 16 Cutoff At: 2049 Duration(ms):190
Array Length: 4194304 Thread Count: 16 Cutoff At: 1025 Duration(ms):431

Array Length: 4194304 Thread Count: 32 Cutoff At: 4194305 Duration(ms):293
Array Length: 4194304 Thread Count: 32 Cutoff At: 2097153 Duration(ms):177
Array Length: 4194304 Thread Count: 32 Cutoff At: 1048577 Duration(ms):137
Array Length: 4194304 Thread Count: 32 Cutoff At: 524289 Duration(ms):112
Array Length: 4194304 Thread Count: 32 Cutoff At: 262145 Duration(ms):123
Array Length: 4194304 Thread Count: 32 Cutoff At: 131073 Duration(ms):121
Array Length: 4194304 Thread Count: 32 Cutoff At: 65537 Duration(ms):132
Array Length: 4194304 Thread Count: 32 Cutoff At: 32769 Duration(ms):134
Array Length: 4194304 Thread Count: 32 Cutoff At: 16385 Duration(ms):141
Array Length: 4194304 Thread Count: 32 Cutoff At: 8193 Duration(ms):136
Array Length: 4194304 Thread Count: 32 Cutoff At: 4097 Duration(ms):137
Array Length: 4194304 Thread Count: 32 Cutoff At: 2049 Duration(ms):172
Array Length: 4194304 Thread Count: 32 Cutoff At: 1025 Duration(ms):360

Array Length: 8388608 Thread Count: 4 Cutoff At: 8388609 Duration(ms):602
Array Length: 8388608 Thread Count: 4 Cutoff At: 4194305 Duration(ms):356
Array Length: 8388608 Thread Count: 4 Cutoff At: 2097153 Duration(ms):382
Array Length: 8388608 Thread Count: 4 Cutoff At: 1048577 Duration(ms):431
Array Length: 8388608 Thread Count: 4 Cutoff At: 524289 Duration(ms):365
Array Length: 8388608 Thread Count: 4 Cutoff At: 262145 Duration(ms):347
Array Length: 8388608 Thread Count: 4 Cutoff At: 131073 Duration(ms):312
Array Length: 8388608 Thread Count: 4 Cutoff At: 65537 Duration(ms):272
Array Length: 8388608 Thread Count: 4 Cutoff At: 32769 Duration(ms):250
Array Length: 8388608 Thread Count: 4 Cutoff At: 16385 Duration(ms):258
Array Length: 8388608 Thread Count: 4 Cutoff At: 8193 Duration(ms):255
Array Length: 8388608 Thread Count: 4 Cutoff At: 4097 Duration(ms):284
Array Length: 8388608 Thread Count: 4 Cutoff At: 2049 Duration(ms):434

Array Length: 8388608 Thread Count: 8 Cutoff At: 8388609 Duration(ms):603
Array Length: 8388608 Thread Count: 8 Cutoff At: 4194305 Duration(ms):359
Array Length: 8388608 Thread Count: 8 Cutoff At: 2097153 Duration(ms):252
Array Length: 8388608 Thread Count: 8 Cutoff At: 1048577 Duration(ms):326
Array Length: 8388608 Thread Count: 8 Cutoff At: 524289 Duration(ms):342
Array Length: 8388608 Thread Count: 8 Cutoff At: 262145 Duration(ms):313
Array Length: 8388608 Thread Count: 8 Cutoff At: 131073 Duration(ms):302
Array Length: 8388608 Thread Count: 8 Cutoff At: 65537 Duration(ms):298
Array Length: 8388608 Thread Count: 8 Cutoff At: 32769 Duration(ms):277
Array Length: 8388608 Thread Count: 8 Cutoff At: 16385 Duration(ms):291
Array Length: 8388608 Thread Count: 8 Cutoff At: 8193 Duration(ms):316
Array Length: 8388608 Thread Count: 8 Cutoff At: 4097 Duration(ms):315
Array Length: 8388608 Thread Count: 8 Cutoff At: 2049 Duration(ms):509

Array Length: 8388608 Thread Count: 16 Cutoff At: 8388609 Duration(ms):591
Array Length: 8388608 Thread Count: 16 Cutoff At: 4194305 Duration(ms):356
Array Length: 8388608 Thread Count: 16 Cutoff At: 2097153 Duration(ms):252
Array Length: 8388608 Thread Count: 16 Cutoff At: 1048577 Duration(ms):226
Array Length: 8388608 Thread Count: 16 Cutoff At: 524289 Duration(ms):281
Array Length: 8388608 Thread Count: 16 Cutoff At: 262145 Duration(ms):292
Array Length: 8388608 Thread Count: 16 Cutoff At: 131073 Duration(ms):277
Array Length: 8388608 Thread Count: 16 Cutoff At: 65537 Duration(ms):275
Array Length: 8388608 Thread Count: 16 Cutoff At: 32769 Duration(ms):276
Array Length: 8388608 Thread Count: 16 Cutoff At: 16385 Duration(ms):272
Array Length: 8388608 Thread Count: 16 Cutoff At: 8193 Duration(ms):297
Array Length: 8388608 Thread Count: 16 Cutoff At: 4097 Duration(ms):338
Array Length: 8388608 Thread Count: 16 Cutoff At: 2049 Duration(ms):473

Array Length: 8388608 Thread Count: 32 Cutoff At: 8388609 Duration(ms):598
Array Length: 8388608 Thread Count: 32 Cutoff At: 4194305 Duration(ms):353
Array Length: 8388608 Thread Count: 32 Cutoff At: 2097153 Duration(ms):249
Array Length: 8388608 Thread Count: 32 Cutoff At: 1048577 Duration(ms):227
Array Length: 8388608 Thread Count: 32 Cutoff At: 524289 Duration(ms):228
Array Length: 8388608 Thread Count: 32 Cutoff At: 262145 Duration(ms):235
Array Length: 8388608 Thread Count: 32 Cutoff At: 131073 Duration(ms):264
Array Length: 8388608 Thread Count: 32 Cutoff At: 65537 Duration(ms):283
Array Length: 8388608 Thread Count: 32 Cutoff At: 32769 Duration(ms):260
Array Length: 8388608 Thread Count: 32 Cutoff At: 16385 Duration(ms):259
Array Length: 8388608 Thread Count: 32 Cutoff At: 8193 Duration(ms):310
Array Length: 8388608 Thread Count: 32 Cutoff At: 4097 Duration(ms):436
Array Length: 8388608 Thread Count: 32 Cutoff At: 2049 Duration(ms):517

Array Length: 16777216 Thread Count: 4 Cutoff At: 16777217 Duration(ms):1199
Array Length: 16777216 Thread Count: 4 Cutoff At: 8388609 Duration(ms):727
Array Length: 16777216 Thread Count: 4 Cutoff At: 4194305 Duration(ms):771
Array Length: 16777216 Thread Count: 4 Cutoff At: 2097153 Duration(ms):882
Array Length: 16777216 Thread Count: 4 Cutoff At: 1048577 Duration(ms):808
Array Length: 16777216 Thread Count: 4 Cutoff At: 524289 Duration(ms):593
Array Length: 16777216 Thread Count: 4 Cutoff At: 262145 Duration(ms):597
Array Length: 16777216 Thread Count: 4 Cutoff At: 131073 Duration(ms):531
Array Length: 16777216 Thread Count: 4 Cutoff At: 65537 Duration(ms):538
Array Length: 16777216 Thread Count: 4 Cutoff At: 32769 Duration(ms):550
Array Length: 16777216 Thread Count: 4 Cutoff At: 16385 Duration(ms):593
Array Length: 16777216 Thread Count: 4 Cutoff At: 8193 Duration(ms):542
Array Length: 16777216 Thread Count: 4 Cutoff At: 4097 Duration(ms):661

Array Length: 16777216 Thread Count: 8 Cutoff At: 16777217 Duration(ms):1259
Array Length: 16777216 Thread Count: 8 Cutoff At: 8388609 Duration(ms):772
Array Length: 16777216 Thread Count: 8 Cutoff At: 4194305 Duration(ms):579
Array Length: 16777216 Thread Count: 8 Cutoff At: 2097153 Duration(ms):702
Array Length: 16777216 Thread Count: 8 Cutoff At: 1048577 Duration(ms):730
Array Length: 16777216 Thread Count: 8 Cutoff At: 524289 Duration(ms):661
Array Length: 16777216 Thread Count: 8 Cutoff At: 262145 Duration(ms):611
Array Length: 16777216 Thread Count: 8 Cutoff At: 131073 Duration(ms):571
Array Length: 16777216 Thread Count: 8 Cutoff At: 65537 Duration(ms):557
Array Length: 16777216 Thread Count: 8 Cutoff At: 32769 Duration(ms):544
Array Length: 16777216 Thread Count: 8 Cutoff At: 16385 Duration(ms):576
Array Length: 16777216 Thread Count: 8 Cutoff At: 8193 Duration(ms):626
Array Length: 16777216 Thread Count: 8 Cutoff At: 4097 Duration(ms):722

Array Length: 16777216 Thread Count: 16 Cutoff At: 16777217 Duration(ms):1188
Array Length: 16777216 Thread Count: 16 Cutoff At: 8388609 Duration(ms):723
Array Length: 16777216 Thread Count: 16 Cutoff At: 4194305 Duration(ms):508
Array Length: 16777216 Thread Count: 16 Cutoff At: 2097153 Duration(ms):441
Array Length: 16777216 Thread Count: 16 Cutoff At: 1048577 Duration(ms):571
Array Length: 16777216 Thread Count: 16 Cutoff At: 524289 Duration(ms):588
Array Length: 16777216 Thread Count: 16 Cutoff At: 262145 Duration(ms):602
Array Length: 16777216 Thread Count: 16 Cutoff At: 131073 Duration(ms):565
Array Length: 16777216 Thread Count: 16 Cutoff At: 65537 Duration(ms):501
Array Length: 16777216 Thread Count: 16 Cutoff At: 32769 Duration(ms):508
Array Length: 16777216 Thread Count: 16 Cutoff At: 16385 Duration(ms):506
Array Length: 16777216 Thread Count: 16 Cutoff At: 8193 Duration(ms):539
Array Length: 16777216 Thread Count: 16 Cutoff At: 4097 Duration(ms):669

Array Length: 16777216 Thread Count: 32 Cutoff At: 16777217 Duration(ms):1223
Array Length: 16777216 Thread Count: 32 Cutoff At: 8388609 Duration(ms):739
Array Length: 16777216 Thread Count: 32 Cutoff At: 4194305 Duration(ms):518
Array Length: 16777216 Thread Count: 32 Cutoff At: 2097153 Duration(ms):457
Array Length: 16777216 Thread Count: 32 Cutoff At: 1048577 Duration(ms):462
Array Length: 16777216 Thread Count: 32 Cutoff At: 524289 Duration(ms):481
Array Length: 16777216 Thread Count: 32 Cutoff At: 262145 Duration(ms):556
Array Length: 16777216 Thread Count: 32 Cutoff At: 131073 Duration(ms):553
Array Length: 16777216 Thread Count: 32 Cutoff At: 65537 Duration(ms):536
Array Length: 16777216 Thread Count: 32 Cutoff At: 32769 Duration(ms):507
Array Length: 16777216 Thread Count: 32 Cutoff At: 16385 Duration(ms):556
Array Length: 16777216 Thread Count: 32 Cutoff At: 8193 Duration(ms):649
Array Length: 16777216 Thread Count: 32 Cutoff At: 4097 Duration(ms):814
 */
 
