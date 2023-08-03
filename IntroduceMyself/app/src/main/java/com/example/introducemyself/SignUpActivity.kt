package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    private lateinit var signInBtn : Button
    private lateinit var editName : EditText
    private lateinit var editID : EditText
    private lateinit var editPW : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signInBtn = findViewById<Button>(R.id.btn_signIn)
        editName = findViewById<EditText>(R.id.editName)
        editID = findViewById<EditText>(R.id.editID)
        editPW = findViewById<EditText>(R.id.editPW)

        signInBtn.setOnClickListener {

            var isExistBlank: Boolean = false
            val name = editName.text.toString()
            val id = editID.text.toString()
            val pw = editPW.text.toString()

            if (name.isEmpty() || id.isEmpty() || pw.isEmpty()) {
                isExistBlank = true
            }

            if (!isExistBlank) {

                val intent = Intent()
                intent.putExtra("id", id)
                intent.putExtra("pw",pw)

                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK, intent)
                finish()

            } else Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}