public abstract class ToppingDecorator extends BasePizza {

}


class ExtraCheese extneds ToppingDecorator {
    BasePizza basePizza;

    public ExtraCheese(BasePizza pizza){
        this.basePizza = pizza;
    }

    public int cost(){
        return this.basePizza.cost() + 10;
    }
}

class Mushroom extends ToppingDecorator {
    BasePizza basePizza;

    public Mushroom(BasePizza pizza){
        this.basePizza = pizza;
    }

    public int cost(){
        return this.basePizza.cost() + 15;
    }
}