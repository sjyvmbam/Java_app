package com.example.lagerapp.Dao;

//import com.example.lagerapp.Dao.AbstractDaoConnection;
//import com.example.lagerapp.Dao.DaoConnection;
import com.example.lagerapp.model.Medikament;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class DaoConnectionImp extends AbstractDaoConnection implements DaoConnection {
    PreparedStatement statement = null;
    Connection conn = null;

    @Override
    public void createTable() {

        try {
            conn = getConnection();
            String query = "create table medicament(id SERIAL primary key, product_name varchar(500),pharmazentralnummer int,stueckzahl int)";
            String query2="create table verkauft_medikament (id serial primary key, produktVerkauft int, medikament_id int references medicament (id) on update cascade on delete cascade)";
            assert conn != null;
            statement = conn.prepareStatement(query);
            statement.executeUpdate();
            statement = conn.prepareStatement(query2);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function erfolgreich");
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
        }
    }

    @Override
    public void addNewMedikament(Medikament medikament){
        String name= medikament.getName();
        int pharmazentralnummer= medikament.getPharmazentralnummer();
        int stueckzahl=medikament.getStueckzahl();
        try {
            conn = getConnection();
            String query = "insert into medicament(product_name,pharmazentralnummer,stueckzahl)" + "VALUES(?,?,?)";
            //String query="insert into medicament(product_name, pharmazentralnummer, stueckzahl) select "+name+", "+pharmazentralnummer+", "+stueckzahl+" where not exists( select 1 from medicament where product_name=? and pharmazentralnummer = ?)";
            assert conn != null;
            statement = conn.prepareStatement(query);
            statement.setString(1,name);
            statement.setInt(2,pharmazentralnummer);
            statement.setInt(3,stueckzahl);
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("error kann nicht speichert");
            //System.out.println(e);
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
        }
    }
    @Override
    public void reduktieren(Medikament medikament) {
        int Id= medikament.getId();
        int referenz= medikament.getPharmazentralnummer();
        int stueck= medikament.getStueckzahl();


        try {
            conn = getConnection();
            String query = "select id,pharmazentralnummer,stueckzahl from medicament where id=?";
            assert conn != null;
            statement = conn.prepareStatement(query);
            statement.setInt(1,Id);
            ResultSet result= statement.executeQuery();
            List<Integer> listMedikament= new ArrayList<>();
            //int[]b={1,2,3,4};

            while(result.next()){
                int id= result.getInt("id");
                int pharmazentralnummer=result.getInt("pharmazentralnummer");
                int stueckzahl= result.getInt("stueckzahl");
                Collections.addAll(listMedikament,pharmazentralnummer,stueckzahl);
                stueckzahl=stueckzahl-stueck;
                String update="update medicament set stueckzahl=?  where (id=?)";
                String insertQuery="insert into verkauft_medikament (produktverkauft, medikament_id) select "+stueck+", "+Id+" where exists( select 1 from medicament where id = ?)";
                assert conn != null;

                statement= conn.prepareStatement(update);
                statement.setInt(1,stueckzahl);
                statement.setInt(2,Id);
                statement.executeUpdate();

                statement= conn.prepareStatement(insertQuery);
                statement.setInt(1,Id);
                statement.executeUpdate();
                System.out.println("Sie haben verkauft " + stueck + " stueck von produkt " +pharmazentralnummer);
                String query2 = "select id,produktverkauft,medikament_id from verkauft_medikament";

            }

            // System.out.println(listMedikament);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
        }

    }
    @Override
    public void erhoehen(Medikament medikament) {
        int referenz= medikament.getId();
        int stueck =medikament.getStueckzahl();
        try {
            conn = getConnection();
            String query = "select id,stueckzahl from medicament where id=?";
            assert conn != null;
            statement = conn.prepareStatement(query);
            statement.setInt(1,referenz);
            ResultSet result= statement.executeQuery();
            List<Integer> listMedikament= new ArrayList<>();
            int id;
            int  stueckzahl;
            while(result.next()){
                id=result.getInt("id");
                stueckzahl= result.getInt("stueckzahl");
                System.out.println(String.format( "%d - %d\n",id,stueckzahl));
                Collections.addAll(listMedikament,id,stueckzahl);
                stueckzahl=stueckzahl+stueck;
                String update="update medicament set stueckzahl=?  where (id=?)";
                assert conn != null;
                PreparedStatement statements= conn.prepareStatement(update);
                statements.setInt(1,stueckzahl);
                statements.setInt(2,referenz);
                statements.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null)
                try {

                    conn.close();
                } catch (SQLException ignore) {
                }
        }

    }

    @Override
    public List<Medikament> findAll() {
        List<Medikament> medikamentList=new ArrayList<>();
        try {
            conn = getConnection();
            String query = "select * from medicament";
            assert conn != null;
            statement = conn.prepareStatement(query);
            ResultSet result= statement.executeQuery();
            while(result.next()){
                int id= result.getInt("id");
                String name= result.getString("product_name");
                int pharmazentralnummer=result.getInt("pharmazentralnummer");
                int  stueckzahl= result.getInt("stueckzahl");
                Medikament medikament= new Medikament(id, name,pharmazentralnummer,stueckzahl);
                medikamentList.add(medikament);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return medikamentList;

    }

    @Override
    public Medikament findOne(int Id) {
        Medikament medikament=new Medikament();

        try {
            conn = getConnection();
            String query = "select id,product_name,pharmazentralnummer,stueckzahl from medicament where id=?";
            assert conn != null;
            statement = conn.prepareStatement(query);
            statement.setInt(1,Id);
            ResultSet result= statement.executeQuery();
            while(result.next()){
                int id= result.getInt("id");
                String name=result.getString("product_name");
                int pharmazentralnummer=result.getInt("pharmazentralnummer");
                int  stueckzahl= result.getInt("stueckzahl");
                medikament= new Medikament(id ,name,pharmazentralnummer,stueckzahl);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return medikament;

    }
    public void erstellenPdf(){
        try {
            conn = getConnection();

            System.out.println("connected");


        } catch (Exception e) {
            e.printStackTrace();
        }

        JasperReportBuilder report= DynamicReports.report();
        report.columns(
                        Columns.column("Id","id", DataTypes.integerType()),
                        Columns.column("medikamentId","medikament_id", DataTypes.integerType()),
                        Columns.column("produktverkauft","produktverkauft", DataTypes.integerType())
                ).title(
                        Components.text("ReportPdf")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource("SELECT id, medikament_id, produktverkauft from verkauft_medikament",
                        conn);

        try{
            report.show();
            report.toPdf(new FileOutputStream("C:/Users/Staige/IdeaProjects/ApothekeAwendung/src/report.pdf"));
        }catch (DRException e){
            e.printStackTrace();

        }catch(FileNotFoundException e){
            e.printStackTrace();

        }

    }

    @Override
    public void erstellenCsv() {
        try {
            conn = getConnection();
            String query = "select * from medicament";
            assert conn != null;
            statement = conn.prepareStatement(query);
            ResultSet result= statement.executeQuery();
            StringBuilder sb= new StringBuilder();
            sb.append("pharmazentralnummer");
            sb.append(";");
            sb.append("stueckzahl");
            sb.append("\n");
            while(result.next()){
                int id= result.getInt("id");
                int pharmazentralnummer=result.getInt("pharmazentralnummer");
                int  stueckzahl= result.getInt("stueckzahl");
                System.out.printf("%d - %d - %d\n%n",id,pharmazentralnummer,stueckzahl);
                PrintWriter pw= new PrintWriter(new File("C:\\Users\\Staige\\IdeaProjects\\ApothekeAwendung\\src\\main\\java\\csv\\inventur.csv"));
                sb.append(pharmazentralnummer);
                sb.append(";");
                sb.append(stueckzahl);
                sb.append("\n");
                pw.write(sb.toString());
                pw.close();

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function  select complete");
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
        }

    }


}







//String query2="create table add_medikament (user_id  int references users (id) on update cascade on delete cascade , medikament_id int references medicament (id) on update cascade , constraint bill_product_pkey primary key (user_id, medikament_id))";
//String query = "create table medicament(id SERIAL primary key,pharmazentralnummer int,stueckzahl int)";
//String query1="create table verkauft_medikament(id serial primary key ,user_id int not null ,medikament_id int not null, foreign key (user_id) references  users (id) on delete cascade,foreign key (medikament_id) references medicament(id) on delete cascade )";
//String q="create table users(id serial primary key)";
//String q1="insert into medicament (pharmazentralnummer, stueckzahl) select "+pharmazentralnummer+", "+stueckzahl+" where exists( select 1 from medicament where pharmazentralnummer = "+referenz+")";
