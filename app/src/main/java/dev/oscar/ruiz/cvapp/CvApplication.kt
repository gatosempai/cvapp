package dev.oscar.ruiz.cvapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.oscar.ruiz.cvapp.di.component.DaggerApplicationComponent

class CvApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }
}