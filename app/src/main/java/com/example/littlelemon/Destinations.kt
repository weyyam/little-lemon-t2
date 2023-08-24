package com.example.littlelemon

interface Destinations{
    val route:String
}

object Home:Destinations{
    override val route = "Home"
}
object Onboard:Destinations{
    override val route = "Onboard"
}
object Profile:Destinations{
    override val route = "Profile"
}