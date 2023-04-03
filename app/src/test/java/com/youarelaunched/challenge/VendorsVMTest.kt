package com.youarelaunched.challenge

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.mock.*
import com.youarelaunched.challenge.mock.CustomRuntimeException
import com.youarelaunched.challenge.ui.screen.view.VendorsVM
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class VendorsVMTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun loadEmptyData()= runTest  {
        val repository : VendorsRepository = VendorsRepositoryEmpty()
        val viewModel = VendorsVM(repository)
        viewModel.getVendors()
        TestCase.assertEquals(viewModel.vendors.value.size, 0)
    }

    @Test
    fun loadNotEmptyData() = runTest {
        val repository : VendorsRepository = VendorsRepositoryWithList()
        val viewModel = VendorsVM(repository)
        viewModel.getVendors()
        TestCase.assertEquals(viewModel.vendors.value.size, 1)
    }

    @Test
    fun loadWithException() = runTest {
        val repository : VendorsRepository = VendorsRepositoryWithException()
        val viewModel = VendorsVM(repository)
        try {
            viewModel.getVendors()
        }catch (e: CustomRuntimeException){
            TestCase.assertEquals(e.message, "Expected message")
        }

    }
}