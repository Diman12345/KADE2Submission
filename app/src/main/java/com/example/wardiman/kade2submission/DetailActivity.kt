package com.example.wardiman.kade2submission

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.wardiman.kade2submission.R.drawable.ic_add_to_favorites
import com.example.wardiman.kade2submission.R.drawable.ic_added_to_favorites
import com.example.wardiman.kade2submission.R.id.add_to_favorite
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.example.wardiman.kade2submission.R.id.swipe_match
import com.example.wardiman.kade2submission.R.menu.detail_menu
import kotlinx.android.synthetic.main.match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.uiThread

class DetailActivity: AppCompatActivity() {
    private lateinit var match:MatchItems
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.match_detail)

        match = intent.getParcelableExtra("MATCH")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = match.eventName
        getData()

        //favoriteState()
        swipe_match.onRefresh {
            getData()
        }
        swipe_match.setColorSchemeResources(R.color.colorAccent)
    }
    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(eventId = {id})",
                            "id" to getData())
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


    private fun getData() {
        swipe_match.isRefreshing = true
        doAsync {
            val matchdata = Gson().fromJson(TheSportDBApi(match.eventId).getMatch(), ApiRepository::class.java)
            val homeTeam = Gson().fromJson(TheSportDBApi(match.homeTeamId).getTeam(), ApiRepository::class.java)
            val awayTeam = Gson().fromJson(TheSportDBApi(match.awayTeamId).getTeam(), ApiRepository::class.java)

            uiThread {
                val match = matchdata.events[0]
                val home = homeTeam.teams[0]
                val away = awayTeam.teams[0]

                val date = strToDate(match.eventDate)
                tv_date.text = FormatDate(date)

                Picasso.get().load(home.teamBadge).into(home_img)

                home_club.text = match.homeTeam
                home_score.text = if (match.homeScore.isNullOrEmpty()) "0" else match.homeScore
                home_formation.text = match.homeFormation
                home_goals.text = if(match.homeGoal.isNullOrEmpty()) "No data" else match.homeGoal?.replace("; ", ",\n")
                home_shots.text = if(match.homeShots.isNullOrEmpty()) "0" else match.homeShots
                home_goalkeeper.text = if(match.homeKeeper.isNullOrEmpty()) "No data" else match.homeKeeper?.replace("; ", ",\n")
                home_defense.text = if(match.homeKeeper.isNullOrEmpty()) "No data" else match.homeKeeper?.replace("; ", ",\n")
                home_midfield.text = if(match.homeMidfield.isNullOrEmpty()) "No data" else match.homeMidfield?.replace("; ", ",\n")
                home_forward.text = if(match.homeForward.isNullOrEmpty()) "No data" else match.homeForward?.replace("; ", ",\n")
                home_subtitutes.text = if(match.homeSubtitues.isNullOrEmpty()) "No data" else match.homeSubtitues?.replace("; ", ",\n")

                Picasso.get().load(away.teamBadge).into(away_img)

                away_club.text = match.awayTeam
                away_score.text = if(match.awayScore.isNullOrEmpty()) "0"  else match.awayScore
                away_formation.text = match.awayFormation
                away_goals.text = if(match.awayGoal.isNullOrEmpty()) "No data" else match.awayGoal?.replace("; ", ",\n")
                away_shots.text = if(match.awayShots.isNullOrEmpty()) "0" else match.awayShots
                away_goalkeeper.text = if(match.awayKeeper.isNullOrEmpty()) "No data" else match.awayKeeper?.replace("; ", ",\n")
                away_defense.text = if(match.awayDefense.isNullOrEmpty()) "No data" else match.awayDefense?.replace("; ", ",\n")
                away_midfield.text = if(match.awayMidfield.isNullOrEmpty()) "No data" else match.awayMidfield?.replace("; ", ",\n")
                away_forward.text = if(match.awayForward.isNullOrEmpty()) "No data" else match.awayForward?.replace("; ", ",\n")
                away_subtitutes.text = if(match.awaySubtitues.isNullOrEmpty()) "No data" else match.awaySubtitues?.replace("; ", ",\n")

                swipe_match.isRefreshing = false
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                //if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to getData(),
                        Favorite.EVENT_DATE to getData(),
                        Favorite.EVENT_DATE to getData(),
                        Favorite.HOME_TEAM_ID to getData(),
                        Favorite.HOME_TEAM to getData(),
                        Favorite.HOME_SCORE to getData(),
                        Favorite.AWAY_TEAM_ID to getData(),
                        Favorite.AWAY_TEAM to getData(),
                        Favorite.AWAY_SCORE to getData())
            }
            snackbar(swipe_match, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipe_match, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
            //snackbar(swipe_match, "Added to favorite").show()
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
            //snackbar(swipe_match, "Remove to favorite").show()
        }
    }


}