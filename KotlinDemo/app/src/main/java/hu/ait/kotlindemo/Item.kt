package hu.ait.kotlindemo

abstract class Item {
    abstract var price: Int?

    abstract fun buy()
}

interface Transportable{
    abstract fun transport()
}

class MobilePhone : Item(), Transportable {
    override var price: Int? = 100

    override fun buy() {

    }

    override fun transport() {

    }

}


class Truck : Item(), Transportable {
    override var price: Int? = 94555555

    override fun buy() {

    }

    override fun transport() {

    }

}