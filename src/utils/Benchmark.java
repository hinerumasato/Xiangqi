package utils;

public class Benchmark {

    private static long start;
    private static long end;
    private static long beforeExecuteMemory;
    private static long afterExecuteMemory;

    public static void startBenchmark() {
        start = System.currentTimeMillis();
        beforeExecuteMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static void endBenchmark() {
        end = System.currentTimeMillis();
        afterExecuteMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static String getBenchmarkResult() {
        String description = "Benchmark: \n";
        description += "Time: " + getDeltaTime() + "ms" + " | ";
        description += "Memory: " + getMemoryUsed() + "bytes";
        return description;
        
    }

    public static long getDeltaTime() {
        return end - start;
    }

    public static long getMemoryUsed() {
        return Math.abs(afterExecuteMemory - beforeExecuteMemory);
    }
}
