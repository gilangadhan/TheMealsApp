package com.dicoding.academy.themealsapp.core.utils

import com.dicoding.academy.themealsapp.core.data.locale.entity.CategoryEntity
import com.dicoding.academy.themealsapp.core.data.remote.reponse.CategoryResponse
import com.dicoding.academy.themealsapp.core.data.remote.reponse.MealResponse
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.core.domain.model.MealModel

object DataMapper {
    fun mapCategoryResponsesToEntities(input: List<CategoryResponse>): List<CategoryEntity> {
        val categoryList = ArrayList<CategoryEntity>()
        input.map {
            val category = CategoryEntity(
                id = it.id,
                title = it.title,
                image = it.image,
                desc = it.description
            )
            categoryList.add(category)
        }
        return categoryList
    }

    fun mapCategoryResponsesToDomain(input: List<CategoryResponse>): List<CategoryModel> {
        val categoryList = ArrayList<CategoryModel>()
        input.map {
            val category = CategoryModel(
                id = it.id,
                title = it.title,
                image = it.image,
                description = it.description
            )
            categoryList.add(category)
        }
        return categoryList
    }

    fun mapMealResponsesToDomain(input: List<MealResponse>): List<MealModel> {
        val categoryList = ArrayList<MealModel>()
        input.map {
            val category = MealModel(
                id = it.id,
                title = it.title,
                image = it.image,
            )
            categoryList.add(category)
        }
        return categoryList
    }

    fun mapCategoryEntitiesToDomain(input: List<CategoryEntity>): List<CategoryModel> =
        input.map {
            CategoryModel(
                id = it.id,
                title = it.title,
                image = it.image,
                description = it.desc
            )
        }

    fun mapCategoryEntityToDomain(input: CategoryEntity) =
            CategoryModel(
                id = input.id,
                title = input.title,
                image = input.image,
                description = input.desc
            )

    fun mapCategoryDomainToEntity(input: CategoryModel) = CategoryEntity(
        id = input.id,
        title = input.title,
        image = input.image,
        desc = input.description
    )
}