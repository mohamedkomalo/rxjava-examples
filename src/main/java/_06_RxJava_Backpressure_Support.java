import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mohamed Kamal on 7/24/2016.
 */
public class _06_RxJava_Backpressure_Support {

    public static void main(String[] args) throws Exception {
        Observable<String> integerObservable = getUsers()
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread());

        integerObservable.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                System.out.println("completed on thread" + Thread.currentThread().getName());

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String integer) {
                System.out.println(integer);
            }
        });

        integerObservable.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                System.out.println("completed 2 " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error 2");
            }

            @Override
            public void onNext(String integer) {
                System.out.println(integer + " from 2");
            }
        });

        System.in.read();
    }

    public static Observable<String> getUsers() {
        return Observable.create(subscriber -> {
            List<String> arrays = Arrays.asList("Bomba", "Bomba", "Bomba", "Bomba", "Bomba", "Bomba");
            Iterator<String> iterator = arrays.iterator();

            subscriber.setProducer(n -> {
                for(int i=0; i<n && iterator.hasNext(); i++) {
                    if (iterator.hasNext()) {
                        subscriber.onNext(iterator.next());
                    }
                }

                if(!iterator.hasNext()){
                    subscriber.onCompleted();
                }
            });
        });
    }
}
