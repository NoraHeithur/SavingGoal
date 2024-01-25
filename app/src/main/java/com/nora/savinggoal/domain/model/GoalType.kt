package com.nora.savinggoal.domain.model

import com.nora.savinggoal.R

enum class GoalType(val id: Int, val label: String, val icon: Int) {
    TRAVEL(1, "Travel", R.drawable.ic_travel),
    EDUCATION(2, "Education", R.drawable.ic_education),
    INVEST(3, "Invest", R.drawable.ic_invest),
    SHOPPING(4, "Shopping", R.drawable.ic_shopping),
    FOOD(5, "Food", R.drawable.ic_food),
    GADGET(6, "Gadget", R.drawable.ic_gadget),
    SUBSCRIPTION(7, "Subscription", R.drawable.ic_subscription)
}