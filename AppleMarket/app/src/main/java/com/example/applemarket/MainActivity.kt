package com.example.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.AudioAttributes
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        val dataList = ItemListRepository.getItemList()

        val adapter = Adapter(dataList as MutableList<ItemList>)
        recyclerView.apply {
            this.adapter = adapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
//            addItemDecoration(DividerRV())
        }
        adapter.setOnItemClickListener(object : Adapter.OnItemClickListener {
            override fun onItemClick(data: ItemList, position: Int) {
                // 해당 상품의 디테일 페이지로 이동
                val intent = Intent(this@MainActivity, DetailActivity::class.java)

                // data를 상세 페이지로 넘김
                val dataList = ArrayList<ItemList>()
                dataList.add(data)
                intent.putParcelableArrayListExtra("itemList", dataList)
                intent.putExtra("position", position)
                intent.putExtra("isHeartFilled", data.isHeartFilled)
                launcher.launch(intent)
            }
        })

        binding.mainAlarm.setOnClickListener {
            notification()
        }

        //플로팅 액션 버튼
        val fabScrollToTop = binding.floatingActionButton
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        fabScrollToTop.hide()

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                fabScrollToTop.hide()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var isFabVisible = false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && !fabScrollToTop.isShown) {
                    // 스크롤 아래로 내릴 때 버튼 보이기
                    isFabVisible = true
                    fabScrollToTop.show()
                    fabScrollToTop.startAnimation(fadeIn)
                } else if (dy < 0 && fabScrollToTop.isShown) {
                    // 스크롤 위로 올릴 때 버튼 숨기기
                    isFabVisible = false
                    fabScrollToTop.hide()
                    fabScrollToTop.startAnimation(fadeOut)
                }
            }
        })
        fabScrollToTop.setOnClickListener {
            fabScrollToTop.startAnimation(fadeOut)
            recyclerView.scrollToPosition(0)
        }
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK && result.data != null) {
                    val isHeartFilled = result.data?.getBooleanExtra("isHeartFilled", false)
                    val position = result.data?.getIntExtra("position", -1)
                    if (isHeartFilled == true && position != -1) {
                        dataList[position!!].isHeartFilled = true
                        dataList[position].likes ++
                        adapter.notifyItemChanged(position)
                    }
                    else {
                        if (dataList[position!!].isHeartFilled) {
                            dataList[position!!].isHeartFilled = false
                            dataList[position].likes--
                            adapter.notifyItemChanged(position)
                        }
                    }
                }
            }
    }

    private fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "애플마켓"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel Description"
                setShowBadge(true)
                // val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(null, audioAttributes)
                enableVibration(true)
            }

            manager?.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        val intent = Intent(this, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
            addAction(R.mipmap.ic_launcher, "Action", pendingIntent)
        }
        manager.notify(11, builder.build())
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setIcon(R.drawable.main_chatting)
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setPositiveButton("확인") { _, _ -> finish() }
        builder.setNegativeButton("취소") { _, _ -> }
        builder.show()
    }
}