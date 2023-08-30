package com.example.contactapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.databinding.ActivityMainBinding
import android.Manifest
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val requestCode = 123

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileList = arrayListOf(
            Profiles(R.drawable.user1, "김로로", "010-1111-1111", 0),
            Profiles(R.drawable.user2, "이모모", "010-2222-2222", 0),
            Profiles(R.drawable.user3, "박도도", "010-3333-3333", 0),
            Profiles(R.drawable.user1, "김로로", "010-1111-1111", 0),
            Profiles(R.drawable.user2, "이모모", "010-2222-2222", 0),
            Profiles(R.drawable.user3, "박도도", "010-3333-3333", 0),
            Profiles(R.drawable.user1, "김로로", "010-1111-1111", 0),
            Profiles(R.drawable.user2, "이모모", "010-2222-2222", 0),
            Profiles(R.drawable.user3, "박도도", "010-3333-3333", 0),
            Profiles(R.drawable.user1, "김로로", "010-1111-1111", 0),
            Profiles(R.drawable.user2, "이모모", "010-2222-2222", 0),
            Profiles(R.drawable.user3, "박도도", "010-3333-3333", 0),
            Profiles(R.drawable.user1, "김로로", "010-1111-1111", 0),
            Profiles(R.drawable.user2, "이모모", "010-2222-2222", 0),
            Profiles(R.drawable.user3, "박도도", "010-3333-3333", 0)
        )

        val adapter = ProfileAdapter(profileList)
        binding.rvProfile.adapter = adapter
        binding.rvProfile.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvProfile.setHasFixedSize(true)

        adapter.itemClick = object : ProfileAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val clickedProfile = profileList[position]
                val phone = clickedProfile.phone
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))

                if (checkCallPermission()) {
                    makePhoneCall(intent, phone)
                } else {
                    requestCallPermission()
                }
            }
        }
    }

    private fun checkCallPermission(): Boolean {
        val permission = Manifest.permission.CALL_PHONE
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
            this, permission
        )
    }

    private fun makePhoneCall(intent: Intent, phoneNumber: String) {
        intent.putExtra("phone_number", phoneNumber)
        startActivity(intent)
    }

    private fun requestCallPermission() {
        val permission = Manifest.permission.CALL_PHONE
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            this.requestCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val phoneNumber = intent.getStringExtra("phone_number")
                    val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                    if (phoneNumber != null) {
                        makePhoneCall(callIntent, phoneNumber)
                    }
                } else {
                    Toast.makeText(this, "전화 걸기 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}