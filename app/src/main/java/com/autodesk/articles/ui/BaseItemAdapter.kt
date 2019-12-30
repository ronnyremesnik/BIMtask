package com.autodesk.articles.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.articles.R
import com.autodesk.articles.app.GlideApp
import com.autodesk.articles.article_screen.ArticleActivity
import com.autodesk.articles.data.Displayable
import kotlinx.android.synthetic.main.article_cell.view.*
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class BaseItemAdapter<Entity : Displayable>(private var listener: BaseListener<Entity>) :
    ListAdapter<Entity, BaseItemAdapter<Entity>.BaseViewHolder<Entity>>(object :
        DiffUtil.ItemCallback<Entity>() {
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem.getItemTitle() == newItem.getItemTitle()
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return Objects.equals(oldItem, newItem)
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Entity> {
        return BaseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.article_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Entity>, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BaseViewHolder<EntityHolder : Displayable>(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(entity: EntityHolder) {

            itemView.tv_title.text = entity.getItemTitle()
            itemView.tv_description.text = entity.getItemDescription()

            GlideApp.with(itemView.context).load(entity.getImageUrl())
                .placeholder(R.drawable.file_download)
                .error(R.drawable.borken_image)
                .into(itemView.iv_article_img)
            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, ArticleActivity::class.java)
//                intent.putExtra(getExtraKey(), entity)
//                itemView.context.startActivity(intent)
                    listener.onItemClick(entity as Entity)

            }

        }
    }

    abstract fun getExtraKey(): String
}

//    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(article: Article) {
//            itemView.tv_title.text = article.title
//            itemView.tv_description.text = article.description
//            val originalTimeFormat = SimpleDateFormat(Constants.ORIGINAL_DATE_FORMAT, Locale.US)
//            originalTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
//            val outputFormat = SimpleDateFormat(Constants.DESIRED_DATE_FORMAT, Locale.US)
//            var resolvedDate: String? = null
//            try {
//                val date = originalTimeFormat.parse(article.timeLine)
//                resolvedDate = outputFormat.format(date)
//            } catch (e: ParseException) {
//                Timber.e(e)
//            }
//            itemView.tv_timeline.text = resolvedDate ?: article.timeLine
//            GlideApp.with(itemView.context).load(article.imgUrl)
//                .placeholder(R.drawable.file_download)
//                .error(R.drawable.borken_image)
//                .into(itemView.iv_article_img)
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, ArticleActivity::class.java)
//                intent.putExtra(Constants.ARTICLE, article)
//                itemView.context.startActivity(intent)
//            }
//        }
//    }
//}