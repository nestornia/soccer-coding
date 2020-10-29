package com.example.soccercodding.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccercodding.databinding.FragmentSoccerDataBinding
import com.example.soccercodding.extensions.show
import com.example.soccercodding.model.SoccerData
import com.example.soccercodding.ui.main.adapter.SoccerDataAdapter
import com.example.soccercodding.util.ApiState
import com.example.soccercodding.util.FragmentType
import com.example.soccercodding.viewmodel.MainViewModel

class SoccerDataFragment : Fragment() {

    private lateinit var binding: FragmentSoccerDataBinding
    private lateinit var fragmentType: FragmentType
    private val vm by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { fragmentType = it.getSerializable(PARAM_FRAGMENT_TYPE) as FragmentType }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSoccerDataBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupObserver()
    }

    private fun setupList() = with(binding.rvList) {
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
        adapter = SoccerDataAdapter(::soccerDataClicked)
    }

    private fun setupObserver() = with(vm) {
        when (fragmentType) {
            FragmentType.FIXTURE -> fixtures.observe(viewLifecycleOwner, ::parseState)
            FragmentType.RESULT -> results.observe(viewLifecycleOwner, ::parseState)
        }
    }

    private fun parseState(state: ApiState<List<SoccerData>>) = when (state) {
        is ApiState.Loading -> binding.loader.show()
        is ApiState.Success -> binding.loader.show(false).also {
            (binding.rvList.adapter as SoccerDataAdapter).loadData(state.data)
        }
        is ApiState.Error -> binding.loader.show(false).also {
            AlertDialog.Builder(requireContext()).setTitle("Error")
                .setMessage(state.exception.message).show()
        }
    }

    private fun soccerDataClicked(data: SoccerData) {
        // NO-OP
    }

    companion object {
        private const val PARAM_FRAGMENT_TYPE = "fragmentType"

        fun newInstance(type: FragmentType) = SoccerDataFragment().apply {
            arguments = Bundle().apply { putSerializable(PARAM_FRAGMENT_TYPE, type) }
        }

    }
}