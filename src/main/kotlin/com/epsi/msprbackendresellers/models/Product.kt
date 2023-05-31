package com.epsi.msprbackendresellers.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class Product @JsonCreator constructor(
    @JsonProperty("label") val label: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("price") val price: BigDecimal,
    @JsonProperty("url") val url: String?,
    @JsonProperty("date_creation") val date_creation: String,
    @JsonProperty("ref") val ref: String,
    @JsonProperty("stock_reel") val stock_reel: Int?,
) {
}
