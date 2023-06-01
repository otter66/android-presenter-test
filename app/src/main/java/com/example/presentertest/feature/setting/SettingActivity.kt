package com.example.presentertest.feature.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presentertest.R

class SettingActivity : AppCompatActivity(), SettingContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }
}