package ru.cft.shift2023winter.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.cft.shift2023winter.presentation.AnimeApplication
import ru.cft.shift2023winter.presentation.MainActivity

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: AnimeApplication)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}