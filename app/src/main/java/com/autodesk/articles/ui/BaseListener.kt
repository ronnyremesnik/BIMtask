package com.autodesk.articles.ui

import com.autodesk.articles.data.Displayable

interface BaseListener<Entity : Displayable> {
    fun onItemClick(entity: Entity)
}