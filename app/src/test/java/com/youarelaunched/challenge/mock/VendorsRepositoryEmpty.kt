package com.youarelaunched.challenge.mock

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor

class VendorsRepositoryEmpty: VendorsRepository {
    override suspend fun getVendors(): List<Vendor> {
        return listOf()
    }
}