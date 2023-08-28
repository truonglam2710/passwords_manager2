package duy.anh.passwords_manager

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.android.ads.mediationtestsuite.activities.HomeActivity

class LoginPWActivity : AppCompatActivity() {
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var showPasswordCheckBox: CheckBox
    private lateinit var loginButton: Button

    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pwactivity)

        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        showPasswordCheckBox = findViewById(R.id.showPasswordCheckBox)
        loginButton = findViewById(R.id.loginButton)

        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            passwordVisible = isChecked
            togglePasswordVisibility()
        }

        loginButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                savePassword(password)
                navigateToHome()
            } else {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun togglePasswordVisibility() {
        if (passwordVisible) {
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            confirmPasswordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            confirmPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    private fun savePassword(password: String) {
        // Lưu mật khẩu vào SharedPreferences hoặc cơ sở dữ liệu

        // Ví dụ sử dụng SharedPreferences
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("password", password).apply()
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}