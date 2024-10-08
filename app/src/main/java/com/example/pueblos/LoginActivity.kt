package com.example.pueblos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pueblos.ConnectionBD.ConnectionBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var connection: Connection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar las vistas
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btnLogin = findViewById(R.id.btnLogin)

        // Inicializar la conexión a la base de datos
        val connectionBD = ConnectionBD()
        connection = connectionBD.connect()

        // Configurar el comportamiento del botón de inicio de sesión
        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                // Verificar las credenciales del usuario en la base de datos
                if (verificarCredenciales(usuario, contrasena)) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí podrías redirigir al usuario a la pantalla principal de la aplicación
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para verificar las credenciales del usuario en la base de datos
    private fun verificarCredenciales(Usuario: String, Contrasena: String): Boolean {
        val query = "SELECT * FROM Usuarios WHERE Usuario = ? AND Contrasena = ?"
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        return try {
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, Usuario)
            preparedStatement.setString(2, Contrasena)
            resultSet = preparedStatement.executeQuery()

            resultSet.next() // Si hay un registro, las credenciales son correctas
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            resultSet?.close()
            preparedStatement?.close()
        }
    }
}
