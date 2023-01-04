package org.asghari.guardiannews.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.asghari.guardiannews.R
import org.asghari.guardiannews.databinding.HomePageFragmentBinding
import org.asghari.guardiannews.presentation.adapters.NewsListAdapter

import org.asghari.guardiannews.presentation.viewmodels.NewsListViewModel
import javax.inject.Inject


@AndroidEntryPoint
class HomePageFragment:Fragment(),NewsListAdapter.ItemClickListener {

    private val _viewModel : NewsListViewModel by viewModels()
    @Inject
    lateinit var newsListAdapter:NewsListAdapter
    private lateinit var binding: HomePageFragmentBinding
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_page_fragment,container,false)
        newsListAdapter.setOnClickListener(this)
        binding.homeNewsRecycler.apply {
                adapter = newsListAdapter
                layoutManager = GridLayoutManager(requireContext(), 1)
                setHasFixedSize(true)
            }


        binding.appendProgress.isVisible = true

        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("4ff>>>>>>>>>>>>",">>>>>>>>>>>>>>>>>>>>>>>>>>>>ss".toString())
        lifecycleScope.launch {
            //repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                    if(!binding.appendProgress.isVisible) {
                        binding.appendProgress.isVisible = it.source.refresh is LoadState.Loading
                    }


                }
          //  }
        }
        _viewModel.newsList.observe(viewLifecycleOwner,{
            if(it.isLoading)
            {
                Toast.makeText(context,"Data is loading!!",Toast.LENGTH_LONG).show()
            }
            else if(it.error.length>1)
            {
                Toast.makeText(context,it.error,Toast.LENGTH_SHORT).show()
            }
            else {
                lifecycleScope.launch {
                    /*it.pagingData?.let {
                        newsListAdapter.submitData(
                            pagingData = it
                        )
                    }*/
                }
                Log.d("88884>>>>>>>>>>>>", it.data.toString())
            }
        })


    }

    override fun onClick(newsId: String) {
        val bundle:Bundle = Bundle()
        bundle.apply {
            putString("newsId",newsId);
        }

        findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToNewsDetailesPageFragment().actionId,bundle);
    }


}