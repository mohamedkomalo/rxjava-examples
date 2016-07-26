import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mohamed Kamal on 7/24/2016.
 */
public class _05_RxJava_Create_Observables {

    public static void main(String[] args) throws Exception {
        Observable<String> integerObservable = getUsers()
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation());

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
                e.printStackTrace();
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
            subscriber.onNext("Bomba");
            subscriber.onNext("Bomba");
            subscriber.onNext("Bomba");
            subscriber.onCompleted();
        });
    }
}
