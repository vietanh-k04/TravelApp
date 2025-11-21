package com.example.travelappandroid.di

import android.app.Application
import com.example.travelappandroid.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        FirebaseModule::class,
        RoomModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        FragmentModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(activity: MainActivity)
}