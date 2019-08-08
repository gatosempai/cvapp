package dev.oscar.ruiz.cvapp.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.oscar.ruiz.cvapp.CvApplication
import dev.oscar.ruiz.cvapp.di.module.ActivityBindingModule
import dev.oscar.ruiz.cvapp.di.module.ApiModule
import dev.oscar.ruiz.cvapp.di.module.ViewModelModule
import dev.oscar.ruiz.cvapp.di.scope.AppScope

/**
 * ApplicationComponent to be used by Dagger for graph
 */

@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent : AndroidInjector<CvApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CvApplication): Builder

        fun build(): ApplicationComponent
    }
}