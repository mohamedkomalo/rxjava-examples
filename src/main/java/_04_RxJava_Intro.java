import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.Random;

/**
 * Created by Mohamed Kamal on 7/24/2016.
 */
public class _04_RxJava_Intro {

    public static void main(String[] args) throws Exception {
        Observable<Integer> integerObservable = Observable.range(0, 10)
                .filter(integer -> integer % 2 == 0)
                .map(integer -> integer * 2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread());

        integerObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed on thread" + Thread.currentThread().getName());

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

        integerObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed 2 " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error 2");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer + " from 2");
            }
        });

        // wait on the main thread so that the program doesn't exit
        System.in.read();
    }
}
