package dev.oscar.ruiz.cvapp.fetchcv.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.oscar.ruiz.cvapp.api.CVFetch
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class FetchRepositoryTest {

    private lateinit var service: CVFetch
    private lateinit var mockWebServer: MockWebServer
    private lateinit var fetchRepository: FetchRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
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
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchCv() {
        val testObserver = TestObserver<CvFetchResponse>()
        enqueueResponse("cv.json", statusCode = 200)
        fetchRepository.fetchCv().subscribe(testObserver)
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