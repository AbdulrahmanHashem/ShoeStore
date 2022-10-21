package com.udacity.shoestore.shoelisting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.compose.ui.layout.AlignmentLine
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.login.LoginRegisterViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private lateinit var registryViewModel: LoginRegisterViewModel

    private lateinit var shoeListViewModel: ShoeListViewModel

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShoeListBinding.inflate(inflater, container, false)

        registryViewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

        shoeListViewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        navController = (requireActivity().supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment).navController

        shoeListViewModel.shoeList.observe(viewLifecycleOwner, Observer { newList ->
            for (Shoe in newList){
                val FloatingCardView = CardView(requireContext())
                val FloatingImageView = ImageView(requireContext())
                FloatingImageView.setImageResource(Shoe.images[1].toInt())
                val FloatingActionButton = FloatingActionButton(requireContext())
                FloatingActionButton.setImageResource(R.drawable.ic_add)

                FloatingActionButton.setOnClickListener { view: View ->
                    navController.graph.get(R.id.shoeDetailsFragment).label = Shoe.name
                    navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(Shoe))
                }

                FloatingCardView.addView(FloatingActionButton, 150, 150)
                FloatingCardView.addView(FloatingImageView, WRAP_CONTENT, WRAP_CONTENT)
                binding.LinearLayout.addView(FloatingCardView, WRAP_CONTENT, 600)
            }
        })

        if (registryViewModel.currentUser.value.isNullOrEmpty()){
            navController.navigate(R.id.loginFragment)
        }
        return binding.root
    }
}