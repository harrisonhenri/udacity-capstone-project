package com.harrisonhenri.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harrisonhenri.myapplication.data.entities.CompanyEntity

@Dao
interface CompanyDao {

    @Query("select * from company_table")
    fun getCachedCompanies(): LiveData<List<CompanyEntity>>

    @Query("update company_table set isFavorite = 1 where id = :companyId")
    suspend fun companyFavorite(companyId: String)

    @Query("select * from company_table where isFavorite = 1")
    fun getFavoriteCompanies(): LiveData<List<CompanyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg companies: CompanyEntity)
}