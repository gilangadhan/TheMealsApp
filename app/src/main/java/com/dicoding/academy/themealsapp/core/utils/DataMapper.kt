package com.dicoding.academy.themealsapp.core.utils

import com.dicoding.academy.themealsapp.core.data.locale.entity.CategoryEntity
import com.dicoding.academy.themealsapp.core.data.remote.reponse.CategoryResponse
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel

object DataMapper {
    fun mapResponsesToEntities(input: List<CategoryResponse>): List<CategoryEntity> {
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

    fun mapResponsesToDomain(input: List<CategoryResponse>): List<CategoryModel> {
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

    fun mapEntitiesToDomain(input: List<CategoryEntity>): List<CategoryModel> =
        input.map {
            CategoryModel(
                id = it.id,
                title = it.title,
                image = it.image,
                description = it.desc
            )
        }

    fun mapEntityToDomain(input: CategoryEntity) =
            CategoryModel(
                id = input.id,
                title = input.title,
                image = input.image,
                description = input.desc
            )

    fun mapDomainToEntity(input: CategoryModel) = CategoryEntity(
        id = input.id,
        title = input.title,
        image = input.image,
        desc = input.description
    )
}