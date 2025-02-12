package com.example.bricksGame.gameObjects

sealed class GameObjects {
    data class Brick(
        var baseModel: BaseModel,
        val cords: Cords,
        val animation: Animation
    ) :
        GameObjects()

    data class Bonus(var baseModel: BaseModel, val cords: Cords, val bonusType: BonusType) :
        GameObjects()

    data class Leaves(var baseModel: BaseModel) : GameObjects()
    data class Rock(var baseModel: BaseModel) : GameObjects()
    data class Empty(var baseModel: BaseModel) : GameObjects()
}
