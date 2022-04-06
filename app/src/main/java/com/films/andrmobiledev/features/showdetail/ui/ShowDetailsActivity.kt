package com.films.andrmobiledev.features.showdetail.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.andrmobiledev.presentation.uidata.ShowDetailUI
import com.films.andrmobiledev.R
import com.films.andrmobiledev.databinding.ActivityShowDetailsBinding
import com.films.andrmobiledev.utils.images.setImageFromUrl
import com.films.andrmobiledev.utils.views.show

class ShowDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDetailsBinding
    private lateinit var show: ShowDetailUI

    companion object {
        private const val SHOW = "SHOW"

        fun getIntent(context: Context, show: ShowDetailUI): Intent {
            val intent = Intent(context, ShowDetailsActivity::class.java)
            intent.putExtra(SHOW, show)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
        show = intent.extras!!.getSerializable(SHOW) as ShowDetailUI
        populateViews()
    }

    private fun populateViews() {
        with(binding) {
            titleTv.text = show.name

            show.summary?.let {
                contentTv.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(it)
                }
            }

            show.rating?.let {
                ratingTv.text = getString(R.string.rating, show.rating.toString())
                ratingTv.show()
            }

            show.image?.let { showsImage.setImageFromUrl(it) }

            show.officialSite?.let {
                officialSiteTv.text = getString(R.string.official_site, it)
                officialSiteTv.show()
            }

            show.premiered?.let {
                premieredTv.text = getString(R.string.release_date, it)
                premieredTv.show()
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = show.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun setView() {
        setContentView(R.layout.activity_show_details)
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}