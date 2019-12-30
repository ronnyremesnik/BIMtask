package com.autodesk.articles.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.autodesk.articles.R
import com.autodesk.articles.app.GlideApp
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
                    listener.onItemClick(entity as Entity)

            }

        }
    }

    abstract fun getExtraKey(): String
}

