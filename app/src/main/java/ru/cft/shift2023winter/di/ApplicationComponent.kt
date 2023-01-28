package ru.cft.shift2023winter.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.cft.shift2023winter.presentation.AnimeApplication

@ApplicationScope
@Component()
interface ApplicationComponent {

    fun inject(application: AnimeApplication)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}