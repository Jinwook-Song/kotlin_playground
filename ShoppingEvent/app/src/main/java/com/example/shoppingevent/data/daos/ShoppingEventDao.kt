package com.example.shoppingevent.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppingevent.data.entities.ShoppingEvent
import com.example.shoppingevent.data.entities.ShoppingItem
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

    @Query(
        """
    SELECT se.*, 
           (SELECT SUM(si.price + si.quantity) FROM shopping_items si WHERE si.event_id = se.id) AS total_cost, 
           si.*
    FROM shopping_events se 
    LEFT JOIN shopping_items si ON se.id = si.event_id 
    WHERE se.id = :id
    """
    )
    fun getEventAndItems(id: Long): Flow<Map<ShoppingEvent, List<ShoppingItem>>>
}