package com.nora.savinggoal.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nora.savinggoal.data.network.socket.BadgeSocketListenerImpl
import com.nora.savinggoal.data.repository.BadgeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val badgeSocketListenerImpl: BadgeSocketListenerImpl,
    private val badgeRepositoryImpl: BadgeRepositoryImpl
): ViewModel() {

    val badgeState = MutableStateFlow(0)

    init {
//        startListenBadgeService()
        getBadge()
    }

    private fun startListenBadgeService() {
        viewModelScope.launch {
            badgeSocketListenerImpl.startListen()
            badgeSocketListenerImpl.emit("new-notification", "60")
        }
    }

    fun disconnectBadgeService() {
        viewModelScope.launch {
            badgeSocketListenerImpl.disconnect()
        }
    }

    private fun getBadge() {
        viewModelScope.launch {
//            badgeState.emit(badgeRepositoryImpl.getBadgeCount().count)
            badgeState.emit(70)
        }
    }
}