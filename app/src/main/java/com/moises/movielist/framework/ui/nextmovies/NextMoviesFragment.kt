package com.moises.movielist.framework.ui.nextmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.moises.movielist.R
import com.moises.movielist.framework.presentation.nextmovies.NextMoviesViewModel

class NextMoviesFragment : Fragment() {

    private lateinit var nextMoviesViewModel: NextMoviesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        nextMoviesViewModel =
                ViewModelProviders.of(this).get(NextMoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_next_movies, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        nextMoviesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
