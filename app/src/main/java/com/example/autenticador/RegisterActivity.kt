package com.example.autenticador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener{
            performSignUp()
        }
    }

    private fun performSignUp() {
        val txtEmail: TextView = findViewById(R.id.edtLogin)
        val txtSenha: TextView = findViewById(R.id.edtSenha)

        if(txtEmail.text.isEmpty() || txtSenha.text.isEmpty()){
            Toast.makeText(this, "Por Favor preencher os campos para efetuar o login!", Toast.LENGTH_SHORT)
            return
        }

        val inputEmail = txtEmail.text.toString()
        val inputSenha = txtSenha.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputSenha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Autenticação realizada com sucesso!", Toast.LENGTH_SHORT)
                } else {
                    Toast.makeText(this, "Usuário e senha não conferem!", Toast.LENGTH_SHORT)
                }
            }
    }
}