package com.example.presentertest.feature.setting

interface SettingContract {

    interface View {
        fun setSwitch(value: Boolean)

    }

    interface Presenter {
        fun loadAlarmInfo()

    }

}