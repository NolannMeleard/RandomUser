package com.nmel.user.storage

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nmel.user.models.local.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM users ORDER BY email")
    fun getUsers(): Single<List<User>>

    @Query("DELETE FROM users")
    fun clearAllUsers(): Completable

    @Query("SELECT COUNT(email) FROM users")
    fun size(): Single<Int>
}