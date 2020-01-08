package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

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
        Log.d("LIST", "BEFORE ADD A")
        list.add("A")
        Log.d("LIST", "AFTER ADD A")
        Log.d("LIST", "BEFORE ADD B")
        list.add("B")
        Log.d("LIST", "AFTER ADD B")
        Log.d("LIST", "BEFORE ADD C")
        list.add("C")
        Log.d("LIST", "AFTER ADD C")
        return list
    }

    fun CoroutineScope.getChannelList(): ReceiveChannel<String> = produce {
        Log.d("CHANNEL", "BEFORE SEND A")
        send("A")
        Log.d("CHANNEL", "AFTER SEND A")
        Log.d("CHANNEL", "BEFORE SEND B")
        send("B")
        Log.d("CHANNEL", "AFTER SEND B")
        Log.d("CHANNEL", "BEFORE SEND C")
        send("C")
        Log.d("CHANNEL", "AFTER SEND C")
    }

    fun getFlowList(): Flow<String> = flow {
        Log.d("FLOW", "BEFORE EMIT A")
        emit("A")
        Log.d("FLOW", "AFTER EMIT A")
        Log.d("FLOW", "BEFORE EMIT B")
        emit("B")
        Log.d("FLOW", "AFTER EMIT B")
        Log.d("FLOW", "BEFORE EMIT C")
        emit("C")
        Log.d("FLOW", "AFTER EMIT C")
    }
}
