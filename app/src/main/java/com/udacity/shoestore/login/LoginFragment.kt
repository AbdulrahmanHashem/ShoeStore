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

        with(binding){

            with(viewModel){

                loginRegister.setOnClickListener { view: View ->
                    view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNewLoginFragment())
                }

                loginLogin.setOnClickListener { view: View ->
                    if (users.get(loginEnterEmail.text.toString()) != null){
                        if (users.get(loginEnterEmail.text.toString()) == loginEnterPassword.text.toString()){
                            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeScreen())
                            login(loginEnterEmail.text.toString(), loginEnterPassword.text.toString())
                        }
                    } else {
                        Toast.makeText(context, "Wrong Email or Password", Toast.LENGTH_SHORT).show()
                    }
                }
                logout()

            }

        }

        return binding.root
    }
}