package com.example.letgo.data.entity

import java.io.Serializable

class Sales(
    var id: Int,
    var saleType: SaleType,
    var saleDesp: String,
    var saleImageList: List<String>,
    var saleBrandName: String,
    var saleCarModel: String,
    var saleCarPackage: String,
    var saleCarYear: String,
    var saleFuelType: String,
    var saleCarKm: String,
    var saleGearType: String,
    var saleMoneyText: String,
    var saleLocate: String,
    var saleStatus: String,
    var saleSaleDate: String
) : Serializable

enum class SaleType {
    CAR,
    PRODUCT
}

object SalesCreator {
    fun createCarSale(
        id: Int,
        description: String,
        imageList: List<String>,
        brandName: String,
        carModel: String,
        carPackage: String,
        carYear: String,
        fuelType: String,
        carKm: String,
        gearType: String,
        moneyText: String,
        locate: String,
        status: String,
        saleDate: String
    ): Sales {
        return Sales(id, SaleType.CAR, description, imageList, brandName, carModel, carPackage, carYear, fuelType, carKm, gearType, moneyText, locate, status, saleDate)
    }

    fun createProductSale(
        id: Int,
        description: String,
        imageList: List<String>,
        productName: String,
        moneyText: String,
        locate: String,
        status: String,
        saleDate: String
    ): Sales {
        return Sales(id, SaleType.PRODUCT, description, imageList, productName, "", "", "", "", "", "", moneyText, locate, status, saleDate)
    }
}
