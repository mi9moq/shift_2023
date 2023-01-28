package ru.cft.shift2023winter.presentation

import android.app.Application
import ru.cft.shift2023winter.di.DaggerApplicationComponent

class AnimeApplication:Application() {

    val component by lazy { DaggerApplicationComponent.factory().create(this) }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}