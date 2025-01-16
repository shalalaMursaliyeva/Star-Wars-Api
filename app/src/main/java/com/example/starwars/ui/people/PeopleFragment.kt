package com.example.starwars.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.adapter.PersonAdapter
import com.example.starwars.databinding.FragmentPeopleBinding
import com.example.starwars.model.data.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private val viewModel: PeopleViewModel by viewModels()
    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding

    private val maleAdapter by lazy {
        PersonAdapter { person -> navigateToPersonDetail(person) }
    }
    private val femaleAdapter by lazy {
        PersonAdapter { person -> navigateToPersonDetail(person) }
    }
    private val naAdapter by lazy {
        PersonAdapter { person -> navigateToPersonDetail(person) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        _binding?.let {
            setupRecyclerViews(it)
            observeViewModel(it)
        }
        return _binding?.root
    }

    private fun setupRecyclerViews(binding: FragmentPeopleBinding) {
        binding.maleRecyclerView.setupAdapter(maleAdapter)
        binding.femaleRecyclerView.setupAdapter(femaleAdapter)
        binding.naRecyclerView.setupAdapter(naAdapter)
    }

    private fun RecyclerView.setupAdapter(adapter: PersonAdapter) {
        layoutManager = LinearLayoutManager(requireContext())
        this.adapter = adapter
    }

    private fun observeViewModel(binding: FragmentPeopleBinding) {
        viewModel.people.observe(viewLifecycleOwner) { people ->
            updateFilteredLists(people)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun updateFilteredLists(people: List<Person>) {
        maleAdapter.submitList(people.filter { it.gender == "male" })
        femaleAdapter.submitList(people.filter { it.gender == "female" })
        naAdapter.submitList(people.filter { it.gender == "n/a" })
    }

    private fun navigateToPersonDetail(person: Person) {
        val action = PeopleFragmentDirections.actionPeopleFragmentToPersonDetailFragment(person)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
