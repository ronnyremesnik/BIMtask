package com.autodesk.articles.main_screen

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.articles.R
import com.autodesk.articles.app.Constants
import com.autodesk.articles.app.GlideApp
import com.autodesk.articles.article_screen.ArticleActivity
import com.autodesk.articles.data.Article
import kotlinx.android.synthetic.main.article_cell.view.*
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter :
    ListAdapter<Article, NewsAdapter.ArticleViewHolder>(object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.article_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            itemView.tv_title.text = article.title
            itemView.tv_description.text = article.description
            val originalTimeFormat = SimpleDateFormat(Constants.ORIGINAL_DATE_FORMAT, Locale.US)
            originalTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
            val outputFormat = SimpleDateFormat(Constants.DESIRED_DATE_FORMAT, Locale.US)
            var resolvedDate: String? = null
            try {
                val date = originalTimeFormat.parse(article.timeLine)
                resolvedDate = outputFormat.format(date)
            } catch (e: ParseException) {
                Timber.e(e)
            }
            itemView.tv_timeline.text = resolvedDate ?: article.timeLine
            GlideApp.with(itemView.context).load(article.imgUrl)
                .placeholder(R.drawable.file_download)
                .error(R.drawable.borken_image)
                .into(itemView.iv_article_img)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ArticleActivity::class.java)
                intent.putExtra(Constants.ARTICLE, article)
                itemView.context.startActivity(intent)
            }
        }
    }
}