package com.example.shoppingevent.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppingevent.data.entities.ShoppingEvent
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingEventDao {
    @Insert
    suspend fun insert(shoppingEvent: ShoppingEvent)

    @Update
    suspend fun update(shoppingEvent: ShoppingEvent)

    @Delete
    suspend fun delete(shoppingEvent: ShoppingEvent)

    @Query("SELECT * FROM shopping_events")
    fun getEvents(): Flow<List<ShoppingEvent>>

}