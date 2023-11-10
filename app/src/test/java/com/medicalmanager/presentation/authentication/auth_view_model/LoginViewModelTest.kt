package com.medicalmanager.presentation.authentication.auth_view_model

import com.google.firebase.auth.AuthResult
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner





@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val mockAuthRepository = mock(AuthRepository::class.java)

    @Before
    fun setUp() {
        viewModel = LoginViewModel(mockAuthRepository)
    }

    @Test
    fun `loginUser() should succeed when the user credentials are correct`() {
        // Given
        val email = "test@example.com"
        val password = "password"
        `when`(mockAuthRepository.loginUser(email, password)).thenReturn(flowOf(Resource.Success<AuthResult>("Login successful")))

        // When
        viewModel.loginUser(email, password)

        // Then
        val loginState = viewModel.loginState.value
        assert(loginState.isLoading)
        assert(loginState.data == "Login successful")
        assert(loginState.error == null)
    }

    @Test
    fun `loginUser() should fail when the user credentials are incorrect`() {
        // Given
        val email = "test@example.com"
        val password = "invalid-password"
        `when`(mockAuthRepository.loginUser(email, password)).thenReturn(flowOf(Resource.Error<AuthResult>("Invalid credentials")))

        // When
        viewModel.loginUser(email, password)

        // Then
        val loginState = viewModel.loginState.value
        assert(loginState.isLoading)
        assert(loginState.data == null)
        assert(loginState.error == "Invalid credentials")
    }
}
