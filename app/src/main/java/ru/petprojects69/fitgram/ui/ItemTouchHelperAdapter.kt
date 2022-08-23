package ru.petprojects69.fitgram.ui

interface ItemTouchHelperAdapter {
    fun onItemRemove(position: Int)
    fun onItemUpdate(position: Int)
    fun detailsClick(position: Int)
}