package org.android.go.sopt.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.android.go.sopt.data.datasource.FriendDataSource
import org.android.go.sopt.data.repository.FriendRepositoryImpl
import org.android.go.sopt.presentation.home.viewmodel.FriendViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java))
            return FriendViewModel(FriendRepositoryImpl(FriendDataSource())) as T
        else if (modelClass.isAssignableFrom(FriendViewModel::class.java)){
            return FriendViewModel(FriendRepositoryImpl(FriendDataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
