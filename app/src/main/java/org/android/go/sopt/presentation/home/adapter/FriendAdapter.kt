package org.android.go.sopt.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import org.android.go.sopt.data.response.FriendData
import org.android.go.sopt.databinding.ItemFriendBinding
import org.android.go.sopt.util.DiffCallback

/*어댑터: item 들을 넣을 리사이클러뷰를 만들어 준다*/
class FriendAdapter() : ListAdapter<FriendData, FriendAdapter.FriendViewHolder>(diffCallback) {

    /*ViewHolder 에 들어갈 View 를 만들어주는 함수*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            ItemFriendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }
    /*각각의 ViewHolder에 데이터를 매칭하는 함수*/
    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class FriendViewHolder(private val binding: ItemFriendBinding) : ViewHolder(binding.root) {
        fun onBind(data: FriendData){
            with(binding){
                tvName.text = data.firstName
                tvEmail.text = data.email
                imgFollower.load(data.avatar)
            }
        }
    }

    companion object {
        private val diffCallback =
            DiffCallback<FriendData>(id = { old, new -> old == new })
    }

}