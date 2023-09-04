package com.example.littlelemon

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase


@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)

@Dao
interface MenuItemDao{
    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems(): List<MenuItemEntity>

    @Insert
    fun insertAll(vararg menuItems: MenuItemEntity)
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun menuItemDoa(): MenuItemDao
}


object DatabaseHelper{

    private fun getDatabase(context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "menu-database"
        ).build()
    }

    suspend fun saveToDatabase(menuItems: List<MenuItemEntity>, context: Context){
        val database = getDatabase(context)
        database.menuItemDoa().insertAll(*menuItems.toTypedArray())
    }

    suspend fun getMenuItems(context: Context): List<MenuItemEntity>{
        val database = getDatabase(context)
        return database.menuItemDoa().getAllMenuItems()
    }
}