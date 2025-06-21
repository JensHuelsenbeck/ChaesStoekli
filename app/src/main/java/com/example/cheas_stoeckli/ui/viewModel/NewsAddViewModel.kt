package com.example.cheas_stoeckli.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepo
import com.example.cheas_stoeckli.data.repositories.NewsAddRepository
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind


class NewsAddViewModel(
    private val newsAddRepo: NewsAddRepository = NewsAddRepository(),
    private val imageRepo: CloudStorageRepo = CloudStorageRepo()
) : ViewModel() {

    fun addNews(
        title: String,
        text: String,
        img: String,
        destination: String,
        date: String,
        time: String,
        type: NewsKind,

    ) {
        val news = News(
            title = title,
            text = text,
            img = img,
            destination = destination,
            date = date,
            time = time,
            type = type,
        )
        newsAddRepo.addAnnoucement(news)
    }


}

