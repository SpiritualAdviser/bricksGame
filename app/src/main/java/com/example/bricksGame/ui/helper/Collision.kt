package com.example.bricksGame.ui.helper

class Collision<T, V> {

    private lateinit var targetsList: MutableList<T>
    private lateinit var itemsList: MutableList<V>

    fun addToCollision(target: T? = null, item: V? = null) {

        if (target != null) {
            targetsList.add(target)
        }
        if (item != null) {
            itemsList.add(item)
        }
    }
}

