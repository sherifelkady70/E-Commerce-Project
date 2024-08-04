package com.example.routee_commerce.utils

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return areContentsTheSame.invoke(oldList[oldItemPosition], newList[newItemPosition])
    }
}