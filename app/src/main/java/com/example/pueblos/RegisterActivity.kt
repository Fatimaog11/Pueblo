package com.example.pueblos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pueblos.ConnectionBD.ConnectionBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var tvIniciarSesion: TextView
    private lateinit var connection: Connection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar las vistas
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        tvIniciarSesion = findViewById(R.id.tvLogin)

        // Inicializar la conexión a la base de datos
        val connectionBD = ConnectionBD()
        connection = connectionBD.connect()

        // Configurar el comportamiento del botón de registro
        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val usuario = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (nombre.isNotEmpty() && correo.isNotEmpty() && usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                // Insertar los datos del usuario en la base de datos
                if (registrarUsuario(nombre, correo, usuario, contrasena)) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Comportamiento del enlace "¿Ya tienes una cuenta? Inicia sesión"
        tvIniciarSesion.setOnClickListener {
            // Redirigir al inicio de sesión
            // Aquí podrías lanzar la actividad LoginActivity
        }
    }

    // Función para registrar un usuario en la base de datos
    private fun registrarUsuario(Nombre: String, Correo: String, Usuario: String, Contrasena: String): Boolean {
        val query = "INSERT INTO Usuarios (Nombre, Correo, Usuario, Contrasena) VALUES (?, ?, ?, ?)"
        var preparedStatement: PreparedStatement? = null

        return try {
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, Nombre)
            preparedStatement.setString(2, Correo)
            preparedStatement.setString(3, Usuario)
            preparedStatement.setString(4, Contrasena)
            preparedStatement.executeUpdate()
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            preparedStatement?.close()
        }
    }
}
