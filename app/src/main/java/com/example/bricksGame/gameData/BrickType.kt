package com.example.bricksGame.gameData

import com.example.bricksGame.R

sealed class BrickType {
    data class Brick(var baseModel: BaseModel) : BrickType()
    data class Bonus(var baseModel: BaseModel) : BrickType()
    data class Leaves(var baseModel: BaseModel) : BrickType()
    data class Rock(var baseModel: BaseModel) : BrickType()
    data class Empty (var baseModel: BaseModel) : BrickType()
}
