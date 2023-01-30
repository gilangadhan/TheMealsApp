package com.dicoding.academy.themealsapp.core.data.locale.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.academy.themealsapp.core.data.locale.entity.CategoryEntity


@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getInstance(context: Context): MealDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "Meal.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}