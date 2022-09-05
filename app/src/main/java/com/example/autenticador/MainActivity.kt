package com.example.autenticador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val txtcadastroUsuario: TextView = findViewById(R.id.cadastroUsuario)

        txtcadastroUsuario.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnLogar: Button = findViewById(R.id.btnLogar)

        btnLogar.setOnClickListener{
            performLogin()
        }

    }

    private fun performLogin() {
        val txtEmail: EditText = findViewById(R.id.edtLogin)
        val txtSenha: EditText = findViewById(R.id.edtSenha)

        if(txtEmail.text.isEmpty() || txtSenha.text.isEmpty()){
            Toast.makeText(this, "Por Favor preencher os campos para efetuar o login!", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = txtEmail.text.toString()
        val inputSenha = txtSenha.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputSenha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Autenticação realizada com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Usuário e senha não conferem!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}