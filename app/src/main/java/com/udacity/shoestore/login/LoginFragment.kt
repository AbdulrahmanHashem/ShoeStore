package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

        binding.loginRegister.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNewLoginFragment())
        }

        binding.loginLogin.setOnClickListener { view: View ->
            if (viewModel.users.get(binding.loginEnterEmail.text.toString()) != null){
                if (viewModel.users.get(binding.loginEnterEmail.text.toString()) == binding.loginEnterPassword.text.toString()){
                    view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeScreen())
                    viewModel.login(binding.loginEnterEmail.text.toString(), binding.loginEnterPassword.text.toString())
                }
            } else {
                Toast.makeText(context, "Wrong Email or Password", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.logout()
        return binding.root
    }
}