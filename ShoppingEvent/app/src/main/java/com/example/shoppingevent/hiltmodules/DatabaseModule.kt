package com.example.shoppingevent.hiltmodules


import android.content.Context
import com.example.shoppingevent.data.ShoppingDatabase
import com.example.shoppingevent.data.daos.ShoppingEventDao
import com.example.shoppingevent.data.daos.ShoppingItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // bind는 impl을 매칭만 시켜준다면 Provides는 실제로 Provide 해야한다.
    @Provides
    fun provideShoppingEventDao(@ApplicationContext context: Context): ShoppingEventDao {
        return ShoppingDatabase.getDatabase(context).shoppingEventDao()
    }

    @Provides
    fun provideShoppingItemDao(@ApplicationContext context: Context): ShoppingItemDao {
        return ShoppingDatabase.getDatabase(context).shoppingItemDao()
    }

}