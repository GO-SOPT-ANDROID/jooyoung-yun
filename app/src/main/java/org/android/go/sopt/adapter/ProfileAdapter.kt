package org.android.go.sopt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.LayoutItemBinding
import org.android.go.sopt.data.Profile

class ProfileAdapter(context: Context): RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var profileList: List<Profile> = emptyList()

    class ProfileViewHolder(
        private val binding: LayoutItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data: Profile){
            binding.imgProfile.setImageDrawable(binding.root.context.getDrawable(data.image ))
            binding.tvProfileAge.text = data.age
            binding.tvProfileName.text = data.name
        }
    }

    /*ViewHolder에 들어갈 View 를 만들어주는 함수. 전채 리사이클러 뷰에 관한 내용*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = LayoutItemBinding.inflate(inflater, parent, false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount() = profileList.size

    /*각각의 ViewHolder 에 데이터를 매칭하는 함수. 리사이클러 뷰의 각각의 아이템*/
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(profileList[position])
    }

    fun setProfileList(profileList: List<Profile>){
        this.profileList = profileList.toList()
        notifyDataSetChanged()
    }

}