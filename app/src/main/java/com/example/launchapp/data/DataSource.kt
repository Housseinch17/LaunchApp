package com.example.launchapp.data

import androidx.annotation.StringRes
import com.example.launchapp.R

object DataSource {

    val chooseEntree: List<Item> = listOf(
        Item("Cauliflower", "Whole cauliflower,brimed, roaster, and deep fried", 7.00), Item(
            "Three Bean Chili",
            "Black beans,read beands kidney beans, slow cooked, topped with onion",
            4.00
        ), Item(
            "Mushroom pasta",
            "Penne pasta,mushrooms,basil with plum,tomatoes cooked in garlic and olive",
            5.50
        ), Item(
            "Spicy Black Bean Skillet",
            "Seasonal vegetables,black beans,house spice blend,served with avocado and quick pickled onions",
            5.50
        )
    )
    val chooseSideDish: List<Item> = listOf(
        Item(
            "Summer Salad", "Heirloom tomatoes,butter lettuce,peaches,avocado,balsamic dress", 2.50
        ),

        Item(
            title = "Butternut Squash Soup",
            description = "Roaster butternut squash,roaster peppers chili oil",
            price = 3.0
        ),
        Item(
            title = "Spicy potatoes",
            description = "Marble potatoes,roasted and fried in house spice blend",
            price = 2.0
        ),
        Item(title = "Coconut Rice", description = "Rice,coconut milk,lime and sugar", price = 1.50)
    )
    val chooseAccompaniment: List<Item> = listOf(
        Item("Lunch Roll","Fresh baked roll made in house",0.5),
        Item("Mixed berries","Strawberries,blueberries,rasberries and huckleberries",1.0),
        Item("Pickled veggies","Pickled cucumber and carrots made in house",0.5)
    )

    enum class LaunchScreen(@StringRes val title: Int) {
        LaunchTray(title = R.string.launch_tray),
        ChooseEntree(R.string.choose_entree),
        ChooseSideDish(R.string.choose_side_Dish),
        ChooseAccompaniment(R.string.choose_accompaniment),
        OrderCheckOut(R.string.order_checkout)

    }
}
