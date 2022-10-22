package com.udacity.shoestore.shoelisting

import android.media.ImageReader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding

class AddShoeFragment : Fragment() {

    lateinit var binding: FragmentAddShoeBinding

    lateinit var viewModel: ShoeListViewModel

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddShoeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()

        navController = (requireActivity().supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment).navController

        binding.addShoe.setOnClickListener{
            if (viewModel.addShoe()){
                navController.navigateUp()
            } else {
                Toast.makeText(requireContext(), "Enter Size Correctly", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancel.setOnClickListener{navController.navigateUp()}
        return binding.root
    }

}