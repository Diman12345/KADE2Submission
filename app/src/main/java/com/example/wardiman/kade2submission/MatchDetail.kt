package com.example.wardiman.kade2submission

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class MatchItems(
        @SerializedName("idEvent")
        var eventId: String?,

        @SerializedName("strEvent")
        var eventName: String?,

        @SerializedName("dateEvent")
        var eventDate: String?,

        @SerializedName("idHomeTeam")
        var homeTeamId: String?,

        @SerializedName("idAwayTeam")
        var awayTeamId: String?,

        @SerializedName("strHomeTeam")
        var homeTeam: String?,

        @SerializedName("strAwayTeam")
        var awayTeam: String?,

        @SerializedName("intHomeScore")
        var homeScore: String?,

        @SerializedName("intAwayScore")
        var awayScore: String?,

        @SerializedName("intHomeShots")
        var homeShots: String?,

        @SerializedName("intAwayShots")
        var awayShots: String?,

        @SerializedName("strHomeFormation")
        var homeFormation: String?,

        @SerializedName("strAwayFormation")
        var awayFormation: String?,

        @SerializedName("strHomeGoalDetails")
        var homeGoal: String?,

        @SerializedName("strAwayGoalDetails")
        var awayGoal: String?,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeKeeper: String?,

        @SerializedName("strHomeLineupDefense")
        var homeLineupDefense: String?,

        @SerializedName("strHomeLineupMidfield")
        var homeMidfield: String?,

        @SerializedName("strHomeLineupForward")
        var homeForward: String?,

        @SerializedName("strHomeLineupSubstitutes")
        var homeSubtitues: String?,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayKeeper: String?,

        @SerializedName("strAwayLineupDefense")
        var awayDefense: String?,

        @SerializedName("strAwayLineupMidfield")
        var awayMidfield: String?,

        @SerializedName("strAwayLineupForward")
        var awayForward: String?,

        @SerializedName("strAwayLineupSubstitutes")
        var awaySubtitues: String?,

        @SerializedName("strTeamBadge")
        var teamBadge: String?
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(eventId)
                parcel.writeString(eventName)
                parcel.writeString(eventDate)
                parcel.writeString(homeTeamId)
                parcel.writeString(awayTeamId)
                parcel.writeString(homeTeam)
                parcel.writeString(awayTeam)
                parcel.writeString(homeScore)
                parcel.writeString(awayScore)
                parcel.writeString(homeShots)
                parcel.writeString(awayShots)
                parcel.writeString(homeFormation)
                parcel.writeString(awayFormation)
                parcel.writeString(homeGoal)
                parcel.writeString(awayGoal)
                parcel.writeString(homeKeeper)
                parcel.writeString(homeLineupDefense)
                parcel.writeString(homeMidfield)
                parcel.writeString(homeForward)
                parcel.writeString(homeSubtitues)
                parcel.writeString(awayKeeper)
                parcel.writeString(awayDefense)
                parcel.writeString(awayMidfield)
                parcel.writeString(awayForward)
                parcel.writeString(awaySubtitues)
                parcel.writeString(teamBadge)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<MatchItems> {
                override fun createFromParcel(parcel: Parcel): MatchItems {
                        return MatchItems(parcel)
                }

                override fun newArray(size: Int): Array<MatchItems?> {
                        return arrayOfNulls(size)
                }
        }
}

annotation class Parcelize
