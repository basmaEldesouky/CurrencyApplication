package com.example.presentation.currencyRates.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.RatesX
import com.example.presentation.databinding.ItemHistoricalDataBinding
import kotlin.math.round

class HistoricalDataViewHolder(private val itemBinding: ItemHistoricalDataBinding): RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var exchangeRateMap: Map<String, Double?>
    fun bind(rates: RatesX, fromCurrency: String, toCurrency: String) {
        exchangeRateMap = mapOf(
            "AED" to rates.AED, "AFN" to rates.AFN, "ALL" to rates.ALL, "AMD" to rates.AMD, "ANG" to rates.ANG, "AOA" to rates.AOA,
            "ARS" to rates.ARS, "AUD" to rates.AUD, "AWG" to rates.AWG, "AZN" to rates.AZN, "BAM" to rates.BAM, "BBD" to rates.BBD,
            "BDT" to rates.BDT, "BGN" to rates.BGN, "BHD" to rates.BHD, "BIF" to rates.BIF, "BMD" to rates.BMD, "BND" to rates.BND,
            "BOB" to rates.BOB, "BRL" to rates.BRL, "BSD" to rates.BSD, "BTC" to rates.BTC, "BTN" to rates.BTN, "BWP" to rates.BWP,
            "BYN" to rates.BYN, "BYR" to rates.BYR, "BZD" to rates.BZD, "CAD" to rates.CAD, "CDF" to rates.CDF, "CHF" to rates.CHF,
            "CLF" to rates.CLF, "CLP" to rates.CLP, "CNY" to rates.CNY, "COP" to rates.COP, "CRC" to rates.CRC, "CUC" to rates.CUC,
            "CUP" to rates.CUP, "CVE" to rates.CVE, "CZK" to rates.CZK, "DJF" to rates.DJF, "DKK" to rates.DKK, "DOP" to rates.DOP,
            "DZD" to rates.DZD, "EGP" to rates.EGP, "ERN" to rates.ERN, "ETB" to rates.ETB, "EUR" to rates.EUR, "FJD" to rates.FJD,
            "FKP" to rates.FKP, "GBP" to rates.GBP, "GEL" to rates.GEL, "GGP" to rates.GGP, "GHS" to rates.GHS, "GIP" to rates.GIP,
            "GMD" to rates.GMD, "GNF" to rates.GNF, "GTQ" to rates.GTQ, "GYD" to rates.GYD, "HKD" to rates.HKD, "HNL" to rates.HNL,
            "HRK" to rates.HRK, "HTG" to rates.HTG, "HUF" to rates.HUF, "IDR" to rates.IDR, "ILS" to rates.ILS, "IMP" to rates.IMP,
            "INR" to rates.INR, "IQD" to rates.IQD, "IRR" to rates.IRR, "ISK" to rates.ISK, "JEP" to rates.JEP, "JMD" to rates.JMD,
            "JOD" to rates.JOD, "JPY" to rates.JPY, "KES" to rates.KES, "KGS" to rates.KGS, "KHR" to rates.KHR, "KMF" to rates.KMF,
            "KPW" to rates.KPW, "KRW" to rates.KRW, "KWD" to rates.KWD, "KYD" to rates.KYD, "KZT" to rates.KZT, "LAK" to rates.LAK,
            "LBP" to rates.LBP, "LKR" to rates.LKR, "LRD" to rates.LRD, "LSL" to rates.LSL, "LTL" to rates.LTL, "LVL" to rates.LVL,
            "LYD" to rates.LYD, "MAD" to rates.MAD, "MDL" to rates.MDL, "MGA" to rates.MGA, "MKD" to rates.MKD, "MMK" to rates.MMK,
            "MNT" to rates.MNT, "MOP" to rates.MOP, "MRO" to rates.MRO, "MUR" to rates.MUR, "MVR" to rates.MVR, "MWK" to rates.MWK,
            "MXN" to rates.MXN, "MYR" to rates.MYR, "MZN" to rates.MZN, "NAD" to rates.NAD, "NGN" to rates.NGN, "NIO" to rates.NIO,
            "NOK" to rates.NOK, "NPR" to rates.NPR, "NZD" to rates.NZD, "OMR" to rates.OMR, "PAB" to rates.PAB, "PEN" to rates.PEN,
            "PGK" to rates.PGK, "PHP" to rates.PHP, "PKR" to rates.PKR, "PLN" to rates.PLN, "PYG" to rates.PYG, "QAR" to rates.QAR,
            "RON" to rates.RON, "RSD" to rates.RSD, "RUB" to rates.RUB, "RWF" to rates.RWF, "SAR" to rates.SAR, "SBD" to rates.SBD,
            "SCR" to rates.SCR, "SDG" to rates.SDG, "SEK" to rates.SEK, "SGD" to rates.SGD, "SHP" to rates.SHP, "SLE" to rates.SLE,
            "SLL" to rates.SLL, "SOS" to rates.SOS, "SRD" to rates.SRD, "SSP" to rates.SSP, "STD" to rates.STD, "SVC" to rates.SVC,
            "SYP" to rates.SYP, "SZL" to rates.SZL, "THB" to rates.THB, "TJS" to rates.TJS, "TMT" to rates.TMT, "TND" to rates.TND,
            "TOP" to rates.TOP, "TRY" to rates.TRY, "TTD" to rates.TTD, "TWD" to rates.TWD, "TZS" to rates.TZS, "UAH" to rates.UAH,
            "UGX" to rates.UGX, "USD" to rates.USD, "UYU" to rates.UYU, "UZS" to rates.UZS, "VEF" to rates.VEF, "VES" to rates.VES,
            "VND" to rates.VND, "VUV" to rates.VUV, "WST" to rates.WST, "XAF" to rates.XAF, "XAG" to rates.XAG, "XAU" to rates.XAU,
            "XCD" to rates.XCD, "XDR" to rates.XDR, "XOF" to rates.XOF, "XPF" to rates.XPF, "YER" to rates.YER, "ZAR" to rates.ZAR,
            "ZMK" to rates.ZMK, "ZMW" to rates.ZMW, "ZWL" to rates.ZWL
        )

        exchangeRateMap.forEach { (currencyCode, exchangeRate) ->
            if(exchangeRate != null) {
                val firstRate = exchangeRateMap[fromCurrency]
                val secondRate = exchangeRateMap[toCurrency]
                val convertedCurrency = firstRate?.let { 1 / it.times(secondRate ?: 1.0) }
                val formattedCurrency = round(convertedCurrency!!.toFloat() * 100) / 100

                itemBinding.tvCurrencySymbol.text = currencyCode
                itemBinding.tvCurrencyRate.text = formattedCurrency.toString()
            }
        }
    }
}