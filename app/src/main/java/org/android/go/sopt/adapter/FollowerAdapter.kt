package org.android.go.sopt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.local.ResponseFollowersDto
import org.android.go.sopt.databinding.ItemFollowersBinding

class FollowerAdapter(_itemList: List<ResponseFollowersDto.RequestFollowersData> = listOf()) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    private var itemList: List<ResponseFollowersDto.RequestFollowersData> = _itemList

    class FollowerViewHolder(private val binding: ItemFollowersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseFollowersDto.RequestFollowersData) {
            with(binding) {
                Glide.with(binding.root)
                    .load(data.avatar)
                    .into(imgFollower)
                tvName.text = data.first_name
                tvEmail.text = data.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setItemList(itemList:List<ResponseFollowersDto.RequestFollowersData>){
        this.itemList = itemList
        notifyDataSetChanged()
    }
}