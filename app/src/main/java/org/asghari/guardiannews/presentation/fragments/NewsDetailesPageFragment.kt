package org.asghari.guardiannews.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.asghari.guardiannews.R

import org.asghari.guardiannews.databinding.NewsDetailesPageFragmentBinding
import org.asghari.guardiannews.presentation.viewmodels.NewsDetailsViewModel

@AndroidEntryPoint
class NewsDetailesPageFragment: Fragment() {

    private val _DetailViewModel:NewsDetailsViewModel by  viewModels()
    private lateinit var binding: NewsDetailesPageFragmentBinding
    private var newsId:String? = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_detailes_page_fragment,container,false)
        newsId = arguments?.getString("newsId")
        newsId?.let {
            _DetailViewModel.getNewsDetails(it, "")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _DetailViewModel.newsDetails.observe(viewLifecycleOwner,{
            if(it.isLoading)
            {
                Toast.makeText(context,"Data is loading!!", Toast.LENGTH_LONG).show()
            }
            else if(it.error.length>1)
            {
                Toast.makeText(context,it.error, Toast.LENGTH_SHORT).show()
            }
            else{
                binding?.apply{
                    Glide.with(root).load(it.data?.fields?.thumbnail)
    .into(newsThumb)
                    newsTitle.text = it.data?.fields?.bodyText
                    newsPublishDate.text = it.data?.webPublicationDate
                }
            }
        })
    }
}