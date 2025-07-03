package com.example.cheas_stoeckli.domain.mappers

import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.domain.models.FirebaseCheese
import com.example.cheas_stoeckli.ui.enums.MilkType

object CheeseMapper {

    fun toApp(dto: FirebaseCheese): Cheese {
        return Cheese(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            milkType = MilkType.convertRawValue(dto.milkType) ?: MilkType.COW ,
            createdAt = dto.createdAt
        )
    }

    fun toFirebase(cheese: Cheese): FirebaseCheese {
        return FirebaseCheese(
            id = cheese.id,
            name = cheese.name,
            description = cheese.description,
            milkType = cheese.milkType.rawValue,
            createdAt = cheese.createdAt
        )
    }
}