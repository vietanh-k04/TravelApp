package com.example.travelappandroid.di

import androidx.lifecycle.ViewModel
import com.google.android.datatransport.runtime.dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)