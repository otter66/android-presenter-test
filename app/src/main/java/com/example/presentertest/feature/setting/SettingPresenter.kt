package com.example.presentertest.feature.setting

import woowacourse.movie.data.setting.AlarmSetting

class SettingPresenter(
    val view: SettingContract.View,
    val setting: AlarmSetting
): SettingContract.Presenter {
    override fun loadAlarmInfo() {

        view.setSwitch(setting.isEnable)
    }
}