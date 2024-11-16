package com.example.bricksGame.ui.helper

import androidx.core.view.ContentInfoCompat.Flags

class Collision<T, V> {

    private lateinit var targetsList: MutableList<T>
    private lateinit var itemsList: MutableList<V>
    private var isStarted = false

    fun runCollision(state: Boolean) {
        isStarted = state
    }

    fun addToCollision(target: T? = null, item: V? = null) {

        if (target != null) {
            targetsList.add(target)
        }
        if (item != null) {
            itemsList.add(item)
        }
    }


}

