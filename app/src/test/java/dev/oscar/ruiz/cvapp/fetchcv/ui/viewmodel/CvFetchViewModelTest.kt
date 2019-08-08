package dev.oscar.ruiz.cvapp.fetchcv.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.oscar.ruiz.cvapp.fetchcv.data.FetchRepository
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import dev.oscar.ruiz.cvapp.utils.Status
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class CvFetchViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var fetchRepository: FetchRepository
    private lateinit var cvFetchViewModel: CvFetchViewModel

    @Before
    fun init() {
        fetchRepository = mock()
        cvFetchViewModel = CvFetchViewModel(fetchRepository)
    }

    @Test
    fun fetchCv_Error() {
        val resource = CvFetchResponse(
            status = Status.ERROR
        )
        Mockito.`when`(fetchRepository.fetchCv()).thenReturn(Single.just(resource))
        val observer = mock<Observer<CvFetchResponse>>()
        cvFetchViewModel.fetchCv.observeForever(observer)
        verifyNoMoreInteractions(observer)
        cvFetchViewModel.fetchCv()
        Mockito.verify(observer).onChanged(any())
    }

    @Test
    fun fetchCv_Success() {
        val resource = CvFetchResponse(
            status = Status.SUCCESS
        )
        Mockito.`when`(fetchRepository.fetchCv()).thenReturn(Single.just(resource))
        val observer = mock<Observer<CvFetchResponse>>()
        cvFetchViewModel.fetchCv.observeForever(observer)
        verifyNoMoreInteractions(observer)
        cvFetchViewModel.fetchCv()
        Mockito.verify(observer).onChanged(any())
    }
}