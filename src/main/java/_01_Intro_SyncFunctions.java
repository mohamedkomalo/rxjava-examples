import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohamed Kamal on 7/25/2016.
 */
public class _01_Intro_SyncFunctions {
    public static void main(String[] args) {
        DataFetcherSync dataFetcher = new DataFetcherSync();

        System.out.println("#1 Calling");
        System.out.println("#1 Result: " + dataFetcher.fetchData());
        System.out.println("#1 Done Calling");

        System.out.println("#2 Calling");
        System.out.println("#2 Result: " + dataFetcher.fetchData());
        System.out.println("#2 Done Calling");
    }

    private static class DataFetcherSync {
        List<String> fetchData() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Arrays.asList("Long", "Task");
        }
    }
}