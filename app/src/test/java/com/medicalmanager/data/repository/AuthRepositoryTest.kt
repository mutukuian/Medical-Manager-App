package com.medicalmanager.data.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.repository.AuthRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner



@RunWith(MockitoJUnitRunner::class)
class AuthRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var authRepository: AuthRepository


    @Before
    fun setup(){
        authRepository = AuthRepositoryImpl(firebaseAuth)
    }

    @Test
    fun loginUser_Success() = runBlocking {
        //mock firebase's signInWithEmailAndPassword
        val mockAuthResult = mock(AuthResult::class.java)
        `when`(firebaseAuth.signInWithEmailAndPassword("mutukui940@gmail.com","password123")).thenReturn(Tasks.forResult(mockAuthResult))

        //call the loginUser method
        val result = authRepository.loginUser("mutukui940@gmail.com","password123").first()

        //verify the result
        assert(result is Resource.Success)
        assertEquals((result as Resource.Success).data,mockAuthResult)
    }

    @Test
    fun loginUser_Failure() = runBlocking {
        //mock firebase's signInWithEmailAndPassword
        val errorMessage = "Login Failed"
        `when`(firebaseAuth.signInWithEmailAndPassword("mutukui940@gmail.com","invalid")).thenThrow(FirebaseAuthInvalidUserException(errorMessage,"Invalid Account"))

        //call the loginUser method
        val result = authRepository.loginUser("mutukui940@gmail.com","invalid").first()

        //verify the result
        assert(result is Resource.Error)
        assertEquals((result as Resource.Error).message,errorMessage)

    }
}