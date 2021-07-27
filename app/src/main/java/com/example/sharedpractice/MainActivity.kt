package com.example.sharedpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sharedpractice.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

//앱을 종료해도 값을 저장해 유지해야할 때 필요.
//앱 제거 후 새로 설치파면 값 초기화 된다. * 유의

//getSharedPreferences mode
//MODE_PRIVATE
//MODE_WORLD_READABLE
//MODE_WORLD_WRITEABLE


class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        binding.serchBar.setText(pref.getString("name",""))
    }

    private fun saveData() {
        //App 내부에다가 pref라는 명칭으로 저장을 하겠다.
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()
        edit.putString("name",binding.serchBar.text.toString())
        edit.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
        mBinding = null
    }


}