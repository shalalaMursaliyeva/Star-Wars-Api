package com.example.starwars.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.PersonAdapter
import com.example.starwars.databinding.FragmentPeopleBinding
import com.example.starwars.model.data.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {
    private val viewModel: PeopleViewModel by viewModels()
    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private lateinit var maleAdapter: PersonAdapter
    private lateinit var femaleAdapter: PersonAdapter
    private lateinit var naAdapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        maleAdapter = PersonAdapter { person ->
            val action = PeopleFragmentDirections.actionPeopleFragmentToPersonDetailFragment(person)
            findNavController().navigate(action)
        }
        femaleAdapter = PersonAdapter { person ->
            val action = PeopleFragmentDirections.actionPeopleFragmentToPersonDetailFragment(person)
            findNavController().navigate(action)
        }
        naAdapter = PersonAdapter { person ->
            val action = PeopleFragmentDirections.actionPeopleFragmentToPersonDetailFragment(person)
            findNavController().navigate(action)
        }

        binding.maleRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = maleAdapter
        }

        binding.femaleRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = femaleAdapter
        }

        binding.naRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = naAdapter
        }

        viewModel.people.observe(viewLifecycleOwner) { people ->
            updateFilteredLists(people)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        return binding.root
    }

    private fun updateFilteredLists(people: List<Person>) {
        maleAdapter.submitList(people.filter { it.gender == "male" })
        femaleAdapter.submitList(people.filter { it.gender == "female" })
        naAdapter.submitList(people.filter { it.gender == "n/a" })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
