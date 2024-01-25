package com.nora.savinggoal.data.network

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket

class BadgeSocketClient {
    private val ioOption = IO.Options().apply {
        this.transports = arrayOf(WebSocket.NAME)
        this.reconnection = true
        this.forceNew = true
    }
    private val socket = IO.socket(
        "wss://px-socket-emitter.saleherethailand.com",
        ioOption
    )

    init {
        socket.on(Socket.EVENT_CONNECT) {
            Log.e("Socket", "connect")
        }
        socket.on(Socket.EVENT_DISCONNECT) {
            Log.e("Socket", "disconnect")
        }
        socket.on(Socket.EVENT_CONNECT_ERROR) {
            Log.e("Socket", "error")
        }
    }

    fun connect() {
        socket.connect()
    }

    fun disconnect() {
        socket.disconnect()
    }

    fun emit(event: String, data: Any) {
        socket.emit(event, data)
    }

    fun on(event: String, callback: (Any) -> Unit) {
        socket.on(event, callback)
    }
}