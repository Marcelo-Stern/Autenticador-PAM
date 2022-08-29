package com.example.autenticador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

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
        val txtEmail: EditText = findViewById(R.id.edtLogin)
        val txtSenha: EditText = findViewById(R.id.edtSenha)

        if(txtEmail.text.isEmpty() || txtSenha.text.isEmpty()){
            Toast.makeText(this, "Por Favor preencher os campos para relizar o cadastro!", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = txtEmail.text.toString()
        val inputSenha = txtSenha.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputSenha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "O cadastro falhou!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Ocorreu o erro ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}