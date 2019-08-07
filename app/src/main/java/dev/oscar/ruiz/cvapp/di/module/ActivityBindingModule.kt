package dev.oscar.ruiz.cvapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.MainActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun MainActivity(): MainActivity
}