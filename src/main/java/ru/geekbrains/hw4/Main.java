package ru.geekbrains.hw4;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBHandler db = new DBHandler();
        db.conn();
        db.dropTable();
        db.createDB();
        db.writeDB();
        db.readDB();
        db.sel1();
        db.sel2();
        db.sel3(540, 900);
        db.sel3(900, 1080);
        db.sel3(1080, 1260);
        db.sel3(1260, 1440);
        db.sel4();
        db.closeDB();
    }
}
