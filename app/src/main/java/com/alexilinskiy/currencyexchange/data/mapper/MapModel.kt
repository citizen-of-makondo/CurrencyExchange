package com.alexilinskiy.currencyexchange.data.mapper

import com.alexilinskiy.currencyexchange.data.Currencies
import com.alexilinskiy.currencyexchange.data.CurrenciesModel
import com.alexilinskiy.currencyexchange.data.Rates
import com.alexilinskiy.currencyexchange.data.RatesModel
import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyModelDB

object MapModel {

    fun mapCurrenciesToModel(currencies: Currencies): List<CurrenciesModel> {
        return listOf(
            CurrenciesModel(
                name = currencies.AUD.name,
                shortName = currencies.AUD::class.simpleName.toString(),
                symbol = currencies.AUD.symbol
            ),
            CurrenciesModel(
                name = currencies.BGN.name,
                shortName = currencies.BGN::class.simpleName.toString(),
                symbol = currencies.BGN.symbol,
            ),
            CurrenciesModel(
                name = currencies.BRL.name,
                shortName = currencies.BRL::class.simpleName.toString(),
                symbol = currencies.BRL.symbol,
            ),
            CurrenciesModel(
                name = currencies.CAD.name,
                shortName = currencies.CAD::class.simpleName.toString(),
                symbol = currencies.BRL.symbol,
            ),
            CurrenciesModel(
                name = currencies.CHF.name,
                shortName = currencies.CHF::class.simpleName.toString(),
                symbol = currencies.CHF.symbol,
            ),
            CurrenciesModel(
                name = currencies.CNY.name,
                shortName = currencies.CNY::class.simpleName.toString(),
                symbol = currencies.CNY.symbol,
            ),
            CurrenciesModel(
                name = currencies.CZK.name,
                shortName = currencies.CZK::class.simpleName.toString(),
                symbol = currencies.CZK.symbol,
            ),
            CurrenciesModel(
                name = currencies.DKK.name,
                shortName = currencies.DKK::class.simpleName.toString(),
                symbol = currencies.DKK.symbol,
            ),
            CurrenciesModel(
                name = currencies.EUR.name,
                shortName = currencies.EUR::class.simpleName.toString(),
                symbol = currencies.EUR.symbol,
            ),
            CurrenciesModel(
                name = currencies.GBP.name,
                shortName = currencies.GBP::class.simpleName.toString(),
                symbol = currencies.GBP.symbol,
            ),
            CurrenciesModel(
                name = currencies.HKD.name,
                shortName = currencies.HKD::class.simpleName.toString(),
                symbol = currencies.HKD.symbol,
            ),
            CurrenciesModel(
                name = currencies.HRK.name,
                shortName = currencies.HRK::class.simpleName.toString(),
                symbol = currencies.HRK.symbol,
            ),
            CurrenciesModel(
                name = currencies.HUF.name,
                shortName = currencies.HUF::class.simpleName.toString(),
                symbol = currencies.HUF.symbol,
            ),
            CurrenciesModel(
                name = currencies.IDR.name,
                shortName = currencies.IDR::class.simpleName.toString(),
                symbol = currencies.IDR.symbol,
            ),
            CurrenciesModel(
                name = currencies.ILS.name,
                shortName = currencies.ILS::class.simpleName.toString(),
                symbol = currencies.ILS.symbol,
            ),
            CurrenciesModel(
                name = currencies.INR.name,
                shortName = currencies.INR::class.simpleName.toString(),
                symbol = currencies.INR.symbol,
            ),
            CurrenciesModel(
                name = currencies.ISK.name,
                shortName = currencies.ISK::class.simpleName.toString(),
                symbol = currencies.ISK.symbol,
            ),
            CurrenciesModel(
                name = currencies.JPY.name,
                shortName = currencies.JPY::class.simpleName.toString(),
                symbol = currencies.JPY.symbol,
            ),
            CurrenciesModel(
                name = currencies.KRW.name,
                shortName = currencies.KRW::class.simpleName.toString(),
                symbol = currencies.KRW.symbol,
            ),
            CurrenciesModel(
                name = currencies.MXN.name,
                shortName = currencies.MXN::class.simpleName.toString(),
                symbol = currencies.MXN.symbol,
            ),
            CurrenciesModel(
                name = currencies.MYR.name,
                shortName = currencies.MYR::class.simpleName.toString(),
                symbol = currencies.MYR.symbol,
            ),
            CurrenciesModel(
                name = currencies.NOK.name,
                shortName = currencies.NOK::class.simpleName.toString(),
                symbol = currencies.NOK.symbol,
            ),
            CurrenciesModel(
                name = currencies.NZD.name,
                shortName = currencies.NZD::class.simpleName.toString(),
                symbol = currencies.NZD.symbol,
            ),
            CurrenciesModel(
                name = currencies.PHP.name,
                shortName = currencies.PHP::class.simpleName.toString(),
                symbol = currencies.PHP.symbol,
            ),
            CurrenciesModel(
                name = currencies.PLN.name,
                shortName = currencies.PLN::class.simpleName.toString(),
                symbol = currencies.PLN.symbol,
            ),
            CurrenciesModel(
                name = currencies.RON.name,
                shortName = currencies.RON::class.simpleName.toString(),
                symbol = currencies.RON.symbol,
            ),
            CurrenciesModel(
                name = currencies.RUB.name,
                shortName = currencies.RUB::class.simpleName.toString(),
                symbol = currencies.RUB.symbol,
            ),
            CurrenciesModel(
                name = currencies.SEK.name,
                shortName = currencies.SEK::class.simpleName.toString(),
                symbol = currencies.SEK.symbol,
            ),
            CurrenciesModel(
                name = currencies.SGD.name,
                shortName = currencies.SGD::class.simpleName.toString(),
                symbol = currencies.SGD.symbol,
            ),
            CurrenciesModel(
                name = currencies.THB.name,
                shortName = currencies.THB::class.simpleName.toString(),
                symbol = currencies.THB.symbol,
            ),
            CurrenciesModel(
                name = currencies.TRY.name,
                shortName = currencies.TRY::class.simpleName.toString(),
                symbol = currencies.TRY.symbol,
            ),
            CurrenciesModel(
                name = currencies.USD.name,
                shortName = currencies.USD::class.simpleName.toString(),
                symbol = currencies.USD.symbol,
            ),
            CurrenciesModel(
                name = currencies.ZAR.name,
                shortName = currencies.ZAR::class.simpleName.toString(),
                symbol = currencies.ZAR.symbol,
            )
        )
    }

