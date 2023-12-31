package com.example.presentation.currencyRates

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.common.Constants
import com.example.common.utils.NetworkUtils
import com.example.common.utils.viewBinding
import com.example.domain.entity.RatesX
import com.example.presentation.R
import com.example.presentation.currencyRates.adapters.HistoricalDataAdapter
import com.example.presentation.currencyRates.adapters.LatestRatesAdapter
import com.example.presentation.databinding.FragmentCurrencyRatesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CurrencyRatesFragment(): Fragment(R.layout.fragment_currency_rates) {

    private val binding by viewBinding(FragmentCurrencyRatesBinding::bind)

    private val viewModel: CurrencyRatesViewModel by viewModels()

    lateinit var  fromCurrency: String

    lateinit var  toCurrency: String

    lateinit var popularCurrencies: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fromCurrency = arguments?.getString("fromCurrency").toString()
        toCurrency = arguments?.getString("toCurrency").toString()
        popularCurrencies = "$toCurrency,XCD,XDR,XOF,XPF,YER,ZAR,ZMK,ZMW,ZWL"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = "$fromCurrency to $toCurrency"

        binding.errorLayout.btnRetry.setOnClickListener{
            handleUiWhenRetrying()
        }

        initObservers()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        fireEndpoints()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fireEndpoints(){
        if(fromCurrency.isNotEmpty() && toCurrency.isNotEmpty()){
            val today = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            for (i in 0 until 3) {
                val date = formatter.format(today.minusDays(i.toLong()))
                viewModel.getHistoricalData(date, Constants.base, fromCurrency, toCurrency, Constants.format)
            }
            viewModel.getLatestRates(Constants.base, fromCurrency, popularCurrencies, Constants.format)
        }
    }


    private fun initObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.historicalData.collect { result ->
                    if(result != null && result.success) {
                        binding.mainLayout.isVisible = true
                        binding.errorLayout.root.isVisible = false
                        val historicalDataAdapter = HistoricalDataAdapter(fromCurrency, popularCurrencies)
                        historicalDataAdapter.submitList(formatRatesResponse(result.rates))
                        binding.rvHistoricalData.adapter = historicalDataAdapter
                    }else{
                        binding.errorLayout.root.isVisible = true
                        binding.mainLayout.isVisible = false
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.latestRates.collect { result ->
                    if(result != null && result.success) {
                        binding.mainLayout.isVisible = true
                        binding.errorLayout.root.isVisible = false
                        val latestRatesAdapter = LatestRatesAdapter(fromCurrency, popularCurrencies)
                        latestRatesAdapter.submitList(formatRatesResponse(result.rates))
                        binding.rvLatestRates.adapter = latestRatesAdapter
                    }else{
                        binding.errorLayout.root.isVisible = true
                        binding.mainLayout.isVisible = false
                    }
                }
            }
        }
    }

    private fun handleUiWhenRetrying(){
        if (!NetworkUtils.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
        }else {
            binding.errorLayout.root.isVisible = false
            binding.mainLayout.isVisible = true
        }
    }

    private fun formatRatesResponse(rates: RatesX): MutableList<RatesX>{
        val ratesXList = mutableListOf<RatesX>()

        rates?.let { rates ->
            ratesXList.add(
                RatesX(
                    AED = rates.AED, AFN = rates.AFN, ALL = rates.ALL, AMD = rates.AMD, ANG = rates.ANG, AOA = rates.AOA,
                    ARS = rates.ARS, AUD = rates.AUD, AWG = rates.AWG, AZN = rates.AZN, BAM = rates.BAM, BBD = rates.BBD,
                    BDT = rates.BDT, BGN = rates.BGN, BHD = rates.BHD, BIF = rates.BIF, BMD = rates.BMD, BND = rates.BND,
                    BOB = rates.BOB, BRL = rates.BRL, BSD = rates.BSD, BTC = rates.BTC, BTN = rates.BTN, BWP = rates.BWP,
                    BYN = rates.BYN, BYR = rates.BYR, BZD = rates.BZD, CAD = rates.CAD, CDF = rates.CDF, CHF = rates.CHF,
                    CLF = rates.CLF, CLP = rates.CLP, CNY = rates.CNY, COP = rates.COP, CRC = rates.CRC, CUC = rates.CUC,
                    CUP = rates.CUP, CVE = rates.CVE, CZK = rates.CZK, DJF = rates.DJF, DKK = rates.DKK, DOP = rates.DOP,
                    DZD = rates.DZD, EGP = rates.EGP, ERN = rates.ERN, ETB = rates.ETB, EUR = rates.EUR, FJD = rates.FJD,
                    FKP = rates.FKP, GBP = rates.GBP, GEL = rates.GEL, GGP = rates.GGP, GHS = rates.GHS, GIP = rates.GIP,
                    GMD = rates.GMD, GNF = rates.GNF, GTQ = rates.GTQ, GYD = rates.GYD, HKD = rates.HKD, HNL = rates.HNL,
                    HRK = rates.HRK, HTG = rates.HTG, HUF = rates.HUF, IDR = rates.IDR, ILS = rates.ILS, IMP = rates.IMP,
                    INR = rates.INR, IQD = rates.IQD, IRR = rates.IRR, ISK = rates.ISK, JEP = rates.JEP, JMD = rates.JMD,
                    JOD = rates.JOD, JPY = rates.JPY, KES = rates.KES, KGS = rates.KGS, KHR = rates.KHR, KMF = rates.KMF,
                    KPW = rates.KPW, KRW = rates.KRW, KWD = rates.KWD, KYD = rates.KYD, KZT = rates.KZT, LAK = rates.LAK,
                    LBP = rates.LBP, LKR = rates.LKR, LRD = rates.LRD, LSL = rates.LSL, LTL = rates.LTL, LVL = rates.LVL,
                    LYD = rates.LYD, MAD = rates.MAD, MDL = rates.MDL, MGA = rates.MGA, MKD = rates.MKD, MMK = rates.MMK,
                    MNT = rates.MNT, MOP = rates.MOP, MRO = rates.MRO, MUR = rates.MUR, MVR = rates.MVR, MWK = rates.MWK,
                    MXN = rates.MXN, MYR = rates.MYR, MZN = rates.MZN, NAD = rates.NAD, NGN = rates.NGN, NIO = rates.NIO,
                    NOK = rates.NOK, NPR = rates.NPR, NZD = rates.NZD, OMR = rates.OMR, PAB = rates.PAB, PEN = rates.PEN,
                    PGK = rates.PGK, PHP = rates.PHP, PKR = rates.PKR, PLN = rates.PLN, PYG = rates.PYG, QAR = rates.QAR,
                    RON = rates.RON, RSD = rates.RSD, RUB = rates.RUB, RWF = rates.RWF, SAR = rates.SAR, SBD = rates.SBD,
                    SCR = rates.SCR, SDG = rates.SDG, SEK = rates.SEK, SGD = rates.SGD, SHP = rates.SHP, SLE = rates.SLE,
                    SLL = rates.SLL, SOS = rates.SOS, SRD = rates.SRD, SSP = rates.SSP, STD = rates.STD, SVC = rates.SVC,
                    SYP = rates.SYP, SZL = rates.SZL, THB = rates.THB, TJS = rates.TJS, TMT = rates.TMT, TND = rates.TND,
                    TOP = rates.TOP, TRY = rates.TRY, TTD = rates.TTD, TWD = rates.TWD, TZS = rates.TZS, UAH = rates.UAH,
                    UGX = rates.UGX, USD = rates.USD, UYU = rates.UYU, UZS = rates.UZS, VEF = rates.VEF, VES = rates.VES,
                    VND = rates.VND, VUV = rates.VUV, WST = rates.WST, XAF = rates.XAF, XAG = rates.XAG, XAU = rates.XAU,
                    XCD = rates.XCD, XDR = rates.XDR, XOF = rates.XOF, XPF = rates.XPF, YER = rates.YER, ZAR = rates.ZAR,
                    ZMK = rates.ZMK, ZMW = rates.ZMW, ZWL = rates.ZWL
                )
            )
        }
        return ratesXList
    }
}