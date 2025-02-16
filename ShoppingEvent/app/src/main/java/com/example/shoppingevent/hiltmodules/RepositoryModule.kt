package com.example.shoppingevent.hiltmodules

import com.example.shoppingevent.data.repositories.ShoppingEventRepository
import com.example.shoppingevent.data.repositories.ShoppingEventRepositoryImpl
import com.example.shoppingevent.data.repositories.ShoppingItemRepository
import com.example.shoppingevent.data.repositories.ShoppingItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindShoppingEventRepository(impl: ShoppingEventRepositoryImpl): ShoppingEventRepository

    @Binds
    abstract fun bindShoppingItemRepository(impl: ShoppingItemRepositoryImpl): ShoppingItemRepository
}