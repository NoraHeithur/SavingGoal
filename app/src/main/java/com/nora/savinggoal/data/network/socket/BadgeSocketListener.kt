package com.nora.savinggoal.data.network.socket

import android.util.Log
import com.nora.savinggoal.data.network.BadgeSocketClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface BadgeSocketListener {
    suspend fun startListen()
    suspend fun disconnect()
    suspend fun emit(event: String, message: String)
    suspend fun listenResponse(event: String): String
}

class BadgeSocketListenerImpl(
    private val client: BadgeSocketClient,
    private val ioDispatcher: CoroutineDispatcher
): BadgeSocketListener {
    override suspend fun startListen() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch {
                    client.connect()
                }
            }
        }
    }

    override suspend fun disconnect() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch {
                    client.disconnect()
                }
            }
        }
    }

    override suspend fun emit(event: String, message: String) {
        withContext(ioDispatcher) {
            coroutineScope {
                launch {
                    client.emit(event = event, data = message)
                }
            }
        }
    }

    override suspend fun listenResponse(event: String): String {
        var responseMessage = ""
        withContext(ioDispatcher) {
            coroutineScope {
                launch {
                    client.on(event = event) {
                        responseMessage = it.toString()
                        Log.d("Badge Socket", "Response $it")
                    }
                }
            }
        }
        return responseMessage
    }
}