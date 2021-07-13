package com.masinerija.knowledge.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brewery_table")
data class Brewery(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "brewery_id")
    var breweryId: Int,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "street")
    var street: String?,

    @ColumnInfo(name = "phone")
    var phone: String?,

    @ColumnInfo(name = "website_url")
    var websiteUrl: String?
)
