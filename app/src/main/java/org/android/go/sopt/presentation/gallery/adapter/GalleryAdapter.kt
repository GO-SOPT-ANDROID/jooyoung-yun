package org.android.go.sopt.presentation.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPagerBinding

class GalleryAdapter(_itemList: List<Int> = listOf()) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    private var itemList: List<Int> = _itemList

    /*ViewHolder 에 들어갈 view를 만들어 준다*/
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryViewHolder {
        val binding = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    class GalleryViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(src: Int) {
            binding.imgGallery.setImageResource(src)
        }
    }
    /*각각의 ViewHolder 에 데이터를 매칭하는 함수. 즉, 리사이클러 뷰의 각각 아이템에 관한 내용*/
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun setItemList(itemList: List<Int>) {
        this.itemList = itemList
        notifyDataSetChanged()/*데이터를 바꾸고 마지막에 알려줘야함*/
    }
}