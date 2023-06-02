package com.example.presentertest.feature.setting

import com.example.presentertest.AbstractTest
import io.mockk.checkUnnecessaryStub
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.data.setting.AlarmSetting

internal class SettingPresenterTest: AbstractTest() {

//    - sharedPreference에서 "영화 시작 30분 전 알림 설정"의 키에 해당하는 value 값을 가져와 화면에 그린다.
//    - 권한이 있는지 확인해서 권한이 있다면 스위치를 on합니다.
//    - 권한이 있는지 확인해서 권한이 없다면 스위치를 off합니다.
//    - sharedPreference off로 설정합니다.


    val view: SettingContract.View = mockk(relaxed = true)
    val sharedPreference: AlarmSetting = mockk()

    // system under test
    lateinit var sut: SettingPresenter

    @Before
    fun setUp() {
        sut = SettingPresenter(view, sharedPreference)
    }

    @Test
    fun `영화 시작 알림 설정이 켜져있다면 화면의 switch도 on으로 바꾼다`() {
        // given
        every { view.setSwitch(any()) } just runs
        every { sharedPreference.isEnable } returns true

        // when
        sut.loadAlarmInfo()

        // then
        verify { sharedPreference.isEnable }
        verify { view.setSwitch(true) }
    }

    @Test
    fun `sharedPreference에서 "영화 시작 30분 전 알림 설정"의 키에 해당하는 value 값이 false라면 화면의 switch도 false로 설정한다`() {
        // given
        every { view.setSwitch(any()) } just runs
        every { sharedPreference.isEnable } returns false

        // when
        sut.loadAlarmInfo()

        // then
        verify { sharedPreference.isEnable }
        view.setSwitch(false)
    }

}