package com.yandexpracticum.example_rxjava.app

import android.app.Application
import android.util.Log
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Вызываем функцию для настройки глобального
        // обработчика ошибок
        initRxGlobalErrorHandler()
    }

    private fun initRxGlobalErrorHandler() {
        RxJavaPlugins.setErrorHandler { e: Throwable ->
            var cause = e
            if (e is UndeliverableException) {
                cause = e.cause!!
            }
            if (cause is SocketException || cause is IOException) {
                // Всё в порядке, это уже нерелевантная проблема сети или API, который бросил исключение при отмене подписки
                return@setErrorHandler
            }
            if (cause is InterruptedException) {
                // Всё в порядке, некоторый блокирующий код был прерван вызовом функции dispose
                return@setErrorHandler
            }
            if (cause is NullPointerException || cause is IllegalArgumentException) {
                // А вот это похоже на баг в приложении!
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), cause)
                return@setErrorHandler
            }

            // Добавили обработку своего исключения,
            // чтобы оно не крашило приложение
            if (cause is Exception && cause.message == "error 2") {
                // Пропускаем ошибку
                return@setErrorHandler
            }

            if (e is IllegalStateException) {
                // А здесь уже баг в RxJava или каком-то кастомном операторе
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), cause)
                return@setErrorHandler
            }
            Log.w("Undeliverable exception received, not sure what to do", cause)
        }
    }

}