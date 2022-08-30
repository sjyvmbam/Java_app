package com.example.lagerapp.Service;
import com.example.lagerapp.Dao.DaoConnection;
import com.example.lagerapp.Dao.DaoConnectionImp;
//import com.example.lagerapp.model.Lager;
import com.example.lagerapp.model.Medikament;

import java.util.ArrayList;
import java.util.List;

public class LagerServiceImp implements LagerService {
    private DaoConnection repository = new DaoConnectionImp();

    @Override
    public String addMedikament(Medikament medikament) {
        List<Medikament> medikaments= repository.findAll();
        for(int i=0; i<medikaments.size();i++){
            if(medikaments.get(i).getPharmazentralnummer()==medikament.getPharmazentralnummer()){
                return "medikament existiert schon";
            }
        }
        repository.addNewMedikament(medikament);
        return "medikament wurde hinzugefuegt.";

    }

    @Override
    public String verkaufenMedikament(Medikament medikament) {
        Medikament medikamentList= repository.findOne(medikament.getPharmazentralnummer());

        if(medikamentList.getId()== medikament.getId() &&
                medikamentList.getStueckzahl()>0 && medikament.getStueckzahl()<=medikamentList.getStueckzahl()) {
            repository.reduktieren(medikament);
            return "Danke fuer Ihr Einkauf";
        }
        return "Danke Medikament wurde ausverkauft oder haben wir es nicht";
    }

    public void erstellenFileToCsv(){
    }

    public void erstellungPdf(){
    }

    @Override
    public String aufstocken(Medikament medikament){
        List<Medikament>medikamentList= repository.findAll();
        try {
            for (int i = 0; i < medikamentList.size(); i++) {
                if (medikamentList.get(i).getPharmazentralnummer() == medikament.getPharmazentralnummer() ||
                    medikamentList.get(i).getId()==medikament.getId()) {
                    repository.erhoehen(medikament);
                    return "Die stueck wurde erfolgreich erhoeht";
                }
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return "Medikament ist nicht in unserer Lager";
    }
    @Override
    public List<Medikament> findAllMedikament(){
        List<Medikament>medikaments=new ArrayList<>();
        try{
            medikaments= repository.findAll();
        }catch(Exception e){
            System.err.println(e);
        }
        return medikaments;
    }


@Override
    public Medikament findOneMedikament(int a){
        Medikament medikament=new Medikament();
        try{
            medikament= repository.findOne(a);
        }catch(Exception e){
            System.err.println(e);
        }
        return medikament;    }

}












/*
  Lager lager =new Lager();
        Medikament medikament=new Medikament(34452710,10);
        lager.addMedikament(medikament);
        int id= lager.getMedikament().get(0).getId();
        int stücke=lager.getMedikament().get(0).getStueckzahl();
        int referenzProdukt= lager.getMedikament().get(0).getPharmazentralnummer();

        insertion(referenzProdukt,stücke);
 */
















 /*
           Medikament medikament =new Medikament();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Bitte geben Sie the stueckzahl");
        int stueckzahl = scanner.nextInt();
        System.out.println("Bitte geben Sie pharmazentralnummer");
        int pharmazentralnummer = scanner.nextInt();
        medikament.setStueckzahl(stueckzahl);
        medikament.setPharmazentralnummer(pharmazentralnummer);
        int stueck= medikament.getStueckzahl();
        int zentralnummer= medikament.getPharmazentralnummer();
        new DaoConnection().insertion(zentralnummer,stueck);
        System.out.println(stueck + "," + zentralnummer);









        PreparedStatement statement=null;
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/apotheke?user=postgres&password=?";
            conn = DriverManager.getConnection(url);
            System.out.println("connected");

            String query = "select medikament_id,produktverkauft from verkauft_medikament";
            assert conn != null;
            Statement statement = conn.createStatement();
            ResultSet result= statement.executeQuery(query);

            while(result.next()){
                System.out.println(result);
                int medikamentId=result.getInt("medikament_id");
                int stueckzahl= result.getInt("produktverkauft");

                System.out.println(String.format( "%d - %d\n",medikamentId,stueckzahl));
                if(medikamentId==id ){
                    stueckzahl=stueckzahl+stuck;
                    String update="update verkauft_medikament set produktverkauft="+stueckzahl+ "  where (medikament_id="+id+")";
                    assert conn != null;
                    PreparedStatement statements= conn.prepareStatement(update);
                    statements.executeUpdate();
                    System.out.println("Sie haben " + medikamentId +" mit " +stueckzahl+ " stueck aktualisiert");
                }
                new DaoConnection().reduktieren(id,referenzProdukt,stuck);

            }





            // Statement myStmt= conn.createStatement();
            //ResultSet myRs= myStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addMedikament(){
        Lager lager =new Lager();
        Medikament medikament=new Medikament(34452710,10);
        lager.addMedikament(medikament);
        int id= lager.getMedikament().get(0).getId();
        int stücke=lager.getMedikament().get(0).getStueckzahl();
        int referenzProdukt= lager.getMedikament().get(0).getPharmazentralnummer();

        insertion(referenzProdukt,stücke);
    }

 */