    fun mapCurrenciesToSpinner(list: List<CurrencyModelDB>): List<String> {
        val result = mutableListOf<String>()
        for (item in list) {
            result.add("${item.symbol} ${item.name}")
        }
        return result
    }

    fun mapRatesToModel(rates: Rates, currenciesList: List<CurrencyModelDB>): List<RatesModel> {
        val result = mutableListOf<RatesModel>()

        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::AUD.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::AUD.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::AUD.name }.symbol,
                count = rates.rates.AUD,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::BGN.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::BGN.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::BGN.name }.symbol,
                count = rates.rates.BGN,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::BRL.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::BRL.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::BRL.name }.symbol,
                count = rates.rates.BRL,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::CAD.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::CAD.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::CAD.name }.symbol,
                count = rates.rates.CAD,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::CHF.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::CHF.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::CHF.name }.symbol,
                count = rates.rates.CHF,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::CNY.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::CNY.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::CNY.name }.symbol,
                count = rates.rates.CNY,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::CZK.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::CZK.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::CZK.name }.symbol,
                count = rates.rates.CZK,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::DKK.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::DKK.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::DKK.name }.symbol,
                count = rates.rates.DKK,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::EUR.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::EUR.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::EUR.name }.symbol,
                count = rates.rates.EUR,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::GBP.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::GBP.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::GBP.name }.symbol,
                count = rates.rates.GBP,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::HKD.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::HKD.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::HKD.name }.symbol,
                count = rates.rates.HKD,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::HRK.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::HRK.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::HRK.name }.symbol,
                count = rates.rates.HRK,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::HUF.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::HUF.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::HUF.name }.symbol,
                count = rates.rates.HUF,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::IDR.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::IDR.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::IDR.name }.symbol,
                count = rates.rates.IDR,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::ILS.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::ILS.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::ILS.name }.symbol,
                count = rates.rates.ILS,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::INR.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::INR.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::INR.name }.symbol,
                count = rates.rates.INR,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::ISK.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::ISK.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::ISK.name }.symbol,
                count = rates.rates.ISK,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::JPY.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::JPY.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::JPY.name }.symbol,
                count = rates.rates.JPY,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::KRW.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::KRW.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::KRW.name }.symbol,
                count = rates.rates.KRW,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::MXN.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::MXN.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::MXN.name }.symbol,
                count = rates.rates.MXN,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::MYR.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::MYR.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::MYR.name }.symbol,
                count = rates.rates.MYR,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::NOK.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::NOK.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::NOK.name }.symbol,
                count = rates.rates.NOK,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::NZD.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::NZD.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::NZD.name }.symbol,
                count = rates.rates.NZD,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::PHP.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::PHP.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::PHP.name }.symbol,
                count = rates.rates.PHP,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::PLN.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::PLN.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::PLN.name }.symbol,
                count = rates.rates.PLN,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::RON.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::RON.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::RON.name }.symbol,
                count = rates.rates.RON,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::RUB.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::RUB.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::RUB.name }.symbol,
                count = rates.rates.RUB,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::SEK.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::SEK.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::SEK.name }.symbol,
                count = rates.rates.SEK,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::SGD.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::SGD.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::SGD.name }.symbol,
                count = rates.rates.SGD,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::THB.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::THB.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::THB.name }.symbol,
                count = rates.rates.THB,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::TRY.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::TRY.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::TRY.name }.symbol,
                count = rates.rates.TRY,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::USD.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::USD.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::USD.name }.symbol,
                count = rates.rates.USD,
            )
        )
        result.add(
            RatesModel(
                name = currenciesList.first { it.shortName == rates.rates::ZAR.name }.name,
                shortName = currenciesList.first { it.shortName == rates.rates::ZAR.name }.shortName,
                symbol = currenciesList.first { it.shortName == rates.rates::ZAR.name }.symbol,
                count = rates.rates.ZAR,
            )
        )

        return result.filter { it.count != 0.0 }.map {
            RatesModel(
                name = it.name,
                shortName = it.shortName,
                symbol = it.symbol,
                count = (Math.round(it.count * 1000.0) / 1000.0),
                isFavotite = it.isFavotite
            )
        }
    }
}
