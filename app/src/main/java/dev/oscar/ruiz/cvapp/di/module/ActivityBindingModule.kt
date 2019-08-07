package dev.oscar.ruiz.cvapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.LandingActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun LandingActivity(): LandingActivity
}