package com.nmel.user.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@Entity(tableName = "info")
data class Info(
    @PrimaryKey
    @ColumnInfo(name = "seed")
    val seed: String,
    @ColumnInfo(name = "page")
    val page: Int
) {
    companion object
}