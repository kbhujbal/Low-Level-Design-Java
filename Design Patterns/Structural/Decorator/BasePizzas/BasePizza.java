public abstract class BasePizza {
    public abstract int cost();
}



class Farmhouse extends BasePizza {

    @Override
    public int cost() {
        return 200;
    }
}

class VegDelight extends BasePizza{
    @Override
    public int cost() {
        return 120;
    }
}

class Margherita extends BasePizza{
    @Override
    public int cost() {
        return 100;
    }
}

