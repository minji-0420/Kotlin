package com.example.introducemyself

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData

class SignInActivity : AppCompatActivity() {

    lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var logInBtn: Button
    private lateinit var signInBtn: Button
    private lateinit var editID: EditText
    private lateinit var editPW: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        logInBtn = findViewById<Button>(R.id.btn_logIn)
        signInBtn = findViewById<Button>(R.id.btn_signIn)
        editID = findViewById<EditText>(R.id.editID)
        editPW = findViewById<EditText>(R.id.editPW)

        setResultNext()

        logInBtn.setOnClickListener {

            var isExistBlank: Boolean = false

            val id = editID.text.toString()
            val pw = editPW.text.toString()

            if (id.isEmpty() || pw.isEmpty()) {
                isExistBlank = true
            }

            if (!isExistBlank) {

                val editID = findViewById<EditText>(R.id.editID)
                val strData = editID.text.toString()
                val intent = Intent(this, HomeActivity::class.java)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                intent.putExtra("idFromSignInActivity", strData)
                startActivity(intent)

            } else Toast.makeText(this, "아이디 또는 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
        }

        signInBtn.setOnClickListener {
            val intentSignUp = Intent(this, SignUpActivity::class.java)
            launcher.launch(intentSignUp)
        }
    }

    private fun setResultNext() {

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK && result.data != null) {
                    val id = result.data?.getStringExtra("id") ?: ""
                    val pw = result.data?.getStringExtra("pw") ?: ""

                    editID.setText(id)
                    editPW.setText(pw)
                }
            }

    }
}



