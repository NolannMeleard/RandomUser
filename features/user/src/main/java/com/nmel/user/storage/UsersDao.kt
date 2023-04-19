package com.nmel.user.storage

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nmel.user.models.local.User
import io.reactivex.rxjava3.core.Completable

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>): Completable

    @Query("SELECT * FROM users ORDER BY email")
    fun getUsers(): PagingSource<Int, User>

    @Query("DELETE FROM users")
    fun clearAllUsers(): Completable
}