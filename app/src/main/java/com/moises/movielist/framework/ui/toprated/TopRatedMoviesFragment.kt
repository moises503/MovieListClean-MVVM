package com.moises.movielist.framework.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moises.movielist.R
import com.moises.movielist.framework.presentation.toprated.TopRatedMoviesViewModel

class TopRatedMoviesFragment : Fragment() {

    private lateinit var topRatedMoviesViewModel: TopRatedMoviesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        topRatedMoviesViewModel = ViewModelProvider(this)
            .get(TopRatedMoviesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_top_rated_movies, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        topRatedMoviesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
