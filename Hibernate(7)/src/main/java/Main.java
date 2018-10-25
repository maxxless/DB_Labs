
import com.pavelchak.*;
import org.hibernate.HibernateException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Properties;
import java.util.Scanner;


public class Main {

    private static SessionFactory ourSessionFactory;

//    static {
//        try { // Create the SessionFactory from hibernate.properties
//            Properties properties = new Properties();
////            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
//            Configuration configuration = new Configuration().setProperties(properties)
//                    .addAnnotatedClass(com.pavelchak.BookEntity.class)
//                    .addAnnotatedClass(com.pavelchak.CityEntity.class)
//                    .addAnnotatedClass(com.pavelchak.PersonEntity.class);
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) { throw new ExceptionInInitializerError(ex); }
//    }

    static {
        try { // Create the SessionFactory from hibernate.cfg.xml
            ourSessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession(); //return opened session
    }

    //---------------------------------------------------------------------------
    public static void main(final String[] args) throws Exception {
        // get opened session
        Session session = getSession();
        try {

            Scanner in = new Scanner(System.in);
            int b=0;
            int a=0;


            System.out.println("MENU:\n 1.updateData:\n 2.readData:\n 3.insertData:\n 4.DeleteData");
            b = in.nextInt();
            while (b!=5) {

                switch(b) {
                    case 1:
                        System.out.println("chose_update:\n 1.updateData_team:\n 2.updateData_player:\n 3.updateData_replacement:\n 4.updateData_connect");
                        a=0;
                        a = in.nextInt();

                        switch (a){
                            case 1:
                                updateTeam(session);
                                break;
                            case 2:
                                updatePlayer(session);
                                break;
                            case 3:
                                //updateData_replacement();
                                break;
                            case 4:
                                //updateData_connect();
                                break;
                        }

                        break;
                    case 2:
                        ReadPlayerTable(session);
                        ReadTeam(session);
                        break;


                    case 3:
                        System.out.println("chose_insert:\n 1.insertData_team:\n 2.insertData_player:\n 3.insertData_replacement:\n 4.insertData_connect");
                        a=0;
                        a = in.nextInt();

                        switch (a){
                            case 1:
                                insertTeam(session);
                                break;
                            case 2:
                                insertPlayer(session);
                                break;
                            case 3:
                                //insertData_replacement();
                                break;
                            case 4:
                                //insertData_connect();
                                break;

                        }


                        break;
                    case 4:
                        System.out.println("chose_delete:\n 1.deleteData_team:\n 2.deleteData_player:\n 3.deleteData_replacement:\n 4.deleteData_connect");
                        a=0;
                        a = in.nextInt();

                        switch (a){
                            case 1:
                               deleteTeam(session);
                                break;
                            case 2:
                               deletePlayer(session);
                                break;
                            case 3:
                                //DeleteData_replacement();
                                break;
                            case 4:
                                //DeleteData_connect();
                                break;

                        }
                        break;
                }
                System.out.println("MENU:\n 1.updateData:\n 2.readData:\n 3.insertData:\n 4.DeleteData");


                b = in.nextInt();


            }
  //          ReadAllTable(session);
             // ReadBookOfPerson(session);
              ReadPlayerTable(session);

//            ReadCityFilter(session);


//            ReadCityTable(session);
//            insertCity(session);
//            ReadCityTable(session);

//            insertPerson(session);
              insertTeam(session);

   //         ReadCityFilter(session);

//            AddBookForPerson(session);
//            ReadAllTable(session);

//            ReadCityTable(session);
//            updateCity(session);
//            ReadAllTable(session);

//            ReadBookOfPerson(session);
 //           AddPairPersonBookWithProcedure(session);
//            ReadBookOfPerson(session);
            System.out.println("Finish work!");
        } finally {
            session.close();
            System.exit(0);
        }
    }

    private static void ReadAllTable(Session session) {

//region Read Person
//        Query query = session.createQuery("from " + "PersonEntity");
//////        System.out.format("\nTable Person --------------------\n");
//////        System.out.format("%3s %-12s %-12s %-10s %s\n", "ID", "Surname", "Name", "City", "Email");
//////        for (Object obj : query.list()) {
//////            PersonEntity person = (PersonEntity) obj;
//////            System.out.format("%3d %-12s %-12s %-10s %s\n", person.getIdPerson(),
//////                    person.getSurname(), person.getName(), person.getCityByCity().getCity(), person.getEmail());
//////        }

        Query query = session.createQuery("from " + "PlayerEntity");
        System.out.format("\nTable Person --------------------\n");
        System.out.format("%3s %-12s %-12s %-10s %s\n", "ID", "Surname", "Name", "City", "Nik");
        for (Object obj : query.list()) {
            PlayerEntity player = (PlayerEntity) obj;
            System.out.format("%3d %-12s %-12s %-10s %s\n", player.getIdplayer(),
                    player.getSurname(), player.getName(), player.getteamByTeam().getTeam(), player.getPlayer());
        }



        //endregion

//region Read Book
        query = session.createQuery("from " + "BookEntity");
        System.out.format("\nTable Book --------------------\n");
        System.out.format("%3s %-18s %-18s %s\n", "ID", "BookName", "Author", "Amount");
        for (Object obj : query.list()) {
            BookEntity book = (BookEntity) obj;
            System.out.format("%3d %-18s %-18s %s\n", book.getIdBook(), book.getBookName(), book.getAuthor(), book.getAmount());
        }
        //endregion

//region Read City
        query = session.createQuery("from " + "CityEntity");
        System.out.format("\nTable City --------------------\n");
        for (Object obj : query.list()) {
            CityEntity city = (CityEntity) obj;
            System.out.format("%s\n", city.getCity());
        }
        //endregion

    }

    private static void ReadCityFilter(Session session) {

        Scanner input = new Scanner(System.in);
        System.out.println("Input name city for Person: ");
        String city_in = input.next();

        CityEntity cityEntity = (CityEntity) session.load(CityEntity.class, city_in);
        if (cityEntity != null) {
            System.out.format("\n%s: %s\n", city_in, "Surname");
            for (PersonEntity obj : cityEntity.getPeopleByCity())
                System.out.format("    %s\n", obj.getSurname());
        } else System.out.println("invalid name of city");
    }

    private static void ReadBookOfPerson(Session session) {
        Query query = session.createQuery("from " + "PersonEntity");
        System.out.format("\nTable Person --------------------\n");
        System.out.format("%3s %-12s %-12s \n", "ID", "Surname", "Name");
        for (Object obj : query.list()) {
            PersonEntity person = (PersonEntity) obj;
            System.out.format("%3s %-12s %-12s->\n", person.getIdPerson(), person.getSurname(), person.getName());
            for (BookEntity booky : person.getBooks()) {
                System.out.format("\t\t%s // %s\n", booky.getBookName(), booky.getAuthor());
            }
        }
    }

    private static void ReadCityTable(Session session) {

        Query query = session.createQuery("from " + "CityEntity");
        System.out.format("\nTable City --------------------\n");
        for (Object obj : query.list()) {
            CityEntity city = (CityEntity) obj;
            System.out.format("%s\n", city.getCity());
        }
    }
    private static void ReadPlayerTable(Session session){

        Query query = session.createQuery("from " + "PlayerEntity");
        System.out.format("\nTable Person --------------------\n");
        System.out.format("%3s %-12s %-12s %-10s %s\n", "ID", "Surname", "Name", "City", "Nik");
        for (Object obj : query.list()) {
            PlayerEntity player = (PlayerEntity) obj;
            System.out.format("%3d %-12s %-12s %-10s %s\n", player.getIdplayer(),
                    player.getSurname(), player.getName(), player.getteamByTeam().getTeam(), player.getPlayer());
        }

    }
    private static void ReadTeam(Session session){

        Query query = session.createQuery("from " + "TeamEntity ");
        System.out.format("\nTable Team --------------------\n");
        for (Object obj : query.list()) {
            TeamEntity team = (TeamEntity) obj;
            System.out.format("%s\n", team.getTeam());

        }

    }

//    private static void insertCity(Session session) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Input a new name city: ");
//        String newcity = input.next();
//
//        session.beginTransaction();
//        CityEntity cityEntity = new CityEntity(newcity);
//        session.save(cityEntity);
//        session.getTransaction().commit();
//
//        System.out.println("end insert city");
//    }

    private static void insertTeam(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new name city: ");
        String newteam = input.next();

        session.beginTransaction();
        TeamEntity teamEntity = new TeamEntity(newteam);
        session.save(teamEntity);
        session.getTransaction().commit();

        System.out.println("end insert city");
    }

    private static void deleteTeam(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a  city what you what delete: ");
        String newteam = input.next();

        session.beginTransaction();
        TeamEntity teamEntity = new TeamEntity(newteam);
        session.remove(teamEntity);
        session.getTransaction().commit();

        System.out.println("all is good");
    }

    private static void deletePlayer(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a  id what you what delete: ");
        String idplayer = input.next();
        int idplayer2 = Integer.parseInt(idplayer);

//        session.beginTransaction();
//        PlayerEntity playerEntity = new PlayerEntity(idplayer2);
//        session.remove(playerEntity);
//        session.getTransaction().commit();

        PlayerEntity playerEntity = (PlayerEntity) session.load(PlayerEntity.class, idplayer2);
        if (playerEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("delete from PlayerEntity where idplayer=:code1");
            query.setParameter("code1", idplayer2);
            //"DELETE FROM player WHERE DB_JDBCC.player.IDplayer=?"

            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("end update team: " + result);
        } else System.out.println("There is no the team");


        System.out.println("all is good");
    }

//    private static void insertPerson(Session session) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Input new Person Surname: ");
//        String surname_new = input.next();
//        System.out.println("Input new Person Surname: ");
//        String name_new = input.next();
//        System.out.println("Input the City for Person: ");
//        String city = input.next();
//        System.out.println("Input new Person Email: ");
//        String email = input.next();
//
//        session.beginTransaction();
//        PersonEntity personEntity = new PersonEntity(surname_new, name_new, city, email);
//        session.save(personEntity);
//        session.getTransaction().commit();
//        System.out.println("end insert person");
//    }

    private static void insertPlayer(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input new player: ");
        String player = input.next();
        System.out.println("Input new  surname: ");
        String surname = input.next();
        System.out.println("Input team: ");
        String team = input.next();
        System.out.println("Input the name: ");
        String name = input.next();


        session.beginTransaction();
        PlayerEntity playerEntity = new PlayerEntity(player, surname, team, name);
        session.save(playerEntity);
        session.getTransaction().commit();
        System.out.println("end insert person");
    }

//    private static void updateCity(Session session) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("\nInput a name city: ");
//        String city = input.next();
//        System.out.println("Input new name city: ");
//        String newCity = input.next();
//
//        CityEntity cityEntity = (CityEntity) session.load(CityEntity.class, city);
//        if (cityEntity != null) {
//            session.beginTransaction();
//            Query query = session.createQuery("update CityEntity set city=:code1  where city = :code2");
//            query.setParameter("code1", newCity);
//            query.setParameter("code2", city);
//            int result = query.executeUpdate();
//            session.getTransaction().commit();
//            System.out.println("end update city: " + result);
//        } else System.out.println("There is no the city");
//    }

    private static void updateTeam(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput a name team: ");
        String team = input.next();
        System.out.println("Input new name team: ");
        String newteam = input.next();

        TeamEntity teamEntity = (TeamEntity) session.load(TeamEntity.class, team);
        if (teamEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("update TeamEntity set team=:code1  where team = :code2");
            query.setParameter("code1", newteam);
            query.setParameter("code2", team);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("end update team: " + result);
        } else System.out.println("There is no the team");
    }


    private static void updatePlayer(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput a IDplayer: ");
        String idplayer = input.next();
        int idplayer2 = Integer.parseInt(idplayer);
        System.out.println("Input new name player: ");
        String newplayer = input.next();

        PlayerEntity playerEntity = (PlayerEntity) session.load(PlayerEntity.class, idplayer2);
        if (playerEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("update PlayerEntity set player=:code1  where idplayer = :code2");
            query.setParameter("code1", newplayer);
            query.setParameter("code2", idplayer2);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("end update team: " + result);
        } else System.out.println("There is no the team");
    }



    private static void AddBookForPerson(Session session) {
        System.out.println("Give a book to person--------------");
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Person Surname:");
        String surname_in = input.next();
        System.out.println("Choose Name Book:");
        String book_in = input.next();

        Query query = session.createQuery("from " + "PersonEntity where surname = :code");
        query.setParameter("code", surname_in);

        if (!query.list().isEmpty()) {
            //Give this person entity from query
            PersonEntity personEntity = (PersonEntity) query.list().get(0);
            //search the book entity  from query
            query = session.createQuery("from " + "BookEntity where bookName = :code");
            query.setParameter("code", book_in);
            if (!query.list().isEmpty()) {
                //Give this book entity from query
                BookEntity bookEntity = (BookEntity) query.list().get(0);
                session.beginTransaction();
                personEntity.addBookEntity(bookEntity);
                session.save(personEntity);
                session.getTransaction().commit();
                System.out.println("end insert boor for person");
            } else {
                System.out.println("There is no the book");
            }
        } else {
            System.out.println("There is no this person");
        }

    }


//    private static void AddPairPersonBookWithProcedure(Session session) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("\nInput Surname for Person: ");
//        String surname = input.next();
//        System.out.println("Input NameBook for Book: ");
//        String book = input.next();
//
//        //to JPA 2.0
////        Query query = session.createSQLQuery(
////                "CALL InsertPersonBook(:Person, :Book)")
////                .setParameter("Person", surname)
////                .setParameter("Book", book);
////        System.out.println(query.list().get(0));
//
//        //from JPA 2.1
//        StoredProcedureQuery query = session
//                .createStoredProcedureQuery("InsertPersonBook")
//                .registerStoredProcedureParameter("SurmanePersonIn", String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("BookNameIN", String.class, ParameterMode.IN)
//                .setParameter("SurmanePersonIn", surname)
//                .setParameter("BookNameIN", book);
//        query.execute();
//        String str = (String) query.getResultList().get(0);
//        System.out.println(str);
//
//        if (str.equals("OK")) {
//            Query query2 = session.createQuery("from " + "PersonEntity");
//            for (Object obj : query2.list()) {
//                session.refresh(obj);
//            }
//            query2 = session.createQuery("from " + "BookEntity ");
//            for (Object obj : query2.list()) {
//                session.refresh(obj);
//            }
//        }
//    }

}