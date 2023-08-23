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
    // 미리 선언하고 초기화를 나중에 해야 할 경우
    // 초기화 : 가서 값 지정 받아랏
    // 상단에서 type 선언 했기 때문에 밑에 메소드에서 type 지정해 줄 필요 없음
    lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var logInBtn: Button
    private lateinit var signInBtn: Button
    private lateinit var editID: EditText
    private lateinit var editPW: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        logInBtn = findViewById(R.id.btn_logIn)
        signInBtn = findViewById(R.id.btn_signIn)
        editID = findViewById(R.id.editID)
        editPW = findViewById(R.id.editPW)

        setResultNext()

        logInBtn.setOnClickListener {

            var isExistBlank: Boolean = false

            val id = editID.text.toString()
            val pw = editPW.text.toString()

            if (id.isEmpty() || pw.isEmpty()) {
                isExistBlank = true
            }

            if (!isExistBlank) {

                val intent = Intent(this, HomeActivity::class.java)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                intent.putExtra("idFromSignInActivity", id) // homeactivity로 아이디 전달
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