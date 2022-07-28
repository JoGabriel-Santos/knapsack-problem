package Utilities;

public class Graph {
    private final long runtime;
    private final int numberItems;

    public Graph(long runtime, int numberItems) {
        this.runtime = runtime;
        this.numberItems = numberItems;
    }

    public long getRuntime() {
        return runtime;
    }

    public int getNumberItems() {
        return numberItems;
    }
}
