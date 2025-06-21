package com.example.cheas_stoeckli.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.data.repositories.NewsAddRepository
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind


class NewsAddViewModel(
    private val newsAddRepo: NewsAddRepository = NewsAddRepository()
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

        Log.d("AddNewsViewModel", "addNews wurde aufgerufen")

        val news = News(
            title = title,
            text = text,
            img = img,
            destination = destination,
            date = date,
            time = time,
            type = type,
        )
        newsAddRepo.addAnnouncement(news)
    }

    fun addNewss(
        news: News,
    ) {
        val news = News(
            title = news.title,
            text = news.text,
            img = news.img,
            destination = news.destination,
            date = news.date,
            time = news.time,
            type = news.type,
        )
        newsAddRepo.addAnnouncement(news)
    }

}

