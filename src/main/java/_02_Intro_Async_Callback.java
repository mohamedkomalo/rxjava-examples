import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohamed Kamal on 7/25/2016.
 */




public class _02_Intro_Async_Callback {
    static class PrintWhenRecieved implements DataFetcher.OnDataFetchedCallback {
        @Override
        public void onDataFetched(List<String> result) {
            System.out.println("#1 Result: " + result);
        }
    }

    public static void main(String[] args) {
        DataFetcher dataFetcher = new DataFetcher();
        System.out.println("#1 Calling");
        dataFetcher.fetchData(new PrintWhenRecieved());
        System.out.println("#1 Done Calling");
    }

    public static class DataFetcher {
        interface OnDataFetchedCallback {
            void onDataFetched(List<String> result);
        }

        void fetchData(OnDataFetchedCallback callback) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callback.onDataFetched(Arrays.asList("Very", "Long", "Task"));
            });
        }
    }
}