package org.asghari.guardiannews.presentation.adapters;

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.asghari.guardiannews.data.models.Result
import org.asghari.guardiannews.databinding.NewsListItemBinding
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log

@Singleton
class NewsListAdapter @Inject constructor():
        PagingDataAdapter<Result,NewsListAdapter.NewsListViewHolder>(diffCallback){
    private var itemClickListener:ItemClickListener? = null
    interface ItemClickListener{
        fun onClick(newsId:String)
    }
    fun setOnClickListener(onClickListener: ItemClickListener)
    {
        itemClickListener = onClickListener
    }
        override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
                val item = getItem(position)
                 item?.let {
                        Log.d(">>>>>>>>>>>bbbb",it.toString());
                         Glide.with(holder.itemView).load(item.fields.thumbnail).into( holder.binding.newsThumb)
                         holder.binding.newsTitle.text = item.webTitle
                         holder.binding.newsPublishDate.text = item.webPublicationDate
                     holder.itemView.setOnClickListener(View.OnClickListener {
                         itemClickListener!!.onClick(item.id)
                     })
                 }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
                Log.d(">>>>>>>>>>>bbbb2","CCCCCreated");
                var holder = NewsListViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.context)
                        ,parent,false))

            return holder
        }




        inner class NewsListViewHolder(val binding:NewsListItemBinding):
                RecyclerView.ViewHolder(binding.root){


        }


        object diffCallback:DiffUtil.ItemCallback<Result>(){
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                     return oldItem.id==newItem.id
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                        return oldItem==newItem
                }

        }
}
