package com.example.wardiman.kade2submission

import android.net.Uri
import java.net.URL
import java.security.cert.CertPath

class TheSportDBApi(val id: String?){
    private fun getJson(path: String?): String {
        val url = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(path)
                .appendQueryParameter("id", id)
                .build().toString()

        return URL(url).readText()
    }

    fun getPrevSchedule() = getJson("eventspastleague.php")
    fun getNextSchedule() = getJson("eventsnextleague.php")
    fun getMatch() = getJson("lookupevent.php")
    fun getTeam() = getJson("lookupteam.php")
}

