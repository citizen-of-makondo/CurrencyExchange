package com.alexilinskiy.currencyexchange.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyModelDB
import com.alexilinskiy.currencyexchange.data.db.rates.RatesModelDB
import com.alexilinskiy.currencyexchange.domain.ICurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currenciesUseCase: ICurrenciesUseCase,
) : ViewModel() {

    private val _currenciesFlow = MutableStateFlow<State<List<CurrencyModelDB>>>(State.LoadingState)
    val currenciesFlow = _currenciesFlow.asStateFlow()

    private val _ratesFlow = MutableStateFlow<State<List<RatesModelDB>>>(State.LoadingState)
    val ratesFlow = _ratesFlow.asStateFlow()

    private val _favoritesRatesFlow =
        MutableStateFlow<State<List<RatesModelDB>>>(State.LoadingState)
    val favoritesRatesFlow = _favoritesRatesFlow.asStateFlow()

    private var currency: CurrencyModelDB? = null
    private val currenciesList: MutableList<CurrencyModelDB> = mutableListOf()

    init {
        getAllCurrencies()
    }

    fun getAllCurrencies() {
        viewModelScope.launch {
            _currenciesFlow.emit(State.LoadingState)
            try {
                currenciesUseCase.getAllCurrenciesFromRemote()
                getAllCurrenciesFromLocal()
            } catch (e: java.lang.Exception) {
                _currenciesFlow.emit(State.ErrorState(e))
            }
        }
    }

    fun getAllCurrenciesFromLocal() {
        viewModelScope.launch {
            _currenciesFlow.emit(State.LoadingState)
            try {
                val data = currenciesUseCase.getAllCurrenciesFromLocal()
                currenciesList.clear()
                currenciesList.addAll(data)
                _currenciesFlow.emit(State.DataState(data))
            } catch (e: java.lang.Exception) {
                _currenciesFlow.emit(State.ErrorState(e))
            }
        }
    }


    fun getFilteredCurrencies() {
        viewModelScope.launch {
            try {
                val data = currenciesUseCase.getFilteredList()
                currenciesList.clear()
                currenciesList.addAll(data)
                _currenciesFlow.emit(State.DataState(data))
            } catch (e: Exception) {
                _currenciesFlow.emit(State.ErrorState(e))
            }
        }
    }

    fun setCurrencyChanged(index: Int, typeSort: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currency = currenciesList[index]

                currency?.let { currencyModelDB ->
                    currenciesList.addAll(currenciesList.sortedByDescending { it.name })
                    currenciesUseCase.setCurrencyChanged(currency!!.copy(isSelected = !currencyModelDB.isSelected))
                }
            }
        }
        getCountedForCurrency(index, typeSort)
    }

    fun getCountedForCurrency(index: Int, typeSort: String?) {
        currency = currenciesList[index]

        viewModelScope.launch {
            _ratesFlow.emit(State.LoadingState)
            try {
                val data = currenciesUseCase.getRates(currency?.shortName.orEmpty(), currenciesList) as MutableList
                typeSort?.let { sort ->
                    when (sort) {
                        "sort" -> data.sortBy { it.count }
                        "sortByDesc" -> data.sortByDescending { it.count }
                        else -> Unit
                    }
                }

                _ratesFlow.emit(State.DataState(data))
                _favoritesRatesFlow.emit(State.DataState(currenciesUseCase.getFavoritesRateList()))
            } catch (e: Exception) {
                _ratesFlow.emit(State.ErrorState(e))
            }
        }
    }

    fun getFavoriteRateList() {
        viewModelScope.launch {
            _favoritesRatesFlow.emit(State.LoadingState)
            try {
                val data = currenciesUseCase.getFavoritesRateList()
                _favoritesRatesFlow.emit(State.DataState(data))
            } catch (e: Exception) {
                _favoritesRatesFlow.emit(State.ErrorState(e))
            }
        }
    }

    fun onFavoriteIconClick(rate: RatesModelDB) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _ratesFlow.emit(State.LoadingState)
                val data =
                    currenciesUseCase.onFavoriteIconClick(rate.copy(isFavorite = !rate.isFavorite))
                _ratesFlow.emit(State.DataState(data))
                getFavoriteRateList()
            }
        }
    }

}