// This is an example of eager loading. That means everytime the class is loaded onto the memory, 
// obj will be initialized. If obj is heavy, then it's heavy process and it'll be initialized 
// everytime the class is loaded in the memory.

public class SingletonDemo {
    public static void main(String[] args){
        Abc obj1 = Abc.getInstance();
        Abc obj2 = Abc.getInstance();   
        // The same object will be returned to obj1 and obj2 both
    }    
}


class Abc {
    public static Abc obj = new Abc();  // static object

    private Abc(){        
        // private constructor so any class can't create object of Abc class explicitly
    }

    public static Abc getInstance(){
        return obj;            // returning the same static object every time object is asked
    }
}