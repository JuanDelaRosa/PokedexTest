package com.example.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokedex.databinding.RxtestActivityBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RxtestActivity : AppCompatActivity() {
    private val TAG = "RxtestActivity"
    private val binding: RxtestActivityBinding by lazy { RxtestActivityBinding.inflate(layoutInflater) }
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //createOperator()
        //justOperator()
        arrayOperator()
        //fromIterableOperator()
        //rangeOperator()
        //mapTransformation()
        //filter()
    }

    private fun filter() {
        val ob = Observable.just(10,"kotlin",30,"rxJava",50,60,"android",80,90,100)
            .filter {
                if(it is String){
                    it.length>7
                }else false

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = ob.subscribe({
            Timber.d(TAG, "filter recibio $it")
        },{
            Timber.e(TAG, "filter onError: ${it.message}")
        },{
            Timber.d(TAG, "filter onComplete")
        })
        compositeDisposable.add(disposable)
    }

    private fun mapTransformation() {
        val ob = Observable.just("1","2","3","ds4","5","6","7","8","9","10")
            .map { val temp = it.toIntOrNull()
                temp ?: -1
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = ob.subscribe({
            Timber.d(TAG, "map recibio $it")
        },{
            Timber.e(TAG, "map onError: ${it.message}")
        },{
            Timber.d(TAG, "map onComplete")
        })
        compositeDisposable.add(disposable)
    }

    private fun rangeOperator() {
        val ob = Observable.range(0,10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = ob.subscribe({
            Timber.d(TAG, "range recibio $it")
        },{
            Timber.e(TAG, "range onError: ${it.message}")
        },{
            Timber.d(TAG, "range onComplete")
        })
        compositeDisposable.add(disposable)
    }

    private fun fromIterableOperator() {
        val list = mutableListOf(1,2,3,4,5,6,7)
        val ob = Observable.fromIterable(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = ob.subscribe({
            Timber.d(TAG, "fromIterable recibio $it")
        },{
            Timber.e(TAG, "fromIterable onError: ${it.message}")
        },{
            Timber.d(TAG, "fromIterable onComplete")
        })
        compositeDisposable.add(disposable)
    }

    private fun arrayOperator() {
        val ob = Observable.fromArray(mutableListOf(1,2,3,4,5,6,7))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = ob.subscribe({
            it.forEach { element ->
                Timber.d(TAG, "fromArray recibio $element")
            }
        },{
            Timber.e(TAG, "fromArray onError: ${it.message}")
        },{
            Timber.d(TAG, "fromArray onComplete")
        })
        compositeDisposable.add(disposable)
    }

    private fun justOperator() {
        val ob = Observable.just(1,2,3,4,5,6,7,8,9,10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        val disposable = ob.subscribe({
            Timber.d(TAG, "Just recibio $it")
        },{
            Timber.e(TAG, "Just onError: ${it.message}")
        },{
            Timber.d(TAG, "Just onComplete")
        })
        compositeDisposable.add(disposable)
    }

    private fun createOperator() {
        val ob = Observable.create<String> { e ->
            e.onNext("Enviando")
            e.onNext("Datos")
            e.onNext("Desde")
            e.onNext("Observable")
            e.onComplete()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


        val disposable = ob.subscribe({
            Timber.d(TAG, "onNext Se recibio ${it}")
        }, {
            Timber.e(TAG, "onError: ${it.message}")
        }, {
            Timber.d(TAG, "onComplete")
        })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}