package com.alexilinskiy.currencyexchange.domain.remote
import com.beust.klaxon.*

private val klaxon = Klaxon()

class JsonParser(elements: Map<String, Currency>) : HashMap<String, Currency>(elements) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = JsonParser (
            klaxon.parseJsonObject(java.io.StringReader(json)) as Map<String, Currency>
        )
    }
}

data class Currency (
    val name: String,
    val symbol: String
)