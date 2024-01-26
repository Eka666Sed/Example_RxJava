package com.yandexpracticum.example_rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.Optional
import java.util.concurrent.TimeUnit
import kotlin.jvm.optionals.getOrNull

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textview)

        Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value ->
                    textView.text = "Interval: $value"
                }
            )

        //Rx-цепочка

//        Observable
//            .just(
//                1,2,3,4,5,6
//            )
//            .map { value -> value * value }
//            .filter { value ->
//                if (value < 5) {
//                    true
//                } else {
//                    throw RuntimeException("Experiment with RxJava")
//                    }
//                }
//            .subscribe(
//                { value -> Log.d("RxJava", "New value: $value")},
//                { error -> Log.e ("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete!")}
//            )

//        Observable.interval(1, TimeUnit.SECONDS)
//            .subscribe(
//                { number -> Log.d("RxJava", "onNext: $number") },
//                { error -> Log.e("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete") }
//            )

//        val intervalObservable = Observable.interval(1, TimeUnit.SECONDS)
//        val disposable = intervalObservable
//            .subscribe(
//                { number -> Log.d("RxJava", "onNext: $number") },
//                { error -> Log.e("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete") }
//            )
//
//        val handler = Handler(Looper.getMainLooper())
//
//        handler.postDelayed(
//            { disposable.dispose() },
//            5000L
//        )
//
//        handler.postDelayed(
//            {
//                val secondDisposable = intervalObservable
//                    .subscribe(
//                        { number -> Log.d("RxJava", "Second - onNext: $number") },
//                        { error -> Log.e("RxJava", "Second - onError", error) },
//                        { Log.d("RxJava", "Second - onComplete") }
//                    )
//                     //secondDisposable.dispose()
//            },
//            10_000L
//        )

//        Observable
//            .just(
//                1, 2, 3, 4, 5, 6
//            )
//            .doOnNext { Log.d("RxJava", "doOnNext 1 [after just] : $it") }
//            .doOnComplete { Log.d("RxJava", "doOnComplete 1 [after just] ") }
//            .map { value -> value * value }
//            .doOnNext { Log.d("RxJava", "doOnNext 2 [after map] : $it") }
//            .doOnComplete { Log.d("RxJava", "doOnComplete 2 [after map] ") }
//            .filter { value ->
//                value > 10
//            }
//            .doOnNext { Log.d("RxJava", "doOnNext 3 [after filter]: $it") }
//            .doOnComplete { Log.d("RxJava", "doOnComplete 3 [after filter]") }
//            .subscribe(
//                { value -> Log.d("RxJava", "New value: $value") },
//                { error -> Log.e("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete!") }
//            )

//        Observable
//            .just(
//                1, 2, 3, 4, 5, 6
//            )
//            .doOnNext { Log.d("RxJava", "doOnNext 1 [after just]: $it") }
//            .doOnError { Log.e("RxJava", "doOnError 1 [after just]", it) }
//            .map { value ->
//                val result = value * value
//                if (result > 10) {
//                    throw RuntimeException("Test doOnError!")
//                } else {
//                    result
//                }
//            }
//            .doOnNext { Log.d("RxJava", "doOnNext 2 [after map]: $it") }
//            .doOnError { Log.e("RxJava", "doOnError 2 [after map]", it) }
//            .doOnNext { Log.d("RxJava", "doOnNext 3 [after map & doOnError]: $it") }
//            .filter { value ->
//                value > 10
//            }
//            .doOnError { Log.e("RxJava", "doOnError 3 [after filter]", it) }
//            .subscribe(
//                { value -> Log.d("RxJava", "New value: $value") },
//                { error -> Log.e("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete!") }
//            )

//        Observable
//            .just(
//                1, 2, 3, 4, 5, 6
//            )
//            .map { value ->
//                if (value < 4) {
//                    Optional.of (value * value)
//                } else {
//                    Optional.empty()
//                }
//            }
//            .filter { value ->
//                value.getOrNull() == null || value.get() > 2
//            }
//            .subscribe(
//                { value -> Log.d("RxJava", "New value: $value") },
//                { error -> Log.e("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete!") }
//            )

//        Observable
//            .just(
//                1, 2, 3, 4, 5, 6
//            )
//            .map { value ->
//                val mappedValue = if (value < 4) {
//                    value * value
//                } else {
//                    null
//                }
//                Optional.ofNullable(mappedValue)
//            }
//            .filter { value ->
//                value.getOrNull() == null || value.get() > 2
//            }
//            .subscribe(
//                { value -> Log.d("RxJava", "New value: ${value.getOrNull()}") },
//                { error -> Log.e("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete!") }
//            )

//        val stringsObservable = Observable.just("1", "2", "three", "4", "five")
//        val numbersObservable = stringsObservable
//            .map { string ->
//                try {
//                    string.toInt()
//                } catch (e: NumberFormatException) {
//                    null
//                }
//            }
//        val evenNumbersObservable = numbersObservable
//            .filter { number ->
//                number != null && number % 2 == 0
//            }
//        evenNumbersObservable
//            .subscribe(
//                { number -> Log.d("RxJava", "onNext: $number") },
//                { error -> Log.e("RxJava", "onError", error) },
//                { Log.d("RxJava", "onComplete") }
//            )
    }
}