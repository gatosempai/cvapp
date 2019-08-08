package dev.oscar.ruiz.cvapp.fetchcv.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.oscar.ruiz.cvapp.api.CVFetch
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import dev.oscar.ruiz.cvapp.utils.Status
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class FetchRepositoryTest {

    private lateinit var service: CVFetch
    private lateinit var mockWebServer: MockWebServer
    private lateinit var fetchRepository: FetchRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CVFetch::class.java)
        fetchRepository = FetchRepository(service)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchCv_success() {
        val testObserver = TestObserver<CvFetchResponse>()
        enqueueResponse("cv.json", statusCode = 200)
        fetchRepository.fetchCv().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)
        val values = testObserver.values()
        val response = values[0]
        assertThat(
            response.status, CoreMatchers.`is`(Status.SUCCESS)
        )
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap(), statusCode: Int) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("json/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setResponseCode(statusCode)
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}