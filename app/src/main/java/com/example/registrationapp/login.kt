package com.example.registrationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerTextView: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            loginUser()
        }
        // Navigate to registration
        registerTextView.setOnClickListener {
            startActivity(Intent(this, register::class.java))
        }
    }
    private fun loginUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        // Sign in the user
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    // Redirect to main activity or dashboard
                    startActivity(Intent(this, MainActivity::class.java)) // Create a MainActivity for logged-in users
                    finish()
                } else {
                    Toast.makeText(this, "Login failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
