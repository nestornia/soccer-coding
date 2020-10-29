package com.example.soccercodding.ui.main.custom

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.soccercodding.databinding.SoccerCardBinding
import com.example.soccercodding.extensions.getDateWithServerTimeStamp
import com.example.soccercodding.extensions.show
import com.example.soccercodding.model.SoccerData
import com.google.android.material.card.MaterialCardView

class SoccerCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: SoccerCardBinding

    init {
        SoccerCardBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        ).also { binding = it }
    }

    fun loadSoccerData(data: SoccerData) = with(binding) {
        groupFixtures.show(data.type == UPCOMING)
        groupResults.show(data.type == FINAL)
        competitionName.text = data.competitionStage?.competition?.name
        venueNameAndDate.text = createVenueNameAndDate(data.venue?.name, data.date, data.state)
        postponed.show(data.state.isPostponed())
        homeTeamName.text = data.homeTeam?.name
        awayTeamName.text = data.awayTeam?.name
        homeTeamScore.text = data.score?.home?.toString()
        awayTeamScore.text = data.score?.away?.toString()
        dateNum.text = data.date?.getDateWithServerTimeStamp("d")
        dateShortDay.text = data.date?.getDateWithServerTimeStamp("EEE")
        when (data.score?.winner) {
            "home" -> {
                awayTeamScore.setTextColor(Color.BLACK)
                homeTeamScore.setTextColor(Color.BLUE)
            }
            "away" -> {
                awayTeamScore.setTextColor(Color.BLUE)
                homeTeamScore.setTextColor(Color.BLACK)
            }
            else -> {
                awayTeamScore.setTextColor(Color.BLACK)
                homeTeamScore.setTextColor(Color.BLACK)
            }
        }
    }

    private fun createVenueNameAndDate(
        name: String?,
        date: String?,
        state: String
    ): SpannableStringBuilder {
        val formattedDate = date?.getDateWithServerTimeStamp("MMM d, yyyy 'at' hh:mm")
        val formattedNameDate = "$name | $formattedDate"
        return SpannableStringBuilder(formattedNameDate).apply {
            if (state.isPostponed())
                setSpan(
                    ForegroundColorSpan(Color.RED),
                    indexOf('|') + 1,
                    length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
            else toString()

        }
    }

    private fun String.isPostponed() = this == "postponed"

    companion object {
        private const val UPCOMING = "FixtureUpcoming"
        private const val FINAL = "FixtureFinal"
    }
}