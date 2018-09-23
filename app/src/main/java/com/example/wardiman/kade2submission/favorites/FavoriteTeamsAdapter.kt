package com.example.wardiman.kade2submission

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wardiman.kade2submission.*
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by root on 1/16/18.
 */
class FavoriteTeamsAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount() = favorite.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val matchDate: TextView = view.find(R.id.date)
        private val homeTeam: TextView = view.find(R.id.homeTeam)
        private val homeScore: TextView = view.find(R.id.homeScore)
        private val awayTeam: TextView = view.find(R.id.awayTeam)
        private val awayScore: TextView = view.find(R.id.awayscore)

        fun bindItem(item: Favorite, listener: (Favorite) -> Unit){
            val date = strToDate(item.eventDate)
            matchDate.text = FormatDate(date)

            homeTeam.text = item.homeTeam
            homeScore.text = item.homeScore
            awayTeam.text = item.awayTeam
            awayScore.text = item.awayScore

            itemView.onClick {
                listener(item)
            }
        }
    }

    class MatchUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    margin = dip(6)
                }

                verticalLayout {

                    textView("xxx,77 xxx 9888"){
                        id = R.id.date
                    }.lparams(width = wrapContent, height = wrapContent){
                        gravity = Gravity.CENTER
                        margin = dip(8)
                    }

                    relativeLayout {

                        textView("xxxx"){
                            id = R.id.homeTeam
                            textSize = 14f
                            textColor = Color.BLACK
                            gravity = Gravity.RIGHT
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.homeScore)
                            rightMargin = dip(10)
                        }

                        textView("999"){
                            id = R.id.homeScore
                            textSize = 12f
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.vs)
                        }

                        textView("VS"){
                            id = R.id.vs
                            textSize = 10f
                        }.lparams(width = wrapContent, height = wrapContent){
                            centerInParent()
                            rightMargin = dip(7)
                            leftMargin = dip(7)

                        }

                        textView("999"){
                            id = R.id.awayscore
                            gravity = Gravity.CENTER
                            textSize = 12f
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.vs)
                        }

                        textView("xxx"){
                            id = R.id.awayTeam
                            textSize = 14f
                            textColor = Color.BLACK
                            gravity = Gravity.LEFT
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.awayscore)
                            leftMargin = dip(10)
                        }

                    }.lparams(width = matchParent, height = wrapContent)

                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    bottomMargin = dip(8)
                }
            }
        }

    }}