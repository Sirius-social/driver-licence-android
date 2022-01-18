package com.sirius.driverlicense.repository

import androidx.lifecycle.LiveData
import com.sirius.driverlicense.repository.local.BaseDatabase


abstract class BaseRepository<T,M> {

    var databaseHolder : BaseDatabase<T,M>? = null

    open fun getDatabase() : BaseDatabase<T,M> {
        if(databaseHolder == null){
            databaseHolder = createDatabase()
        }
        return databaseHolder!!
    }

    abstract fun createDatabase()  : BaseDatabase<T,M>

    open fun isExist(item: T): LiveData<Boolean> {
        return  getDatabase().isExist(item)
    }
    open  fun createItem(item : T){
        getDatabase().create(item)
    }
    open fun createOrUpdateItem(item : T) {
        getDatabase().createOrUpdate(item)
    }
    open  fun getAllItems() : LiveData<List<T>>{
        return getDatabase().getAll()
    }

    open  fun getItemsBy(column : String, query : Any) :  LiveData<List<T>>{
        return getDatabase().getItemsBy(column, query )
    }

    open  fun getItemsBy(args : Map<String,Any>) :  LiveData<List<T>>{
        return getDatabase().getItemsBy(args)
    }

    open  fun getItemById(id : M) : LiveData<T?>{
        return getDatabase().getItemById(id)
    }
    open  fun getItemBy(id : M) : T?{
        return getDatabase().getItemBy(id)
    }

    open  fun deleteAllFor(column: String, query: Any) {
        getDatabase().deleteAllFor(column, query)
    }

}