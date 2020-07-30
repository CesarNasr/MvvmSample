package com.example.mymvvmapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymvvmapp.data.db.entities.CURRENT_USER_ID
import com.example.mymvvmapp.data.db.entities.User

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long

    @Query("SELECT * FROM User where uid = $CURRENT_USER_ID")
     fun getUser(): LiveData<User>


}