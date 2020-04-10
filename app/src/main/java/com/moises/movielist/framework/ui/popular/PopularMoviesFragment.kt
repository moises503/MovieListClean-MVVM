package com.moises.movielist.framework.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.moises.movielist.R
import com.moises.movielist.framework.presentation.popular.PopularMoviesViewModel

class PopularMoviesFragment : Fragment() {

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        popularMoviesViewModel =
                ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_popular_movies, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        popularMoviesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
