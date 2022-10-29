package com.example.ejercicio3.tablas;


public class Transacciones {

    public static final String NameDatabase = "PM14";

    public static final String TbPersonas = "personas";

    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido= "apellido";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    public static final String CTBPersonas = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "nombre TEXT, apellido TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String Getpersonas = "SELECT * FROM " + Transacciones.TbPersonas;

    public static final String DPpersonas = "DROP TABLE IF EXIST personas";


}
