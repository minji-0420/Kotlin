package com.example.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSync.setOnClickListener {
            val startTime = System.currentTimeMillis()
            synchronousTask1()
            Log.e("resultTime", "1번 완료")
            synchronousTask2()
            Log.e("resultTime", "2번 완료")
            synchronousTask3()
            Log.e("resultTime", "3번 완료")
            synchronousTask4()
            Log.e("resultTime", "4번 완료")
            synchronousTask5()
            Log.e("resultTime", "5번 완료")
            synchronousTask6()
            Log.e("resultTime", "6번 완료")
            val endTime = System.currentTimeMillis()
            val resultTime = (endTime - startTime) / 1000
            Log.e("resultTime", "$resultTime 초 걸림")
        }

        binding.btnAsync.setOnClickListener {
            GlobalScope.launch {
                val startTime = System.currentTimeMillis()
                synchronousTask1()
                Log.e("resultTime", "1번 완료")
                val deferred = listOf(
                    async { synchronousTask2() },
                    async { synchronousTask3() },
                    async { synchronousTask4() },
                    async { synchronousTask5() },
                )
                Log.e("resultTime", "2345번 완료")
                deferred.awaitAll()
                synchronousTask6()
                Log.e("resultTime", "6번 완료")
                val endTime = System.currentTimeMillis()
                val resultTime = (endTime - startTime) / 1000
                Log.e("resultTime", "$resultTime 초 걸림")
            }

        }
    }
    fun synchronousTask1(): String {
        Thread.sleep(1000)
        return "1번 작업 완료"
    }
    fun synchronousTask2(): String {
        Thread.sleep(2000)
        return "2번 작업 완료"
    }
    fun synchronousTask3(): String {
        Thread.sleep(3000)
        return "3번 작업 완료"
    }
    fun synchronousTask4(): String {
        Thread.sleep(4000)
        return "4번 작업 완료"
    }
    fun synchronousTask5(): String {
        Thread.sleep(5000)
        return "5번 작업 완료"
    }
    fun synchronousTask6(): String {
        Thread.sleep(1000)
        return "6번 작업 완료"
    }
}