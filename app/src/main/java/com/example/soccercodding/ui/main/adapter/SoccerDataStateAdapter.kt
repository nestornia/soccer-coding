package com.example.soccercodding.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.soccercodding.ui.main.SoccerDataFragment
import com.example.soccercodding.util.FragmentType

class SoccerDataStateAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = SoccerDataFragment.newInstance(
        if (position == 0) FragmentType.FIXTURE else FragmentType.RESULT
    )

}