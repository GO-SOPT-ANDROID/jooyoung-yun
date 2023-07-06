package org.android.go.sopt.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.android.go.sopt.data.datasource.FriendDataSource
import org.android.go.sopt.data.datasource.LogInDataSource
import org.android.go.sopt.data.datasource.SignUpDataSource
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.data.repository.FriendRepositoryImpl
import org.android.go.sopt.data.repository.LogInRepositoryImpl
import org.android.go.sopt.data.repository.SignUpRepositoryImpl
import org.android.go.sopt.presentation.home.viewmodel.FriendViewModel
import org.android.go.sopt.presentation.login.viewmodel.LogInViewModel
import org.android.go.sopt.presentation.signup.viewmodel.SignUpViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java))
            return FriendViewModel(FriendRepositoryImpl(FriendDataSource())) as T
        else if (modelClass.isAssignableFrom(LogInViewModel::class.java)){
            return LogInViewModel(GSDataStore(context),LogInRepositoryImpl(LogInDataSource())) as T
        }
        else if (modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(GSDataStore(context),SignUpRepositoryImpl(SignUpDataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
