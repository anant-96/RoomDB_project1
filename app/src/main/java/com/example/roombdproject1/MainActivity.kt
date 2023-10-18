package com.example.roombdproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.roombdproject1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val db by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "User.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveData.setOnClickListener {
            db.userDao().insert(User("Anant Desai", "8085676543", "Dombivli, maharashtra", 27))
        }
        binding.btnShowData.setOnClickListener {
            val list = db.userDao().getAllUser()
            if (list.isNotEmpty()){
                with(list[0]){
                    binding.tw1.text = name
                    binding.tw2.text= age.toString()
                    binding.tw3.text = address
                    binding.tw4.text = number
                }
            }
        }
    }
}