package wize.room.pets

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets_table")
data class Pet(
    @PrimaryKey
    @ColumnInfo(name = "pet_name")
    val name: String,
    @ColumnInfo(name = "pet_breech")
    val breech: String,
    @ColumnInfo(name ="pet_gender")
    val gender: String,
    @ColumnInfo(name = "pet_weight")
    val weight: String
)