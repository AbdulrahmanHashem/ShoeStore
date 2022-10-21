package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsScreenBinding

class InstructionsScreenFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsScreenBinding

    private lateinit var viewModel: LoginRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstructionsScreenBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

        binding.shoeList.setOnClickListener { view: View ->
            binding.root.findNavController().navigate(InstructionsScreenFragmentDirections.actionInstructionsScreenToShoeListFragment())
        }

        return binding.root
    }
}