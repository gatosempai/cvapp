package dev.oscar.ruiz.cvapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.oscar.ruiz.cvapp.di.factory.ViewModelFactory
import dev.oscar.ruiz.cvapp.di.factory.ViewModelKey
import dev.oscar.ruiz.cvapp.fetchcv.ui.viewmodel.CvFetchViewModel

/**
 * This Module binds instances of the ViewModels & ViewModelFactory allowing us to use them properly on the
 * Activities that will use them
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CvFetchViewModel::class)
    abstract fun bindMainActivityViewModel(cvFetchViewModel: CvFetchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}