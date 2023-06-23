package com.example.appviagens.repository

import com.example.appviagens.dao.UsuarioDao
import com.example.appviagens.entity.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioRepository (private val usuarioDao: UsuarioDao) {


    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addUser(usuario: Usuario) {
        coroutine.launch(Dispatchers.IO) {
            usuarioDao.insert(usuario)
        }
    }

    fun delete(usuario: Usuario) {
        coroutine.launch(Dispatchers.IO) {
            usuarioDao.delete(usuario)
        }
    }

    suspend fun loadAllUsers(): List<Usuario> {
        return usuarioDao.getAll()
    }

    suspend fun findByName(nome: String): Usuario? =
        usuarioDao.findByName(nome)
}