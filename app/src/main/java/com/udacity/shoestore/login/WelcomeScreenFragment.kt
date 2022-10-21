package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

        binding.instructions.setOnClickListener { view: View ->
            view.findNavController().navigate(WelcomeScreenFragmentDirections.actionWelcomeScreenToInstructionsScreen())
        }

        return binding.root
    }

}