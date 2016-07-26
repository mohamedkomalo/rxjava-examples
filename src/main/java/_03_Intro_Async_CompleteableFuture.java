import org.eclipse.egit.github.core.User;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorCompletionService;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by Mohamed Kamal on 7/25/2016.
 */
public class _03_Intro_Async_CompleteableFuture {
    public static void main(String[] args) throws IOException {
        DataFetcher dataFetcher = new DataFetcher();

        System.out.println("#1 Calling");

        CompletionStage<List<String>> future1 = dataFetcher.fetchData();

        future1.thenAccept(list -> System.out.println("#1 Result: " + list));

        System.out.println("#1 Done Calling");

        //--------------------------------

        System.out.println("#2 Calling");

        CompletionStage<List<String>> future2 = dataFetcher.fetchData();

        future2.thenAccept(list -> System.out.println("#2 Result: " + list));

        System.out.println("#2 Done Calling");

        // wait in the main thread so that it doesn't exit the program
        System.in.read();
    }

    private static class DataFetcher {
        public CompletionStage<List<String>> fetchData() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Arrays.asList("Long", "Task");
            });
        }
    }

}
