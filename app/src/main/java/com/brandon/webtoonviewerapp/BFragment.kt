package com.brandon.webtoonviewerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.brandon.webtoonviewerapp.databinding.FragmentBBinding
import com.brandon.webtoonviewerapp.databinding.FragmentWebviewBinding

class BFragment : Fragment() {
    private lateinit var binding: FragmentBBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
