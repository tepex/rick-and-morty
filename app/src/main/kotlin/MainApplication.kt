package ru.work_mate.rick_and_morty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import ru.it_arch.k3dm.ValueObject
import ru.work_mate.rick_and_morty.domain.Test

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        TestImpl("qqq").also { Timber.d("Test: $it") }
    }

    data class TestImpl(
        override val str: String
    ) : Test {

        override fun <T : ValueObject.Data> fork(vararg args: Any?): T {
            TODO("not implemented")
        }
    }
}

