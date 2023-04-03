package com.youarelaunched.challenge.mock

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor

class VendorsRepositoryWithException: VendorsRepository {
    override suspend fun getVendors(): List<Vendor> {
        throw  CustomRuntimeException("Fake Exception")
    }
}

internal class CustomRuntimeException(
    message: String?,
    override val cause: Throwable? = null,
) : RuntimeException(message)