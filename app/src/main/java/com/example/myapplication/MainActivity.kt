package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickListButton(v: View) = runBlocking {
        val list = getList()
        list.forEach {
            Log.d("LIST", "PRINT $it")
        }
    }

    fun onClickChannelButton(v: View) = runBlocking {
        val channel = getChannelList()
        for (result in channel) {
            Log.d("CHANNEL", "PRINT $result")
        }
    }

    fun onClickFlowButton(v: View) = runBlocking {
        val flow = getFlowList()
        flow.collect { result ->
            Log.d("FLOW", "PRINT $result")
        }
    }

    fun getList(): List<String> {
        val list = ArrayList<String>()
        Log.d("LIST", "ADD A")
        list.add("A")
        Log.d("LIST", "ADD B")
        list.add("B")
        Log.d("LIST", "ADD C")
        list.add("C")
        return list
    }

    fun CoroutineScope.getChannelList(): ReceiveChannel<String> = produce {
        Log.d("CHANNEL", "SEND A")
        send("A")
        Log.d("CHANNEL", "SEND B")
        send("B")
        Log.d("CHANNEL", "SEND C")
        send("C")
    }

    fun getFlowList(): Flow<String> = flow {
        Log.d("FLOW", "EMIT A")
        emit("A")
        Log.d("FLOW", "EMIT B")
        emit("B")
        Log.d("FLOW", "EMIT C")
        emit("C")
    }
}
