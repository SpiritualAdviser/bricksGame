package com.example.bricksGame.config

data class NegativeBonus(
    var id: Int,
    var life: Int,
    var imageFullLife: Int,
    var imageOnDamage: Int,
    var spriteName: String,
    var animationFullLife: String?,
    var animationOnDamage: String?,
    var animationOnDestroy: String?,
)