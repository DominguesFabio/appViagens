package com.example.appviagens.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appviagens.dao.DespesaDao
import com.example.appviagens.dao.UsuarioDao
import com.example.appviagens.dao.ViagemDao
import com.example.appviagens.entity.Despesa
import com.example.appviagens.entity.Usuario
import com.example.appviagens.entity.Viagem

@Database(entities = [Usuario::class, Viagem::class, Despesa::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun usuarioDao(): UsuarioDao
    abstract fun viagemDao(): ViagemDao
    abstract fun despesaDao(): DespesaDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(application: Application): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                "my-db7"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}