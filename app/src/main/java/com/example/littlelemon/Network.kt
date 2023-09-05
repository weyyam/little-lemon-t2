package com.example.littlelemon

import android.icu.text.CaseMap.Title
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MenuNetworkdata(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String

)
/*
{
fun toMenuItemRoom() = MenuItemRoom(
    id,
    title,
    price,
    ect
    )
    }
 */