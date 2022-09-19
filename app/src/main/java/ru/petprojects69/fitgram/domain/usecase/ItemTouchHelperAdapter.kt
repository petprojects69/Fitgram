package ru.petprojects69.fitgram.domain.usecase

interface ItemTouchHelperAdapter {
    fun onItemRemove(position: Int){}
    fun onItemUpdate(position: Int){}
    fun detailsClick(position: Int){}
    fun onItemClick(position: Int){}
    fun onItemMove(from :Int, to:Int){}
}