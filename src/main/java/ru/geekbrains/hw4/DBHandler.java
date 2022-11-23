package ru.geekbrains.hw4;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class DBHandler {
    private Connection conn;
    private ResultSet resSet;
    private PreparedStatement ps;
    private DecimalFormat dF = new DecimalFormat( "00" );

    public  void conn() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:cinema.db");
        System.out.println("База подключена.");
    }
    public  void createDB() throws SQLException {
        ps = conn.prepareStatement("create table if not exists films (" +
                "id_films integer not null primary key autoincrement," +
                "name varchar(50) not null," +
                "duration integer not null)");
        ps.execute();
        ps = conn.prepareStatement("create table if not exists sessions (" +
                "id integer not null primary key autoincrement," +
                "date date not null," +
                "time integer not null," +
                "id_film integer not null," +
                "price integer not null," +
                "CONSTRAINT `id_film_fk` FOREIGN KEY (`id_film`) REFERENCES `films` (`id_films`) ON DELETE CASCADE ON UPDATE CASCADE)");
        ps.execute();
        ps = conn.prepareStatement("create table if not exists sales (" +
                "ticket_number integer not null primary key autoincrement," +
                "session integer not null," +
                "CONSTRAINT `session_fk` FOREIGN KEY (`session`) REFERENCES `sessions` (`id`))");
        ps.execute();
    }

    public  void writeDB() throws SQLException {
        String[] filmsName = {"Убить Билла",
                "Золушка",
                "Обыкновенное чудо",
                "Шаг вперед",
                "Терминатор"};
        int[] filmsDuration = {120,90,120,60,90};
        for (int i = 0; i < 5; i++) {
            ps = conn.prepareStatement("insert into films (name,duration) values(?,?);");
            ps.setString(1, filmsName[i]);
            ps.setInt(2,filmsDuration[i]);
            ps.executeUpdate();
        }
        LocalDate date = LocalDate.of(2020,11,20);
        LocalTime[] time = {LocalTime.parse("09:00:00"),
                LocalTime.parse("12:00:00"),
                LocalTime.parse("21:15:00"),
                LocalTime.parse("14:45:00"),
                LocalTime.parse("19:15:00"),
                LocalTime.parse("13:00:00"),
                LocalTime.parse("16:00:00"),
                LocalTime.parse("22:10:00"),
                LocalTime.parse("18:20:00")
        };
        int[] idFilm = {2,2,1,4,4,3,3,5,5};
        int[] price = {120,140,250,160,200,140,160,300,200};
        for (int i = 0; i < 9; i++) {
            ps = conn.prepareStatement("insert into sessions (date,time,id_film,price) values(?,?,?,?);");
            ps.setDate(1, Date.valueOf(date));
            ps.setInt(2, (time[i].getHour()*60 + time[i].getMinute()));
            ps.setInt(3, idFilm[i]);
            ps.setInt(4, price[i]);
            ps.executeUpdate();
        }
        int[] session = {1,1,1,1,2,2,3,3,4,4,4,4,5,5,5,6,6,6,7,8,8,8,8,6};
        for (int i = 0; i < 24; i++) {
            ps = conn.prepareStatement("insert into sales (session) values(?);");
            ps.setInt(1, session[i]);
            ps.executeUpdate();
        }
    }

    public  void readDB() throws SQLException {
        ps = conn.prepareStatement("SELECT * FROM films");
        resSet = ps.executeQuery();
        System.out.println("\n  Таблица films");
        while (resSet.next()){
            int id = resSet.getInt("id_films");
            String name = resSet.getString("name");
            int duration = resSet.getInt("duration");
            System.out.println(id+" "+name+" "+duration);

        }
        System.out.println();
        ps = conn.prepareStatement("SELECT * FROM sessions");
        resSet = ps.executeQuery();
        System.out.println("\n  Таблица sessions");
        while (resSet.next()){
            int id = resSet.getInt("id");
            Date name = resSet.getDate("date");
            int time = resSet.getInt("time");
            int id_film = resSet.getInt("id_film");
            int price = resSet.getInt("price");
            System.out.println(id+" "+name+" "+time+" "+id_film+" "+price);

        }
        System.out.println();
        ps = conn.prepareStatement("SELECT * FROM sales");
        resSet = ps.executeQuery();
        System.out.println("\n  Таблица sales");
        while (resSet.next()){
            int id = resSet.getInt("ticket_number");
            int session = resSet.getInt("session");
            System.out.println(id+" "+session);
        }
    }
    public  void closeDB() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void dropTable() {
        try {
            ps = conn.prepareStatement("drop table sales");
            ps.execute();
            ps = conn.prepareStatement("drop table sessions");
            ps.execute();
            ps = conn.prepareStatement("drop table films");
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void sel1() throws SQLException {
        ps = conn.prepareStatement("SELECT " +
                "    f.name as fname, " +
                "    ss.time as sstime," +
                "    f.duration as fduration," +
                "    f1.name as f1name, " +
                "    ss1.time as ss1time," +
                "    f1.duration as f1duration " +
                "FROM  films f, sessions ss, films f1, sessions ss1 " +
                "WHERE f.id_films=ss.id_film  and f1.id_films=ss1.id_film " +
                " and (ss1.time > ss.time and " +
                " ss1.time < (ss.time+f.duration)) "+
                "ORDER BY ss.time;");
        resSet = ps.executeQuery();
        System.out.println("\n    Фильм          Время начала   Длительность    Фильм          Время начала   Длительность");
        while (resSet.next()){
            StringBuilder sb = new StringBuilder();
            String fName = resSet.getString(1);
            sb.append(fName);
            int k = 21 - fName.length();
            for (int i = 0; i < k; i++) {
                sb.append(" ");
            }
            int ssTime = resSet.getInt(2);
            sb.append(dF.format(ssTime/60)).append(":").append(dF.format(ssTime% 60)).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(" ");
            }
            int fDuration = resSet.getInt(3);
            sb.append(dF.format(fDuration/60)).append(":").append(dF.format(fDuration % 60)).append(" ");
            for (int i = 0; i < 4; i++) {
                sb.append(" ");
            }
            String f1Name = resSet.getString(4);
            sb.append(f1Name).append(" ");
            k = 21 - f1Name.length();
            for (int i = 0; i < k; i++) {
                sb.append(" ");
            }
            int ss1Time = resSet.getInt(5);
            sb.append(dF.format(ss1Time/60)).append(":").append(dF.format(ss1Time% 60)).append(" ");
            for (int i = 0; i < 9; i++) {
                sb.append(" ");
            }
            int f1Duration = resSet.getInt(6);
            sb.append(dF.format(f1Duration/60)).append(":").append(dF.format(f1Duration % 60)).append(" ");
            System.out.println(sb);
        }
    }
    public void sel2() throws SQLException {
        ps = conn.prepareStatement("SELECT " +
                "    name as 'Фильм', " +
                "    count(*) as 'Общее число посетителей', " +
                "    (select avg(t.cnt) " +
                "    from " +
                "        (select name, ss.time, count(*) cnt " +
                "        FROM films f, sessions ss, sales s " +
                "        WHERE id_films=ss.id_film " +
                "            and s.session=ss.id " +
                "        GROUP BY name, ss.time) t " +
                "    where t.name=name " +
                "    order by t.name) 'В среднем зрителей за сеанс', " +
                "    sum(price) 'Сумма сборов'  " +
                "FROM films f, sessions ss, sales s " +
                "WHERE id_films=ss.id_film " +
                "    and s.session=ss.id " +
                "GROUP BY name " +
                "ORDER BY sum(price) DESC; ");
        resSet = ps.executeQuery();
        System.out.println("\n  Фильм               Общее число посетителей");

        while (resSet.next()){
            StringBuilder sb = new StringBuilder();
            String fName = resSet.getString(1);
            sb.append(fName);
            int k = 34 - fName.length();
            for (int i = 0; i < k; i++) {
                sb.append(" ");
            }
            int fCount = resSet.getInt(2);
            sb.append(fCount);
            System.out.println(sb);
        }
        ps = conn.prepareStatement("SELECT  " +
                "    count(*) as 'Общее число посетителей', " +
                "    (select avg(t.cnt) " +
                "    from (select f.name, ss.time, count(*) cnt " +
                "        FROM films f, sessions ss, sales s " +
                "        WHERE id_films=ss.id_film  " +
                "            and s.session=ss.id " +
                "        GROUP BY f.name,ss.time) t) 'avg', " +
                "    sum(price) 'Сумма сборов'  " +
                "FROM films f, sessions ss, sales s " +
                "WHERE f.id_films=ss.id_film  " +
                "    and s.session=ss.id;");
        resSet = ps.executeQuery();
        while (resSet.next()){
            StringBuilder sb = new StringBuilder("Итого");
            for (int i = 0; i < 29; i++) {
                sb.append(" ");
            }
            int fCount = resSet.getInt(1);
            sb.append(fCount);
            System.out.println(sb);
        }
        System.out.println();
    }

    public void sel3(int time1, int time2) throws SQLException {
        ps = conn.prepareStatement("SELECT  " +
                "    count(*), " +
                "    sum(price)  " +
                " FROM films f, sessions ss, sales s " +
                " WHERE f.id_films=ss.id_film  " +
                "    and s.session=ss.id " +
                "    and (ss.time>=" + time1 + " and ss.time<=" + time2 + ");");
        resSet = ps.executeQuery();
        System.out.println("     с "+time1/60+" до "+time2/60);
        System.out.println("  Общее число посетителей  Сумма сборов");

        while (resSet.next()){
            StringBuilder sb = new StringBuilder();
            int fCount = resSet.getInt(1);
            sb.append("      ").append(fCount);
            for (int i = 0; i < 25; i++) {
                sb.append(" ");
            }
            int fSumPrice = resSet.getInt(2);
            sb.append(fSumPrice);
            System.out.println(sb);
        }
    }

    public void sel4() throws SQLException {
        ps = conn.prepareStatement("select " +
                "        f1_name 'Фильм1', " +
                "                vnf1 'Время начала', " +
                "                df1 'Длительность', " +
                "                vnvf 'Время начала второго фильма', " +
                "                (vnvf - vnf1 - df1) 'Длительность перерыва' " +
                "        from (SELECT f.name f1_name, ss.time vnf1, f.duration df1, " +
                "                (SELECT min(ss2.time) " +
                "                FROM films f1, sessions ss1, films f2, sessions ss2 " +
                "                WHERE f1.id_films=ss1.id_film and f2.id_films=ss2.id_film and ss.time=ss1.time " +
                "                and ss2.time > ss1.time " +
                "                group BY ss1.time " +
                "                limit 1) vnvf " +
                "        FROM films f, sessions ss " +
                "        WHERE f.id_films=ss.id_film) t1 " +
                "        where (vnvf - vnf1 - df1) >=30 " +
                "        order by (vnvf - vnf1 - df1) desc;");
        resSet = ps.executeQuery();
        System.out.println("\n   Фильм      Сеанс 1   Длительность  Сеанс 2  Длительность перерыва");
        while (resSet.next()){
            StringBuilder sb = new StringBuilder();
            String fName = resSet.getString(1);
            sb.append(fName);
            int k = 15 - fName.length();
            for (int i = 0; i < k; i++) {
                sb.append(" ");
            }
            int vnf1 = resSet.getInt(2);
            sb.append(dF.format(vnf1/60)).append(":").append(dF.format(vnf1 % 60)).append(" ");

            for (int i = 0; i < 6; i++) {
                sb.append(" ");
            }
            int df1 = resSet.getInt(3);
            sb.append(dF.format(df1/60)).append(":").append(dF.format(df1 % 60)).append(" ");
            for (int i = 0; i < 6; i++) {
                sb.append(" ");
            }
            int vnf2 = resSet.getInt(4);
            sb.append(dF.format(vnf2/60)).append(":").append(dF.format(vnf2 % 60)).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(" ");
            }
            int df2 = resSet.getInt(5);
            sb.append(dF.format(df2/60)).append(":").append(dF.format(df2 % 60)).append(" ");
            System.out.println(sb);
        }
    }
}
