package org.android.go.sopt.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.LayoutTitleBinding

class TitleAdapter(context: Context): RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    //private var titleList: List<TitleData> = emptyList()

    class TitleViewHolder(private val binding: LayoutTitleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val binding = LayoutTitleBinding.inflate(inflater,parent,false)
        return TitleViewHolder(binding)
    }

    override fun getItemCount() : Int = 1

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {

    }
}