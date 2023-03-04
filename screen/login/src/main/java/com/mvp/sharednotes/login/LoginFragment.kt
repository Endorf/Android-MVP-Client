package com.mvp.sharednotes.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mvp.sharednotes.login.databinding.FragmentLoginBinding
import com.mvp.sharednotes.login.view.Presenter
import com.mvp.sharednotes.login.view.PresenterImpl
import com.mvp.sharednotes.login.view.entity.UserCredentials
import com.mvp.sharednotes.login.view.entity.state.ErrorState
import com.mvp.sharednotes.login.view.entity.state.ProgressState

class LoginFragment : Fragment(), LoginView {

    private lateinit var presenter: Presenter
    private lateinit var inputMethodManager: InputMethodManager

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo: remove it
        presenter = PresenterImpl(this)
        inputMethodManager = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.progressState = ProgressState(false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener { initiateLogin() }

        binding.emailField.apply {
            addTextChangedListener { binding.emailInputLayout.error = null }

            setOnEditorActionListener { _, actionId, _ ->
                actionId == EditorInfo.IME_ACTION_DONE
                    .also { initiateLogin() }
            }
        }
    }

    private fun initiateLogin() {
        UserCredentials(
            binding.emailField.toString()
        ).let {
            presenter.login(it)
        }
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onLoadingStart() {
        binding.progressState = ProgressState(true)
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onLoadingFinish() {
        binding.progressState = ProgressState(false)
    }

    override fun userSignUpProcessing() {
        TODO("Not yet implemented")
    }

    override fun onSuccessfulLogin() {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable) {
        binding.errorState = ErrorState.create(e)
    }
}