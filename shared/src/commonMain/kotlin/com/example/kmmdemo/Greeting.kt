package com.example.kmmdemo

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()
    private val httpClient = HttpClient {
        install(ContentNegotiation){
            json(Json { prettyPrint = true
            isLenient = true
                ignoreUnknownKeys = true})
        }
    }



    fun greet(): String {
        return "Hello, ${platform.name}! \n There are only ${daysUntilNewYear()} days left until new year."
    }

    fun daysLeft(): String {
        return "There are only ${daysUntilNewYear()} days left."
    }

    @Throws(Exception::class)
    public suspend fun greetings():String {
        val rockets: List<RocketLaunch> = httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessLaunch = rockets.last{ it.launchSuccess == true}
        return "Hello, ${platform.name}! \n" +
                " There are only ${daysUntilNewYear()} days left until new year. \nThe last successful launch was ${lastSuccessLaunch.launchDateUTC}"
    }
}