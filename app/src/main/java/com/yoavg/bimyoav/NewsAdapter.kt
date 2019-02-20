package com.yoavg.bimyoav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoavg.bimyoav.app.GlideApp
import com.yoavg.bimyoav.data.Article
import kotlinx.android.synthetic.main.article_cell.view.*


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
            itemView.tv_timeline.text = article.timeLine
            GlideApp.with(itemView.context).load(article.imgUrl).into(itemView.iv_article_img)
            itemView.setOnClickListener {

            }
        }

    }
}