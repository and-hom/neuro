package ru.mai.k806.commons;

/**
 *
 */
public class НесовпадениеРазмерности extends Exception{

public НесовпадениеРазмерности(){
        }

public НесовпадениеРазмерности(String message){
        super(message);
}

public НесовпадениеРазмерности(String message,Throwable cause){
        super(message,cause);
}

public НесовпадениеРазмерности(Throwable cause){
        super(cause);
}
        }
