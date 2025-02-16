package com.example.shoppingevent.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shoppingevent.data.daos.ShoppingEventDao
import com.example.shoppingevent.data.daos.ShoppingItemDao
import com.example.shoppingevent.data.entities.ShoppingEvent
import com.example.shoppingevent.data.entities.ShoppingItem


@Database(entities = [ShoppingEvent::class, ShoppingItem::class], version = 2)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun shoppingEventDao(): ShoppingEventDao
    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object {
        const val DATABASE_NAME = "shopping_database"

        @Volatile
        private var Instance: ShoppingDatabase? = null


        // 버전 1에서 2로의 마이그레이션 로직 예시
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 1. 외래키 제약조건이 포함된 새 테이블 생성
                database.execSQL(
                    """
            CREATE TABLE shopping_items_new (
                item_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                event_id INTEGER NOT NULL,
                item_name TEXT NOT NULL,
                price REAL NOT NULL,
                quantity REAL NOT NULL,
                FOREIGN KEY(event_id) REFERENCES shopping_events(id) ON DELETE CASCADE
            )
        """.trimIndent()
                )

                // 2. 기존 데이터 복사
                database.execSQL(
                    """
            INSERT INTO shopping_items_new (item_id, event_id, item_name, price, quantity)
            SELECT item_id, event_id, item_name, price, quantity FROM shopping_items
        """.trimIndent()
                )

                // 3. 기존 테이블 삭제
                database.execSQL("DROP TABLE shopping_items")

                // 4. 새 테이블의 이름을 기존 테이블 이름으로 변경
                database.execSQL("ALTER TABLE shopping_items_new RENAME TO shopping_items")
            }
        }

        fun getDatabase(context: Context): ShoppingDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ShoppingDatabase::class.java,
                    DATABASE_NAME
                )
                    .addMigrations(MIGRATION_1_2)
                    .build().also {
                        Instance = it
                    }
            }
        }
    }
}