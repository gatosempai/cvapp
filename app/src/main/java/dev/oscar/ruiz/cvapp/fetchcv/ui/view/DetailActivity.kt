package dev.oscar.ruiz.cvapp.fetchcv.ui.view

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import dev.oscar.ruiz.cvapp.R
import dev.oscar.ruiz.cvapp.databinding.ActivityDetailBinding
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.*
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.LandingActivity.Companion.EXTRA_CV_DATA
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.adapter.EducationDataAdapter
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.adapter.ProfessionalHistoryDataAdapter
import dev.oscar.ruiz.cvapp.utils.*
import kotlin.math.abs

class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val imageUrl = "https://images.all-free-download.com/images/graphiclarge/harry_potter_icon_6825007.jpg"
    private val percentToShowTitleAtToolbar = 0.9f
    private val percentToHideTitleDetails = 0.3f
    private val alphaAnimDuration = 200

    lateinit var activityDetailBinding: ActivityDetailBinding
    lateinit var professionalHistoryDataAdapter: ProfessionalHistoryDataAdapter
    lateinit var educationDataAdapter: EducationDataAdapter
    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        if (intent.getParcelableExtra<CvData>(EXTRA_CV_DATA) != null) initView(intent.getParcelableExtra(EXTRA_CV_DATA))
        else finishDetail()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, offset: Int) {
        val maxScroll = activityDetailBinding.aplDetail.totalScrollRange
        val percentage = abs(offset).toFloat() / maxScroll.toFloat()
        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    private fun initView(data: CvData) {
        val name = formatName(data.personalInformation.name, data.personalInformation.lastName)
        activityDetailBinding.imageUrl = imageUrl
        activityDetailBinding.tvDetailToolbarName.text = name
        activityDetailBinding.tvDetailName.text = name
        activityDetailBinding.tvDetailJob.text = data.professionalInformation.title
        initPersonalData(data.personalInformation)
        initProfessionalData(data.professionalInformation)
        initEducationData(data.education)
        initControlData(data.control)
        activityDetailBinding.aplDetail.addOnOffsetChangedListener(this)
        startAlphaAnimation(activityDetailBinding.tvDetailToolbarName, 0, View.INVISIBLE)
    }

    private fun initPersonalData(personal: PersonalInformation) {
        activityDetailBinding.tvDetailPersonalAge.text = formatAge(personal.age)
        activityDetailBinding.tvDetailPersonalEmail.text = formatMail(personal.email)
        activityDetailBinding.tvDetailPersonalPhone.text = formatPhone(personal.phone)
        activityDetailBinding.tvDetailPersonalCountry.text = formatCountry(personal.country)
    }

    private fun initProfessionalData(professional: ProfessionalInformation) {
        activityDetailBinding.tvDetailProfessionalSummary.text = professional.summary
        val recyclerView = activityDetailBinding.rvDetail
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        professionalHistoryDataAdapter = ProfessionalHistoryDataAdapter(professional.history)
        recyclerView.adapter = professionalHistoryDataAdapter
    }

    private fun initEducationData(education: List<Education>) {
        val recyclerView = activityDetailBinding.rvDetailEducation
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        educationDataAdapter = EducationDataAdapter(education)
        recyclerView.adapter = educationDataAdapter
    }

    private fun initControlData(control: Control) {
        activityDetailBinding.tvDetailControlIsLookingJob.text = formatLookingJob(control.isLookingJob)
        activityDetailBinding.tvDetailControlLastUpdate.text = formatLastUpdate(control.lastUpdate)
    }

    private fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation = if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f)
        else AlphaAnimation(1f, 0f)
        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= percentToShowTitleAtToolbar) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(activityDetailBinding.tvDetailToolbarName, alphaAnimDuration.toLong(), View.VISIBLE)
                mIsTheTitleVisible = true
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(
                    activityDetailBinding.tvDetailToolbarName,
                    alphaAnimDuration.toLong(),
                    View.INVISIBLE
                )
                mIsTheTitleVisible = false
            }
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= percentToHideTitleDetails) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(activityDetailBinding.llDetailTitle, alphaAnimDuration.toLong(), View.INVISIBLE)
                mIsTheTitleContainerVisible = false
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(activityDetailBinding.llDetailTitle, alphaAnimDuration.toLong(), View.VISIBLE)
                mIsTheTitleContainerVisible = true
            }
        }
    }

    private fun finishDetail() {
        Toast.makeText(this, "Something is wrong. Try again", Toast.LENGTH_LONG).show()
        finish()
    }
}
