package com.sirius.driverlicense.repository.local

import android.content.Context
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.stmt.UpdateBuilder
import com.sirius.driverlicense.repository.models.LocalMessage

import java.lang.Exception
import java.sql.SQLException
import java.util.ArrayList

class MessageDatabase(ctx: Context?) : BaseDatabase<LocalMessage, String>(ctx) {

    override fun getMainDao(): Dao<LocalMessage, String> {
        return db!!.messagesDao
    }

    override fun deleteAll() {
        db!!.clearMessages()
    }

    fun getAllMessagesForDid(did: String): List<LocalMessage>? {
        try {
            return getMainDao().queryForEq("pairwiseDid", did)
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return ArrayList()
    }

    fun getIdUpdateBuilder(id: String): UpdateBuilder<LocalMessage, String> {
        val builder = getUpdateBuilder()
        builder.where().idEq(id)
        return builder
    }

    fun updateLoading(id: String, isLoading: Boolean) {
        try{
            val builder = getIdUpdateBuilder(id)
            builder.updateColumnValue("isLoading", isLoading)
            builder.update()
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun getMainActionsMessages(): List<LocalMessage> {
        try {
            return getQueryeBuilder().where().eq("isAccepted", false).and().eq("isCanceled", false)
                .and().not().eq("type", "text").query().orEmpty()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return listOf()
    }

    fun updateErrorAccepted(
        id: String,
        isAccepted: Boolean,
        canceled: Boolean,
        error: String?,
        comment: String?
    ) {
        try{
            val builder = getIdUpdateBuilder(id)
            builder.updateColumnValue("isLoading", false)
            builder.updateColumnValue("isAccepted", isAccepted)
            builder.updateColumnValue("isCanceled", canceled)
            builder.updateColumnValue("acceptedComment", comment)
            builder.updateColumnValue("canceledComment", error)
            builder.update()
        }catch (e : Exception){
            e.printStackTrace()
        }

    }
}