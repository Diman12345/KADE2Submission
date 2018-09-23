package com.example.wardiman.kade2submission

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.autofill.AutofillId
import android.widget.LinearLayout
import com.google.gson.Gson
import kotlinx.coroutines.experimental.channels.POLL_FAILED
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.uiThread

class MatchFragment: Fragment() {
    private var match: MutableList<MatchItems> = mutableListOf()
    private lateinit var review: RecyclerView
    private lateinit var swipe: SwipeRefreshLayout
    private var Position = 1
    private var Id_league = "4331"

    companion object {
        fun newInstance(position: Int, Id_league: String): MatchFragment {
            val fragment = MatchFragment()
            fragment.Position = position
            fragment.Id_league = Id_league
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.match_list, container, false)
        review = view.findViewById(R.id.review_match)
        swipe = view.find(R.id.swipe_schedul)
        review.layoutManager = LinearLayoutManager(activity)
        review.adapter = MatchAdapter(match) {
            startActivity<DetailActivity>("MATCH" to it)
        }
        getData()

        swipe.setColorSchemeResources(R.color.colorAccent)
        swipe.onRefresh {
            getData()
        }

    return view
}

    private fun getData() {
        swipe.isRefreshing = true
        doAsync {
            val api = TheSportDBApi(Id_league)
            val json = if(Position == 1) api.getPrevSchedule() else api.getNextSchedule()
            val data = Gson().fromJson(json, ApiRepository::class.java)

            uiThread {
                swipe.isRefreshing = false
                match.clear()
                match.addAll(data.events)
                review.adapter?.notifyDataSetChanged()
            }
        }

    }
}