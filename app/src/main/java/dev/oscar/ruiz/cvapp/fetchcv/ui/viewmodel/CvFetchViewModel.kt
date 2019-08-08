package dev.oscar.ruiz.cvapp.fetchcv.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.oscar.ruiz.cvapp.BuildConfig
import dev.oscar.ruiz.cvapp.fetchcv.data.FetchRepository
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.ApiError
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import dev.oscar.ruiz.cvapp.utils.Status
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class CvFetchViewModel
@Inject constructor(
    private val fetchRepository: FetchRepository
) : ViewModel() {

    private val fetchResult = MutableLiveData<CvFetchResponse>()
    val fetchCv: LiveData<CvFetchResponse> get() = fetchResult
    private lateinit var fetchDisposable: Disposable

    fun fetchCv() {
        fetchDisposable = fetchRepository.fetchCv()
            .subscribeWith(
                object : DisposableSingleObserver<CvFetchResponse>() {
                    override fun onSuccess(t: CvFetchResponse?) {
                        fetchResult.postValue(
                            t
                        )
                    }

                    override fun onError(e: Throwable?) {
                        fetchResult.postValue(
                            CvFetchResponse(
                                status = Status.ERROR,
                                apiError = ApiError(e?.message)
                            )
                        )
                        if (BuildConfig.DEBUG) {
                            Log.d("Error", e?.message)
                            e?.printStackTrace()
                        }
                    }
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        fetchDisposable.dispose()
    }
}